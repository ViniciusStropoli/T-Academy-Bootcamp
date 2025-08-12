package Trabalho;

public class BancoDados {

    public static void carregarReceitas() {
        Receita processador = new Receita(" Processador ");
        processador.adicionarIngrediente("Chip", 1);
        processador.adicionarIngrediente("Metal", 2);

        Receita computador = new Receita(" Computador ");
        computador.adicionarIngrediente("Processador", 1);
        computador.adicionarIngrediente("Placa de Circuito", 2);
        computador.adicionarIngrediente("Gabinete", 1);

        Receita robo = new Receita(" Robô ");
        robo.adicionarIngrediente("Computador", 1);
        robo.adicionarIngrediente("Sensor", 2);
        robo.adicionarIngrediente("Rodas", 4);

        Receita estatuaLiberdade = new Receita(" Estátua da Liberdade ");
        estatuaLiberdade.adicionarIngrediente("Cobre", 7);
        estatuaLiberdade.adicionarIngrediente("Aço", 3);
        estatuaLiberdade.adicionarIngrediente("Cimento", 2);

        Receita carro = new Receita(" Carro ");
        carro.adicionarIngrediente("Computador de bordo", 1);
        carro.adicionarIngrediente("Chassi", 1);
        carro.adicionarIngrediente("Rodas", 5);



        App.receitas.add(estatuaLiberdade);
        App.receitas.add(processador);
        App.receitas.add(computador);
        App.receitas.add(robo);
        App.receitas.add(carro);

        for (Receita receita : App.receitas) {
            for (String nomeIngrediente : receita.ingredientes.keySet()) {
                if (!App.catalogo.containsKey(nomeIngrediente)) {
                    App.catalogo.put(nomeIngrediente, new Item(nomeIngrediente, 0));
                }
            }

            String nomeProdutoFinal = receita.getNomeDoItemFinal();
            if (!App.catalogo.containsKey(nomeProdutoFinal)) {
                App.catalogo.put(nomeProdutoFinal, new Item(nomeProdutoFinal, 0));
            }
        }

    }
}
