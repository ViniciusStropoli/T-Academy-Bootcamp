-- ============================================================
-- MEGA ESQUEMA: Marketplace Multitenant Completo (MySQL 8.0+)
-- ============================================================

-- Recomendado antes de executar:
-- SET sql_notes = 0; -- (opcional: silencia warnings em criação)
-- SET sql_safe_updates = 0;

DROP DATABASE IF EXISTS mega_marketplace;
CREATE DATABASE mega_marketplace
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_0900_ai_ci;
USE mega_marketplace;

-- Organização: todas as tabelas InnoDB + checks (8.0+), JSON, POINT(SRID 4326),
-- geradas (VIRTUAL/STORED), triggers, views, rotinas e particionamento.

-- =========================
-- 1) Tabelas de Referência
-- =========================

CREATE TABLE currencies (
  code CHAR(3) PRIMARY KEY,                 -- ISO 4217
  name VARCHAR(64) NOT NULL,
  symbol VARCHAR(8) NOT NULL,
  minor_units TINYINT NOT NULL CHECK (minor_units BETWEEN 0 AND 3)
) ENGINE=InnoDB;

INSERT INTO currencies VALUES
('USD','US Dollar','$',2),('EUR','Euro','€',2),('BRL','Real','R$',2);

CREATE TABLE countries (
  iso2 CHAR(2) PRIMARY KEY,
  name VARCHAR(64) NOT NULL,
  currency_code CHAR(3) NOT NULL,
  CONSTRAINT fk_countries_currency FOREIGN KEY (currency_code) REFERENCES currencies(code)
) ENGINE=InnoDB;

INSERT INTO countries VALUES
('US','United States','USD'),
('BR','Brazil','BRL'),
('IT','Italy','EUR');

-- =========================
-- 2) Multi-tenant e usuários
-- =========================

CREATE TABLE tenants (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(120) NOT NULL,
  legal_name VARCHAR(160),
  country_iso2 CHAR(2) NOT NULL,
  default_currency CHAR(3) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status ENUM('active','suspended','deleted') NOT NULL DEFAULT 'active',
  UNIQUE KEY uq_tenant_name (name),
  CONSTRAINT fk_tenant_country FOREIGN KEY (country_iso2) REFERENCES countries(iso2),
  CONSTRAINT fk_tenant_currency FOREIGN KEY (default_currency) REFERENCES currencies(code)
) ENGINE=InnoDB;

CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenant_id BIGINT NOT NULL,
  email VARCHAR(190) NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  full_name VARCHAR(120) NOT NULL,
  phone_raw VARCHAR(40),
  phone_norm VARCHAR(20) GENERATED ALWAYS AS (
    REPLACE(REPLACE(REPLACE(REPLACE(phone_raw,'+',''),'(',''),')',''),'-','')
  ) STORED,
  locale VARCHAR(10) DEFAULT 'pt-BR',
  tz VARCHAR(50) DEFAULT 'America/Sao_Paulo',
  is_staff BOOLEAN NOT NULL DEFAULT 0,
  is_superadmin BOOLEAN NOT NULL DEFAULT 0,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status ENUM('active','pending','blocked','deleted') NOT NULL DEFAULT 'active',
  UNIQUE KEY uq_user_tenant_email (tenant_id, email),
  KEY idx_user_phone_norm (phone_norm),
  CONSTRAINT fk_user_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id)
) ENGINE=InnoDB;

-- RBAC simples, mas com granularidade
CREATE TABLE roles (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenant_id BIGINT NOT NULL,
  name VARCHAR(64) NOT NULL,
  is_system BOOLEAN NOT NULL DEFAULT 0,
  UNIQUE KEY uq_role_tenant_name (tenant_id, name),
  CONSTRAINT fk_role_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id)
) ENGINE=InnoDB;

CREATE TABLE permissions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  code VARCHAR(100) NOT NULL UNIQUE,
  description VARCHAR(255)
) ENGINE=InnoDB;

INSERT INTO permissions(code,description) VALUES
('product.read','Read products'),
('product.write','Write products'),
('order.read','Read orders'),
('order.write','Write orders'),
('finance.read','Read finance'),
('finance.write','Write finance');

CREATE TABLE role_permissions (
  role_id BIGINT NOT NULL,
  permission_id BIGINT NOT NULL,
  PRIMARY KEY (role_id, permission_id),
  CONSTRAINT fk_rp_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
  CONSTRAINT fk_rp_perm FOREIGN KEY (permission_id) REFERENCES permissions(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE user_roles (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT fk_ur_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  CONSTRAINT fk_ur_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- =========================
-- 3) Endereços e geoespacial
-- =========================

CREATE TABLE addresses (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenant_id BIGINT NOT NULL,
  line1 VARCHAR(160) NOT NULL,
  line2 VARCHAR(160),
  district VARCHAR(80),
  city VARCHAR(80) NOT NULL,
  state VARCHAR(80),
  postal_code VARCHAR(20),
  country_iso2 CHAR(2) NOT NULL,
  location POINT SRID 4326 NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_addr_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id),
  CONSTRAINT fk_addr_country FOREIGN KEY (country_iso2) REFERENCES countries(iso2),
  SPATIAL INDEX spx_addr_location (location)
) ENGINE=InnoDB;

-- =========================
-- 4) Catálogo: categorias (com closure table), produtos, variantes, tags
-- =========================

