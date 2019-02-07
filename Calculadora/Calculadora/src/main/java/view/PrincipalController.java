package view;

import com.sun.istack.internal.logging.Logger;
import java.net.URL;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrincipalController implements Initializable {

    @FXML
    private Label lblResult;

    @FXML
    private TextField txtFldPrimeiro;

    @FXML
    private TextField txtFldSegundo;

    @FXML
    private Button btnFechar;

    @FXML
    private Button btnSomar;
    
    @FXML
    private Button btnSubtrair;
    
    @FXML
    private Button btnMultiplicar;
    
    @FXML
    private Button btnDividir;
    
    @FXML
    private Button btnLimpar;

    @FXML
    private void btnFecharClick(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void btnSomarClick(ActionEvent event) {
        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());

        try {
            //double num1 = nf.parse(txtFldPrimeiro.getText()).doubleValue();
            //double num2 = nf.parse(txtFldSegundo.getText()).doubleValue();
            //double result = num1 + num2;

            lblResult.setText(nf.format(nf.parse(txtFldPrimeiro.getText()).doubleValue() + nf.parse(txtFldSegundo.getText()).doubleValue()));
        } 
        catch (ParseException ex) {
            java.util.logging.Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(txtFldPrimeiro.getText());

    }
    
    @FXML
    private void btnSubtrairClick(ActionEvent event) {
        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());

        try {
            //double num1 = nf.parse(txtFldPrimeiro.getText()).doubleValue();
            //double num2 = nf.parse(txtFldSegundo.getText()).doubleValue();
            //double result = num1 + num2;

            lblResult.setText(nf.format(nf.parse(txtFldPrimeiro.getText()).doubleValue() - nf.parse(txtFldSegundo.getText()).doubleValue()));
        } 
        catch (ParseException ex) {
            java.util.logging.Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(txtFldPrimeiro.getText());

    }
    
    @FXML
    private void btnMultiplicarClick(ActionEvent event) {
        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());

        try {
            //double num1 = nf.parse(txtFldPrimeiro.getText()).doubleValue();
            //double num2 = nf.parse(txtFldSegundo.getText()).doubleValue();
            //double result = num1 + num2;

            lblResult.setText(nf.format(nf.parse(txtFldPrimeiro.getText()).doubleValue() * nf.parse(txtFldSegundo.getText()).doubleValue()));
        } 
        catch (ParseException ex) {
            java.util.logging.Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(txtFldPrimeiro.getText());

    }
    
    @FXML
    private void btnDividirClick(ActionEvent event) {
        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());

        try {
            /*double num1 = nf.parse(txtFldPrimeiro.getText()).doubleValue();
            double num2 = nf.parse(txtFldSegundo.getText()).doubleValue();
            double result = num1 + num2;*/

            lblResult.setText(nf.format(nf.parse(txtFldPrimeiro.getText()).doubleValue() / nf.parse(txtFldSegundo.getText()).doubleValue()));
        } 
        catch (ParseException ex) {
            java.util.logging.Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(txtFldPrimeiro.getText());

    }
    
    @FXML
    private void btnLimparClick(ActionEvent event) {
        txtFldPrimeiro.setText("");
        txtFldSegundo.setText("");
        lblResult.setText("");
        Platform.runLater(() -> txtFldPrimeiro.requestFocus());
    }
    
    private void btns_habilitados(){
        btnSomar.disableProperty().set(
            txtFldPrimeiro.getText().isEmpty()
                    ||(txtFldPrimeiro.getText().charAt(0) == '-'
                       && txtFldPrimeiro.getText().length()==1)
                    ||(txtFldPrimeiro.getText().charAt(0) == ds
                       && txtFldPrimeiro.getText().length()==1)
            
         || txtFldSegundo.getText().isEmpty()
                    ||(txtFldSegundo.getText().charAt(0) == '-'
                       && txtFldSegundo.getText().length()==1)
                    ||(txtFldSegundo.getText().charAt(0) == ds
                       && txtFldSegundo.getText().length()==1)
                
        );
        btnSubtrair.disableProperty().set(btnSomar.isDisable());
        btnMultiplicar.disableProperty().set(btnSomar.isDisable());
        btnDividir.disableProperty().set(btnSomar.isDisable());
    }
    
    private final char ds = new DecimalFormatSymbols(
            Locale.getDefault(Locale.Category.FORMAT)).getDecimalSeparator();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btns_habilitados();
        
        txtFldPrimeiro.textProperty().addListener((observable,oldValue,newValue) -> {
            btns_habilitados();
            
            if (!newValue.matches("(\\-)?\\d*(\\"+ ds +"\\d*)?") && !newValue.isEmpty()){
               txtFldPrimeiro.setText(oldValue); 
            };
        }
            
        );
            
               txtFldSegundo.textProperty().addListener((observable,oldValue,newValue) -> {
            btns_habilitados();
            
            if (!newValue.matches("(\\-)?\\d*(\\"+ ds +"\\d*)?") && !newValue.isEmpty()){
               txtFldSegundo.setText(oldValue); 
            };
        }
            
        );
    }
}
