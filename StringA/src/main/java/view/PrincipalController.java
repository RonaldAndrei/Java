package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;

public class PrincipalController implements Initializable {

    @FXML
    private TextField txtFrase, txtTamanho;

    @FXML
    private Label lblPrimeiro;

    @FXML
    private Label lblSegundo;

    @FXML
    private AnchorPane AnchorPane1;

    private void Separar() {
        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
        int i = 0;
        if (txtFrase.getText().isEmpty()) {
            lblPrimeiro.setText("");
            lblSegundo.setText("");
        } else {
            try {
                int condicao = nf.parse(txtTamanho.getText()).intValue();

                String str = new String(txtFrase.getText());
                int tamanho = str.length();
                //int condicao = 5;
                i = condicao;

                if (tamanho <= condicao) {
                    String subUm = str.substring(0, tamanho);
                    lblPrimeiro.setText(subUm);
                    System.out.println(subUm);
                } else if (str.charAt(condicao) == ' ') {
                    String subUm = str.substring(0, condicao);
                    lblPrimeiro.setText(subUm);
                    String subDois = str.substring(condicao, tamanho);
                    lblSegundo.setText(subDois);
                } else if (str.charAt(condicao) != ' ') {

                    while (str.charAt(i) != ' ' && i != -1) {
                        i--;
                        if (i != -1) {
                        String subUm = str.substring(0, i);
                        lblPrimeiro.setText(subUm);
                        String subDois = str.substring(i, tamanho);
                        lblSegundo.setText(subDois);
                        //       break;
                    } else {
                        String subUm = str.substring(0, condicao);
                        lblPrimeiro.setText(subUm);
                        String subDois = str.substring(condicao, tamanho);
                        lblSegundo.setText(subDois);
                    }
                    }
                    

                }
            } catch (ParseException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFrase.setTooltip(new Tooltip("Digite Aqui!"));
        txtTamanho.setTooltip(new Tooltip("Digite Aqui!"));
        BackgroundImage a = new BackgroundImage(new Image("/imagem/fundo.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        AnchorPane1.setBackground(new Background(a));
        txtFrase.textProperty().addListener((observable, oldValue, newValue) -> {
            Separar();
            if (!newValue.matches(".*") && !newValue.isEmpty()) {
                txtFrase.setText(oldValue);
            };
        }
        );

        txtTamanho.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*") && !newValue.isEmpty()) {
                txtTamanho.setText(oldValue);
            };
        }
        );
    }
}
// FALTOU UM LISTENER NO OUTRO CAMPO
/*
Trabalho
Implementar no programa de manipulação de string.
1. Utilizando: charAt, toLowerCase, toUpperCase,
               lenght, split
2. Exibir número de palavras;
3. Exibir tamanho do nome;
4. Transformar o nome em primeira letra maiuscula e
   demais minuscula
5. Simplificar nomes intermediários para apenas 1 letra
   ex.: Idomar Augusto Cerutti -> Idomar A Cerutti
*/