-- Árvore de categorias
CREATE TABLE categories (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenant_id BIGINT NOT NULL,
  name VARCHAR(120) NOT NULL,
  slug VARCHAR(160) NOT NULL,
  parent_id BIGINT NULL,
  sort_order INT NOT NULL DEFAULT 0,
  UNIQUE KEY uq_cat_tenant_slug (tenant_id, slug),
  KEY idx_cat_parent (parent_id),
  CONSTRAINT fk_cat_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id),
  CONSTRAINT fk_cat_parent FOREIGN KEY (parent_id) REFERENCES categories(id)
) ENGINE=InnoDB;

-- Tabela de fechamento para consultas de árvore (ancestral/descendente)
CREATE TABLE category_closure (
  ancestor_id BIGINT NOT NULL,
  descendant_id BIGINT NOT NULL,
  depth INT NOT NULL,
  PRIMARY KEY (ancestor_id, descendant_id),
  CONSTRAINT fk_cc_anc FOREIGN KEY (ancestor_id) REFERENCES categories(id) ON DELETE CASCADE,
  CONSTRAINT fk_cc_desc FOREIGN KEY (descendant_id) REFERENCES categories(id) ON DELETE CASCADE,
  CHECK (depth >= 0)
) ENGINE=InnoDB;

-- Produtos
CREATE TABLE products (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenant_id BIGINT NOT NULL,
  name VARCHAR(180) NOT NULL,
  slug VARCHAR(200) NOT NULL,
  description LONGTEXT,
  attributes JSON, -- ex: {"materials":["algodao"],"gender":"unissex"}
  brand VARCHAR(120),
  status ENUM('draft','active','archived') NOT NULL DEFAULT 'draft',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uq_prod_tenant_slug (tenant_id, slug),
  FULLTEXT KEY ftx_product (name, description),
  CONSTRAINT fk_prod_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id)
) ENGINE=InnoDB;

-- Relacionamento produto-categoria (N:N)
CREATE TABLE product_categories (
  product_id BIGINT NOT NULL,
  category_id BIGINT NOT NULL,
  PRIMARY KEY (product_id, category_id),
  CONSTRAINT fk_pc_prod FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
  CONSTRAINT fk_pc_cat FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- Tags (N:N)
CREATE TABLE tags (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenant_id BIGINT NOT NULL,
  name VARCHAR(80) NOT NULL,
  slug VARCHAR(100) NOT NULL,
  UNIQUE KEY uq_tag_tenant_slug (tenant_id, slug),
  CONSTRAINT fk_tag_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id)
) ENGINE=InnoDB;

CREATE TABLE product_tags (
  product_id BIGINT NOT NULL,
  tag_id BIGINT NOT NULL,
  PRIMARY KEY (product_id, tag_id),
  CONSTRAINT fk_pt_prod FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
  CONSTRAINT fk_pt_tag FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- Variantes
CREATE TABLE product_variants (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  product_id BIGINT NOT NULL,
  sku VARCHAR(64) NOT NULL,
  variant_attrs JSON, -- ex: {"cor":"preto","tamanho":"M"}
  price_cents INT NOT NULL CHECK (price_cents >= 0),
  currency CHAR(3) NOT NULL,
  weight_g INT CHECK (weight_g >= 0),
  barcode VARCHAR(64),
  status ENUM('active','inactive') NOT NULL DEFAULT 'active',
  UNIQUE KEY uq_variant_sku (sku),
  KEY idx_variant_product (product_id),
  CONSTRAINT fk_variant_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
  CONSTRAINT fk_variant_currency FOREIGN KEY (currency) REFERENCES currencies(code)
) ENGINE=InnoDB;

-- =========================
-- 5) Estoque & armazéns
-- =========================

CREATE TABLE warehouses (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenant_id BIGINT NOT NULL,
  name VARCHAR(120) NOT NULL,
  address_id BIGINT,
  is_active BOOLEAN NOT NULL DEFAULT 1,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY uq_wh_tenant_name (tenant_id, name),
  CONSTRAINT fk_wh_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id),
  CONSTRAINT fk_wh_addr FOREIGN KEY (address_id) REFERENCES addresses(id)
) ENGINE=InnoDB;

-- Lotes/validades + rastreio por série
CREATE TABLE stock_lots (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  warehouse_id BIGINT NOT NULL,
  variant_id BIGINT NOT NULL,
  lot_code VARCHAR(64) NOT NULL,
  expires_on DATE NULL,
  qty INT NOT NULL CHECK (qty >= 0),
  locked_qty INT NOT NULL DEFAULT 0 CHECK (locked_qty >= 0),
  UNIQUE KEY uq_lot (warehouse_id, variant_id, lot_code),
  CONSTRAINT fk_lot_wh FOREIGN KEY (warehouse_id) REFERENCES warehouses(id),
  CONSTRAINT fk_lot_variant FOREIGN KEY (variant_id) REFERENCES product_variants(id)
) ENGINE=InnoDB;

