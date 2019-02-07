package model;

public class Game {
    
    String timeA,timeB;
    int golA, golB;

    public Game(String timeA, int golA, String timeB, int golB) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.golA = golA;
        this.golB = golB;
    }
        
    public String getTimeA() {
        return timeA;
    }

    public void setTimeA(String timeA) {
        this.timeA = timeA;
    }

    public String getTimeB() {
        return timeB;
    }

    public void setTimeB(String timeB) {
        this.timeB = timeB;
    }

    public int getGolA() {
        return golA;
    }

    public void setGolA(int golA) {
        this.golA = golA;
    }

    public int getGolB() {
        return golB;
    }

    public void setGolB(int golB) {
        this.golB = golB;
    }

}
