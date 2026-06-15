package br.com.batalhanaval;

public class Tabuleiro {

    public static final int TAMANHO = 10;

    private char[][] mapa;

    public Tabuleiro() {

        mapa = new char[TAMANHO][TAMANHO];

        inicializarMapa();
    }

    private void inicializarMapa() {

        for (int linha = 0; linha < TAMANHO; linha++) {

            for (int coluna = 0; coluna < TAMANHO; coluna++) {

                mapa[linha][coluna] = '~';
            }
        }
    }

    public boolean posicionarBarco(int linha,
                                   int coluna,
                                   int tamanho,
                                   char direcao) {

        direcao = Character.toUpperCase(direcao);

        if (direcao == 'H') {

            if (coluna + tamanho > TAMANHO) {
                return false;
            }

            for (int i = 0; i < tamanho; i++) {

                if (mapa[linha][coluna + i] == 'B') {
                    return false;
                }
            }

            for (int i = 0; i < tamanho; i++) {

                mapa[linha][coluna + i] = 'B';
            }

        } else if (direcao == 'V') {

            if (linha + tamanho > TAMANHO) {
                return false;
            }

            for (int i = 0; i < tamanho; i++) {

                if (mapa[linha + i][coluna] == 'B') {
                    return false;
                }
            }

            for (int i = 0; i < tamanho; i++) {

                mapa[linha + i][coluna] = 'B';
            }

        } else {

            return false;
        }

        return true;
    }

    public boolean atacar(int linha, int coluna) {

        if (!posicaoValida(linha, coluna)) {

            return false;
        }

        if (mapa[linha][coluna] == 'B') {

            mapa[linha][coluna] = 'X';
            return true;
        }

        if (mapa[linha][coluna] == '~') {

            mapa[linha][coluna] = 'O';
            return false;
        }

        return false;
    }

    public boolean jaFoiAtacado(int linha, int coluna) {

        return mapa[linha][coluna] == 'X'
                || mapa[linha][coluna] == 'O';
    }

    public boolean possuiBarcos() {

        for (int linha = 0; linha < TAMANHO; linha++) {

            for (int coluna = 0; coluna < TAMANHO; coluna++) {

                if (mapa[linha][coluna] == 'B') {

                    return true;
                }
            }
        }

        return false;
    }

    public int contarPartesRestantes() {

        int contador = 0;

        for (int linha = 0; linha < TAMANHO; linha++) {

            for (int coluna = 0; coluna < TAMANHO; coluna++) {

                if (mapa[linha][coluna] == 'B') {

                    contador++;
                }
            }
        }

        return contador;
    }

    public boolean posicaoValida(int linha, int coluna) {

        return linha >= 0
                && linha < TAMANHO
                && coluna >= 0
                && coluna < TAMANHO;
    }

    public void exibir(boolean mostrarBarcos) {

        System.out.println();

        System.out.print("   ");

        for (int coluna = 0; coluna < TAMANHO; coluna++) {

            System.out.print(coluna + " ");
        }

        System.out.println();

        for (int linha = 0; linha < TAMANHO; linha++) {

            System.out.print(linha + "  ");

            for (int coluna = 0; coluna < TAMANHO; coluna++) {

                char simbolo = mapa[linha][coluna];

                if (!mostrarBarcos && simbolo == 'B') {

                    System.out.print("~ ");

                } else {

                    System.out.print(simbolo + " ");
                }
            }

            System.out.println();
        }

        System.out.println();
    }

    public char getPosicao(int linha, int coluna) {

        return mapa[linha][coluna];
    }
}