CREATE TABLE stock_serials (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  lot_id BIGINT NOT NULL,
  serial_code VARCHAR(120) NOT NULL,
  is_disposed BOOLEAN NOT NULL DEFAULT 0,
  UNIQUE KEY uq_serial (lot_id, serial_code),
  CONSTRAINT fk_serial_lot FOREIGN KEY (lot_id) REFERENCES stock_lots(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- Saldos agregados por variante/armazém (tabela materializada via triggers)
CREATE TABLE stock_balances (
  warehouse_id BIGINT NOT NULL,
  variant_id BIGINT NOT NULL,
  on_hand INT NOT NULL DEFAULT 0,
  locked INT NOT NULL DEFAULT 0,
  PRIMARY KEY (warehouse_id, variant_id),
  CONSTRAINT fk_sb_wh FOREIGN KEY (warehouse_id) REFERENCES warehouses(id),
  CONSTRAINT fk_sb_variant FOREIGN KEY (variant_id) REFERENCES product_variants(id)
) ENGINE=InnoDB;

-- Movimentações (entrada/saída)
CREATE TABLE stock_movements (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  warehouse_id BIGINT NOT NULL,
  variant_id BIGINT NOT NULL,
  lot_id BIGINT,
  direction ENUM('in','out') NOT NULL,
  qty INT NOT NULL CHECK (qty > 0),
  reason ENUM('purchase','return','adjustment','allocation','shipment') NOT NULL,
  ref_type VARCHAR(40) NULL, -- ex: 'order','po'
  ref_id BIGINT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_sm_wh FOREIGN KEY (warehouse_id) REFERENCES warehouses(id),
  CONSTRAINT fk_sm_variant FOREIGN KEY (variant_id) REFERENCES product_variants(id),
  CONSTRAINT fk_sm_lot FOREIGN KEY (lot_id) REFERENCES stock_lots(id)
) ENGINE=InnoDB;

-- =========================
-- 6) Clientes
-- =========================

CREATE TABLE customers (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenant_id BIGINT NOT NULL,
  user_id BIGINT NULL, -- se o cliente também é usuário logado
  email VARCHAR(190) NOT NULL,
  full_name VARCHAR(120) NOT NULL,
  default_address_id BIGINT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY uq_customer_tenant_email (tenant_id, email),
  CONSTRAINT fk_cust_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id),
  CONSTRAINT fk_cust_user FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT fk_cust_addr FOREIGN KEY (default_address_id) REFERENCES addresses(id)
) ENGINE=InnoDB;

CREATE TABLE customer_addresses (
  customer_id BIGINT NOT NULL,
  address_id BIGINT NOT NULL,
  is_default BOOLEAN NOT NULL DEFAULT 0,
  PRIMARY KEY (customer_id, address_id),
  CONSTRAINT fk_caddr_cust FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE,
  CONSTRAINT fk_caddr_addr FOREIGN KEY (address_id) REFERENCES addresses(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- =========================
-- 7) Pedidos, Itens, Entregas, Pagamentos
-- =========================

-- Pedidos particionados por mês (ex.: 2 anos adiante; ajuste conforme necessidade)
CREATE TABLE orders (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenant_id BIGINT NOT NULL,
  customer_id BIGINT NOT NULL,
  currency CHAR(3) NOT NULL,
  subtotal_cents INT NOT NULL DEFAULT 0 CHECK (subtotal_cents >= 0),
  discount_cents INT NOT NULL DEFAULT 0 CHECK (discount_cents >= 0),
  shipping_cents INT NOT NULL DEFAULT 0 CHECK (shipping_cents >= 0),
  tax_cents INT NOT NULL DEFAULT 0 CHECK (tax_cents >= 0),
  total_cents INT NOT NULL DEFAULT 0 CHECK (total_cents >= 0),
  status ENUM('created','paid','fulfilled','cancelled','refunded','partial_refunded') NOT NULL DEFAULT 'created',
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
  shipping_address_id BIGINT NULL,
  billing_address_id BIGINT NULL,
  notes TEXT,
  KEY idx_orders_tenant_created (tenant_id, created_at),
  CONSTRAINT fk_order_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id),
  CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES customers(id),
  CONSTRAINT fk_order_curr FOREIGN KEY (currency) REFERENCES currencies(code),
  CONSTRAINT fk_order_shipaddr FOREIGN KEY (shipping_address_id) REFERENCES addresses(id),
  CONSTRAINT fk_order_billaddr FOREIGN KEY (billing_address_id) REFERENCES addresses(id)
)
ENGINE=InnoDB
PARTITION BY RANGE (TO_DAYS(created_at)) (
  PARTITION p2024m01 VALUES LESS THAN (TO_DAYS('2024-02-01')),
  PARTITION p2024m02 VALUES LESS THAN (TO_DAYS('2024-03-01')),
  PARTITION p2024m03 VALUES LESS THAN (TO_DAYS('2024-04-01')),
  PARTITION p2024m04 VALUES LESS THAN (TO_DAYS('2024-05-01')),
  PARTITION p2024m05 VALUES LESS THAN (TO_DAYS('2024-06-01')),
  PARTITION p2024m06 VALUES LESS THAN (TO_DAYS('2024-07-01')),
  PARTITION p2024m07 VALUES LESS THAN (TO_DAYS('2024-08-01')),
  PARTITION p2024m08 VALUES LESS THAN (TO_DAYS('2024-09-01')),
  PARTITION p2024m09 VALUES LESS THAN (TO_DAYS('2024-10-01')),
  PARTITION p2024m10 VALUES LESS THAN (TO_DAYS('2024-11-01')),
  PARTITION p2024m11 VALUES LESS THAN (TO_DAYS('2024-12-01')),
  PARTITION p2024m12 VALUES LESS THAN (TO_DAYS('2025-01-01')),
  PARTITION pmax VALUES LESS THAN MAXVALUE
);

