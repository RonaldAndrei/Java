package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import org.omg.CORBA.Current;

public class PrincipalController implements Initializable {

    private final char ds = new DecimalFormatSymbols(
            Locale.getDefault(Locale.Category.FORMAT)).getDecimalSeparator();

    @FXML
    private Label lblResultPreco;
    @FXML
    private Label lblPreco;
    
    //
    @FXML
    private HBox hbxResult;
    
    //    
    @FXML
    private TextField txtFldPLE;

    @FXML
    private TextField txtFldPLG;

    @FXML
    private TextField txtFldCE;

    @FXML
    private TextField txtFldCG;

    //
    @FXML
    private Button btnLimparPreco;

    @FXML
    private Button btnLimparConsumo;
    
    //
    @FXML
    private void btnLimparPrecoClick(ActionEvent event) {
        txtFldPLE.setText("");
        txtFldPLG.setText("");
        Platform.runLater(() -> txtFldPLE.requestFocus());
    }

    @FXML
    private void btnFecharClick(ActionEvent event) {
        gravar();
        System.exit(0);
    }
    
    @FXML
    private void btnLimparConsumoClick(ActionEvent event) {
        txtFldCE.setText("");
        txtFldCG.setText("");
        lblPreco.setText("");
        Platform.runLater(() -> txtFldCE.requestFocus());
    }
    
    private void calcula_comb(){
        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
        
        if(txtFldPLE.getText().isEmpty() || txtFldPLG.getText().isEmpty() || 
           txtFldCE.getText().isEmpty() || txtFldCG.getText().isEmpty()){
            
            lblResultPreco.setText("");
            lblPreco.setText("");
            hbxResult.setStyle("");
            txtFldPLE.setStyle("");
            txtFldPLG.setStyle("");
            txtFldPLE.setStyle("");
            txtFldPLG.setStyle("");
            }
        else{
            try {
                double PLE = nf.parse(txtFldPLE.getText()).doubleValue();
                double PLG = nf.parse(txtFldPLG.getText()).doubleValue();
                double CE = nf.parse(txtFldCE.getText()).doubleValue();
                double CG = nf.parse(txtFldCG.getText()).doubleValue();
                
                double resultEt = PLE / CE;
                double resultGas = PLG / CG;
            
            if(resultEt < resultGas){
                txtFldPLG.setStyle("");
                txtFldPLE.setStyle("");
                lblResultPreco.setText("Etanol");
                lblPreco.setText(nf.format(resultEt));
                hbxResult.setStyle("-fx-background-color: Green");
                txtFldPLE.setStyle("-fx-border-color: Green");
            }
            
            else if(resultGas < resultEt){
                txtFldPLG.setStyle("");
                txtFldPLE.setStyle("");
                lblResultPreco.setText("Gasolina");
                lblPreco.setText(nf.format(resultGas));
                hbxResult.setStyle("-fx-background-color: Red");
                txtFldPLG.setStyle("-fx-border-color: Red");
            }    
                       
            else{
                txtFldPLG.setStyle("");
                txtFldPLE.setStyle("");
                lblResultPreco.setText("Indiferente");
                lblPreco.setText(nf.format(resultGas));
                hbxResult.setStyle("-fx-background-color: Blue");
                
                    
            }
            } catch (ParseException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
    public void gravar() {
    File file = new File("src/main/resources/config/consumos.txt");
        try {
            file.createNewFile();
            
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            String linha;
            
            linha = txtFldCE.getText() + ";";
            linha += txtFldCG.getText();
            
            bw.write(linha + "\n");
            bw.close();
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    
    //
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //listener preço
        txtFldPLE.textProperty().addListener((observable, oldValue, newValue) -> {            
            calcula_comb();
            if (!newValue.matches("\\d*(\\" + ds + "\\d*)?") && !newValue.isEmpty()) {
                txtFldPLE.setText(oldValue);
            }
        }
        );

        txtFldPLG.textProperty().addListener((observable, oldValue, newValue) -> {
            calcula_comb();
            if (!newValue.matches("\\d*(\\" + ds + "\\d*)?") && !newValue.isEmpty()) {
                txtFldPLG.setText(oldValue);
            }
        }
        );

        //listener consumo
        txtFldCE.textProperty().addListener((observable, oldValue, newValue) -> {
            calcula_comb();
            if (!newValue.matches("\\d*(\\" + ds + "\\d*)?") && !newValue.isEmpty()) {
                txtFldCE.setText(oldValue);
            }
        }
        );

        txtFldCG.textProperty().addListener((observable, oldValue, newValue) -> {
            calcula_comb();
            if (!newValue.matches("\\d*(\\" + ds + "\\d*)?") && !newValue.isEmpty()) {
                txtFldCG.setText(oldValue);
            }
        }
        );

        //Buffer Reader
        BufferedReader br = null;
            String sCurrentLine;
        String[] info;

  
        try {
            br = new BufferedReader(new FileReader("src/main/resources/config/consumos.txt"));
             while ((sCurrentLine = br.readLine()) != null) {
                info = sCurrentLine.split(";");
                txtFldCE.setText(info[0]);
                txtFldCG.setText(info[1]);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
}
