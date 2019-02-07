package view;

import java.net.URL;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrincipalController implements Initializable {

    private final char ds = new DecimalFormatSymbols(
            Locale.getDefault(Locale.Category.FORMAT)).getDecimalSeparator();

    @FXML
    private ImageView cubo;
    @FXML
    private ImageView cilindro;
    @FXML
    private ImageView piramide;
    //
    @FXML
    private TextField txtFldBcubo;

    @FXML
    private TextField txtFldRcil;
    @FXML
    private TextField txtFldAcil;

    @FXML
    private TextField txtFldBpir;
    @FXML
    private TextField txtFldApir;
    //
    @FXML
    private Label lblResultcubo;
    @FXML
    private Label lblResultcilindro;
    @FXML
    private Label lblResultpiramide;

    //limpar cubo
    @FXML
    private void btnLimparCuboClick(ActionEvent event) {
        txtFldBcubo.setText("");

        Platform.runLater(() -> txtFldBcubo.requestFocus());
    }

    //limpar cilindro
    @FXML
    private void btnLimparCilindroClick(ActionEvent event) {
        txtFldRcil.setText("");
        txtFldAcil.setText("");

        Platform.runLater(() -> txtFldRcil.requestFocus());
    }

    //limpar piramide
    @FXML
    private void btnLimparPiramideClick(ActionEvent event) {
        txtFldBpir.setText("");
        txtFldApir.setText("");

        Platform.runLater(() -> txtFldBpir.requestFocus());
    }

    //calcula cubo
    private void calculaCubo() {

        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());

        if (txtFldBcubo.getText().isEmpty()) {
            lblResultcubo.setText("");
        } else {

            try {
                double baseCubo = nf.parse(txtFldBcubo.getText()).doubleValue();

                double resultCubo = baseCubo * baseCubo * baseCubo;

                lblResultcubo.setText(nf.format(resultCubo));
            } catch (ParseException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    //calcula cilindro
    private void calculaCilindro() {

        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());

        if (txtFldRcil.getText().isEmpty() || txtFldAcil.getText().isEmpty()) {
            lblResultcilindro.setText("");
        } else {

            try {
                double rCil = nf.parse(txtFldRcil.getText()).doubleValue();
                double aCil = nf.parse(txtFldAcil.getText()).doubleValue();

                double Resultcilindro = 3.14159 * (rCil * rCil) * aCil;

                lblResultcilindro.setText(nf.format(Resultcilindro));
            } catch (ParseException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //calcula piramide

    private void calculaPiramide() {

        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());

        if (txtFldBpir.getText().isEmpty() || txtFldApir.getText().isEmpty()) {
            lblResultpiramide.setText("");
        } else {

            try {
                double bPir = nf.parse(txtFldBpir.getText()).doubleValue();
                double aPir = nf.parse(txtFldApir.getText()).doubleValue();

                double Resultpiramide = 0.33333*(bPir*bPir)*aPir;

                lblResultpiramide.setText(nf.format(Resultpiramide));

            } catch (ParseException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //calcula cubo
        txtFldBcubo.textProperty().addListener((observable, oldValue, newValue) -> {
            calculaCubo();

            if (!newValue.matches("\\d*(\\" + ds + "\\d*)?") && !newValue.isEmpty()) {
                txtFldBcubo.setText(oldValue);
            }
        }
        );
        //calcula cilindro
        txtFldRcil.textProperty().addListener((observable, oldValue, newValue) -> {
            calculaCilindro();

            if (!newValue.matches("\\d*(\\" + ds + "\\d*)?") && !newValue.isEmpty()) {
                txtFldRcil.setText(oldValue);
            }
        }
        );
        txtFldAcil.textProperty().addListener((observable, oldValue, newValue) -> {
            calculaCilindro();

            if (!newValue.matches("\\d*(\\" + ds + "\\d*)?") && !newValue.isEmpty()) {
                txtFldAcil.setText(oldValue);
            }
        }
        );
        //calcula piramide
        txtFldBpir.textProperty().addListener((observable, oldValue, newValue) -> {
            calculaPiramide();

            if (!newValue.matches("\\d*(\\" + ds + "\\d*)?") && !newValue.isEmpty()) {
                txtFldBpir.setText(oldValue);
            }
        }
        );
        txtFldApir.textProperty().addListener((observable, oldValue, newValue) -> {
            calculaPiramide();

            if (!newValue.matches("\\d*(\\" + ds + "\\d*)?") && !newValue.isEmpty()) {
                txtFldApir.setText(oldValue);
            }
        }
        );
    }
}
