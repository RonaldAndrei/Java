package model;


public class Jogador {
    String nome, posicao;
    int numCam;

    public Jogador(String nome, String posicao, int numCam) {
        this.nome = nome;
        this.posicao = posicao;
        this.numCam = numCam;
    }

    public Jogador(String nomeJog) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getNumCam() {
        return numCam;
    }

    public void setNumCam(int numCam) {
        this.numCam = numCam;
    }
    
}
