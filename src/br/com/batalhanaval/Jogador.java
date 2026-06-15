package br.com.batalhanaval;

public class Jogador {

    private String nome;
    private Tabuleiro tabuleiro;

    public Jogador(String nome) {

        this.nome = nome;
        this.tabuleiro = new Tabuleiro();
    }

    public String getNome() {
        return nome;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public boolean perdeu() {

        return !tabuleiro.possuiBarcos();
    }

    public int partesRestantes() {

        return tabuleiro.contarPartesRestantes();
    }

    public void mostrarMeuTabuleiro() {

        System.out.println("\nTABULEIRO DE " + nome);

        tabuleiro.exibir(true);
    }

    public void mostrarTabuleiroInimigo() {

        System.out.println("\nTABULEIRO DO ADVERSARIO");

        tabuleiro.exibir(false);
    }
}