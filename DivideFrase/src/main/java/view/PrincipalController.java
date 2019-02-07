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
import javafx.scene.paint.Color;

public class PrincipalController implements Initializable {

    @FXML
    private TextField txtFldFrase;

    @FXML
    private Label lblParteUm;

    @FXML
    private Label lblParteDois;

    @FXML
    private void separa() {

        int i = 0;
        if (txtFldFrase.getText().isEmpty()) {

            lblParteUm.setText("");
            lblParteDois.setText("");
            //se a lacuna do raio ou da altura estiver vazia limpar lacuna do resultado.
        } else {
            String str = new String(txtFldFrase.getText());

            int tam = str.length();
            int parametro = 8;
            i = parametro;

            if (tam < parametro - 1) {
                String subUm = str.substring(0, tam);
                lblParteUm.setText(subUm);
            } else if (str.charAt(parametro) == ' ') {
                System.out.println("jhgkfjghk" + str.substring(0, parametro));
                String subUm = str.substring(0, parametro);
                lblParteUm.setText(subUm);
                String subDois = str.substring(parametro, tam);
                lblParteDois.setText(subDois);
            } else if (str.charAt(parametro) != ' ') {

                while (str.charAt(parametro) != ' ' && i != -1) {
                    System.out.println(i);
                    i--;
                    System.out.println(i);
                    if (str.charAt(i) == ' ') {
                        String subUm = str.substring(0, i);
                        lblParteUm.setText(subUm);
                        String subDois = str.substring(i, tam);
                        lblParteDois.setText(subDois);
                        break;
                    } else {
                        String subUm = str.substring(0, parametro);
                        lblParteUm.setText(subUm);
                        String subDois = str.substring(parametro, tam);
                        lblParteDois.setText(subDois);
                    }
                }

            }

           /* if (i == -1) {
                String subUm = str.substring(0, parametro);
                lblParteUm.setText(subUm);
                String subDois = str.substring(parametro, tam);
                lblParteDois.setText(subDois);
            }*/
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFldFrase.textProperty().addListener((observable, oldValue, newValue) -> {
            separa();
        }
        );
    }
}