CREATE TABLE order_items (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  variant_id BIGINT NOT NULL,
  qty INT NOT NULL CHECK (qty > 0),
  unit_price_cents INT NOT NULL CHECK (unit_price_cents >= 0),
  discount_cents INT NOT NULL DEFAULT 0 CHECK (discount_cents >= 0),
  tax_cents INT NOT NULL DEFAULT 0 CHECK (tax_cents >= 0),
  total_cents INT NOT NULL CHECK (total_cents >= 0),
  metadata JSON,
  KEY idx_oi_order (order_id),
  CONSTRAINT fk_oi_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
  CONSTRAINT fk_oi_product FOREIGN KEY (product_id) REFERENCES products(id),
  CONSTRAINT fk_oi_variant FOREIGN KEY (variant_id) REFERENCES product_variants(id)
) ENGINE=InnoDB;

CREATE TABLE shipments (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_id BIGINT NOT NULL,
  warehouse_id BIGINT NOT NULL,
  carrier VARCHAR(80) NOT NULL,
  service VARCHAR(80),
  tracking_code VARCHAR(100),
  shipped_at DATETIME NULL,
  delivered_at DATETIME NULL,
  status ENUM('pending','shipped','delivered','lost','returned') NOT NULL DEFAULT 'pending',
  CONSTRAINT fk_ship_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
  CONSTRAINT fk_ship_wh FOREIGN KEY (warehouse_id) REFERENCES warehouses(id)
) ENGINE=InnoDB;

CREATE TABLE shipment_items (
  shipment_id BIGINT NOT NULL,
  order_item_id BIGINT NOT NULL,
  qty INT NOT NULL CHECK (qty > 0),
  PRIMARY KEY (shipment_id, order_item_id),
  CONSTRAINT fk_si_shipment FOREIGN KEY (shipment_id) REFERENCES shipments(id) ON DELETE CASCADE,
  CONSTRAINT fk_si_oi FOREIGN KEY (order_item_id) REFERENCES order_items(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE payments (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_id BIGINT NOT NULL,
  provider VARCHAR(60) NOT NULL, -- ex: 'stripe','paypal','pix','boleto'
  method VARCHAR(60) NOT NULL,   -- ex: 'card','pix','boleto'
  amount_cents INT NOT NULL CHECK (amount_cents >= 0),
  currency CHAR(3) NOT NULL,
  external_id VARCHAR(120),
  status ENUM('pending','authorized','captured','failed','refunded') NOT NULL DEFAULT 'pending',
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  settled_at DATETIME NULL,
  raw_response JSON,
  KEY idx_payment_order (order_id),
  CONSTRAINT fk_pay_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
  CONSTRAINT fk_pay_currency FOREIGN KEY (currency) REFERENCES currencies(code)
) ENGINE=InnoDB;

CREATE TABLE refunds (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  payment_id BIGINT NOT NULL,
  amount_cents INT NOT NULL CHECK (amount_cents > 0),
  reason VARCHAR(160),
  status ENUM('requested','processed','failed') NOT NULL DEFAULT 'requested',
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_refund_payment FOREIGN KEY (payment_id) REFERENCES payments(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- =========================
-- 8) Faturamento & Notas
-- =========================

CREATE TABLE invoices (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenant_id BIGINT NOT NULL,
  order_id BIGINT NULL,
  number VARCHAR(60) NOT NULL,
  issue_date DATE NOT NULL,
  due_date DATE NOT NULL,
  currency CHAR(3) NOT NULL,
  total_cents INT NOT NULL CHECK (total_cents >= 0),
  status ENUM('open','paid','cancelled') NOT NULL DEFAULT 'open',
  UNIQUE KEY uq_invoice_tenant_number (tenant_id, number),
  CONSTRAINT fk_inv_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id),
  CONSTRAINT fk_inv_order FOREIGN KEY (order_id) REFERENCES orders(id),
  CONSTRAINT fk_inv_curr FOREIGN KEY (currency) REFERENCES currencies(code)
) ENGINE=InnoDB;

CREATE TABLE invoice_items (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  invoice_id BIGINT NOT NULL,
  description VARCHAR(255) NOT NULL,
  qty DECIMAL(18,3) NOT NULL CHECK (qty > 0),
  unit_price_cents INT NOT NULL CHECK (unit_price_cents >= 0),
  tax_cents INT NOT NULL DEFAULT 0 CHECK (tax_cents >= 0),
  total_cents INT NOT NULL CHECK (total_cents >= 0),
  CONSTRAINT fk_iitem_invoice FOREIGN KEY (invoice_id) REFERENCES invoices(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- =========================
-- 9) Assinaturas (plans, subs, ciclos)
-- =========================

CREATE TABLE plans (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenant_id BIGINT NOT NULL,
  code VARCHAR(60) NOT NULL,
  name VARCHAR(120) NOT NULL,
  billing_period ENUM('day','week','month','year') NOT NULL,
  interval_count INT NOT NULL DEFAULT 1 CHECK (interval_count > 0),
  price_cents INT NOT NULL CHECK (price_cents >= 0),
  currency CHAR(3) NOT NULL,
  trial_days INT NOT NULL DEFAULT 0 CHECK (trial_days >= 0),
  status ENUM('active','inactive') NOT NULL DEFAULT 'active',
  UNIQUE KEY uq_plan_tenant_code (tenant_id, code),
  CONSTRAINT fk_plan_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id),
  CONSTRAINT fk_plan_curr FOREIGN KEY (currency) REFERENCES currencies(code)
) ENGINE=InnoDB;

CREATE TABLE subscriptions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenant_id BIGINT NOT NULL,
  customer_id BIGINT NOT NULL,
  plan_id BIGINT NOT NULL,
  started_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  current_period_end DATETIME NOT NULL,
  status ENUM('trialing','active','past_due','canceled') NOT NULL DEFAULT 'trialing',
  cancel_at_period_end BOOLEAN NOT NULL DEFAULT 0,
  UNIQUE KEY uq_sub_customer_plan_active (customer_id, plan_id, status),
  CONSTRAINT fk_sub_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id),
  CONSTRAINT fk_sub_customer FOREIGN KEY (customer_id) REFERENCES customers(id),
  CONSTRAINT fk_sub_plan FOREIGN KEY (plan_id) REFERENCES plans(id)
) ENGINE=InnoDB;

