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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;

public class PrincipalController implements Initializable {
    
    private final char ds = new DecimalFormatSymbols(
            Locale.getDefault(Locale.Category.FORMAT)).getDecimalSeparator();
    
    @FXML
    private Label lblResult;
    
    @FXML
    private Label lblCategoria;
    
    @FXML
    private TextField txtFldPeso;

    @FXML
    private TextField txtFldAltura;
    
    @FXML
    private Button btnLimpar;
   
    @FXML
    private HBox hbxResult;
    
    
    @FXML
    private void btnLimparClick(ActionEvent event) {
        txtFldPeso.setText("");
        txtFldAltura.setText("");
        lblResult.setText("Resultado");
        lblCategoria.setText("Categoria");
        hbxResult.setStyle("");
        Platform.runLater(() -> txtFldPeso.requestFocus());

    }
    
    private void calcula_imc(){
        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
        
        if(txtFldPeso.getText().isEmpty() || txtFldAltura.getText().isEmpty()){
            
            lblResult.setText("Resultado");
            lblCategoria.setText("Categoria");
            hbxResult.setStyle("");
        }
        
        else{
            try {
            double peso = nf.parse(txtFldPeso.getText()).doubleValue();
            double altura = nf.parse(txtFldAltura.getText()).doubleValue();
            double result = peso/(altura*altura);
            
            lblResult.setText(nf.format(result));
            
            
                if(result < 17)
                {
                lblCategoria.setText("Muito abaixo do peso");
                lblCategoria.setTextFill(Color.web("Black"));
                hbxResult.setStyle("-fx-background-color: Turquoise");
                }
            
                else if(result < 18.5)
                {
                lblCategoria.setText("Abaixo do peso");
                lblCategoria.setTextFill(Color.web("Black"));
                hbxResult.setStyle("-fx-background-color: SteelBlue");
                }
            
                else if(result < 25)
                {
                lblCategoria.setText("Peso normal");
                lblCategoria.setTextFill(Color.web("Black"));
                hbxResult.setStyle("-fx-background-color: LimeGreen");
                }
            
                else if(result < 30)
                {
                lblCategoria.setText("Acima do peso");
                lblCategoria.setTextFill(Color.web("Black"));
                hbxResult.setStyle("-fx-background-color: Tomato");
                }
            
                else if(result < 35)
                {
                lblCategoria.setText("Obesidade I");
                lblCategoria.setTextFill(Color.web("Black"));
                hbxResult.setStyle("-fx-background-color: OrangeRed");
                }
            
                else if(result < 40)
                {
                lblCategoria.setText("Obesidade II (Severa)");
                lblCategoria.setTextFill(Color.web("Black"));
                hbxResult.setStyle("-fx-background-color: Red");
                }
            
                else
                {
                lblCategoria.setText("Obesidade III (Mórbita)");
                lblCategoria.setTextFill(Color.web("White"));
                hbxResult.setStyle("-fx-background-color: Black");
                }
                        
        } 
        
        
        catch (ParseException ex) {
            java.util.logging.Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        

        System.out.println(txtFldPeso.getText());
        
    
    }
       
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              
           txtFldPeso.textProperty().addListener((observable, oldValue, newValue) -> {
           calcula_imc();
                
                if (!newValue.matches("\\d*(\\" + ds + "\\d*)?") && !newValue.isEmpty()) {
                    txtFldPeso.setText(oldValue);
                }
            }
        );
        
            txtFldAltura.textProperty().addListener((observable, oldValue, newValue) -> {
            calcula_imc();
            
                if (!newValue.matches("\\d*(\\" + ds + "\\d*)?") && !newValue.isEmpty()) {
                txtFldAltura.setText(oldValue);
                }
            }
        );
        
       
    
    }    
}
