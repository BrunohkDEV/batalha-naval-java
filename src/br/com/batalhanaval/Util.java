package br.com.batalhanaval;

public class Util {

    public static void limparTela() {

        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    public static void exibirTitulo(String titulo) {

        System.out.println();
        System.out.println("================================");
        System.out.println(titulo);
        System.out.println("================================");
    }

    public static boolean linhaOuColunaValida(int valor) {

        return valor >= 0 && valor <= 9;
    }
}