-- =========================
-- 10) Promoções e cupons
-- =========================

CREATE TABLE promotions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenant_id BIGINT NOT NULL,
  code VARCHAR(40) NOT NULL,
  type ENUM('percent','fixed') NOT NULL,
  value INT NOT NULL CHECK (value > 0), -- percent (1..100) ou cents
  starts_at DATETIME,
  ends_at DATETIME,
  max_redemptions INT,
  times_redeemed INT NOT NULL DEFAULT 0,
  status ENUM('active','inactive') NOT NULL DEFAULT 'active',
  UNIQUE KEY uq_promo_tenant_code (tenant_id, code),
  CONSTRAINT fk_promo_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id)
) ENGINE=InnoDB;

CREATE TABLE coupon_redemptions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  promotion_id BIGINT NOT NULL,
  order_id BIGINT NOT NULL,
  customer_id BIGINT NOT NULL,
  redeemed_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_red_promo FOREIGN KEY (promotion_id) REFERENCES promotions(id),
  CONSTRAINT fk_red_order FOREIGN KEY (order_id) REFERENCES orders(id),
  CONSTRAINT fk_red_customer FOREIGN KEY (customer_id) REFERENCES customers(id),
  UNIQUE KEY uq_red_unique (promotion_id, order_id, customer_id)
) ENGINE=InnoDB;

-- =========================
-- 11) Avaliações de produto (moderação)
-- =========================

CREATE TABLE product_reviews (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  product_id BIGINT NOT NULL,
  customer_id BIGINT NOT NULL,
  rating TINYINT NOT NULL CHECK (rating BETWEEN 1 AND 5),
  title VARCHAR(120),
  body TEXT,
  status ENUM('pending','approved','rejected') NOT NULL DEFAULT 'pending',
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_rev_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
  CONSTRAINT fk_rev_customer FOREIGN KEY (customer_id) REFERENCES customers(id)
) ENGINE=InnoDB;

-- =========================
-- 12) Suporte (tickets)
-- =========================

CREATE TABLE support_tickets (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenant_id BIGINT NOT NULL,
  customer_id BIGINT NOT NULL,
  subject VARCHAR(160) NOT NULL,
  status ENUM('open','pending','solved','closed') NOT NULL DEFAULT 'open',
  priority ENUM('low','normal','high','urgent') NOT NULL DEFAULT 'normal',
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_st_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id),
  CONSTRAINT fk_st_customer FOREIGN KEY (customer_id) REFERENCES customers(id)
) ENGINE=InnoDB;

CREATE TABLE support_messages (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  ticket_id BIGINT NOT NULL,
  author_type ENUM('customer','agent') NOT NULL,
  author_user_id BIGINT NULL,
  body TEXT NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_sm_ticket FOREIGN KEY (ticket_id) REFERENCES support_tickets(id) ON DELETE CASCADE,
  CONSTRAINT fk_sm_user FOREIGN KEY (author_user_id) REFERENCES users(id)
) ENGINE=InnoDB;

-- =========================
-- 13) Educação (cursos, aulas, matrículas)
-- =========================

CREATE TABLE courses (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenant_id BIGINT NOT NULL,
  code VARCHAR(60) NOT NULL,
  title VARCHAR(180) NOT NULL,
  summary TEXT,
  price_cents INT NOT NULL DEFAULT 0 CHECK (price_cents >= 0),
  currency CHAR(3) NOT NULL,
  status ENUM('draft','published','archived') NOT NULL DEFAULT 'draft',
  UNIQUE KEY uq_course_tenant_code (tenant_id, code),
  FULLTEXT KEY ftx_course (title, summary),
  CONSTRAINT fk_course_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id),
  CONSTRAINT fk_course_curr FOREIGN KEY (currency) REFERENCES currencies(code)
) ENGINE=InnoDB;

CREATE TABLE lessons (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  course_id BIGINT NOT NULL,
  title VARCHAR(180) NOT NULL,
  content LONGTEXT,
  sort_order INT NOT NULL DEFAULT 0,
  CONSTRAINT fk_lesson_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE enrollments (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  course_id BIGINT NOT NULL,
  customer_id BIGINT NOT NULL,
  enrolled_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  progress TINYINT NOT NULL DEFAULT 0 CHECK (progress BETWEEN 0 AND 100),
  UNIQUE KEY uq_enrollment (course_id, customer_id),
  CONSTRAINT fk_enr_course FOREIGN KEY (course_id) REFERENCES courses(id),
  CONSTRAINT fk_enr_customer FOREIGN KEY (customer_id) REFERENCES customers(id)
) ENGINE=InnoDB;

-- =========================
-- 14) Notificações & API Keys
-- =========================

