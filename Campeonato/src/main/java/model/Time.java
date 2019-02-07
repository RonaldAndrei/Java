package model;

import java.util.ArrayList;
import java.util.List;

public class Time {
    
    private List<Game> games = new ArrayList<>();

    String nome;
    int vit = 0, der = 0, emp = 0, golP = 0, golN = 0, saldoGols=0, pontos = 0, partidas = 0, cla;
    
    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
    
    public Time(String nome) {
        this.nome = nome;
    }

    public int getVit() {
        return vit;
    }

    public void incVit() {
        this.vit += 1;
    }

    public int getDer() {
        return der;
    }

    public void incDer() {
        this.der += 1;
    }

    public int getEmp() {
        return emp;
    }

    public void incEmp() {
        this.emp += 1;
    }

    public int getGolP() {
        return golP;
    }

    public void incGolP(int gol) {
        this.golP += gol;
    }

    public int getGolN() {
        return golN;
    }

    public void incGolN(int gol) {
        this.golN += gol;
    }

    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return (this.vit * 3) + this.emp;
    }

    public int getPartidas() {
        return (this.vit + this.der + this.emp);
    }

    public int getSaldoGols() {
        return (this.golP-this.golN);
    }

    public int getCla() {
        return cla;
    }

    public void setCla(int cla) {
        this.cla = cla;
    }
     
    
}
