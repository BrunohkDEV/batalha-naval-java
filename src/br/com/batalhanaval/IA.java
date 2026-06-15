package br.com.batalhanaval;

import java.util.Random;

public class IA {

    private Random random;

    public IA() {

        random = new Random();
    }

    public void posicionarAutomaticamente(Jogador jogador) {

        int[] barcos = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};

        for (int tamanho : barcos) {

            boolean posicionado = false;

            while (!posicionado) {

                int linha = random.nextInt(10);
                int coluna = random.nextInt(10);

                char direcao;

                if (random.nextBoolean()) {
                    direcao = 'H';
                } else {
                    direcao = 'V';
                }

                posicionado =
                        jogador.getTabuleiro()
                                .posicionarBarco(
                                        linha,
                                        coluna,
                                        tamanho,
                                        direcao);
            }
        }
    }

    public void atacar(Jogador adversario) {

        boolean ataqueRealizado = false;

        while (!ataqueRealizado) {

            int linha = random.nextInt(10);
            int coluna = random.nextInt(10);

            if (!adversario.getTabuleiro()
                    .jaFoiAtacado(linha, coluna)) {

                System.out.println();
                System.out.println("=== TURNO DO COMPUTADOR ===");
                System.out.println(
                        "Ataque em [" +
                                linha +
                                "," +
                                coluna +
                                "]");

                boolean acertou =
                        adversario.getTabuleiro()
                                .atacar(
                                        linha,
                                        coluna);

                if (acertou) {

                    System.out.println("ACERTOU!");

                } else {

                    System.out.println("AGUA!");
                }

                ataqueRealizado = true;
            }
        }
    }
}