CREATE TABLE notifications (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenant_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  type VARCHAR(80) NOT NULL,
  payload JSON,
  is_read BOOLEAN NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY idx_notif_user (user_id, is_read),
  CONSTRAINT fk_notif_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id),
  CONSTRAINT fk_notif_user FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB;

CREATE TABLE api_keys (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenant_id BIGINT NOT NULL,
  key_hash VARBINARY(32) NOT NULL, -- guarde hash (p.ex. SHA-256)
  label VARCHAR(120),
  scopes JSON,                     -- ["orders:read","products:write"]
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  revoked_at DATETIME NULL,
  UNIQUE KEY uq_api_key (tenant_id, key_hash),
  CONSTRAINT fk_apikey_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id)
) ENGINE=InnoDB;

-- =========================
-- 15) Auditoria/Logs (particionado)
-- =========================

CREATE TABLE audit_events (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenant_id BIGINT NOT NULL,
  actor_user_id BIGINT NULL,
  entity_type VARCHAR(60) NOT NULL, -- 'order','product','stock_movement', etc.
  entity_id BIGINT NULL,
  action VARCHAR(60) NOT NULL,      -- 'create','update','delete','transition'
  data JSON,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY idx_audit_entity (entity_type, entity_id),
  KEY idx_audit_tenant_time (tenant_id, created_at),
  CONSTRAINT fk_audit_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id),
  CONSTRAINT fk_audit_user FOREIGN KEY (actor_user_id) REFERENCES users(id)
)
ENGINE=InnoDB
PARTITION BY RANGE (TO_DAYS(created_at)) (
  PARTITION p2024q1 VALUES LESS THAN (TO_DAYS('2024-04-01')),
  PARTITION p2024q2 VALUES LESS THAN (TO_DAYS('2024-07-01')),
  PARTITION p2024q3 VALUES LESS THAN (TO_DAYS('2024-10-01')),
  PARTITION p2024q4 VALUES LESS THAN (TO_DAYS('2025-01-01')),
  PARTITION pmax VALUES LESS THAN MAXVALUE
);

-- =========================
-- 16) Funções e Procedures
-- =========================
DELIMITER $$

-- Função: normaliza telefone (remove não dígitos)
CREATE FUNCTION fn_normalize_phone(p_in VARCHAR(40))
RETURNS VARCHAR(20)
DETERMINISTIC
BEGIN
  DECLARE r VARCHAR(20);
  SET r = REPLACE(REPLACE(REPLACE(REPLACE(p_in,'+',''),'(',''),')',''),'-','');
  SET r = REGEXP_REPLACE(r, '[^0-9]', '');
  RETURN r;
END$$

-- Procedure: movimenta estoque (atômico)
CREATE PROCEDURE sp_stock_move(
  IN p_warehouse_id BIGINT,
  IN p_variant_id BIGINT,
  IN p_lot_id BIGINT,
  IN p_direction ENUM('in','out'),
  IN p_qty INT,
  IN p_reason ENUM('purchase','return','adjustment','allocation','shipment'),
  IN p_ref_type VARCHAR(40),
  IN p_ref_id BIGINT
)
BEGIN
  DECLARE v_effect INT;
  IF p_qty <= 0 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Qty must be > 0';
  END IF;

  SET v_effect = IF(p_direction='in', p_qty, -p_qty);

  INSERT INTO stock_movements(warehouse_id, variant_id, lot_id, direction, qty, reason, ref_type, ref_id)
  VALUES (p_warehouse_id, p_variant_id, p_lot_id, p_direction, p_qty, p_reason, p_ref_type, p_ref_id);

  INSERT INTO stock_balances(warehouse_id, variant_id, on_hand, locked)
  VALUES (p_warehouse_id, p_variant_id, GREATEST(v_effect,0), 0)
  ON DUPLICATE KEY UPDATE on_hand = GREATEST(on_hand + v_effect, 0);

  IF p_lot_id IS NOT NULL THEN
    UPDATE stock_lots
      SET qty = GREATEST(qty + v_effect, 0)
      WHERE id = p_lot_id;
  END IF;
END$$

-- Procedure: recalcula saldo a partir de movimentos (resincronização)
CREATE PROCEDURE sp_recalc_stock_balance(IN p_warehouse_id BIGINT, IN p_variant_id BIGINT)
BEGIN
  DECLARE v_on_hand INT;
  SELECT IFNULL(SUM(CASE WHEN direction='in' THEN qty ELSE -qty END),0)
    INTO v_on_hand
    FROM stock_movements
   WHERE warehouse_id=p_warehouse_id AND variant_id=p_variant_id;

  INSERT INTO stock_balances(warehouse_id, variant_id, on_hand, locked)
  VALUES(p_warehouse_id, p_variant_id, v_on_hand, 0)
  ON DUPLICATE KEY UPDATE on_hand=VALUES(on_hand);
END$$

DELIMITER ;

-- =========================
-- 17) Triggers (exemplos)
-- =========================
DELIMITER $$

-- Trigger: manter closure table de categorias (inserção de nó)
CREATE TRIGGER trg_category_after_insert
AFTER INSERT ON categories
FOR EACH ROW
BEGIN
  -- auto-relacionamento (nó para ele mesmo)
  INSERT INTO category_closure(ancestor_id, descendant_id, depth)
  VALUES (NEW.id, NEW.id, 0);

  -- herda ancestrais do pai
  IF NEW.parent_id IS NOT NULL THEN
    INSERT INTO category_closure(ancestor_id, descendant_id, depth)
    SELECT ancestor_id, NEW.id, depth + 1
      FROM category_closure
     WHERE descendant_id = NEW.parent_id;
  END IF;
