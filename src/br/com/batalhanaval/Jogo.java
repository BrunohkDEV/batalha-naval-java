package br.com.batalhanaval;

import java.util.Scanner;

public class Jogo {

    private Scanner scanner;
    private IA ia;

    public Jogo() {

        scanner = new Scanner(System.in);
        ia = new IA();
    }

    public void iniciar() {

        int opcao;

        do {

            System.out.println("\n===== BATALHA NAVAL =====");
            System.out.println("1 - Jogador vs Computador");
            System.out.println("2 - Jogador vs Jogador");
            System.out.println("3 - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    jogarContraComputador();
                    break;

                case 2:
                    jogarContraJogador();
                    break;

                case 3:
                    System.out.println("Encerrando jogo...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while (opcao != 3);
    }

    private void jogarContraComputador() {

        Jogador jogador = new Jogador("Jogador");
        Jogador computador = new Jogador("Computador");

        configurarJogador(jogador);

        System.out.println("\nComputador posicionando barcos...");
        ia.posicionarAutomaticamente(computador);

        loopPartida(jogador, computador, true);
    }

    private void jogarContraJogador() {

        scanner.nextLine();

        System.out.print("Nome Jogador 1: ");
        String nome1 = scanner.nextLine();

        System.out.print("Nome Jogador 2: ");
        String nome2 = scanner.nextLine();

        Jogador jogador1 = new Jogador(nome1);
        Jogador jogador2 = new Jogador(nome2);

        System.out.println("\nConfiguracao de " + nome1);
        configurarJogador(jogador1);

        limparTela();

        System.out.println("\nConfiguracao de " + nome2);
        configurarJogador(jogador2);

        loopPartida(jogador1, jogador2, false);
    }

    private void configurarJogador(Jogador jogador) {

        System.out.println("\n1 - Posicionamento Manual");
        System.out.println("2 - Posicionamento Automatico");
        System.out.print("Escolha: ");

        int opcao = scanner.nextInt();

        if (opcao == 2) {

            ia.posicionarAutomaticamente(jogador);

        } else {

            posicionamentoManual(jogador);
        }
    }

    private void posicionamentoManual(Jogador jogador) {

        int[] barcos = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};

        for (int tamanho : barcos) {

            boolean posicionado = false;

            while (!posicionado) {

                jogador.mostrarMeuTabuleiro();

                System.out.println("Barco tamanho " + tamanho);

                System.out.print("Linha (0-9): ");
                int linha = scanner.nextInt();

                System.out.print("Coluna (0-9): ");
                int coluna = scanner.nextInt();

                System.out.print("Direcao (H/V): ");
                char direcao =
                        scanner.next()
                                .toUpperCase()
                                .charAt(0);

                posicionado =
                        jogador.getTabuleiro()
                                .posicionarBarco(
                                        linha,
                                        coluna,
                                        tamanho,
                                        direcao);

                if (!posicionado) {

                    System.out.println(
                            "Posicao invalida. Tente novamente.");
                }
            }
        }
    }

    private void loopPartida(
            Jogador jogador1,
            Jogador jogador2,
            boolean contraComputador) {

        boolean turnoJogador1 = true;

        while (!jogador1.perdeu()
                && !jogador2.perdeu()) {

            if (turnoJogador1) {

                turnoJogador(
                        jogador1,
                        jogador2);

            } else {

                if (contraComputador) {

                    ia.atacar(jogador1);

                } else {

                    turnoJogador(
                            jogador2,
                            jogador1);
                }
            }

            turnoJogador1 = !turnoJogador1;
        }

        System.out.println("\n===== FIM DE JOGO =====");

        if (jogador1.perdeu()) {

            System.out.println(
                    jogador2.getNome()
                            + " venceu!");

        } else {

            System.out.println(
                    jogador1.getNome()
                            + " venceu!");
        }
    }

    private void turnoJogador(
            Jogador atacante,
            Jogador defensor) {

        System.out.println(
                "\n=== TURNO DE "
                        + atacante.getNome()
                        + " ===");

        defensor.mostrarTabuleiroInimigo();

        boolean ataqueValido = false;

        while (!ataqueValido) {

            System.out.print("Linha: ");
            int linha = scanner.nextInt();

            System.out.print("Coluna: ");
            int coluna = scanner.nextInt();

            if (defensor.getTabuleiro()
                    .jaFoiAtacado(
                            linha,
                            coluna)) {

                System.out.println(
                        "Posicao ja atacada.");

                continue;
            }

            boolean acertou =
                    defensor.getTabuleiro()
                            .atacar(
                                    linha,
                                    coluna);

            if (acertou) {

                System.out.println(
                        "ACERTOU UM NAVIO!");

            } else {

                System.out.println(
                        "AGUA!");
            }

            ataqueValido = true;
        }
    }

    private void limparTela() {

        for (int i = 0; i < 30; i++) {

            System.out.println();
        }
    }
}