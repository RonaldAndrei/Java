package utilit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Game;
import model.Time;

public class Dados {

    private BufferedReader br = null;
    private String linha, nomeArquivo;
    private Time timeA, timeB;
    private int golA, golB;

    List<Time> lstTimes = new ArrayList<>();
    
    public Game gameLn;
    
    public Dados(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    private void ordena() {
        Time timeAux;
        int i, j;
        i = 1;
        while (i <= lstTimes.size() - 1) {
            j = i;

            while ((j > 0)
                    && ((lstTimes.get(j - 1).getPontos() < lstTimes.get(j).getPontos()) // pontos 
                    ||//ou
                    ((lstTimes.get(j - 1).getPontos() == lstTimes.get(j).getPontos())
                    && (lstTimes.get(j - 1).getVit() < lstTimes.get(j).getVit())) // vitorias 
                    ||//ou
                    ((lstTimes.get(j - 1).getPontos() == lstTimes.get(j).getPontos())
                    && (lstTimes.get(j - 1).getVit() == lstTimes.get(j).getVit())
                    && (lstTimes.get(j - 1).getSaldoGols() < lstTimes.get(j).getSaldoGols())) // saldo de gols 
                    )) {

                timeAux = lstTimes.get(j - 1);
                lstTimes.set(j - 1, lstTimes.get(j));
                lstTimes.set(j, timeAux);
                j--;
            }
            i++;
        }
        for (i = 0; i < lstTimes.size(); i++) {
            lstTimes.get(i).setCla(i + 1);
        }
    }

    private Time achaTime(String nome) {

        for (Time t : lstTimes) {
            if (t.getNome().equals(nome)) {
                return t;
            }
        }

        Time timeTmp = new Time(nome);
        lstTimes.add(timeTmp);
        return timeTmp;
    }

    private void analisa(String[] jogo) {
        timeA = achaTime(jogo[0]);
        timeB = achaTime(jogo[2]);
        golA = Integer.parseInt(jogo[1]);
        golB = Integer.parseInt(jogo[3]);
        
        gameLn = new Game(timeA.getNome(), golA, timeB.getNome(), golB);
        timeA.getGames().add(gameLn);
        timeB.getGames().add(gameLn);

        if (golA > golB) {
            timeA.incVit();
            timeB.incDer();
        } else if (golA < golB) {
            timeB.incVit();
            timeA.incDer();
        } else {
            timeB.incEmp();
            timeA.incEmp();
        }

        timeA.incGolP(golA);
        timeA.incGolN(golB);
        timeB.incGolP(golB);
        timeB.incGolN(golA);

    }

    ;
    
    public List<Time> ler() {
        try {
            br = new BufferedReader(new FileReader(nomeArquivo));
            while ((linha = br.readLine()) != null) {
                analisa(linha.split(","));
            }
            ordena();

        } catch (Exception e) {
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {

            }
        }
        return lstTimes;
    }
}