END$$

-- Trigger: ao inserir item de pedido, atualiza totais e cria alocação de estoque (locked)
CREATE TRIGGER trg_order_item_after_insert
AFTER INSERT ON order_items
FOR EACH ROW
BEGIN
  -- total do pedido (recalc rápido; em sistemas grandes faça somatórios por eventos)
  UPDATE orders o
     JOIN (
       SELECT order_id,
              SUM(total_cents) AS t,
              SUM(discount_cents) AS d,
              SUM(tax_cents) AS x
         FROM order_items
        WHERE order_id = NEW.order_id
     ) s ON s.order_id = o.id
     SET o.subtotal_cents = s.t + s.d - s.x + 0, -- (apenas placeholder; ajuste à sua regra)
         o.tax_cents = s.x,
         o.total_cents = GREATEST(s.t + o.shipping_cents, 0);

  -- aloca (lock) estoque no primeiro armazém do tenant (exemplo didático)
  DECLARE v_wh BIGINT;
  SELECT w.id INTO v_wh
    FROM warehouses w
    JOIN orders o ON o.tenant_id = w.tenant_id AND o.id = NEW.order_id
   WHERE w.is_active=1
   ORDER BY w.id
   LIMIT 1;

  IF v_wh IS NOT NULL THEN
    INSERT INTO stock_balances(warehouse_id, variant_id, on_hand, locked)
    VALUES (v_wh, NEW.variant_id, 0, NEW.qty)
    ON DUPLICATE KEY UPDATE locked = locked + NEW.qty;

    INSERT INTO audit_events(tenant_id, actor_user_id, entity_type, entity_id, action, data)
    SELECT o.tenant_id, NULL, 'order_item', NEW.id, 'allocate',
           JSON_OBJECT('variant_id', NEW.variant_id, 'qty', NEW.qty)
      FROM orders o WHERE o.id = NEW.order_id;
  END IF;
END$$

-- Trigger: ao criar pagamento capturado, marca pedido como 'paid' se cobriu total
CREATE TRIGGER trg_payment_after_insert
AFTER INSERT ON payments
FOR EACH ROW
BEGIN
  IF NEW.status IN ('authorized','captured') THEN
    DECLARE v_paid INT;
    SELECT IFNULL(SUM(CASE WHEN status IN ('authorized','captured') THEN amount_cents ELSE 0 END),0)
      INTO v_paid
      FROM payments WHERE order_id = NEW.order_id;

    UPDATE orders SET status = IF(v_paid >= total_cents, 'paid', status)
     WHERE id = NEW.order_id;
  END IF;
END$$

-- Trigger: ao enviar remessa, dar saída do estoque (ship)
CREATE TRIGGER trg_shipment_after_update
AFTER UPDATE ON shipments
FOR EACH ROW
BEGIN
  IF NEW.status='shipped' AND (OLD.status <> 'shipped' OR OLD.status IS NULL) THEN
    -- consome locked -> on_hand (simples: reduz locked; prática real distribuir por lotes FIFO)
    UPDATE stock_balances sb
      JOIN shipment_items si ON si.shipment_id = NEW.id
      JOIN order_items oi ON oi.id = si.order_item_id
       SET sb.locked = GREATEST(sb.locked - si.qty, 0),
           sb.on_hand = GREATEST(sb.on_hand - si.qty, 0)
     WHERE sb.warehouse_id = NEW.warehouse_id
       AND sb.variant_id = oi.variant_id;

    INSERT INTO audit_events(tenant_id, actor_user_id, entity_type, entity_id, action, data)
    SELECT o.tenant_id, NULL, 'shipment', NEW.id, 'ship',
           JSON_OBJECT('warehouse_id', NEW.warehouse_id, 'order_id', NEW.order_id)
      FROM orders o WHERE o.id = NEW.order_id;
  END IF;
END$$

DELIMITER ;

-- =========================
-- 18) Views analíticas
-- =========================

-- KPIs por pedido
CREATE OR REPLACE VIEW v_order_financials AS
SELECT
  o.id AS order_id,
  o.tenant_id,
  o.customer_id,
  o.currency,
  o.subtotal_cents,
  o.discount_cents,
  o.tax_cents,
  o.shipping_cents,
  o.total_cents,
  o.status,
  o.created_at,
  (SELECT IFNULL(SUM(CASE WHEN status IN ('authorized','captured') THEN amount_cents ELSE 0 END),0)
     FROM payments p WHERE p.order_id = o.id) AS paid_cents,
  (SELECT IFNULL(SUM(amount_cents),0) FROM refunds r JOIN payments p2 ON p2.id=r.payment_id WHERE p2.order_id=o.id) AS refunded_cents
FROM orders o;

-- Receita por mês/tenant
CREATE OR REPLACE VIEW v_revenue_monthly AS
SELECT
  tenant_id,
  DATE_FORMAT(created_at, '%Y-%m') AS ym,
  currency,
  SUM(total_cents) AS total_cents
FROM orders
WHERE status IN ('paid','fulfilled')
GROUP BY tenant_id, ym, currency;

-- =========================
-- 19) Índices adicionais úteis
-- =========================
CREATE INDEX idx_products_status ON products(status, tenant_id);
CREATE INDEX idx_variants_product_status ON product_variants(product_id, status);
CREATE INDEX idx_orders_status ON orders(tenant_id, status, created_at);
CREATE INDEX idx_payments_status ON payments(status, created_at);
CREATE INDEX idx_shipments_status ON shipments(status, shipped_at);

-- =========================
-- 20) Dados mínimos (demo)
-- =========================
INSERT INTO tenants(name, legal_name, country_iso2, default_currency) VALUES
('LojaX','Loja X Ltda','BR','BRL'),
('ShopY','Shop Y Inc.','US','USD');

INSERT INTO roles(tenant_id,name,is_system) VALUES
(1,'admin',1),(1,'operador',0),(2,'admin',1);

INSERT INTO users(tenant_id,email,password_hash,full_name,is_staff,is_superadmin)
VALUES
(1,'admin@lojax.com','$2y$12$hash','Admin LojaX',1,0),
(1,'op@lojax.com','$2y$12$hash','Operador LojaX',1,0),
(2,'admin@shopy.com','$2y$12$hash','Admin ShopY',1,0);

INSERT INTO user_roles(user_id,role_id)
SELECT u.id,r.id FROM users u JOIN roles r
 ON r.tenant_id=u.tenant_id AND r.name='admin' WHERE u.email LIKE 'admin@%';

-- Categoria raiz e sub (gera closure via trigger)
INSERT INTO categories(tenant_id,name,slug,parent_id) VALUES (1,'Roupas','roupas',NULL);
INSERT INTO categories(tenant_id,name,slug,parent_id)
SELECT 1,'Camisetas','camisetas', id FROM categories WHERE slug='roupas' AND tenant_id=1;

INSERT INTO products(tenant_id,name,slug,status,description,attributes,brand)
VALUES (1,'Camiseta Clássica','camiseta-classica','active','Camiseta 100% algodão',JSON_OBJECT('material','algodao','gender','unissex'),'Algodex');

INSERT INTO product_categories(product_id,category_id)
SELECT p.id,c.id FROM products p, categories c WHERE p.slug='camiseta-classica' AND c.slug='camisetas' AND p.tenant_id=1 AND c.tenant_id=1;

INSERT INTO product_variants(product_id,sku,variant_attrs,price_cents,currency,weight_g,status)
SELECT id,'SKU-CAM-PT-M',JSON_OBJECT('cor','preto','tamanho','M'),4990,'BRL',180,'active' FROM products WHERE slug='camiseta-classica';

INSERT INTO addresses(tenant_id,line1,city,state,postal_code,country_iso2,location)
VALUES (1,'Rua das Flores 100','Blumenau','SC','89000-000','BR',ST_SRID(POINT(-49.063, -26.916),4326));

INSERT INTO warehouses(tenant_id,name,address_id)
SELECT 1,'CD-SC',a.id FROM addresses a WHERE a.tenant_id=1 LIMIT 1;

-- Lote e saldo
INSERT INTO stock_lots(warehouse_id,variant_id,lot_code,expires_on,qty)
SELECT w.id, v.id,'L2025-001',NULL,100
FROM warehouses w CROSS JOIN product_variants v
WHERE w.tenant_id=1 LIMIT 1;

CALL sp_recalc_stock_balance((SELECT id FROM warehouses WHERE tenant_id=1 LIMIT 1),
                             (SELECT id FROM product_variants LIMIT 1));

-- Cliente e pedido demo
INSERT INTO customers(tenant_id,email,full_name)
VALUES (1,'cliente@exemplo.com','Cliente Demo');

INSERT INTO orders(tenant_id,customer_id,currency,shipping_cents)
SELECT 1,c.id,'BRL',1500 FROM customers c WHERE c.email='cliente@exemplo.com';

INSERT INTO order_items(order_id,product_id,variant_id,qty,unit_price_cents,discount_cents,tax_cents,total_cents,metadata)
SELECT o.id,p.id,v.id,2,4990,0,0,2*4990,JSON_OBJECT('note','primeira compra')
FROM orders o, products p, product_variants v
WHERE o.customer_id=(SELECT id FROM customers WHERE email='cliente@exemplo.com') AND p.id=v.product_id AND p.slug='camiseta-classica'
LIMIT 1;

INSERT INTO payments(order_id,provider,method,amount_cents,currency,status)
SELECT id,'pix','pix', (SELECT total_cents FROM orders WHERE orders.id = id), 'BRL','captured'
FROM orders WHERE customer_id=(SELECT id FROM customers WHERE email='cliente@exemplo.com') LIMIT 1;

-- Shipment demo
INSERT INTO shipments(order_id,warehouse_id,carrier,service,status,shipped_at)
SELECT o.id,w.id,'Correios','PAC','shipped',NOW()
FROM orders o JOIN warehouses w ON w.tenant_id=o.tenant_id
WHERE o.customer_id=(SELECT id FROM customers WHERE email='cliente@exemplo.com') LIMIT 1;

INSERT INTO shipment_items(shipment_id, order_item_id, qty)
SELECT s.id, oi.id, 2
FROM shipments s JOIN orders o ON o.id=s.order_id
JOIN order_items oi ON oi.order_id=o.id
LIMIT 1;

-- Força update de status para disparar trigger de movimentação
UPDATE shipments SET status='pending' WHERE status='shipped' LIMIT 1;
UPDATE shipments SET status='shipped' WHERE status='pending' LIMIT 1;

