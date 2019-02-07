package view;

import java.net.URL;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Medida;
import model.Volume;

public class PrincipalController implements Initializable {

    private final char separadorDecimal = new DecimalFormatSymbols(Locale.getDefault(Locale.Category.FORMAT)).getDecimalSeparator();

    @FXML
    private ComboBox cmbMedidas;
    @FXML
    private ComboBox cmbVolumes;
    //    
    @FXML
    private ImageView imgSetas;
    @FXML
    private ImageView imgSetasvol;
    //    
    @FXML
    private Label lblUnDe;
    @FXML
    private Label lblUnPara;
    @FXML
    private Label lblUnDevol;
    @FXML
    private Label lblUnParavol;
    //
    @FXML
    private TextField txtFldDe;
    @FXML
    private TextField txtFldPara;
    @FXML
    private TextField txtFldDevol;
    @FXML
    private TextField txtFldParavol;
    //
    @FXML
    private Button btnFechar;

    @FXML
    private void btnFecharClick(ActionEvent event) {
        System.exit(0);
    }
    //medida
    private boolean ehDe;
    private double fator = 1;

    private List<Medida> medidas = new ArrayList<>();
    //volume
    private boolean ehDevol;
    private double fatorvol = 1;

    private List<Volume> volumes = new ArrayList<>();

    private final NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
    //medida
    private String convertido(double val) {
        double v;
        if (ehDe) {
            v = val * fator;
        } else {
            v = val / fator;
        }
        return nf.format(v);
    }
    
    //volume
    private String convertidovol(double val) {
        double v;
        if (ehDevol) {
            v = val * fatorvol;
        } else {
            v = val / fatorvol;
        }
        return nf.format(v);
    }
    
    //medida
    private void atualizaTxtFld() {
        if (ehDe) {
            try {
                txtFldPara.setText(convertido(nf.parse(txtFldDe.getText()).doubleValue()));
            } catch (ParseException ex) {
                txtFldPara.setText("");
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                txtFldDe.setText(convertido(nf.parse(txtFldPara.getText()).doubleValue()));
            } catch (ParseException ex) {
                txtFldDe.setText("");
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    //volume
    private void atualizaVolTxtFld() {
        if (ehDevol) {
            try {
                txtFldParavol.setText(convertidovol(nf.parse(txtFldDevol.getText()).doubleValue()));
            } catch (ParseException ex) {
                txtFldParavol.setText("");
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                txtFldDevol.setText(convertidovol(nf.parse(txtFldParavol.getText()).doubleValue()));
            } catch (ParseException ex) {
                txtFldDevol.setText("");
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    //medida
    private final ChangeListener<? super String> listenerDe =
        (observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*(\\" + separadorDecimal + "\\d*)?") && !newValue.isEmpty()){
            txtFldDe.setText (oldValue);
        } else {
            atualizaTxtFld();
        }
    };
    
    private final ChangeListener<? super String> listenerPara =
        (observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*(\\" + separadorDecimal + "\\d*)?") && !newValue.isEmpty()){
            txtFldPara.setText (oldValue);
        } else {
            atualizaTxtFld();
        }
    };
    
    //volume
    private final ChangeListener<? super String> listenerDevol =
        (observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*(\\" + separadorDecimal + "\\d*)?") && !newValue.isEmpty()){
            txtFldDevol.setText (oldValue);
        } else {
            atualizaVolTxtFld();
        }
    };
    
    private final ChangeListener<? super String> listenerParavol =
        (observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*(\\" + separadorDecimal + "\\d*)?") && !newValue.isEmpty()){
            txtFldParavol.setText (oldValue);
        } else {
            atualizaVolTxtFld();
        }
    };
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //medida
        medidas.add(new Medida("Quilometros", "km", "Metros", "m", 1000));
        medidas.add(new Medida("Quilometros", "km", "Milhas", "mi", 0.62));
        medidas.add(new Medida("Quilometros", "km", "Polegadas", "pol", 39370.08));
        //System.out.println (medidas);
        cmbMedidas.setItems(FXCollections.observableList(medidas));

        cmbMedidas.valueProperty().addListener(
                new ChangeListener<Medida>() {
                    @Override
                    public void changed(ObservableValue<? extends Medida> observable, Medida oldValue, Medida newValue) {
                        lblUnDe.setText(newValue.getSiglaDe());
                        lblUnPara.setText(newValue.getSiglaPara());
                        fator = newValue.getFator();
                        atualizaTxtFld();
                    }
                });
        //volume
        volumes.add(new Volume("Litro", "l", "Centímetro cúbico", "cm³", 1000));
        volumes.add(new Volume("Colher de sopa", "CS", "Mililitro", "ml", 15));
        volumes.add(new Volume("Metro cúbico", "m³", "Litro", "l", 1000));
        cmbVolumes.setItems(FXCollections.observableList(volumes));

        cmbVolumes.valueProperty().addListener(
                new ChangeListener<Volume>() {
                    @Override
                    public void changed(ObservableValue<? extends Volume> observable, Volume oldValue, Volume newValue) {
                        lblUnDevol.setText(newValue.getSiglaDevol());
                        lblUnParavol.setText(newValue.getSiglaParavol());
                        fatorvol = newValue.getFatorvol();
                        atualizaVolTxtFld();
                    }
                });
        //medida
        txtFldDe.focusedProperty().addListener(
                (ObservableValue<? extends Boolean> observable,
                        Boolean oldValue, Boolean newValue) -> {
                    if (newValue) {
                        ehDe = true;
                        imgSetas.setImage(new Image("/images/SetasDireita.png"));
                        txtFldDe.textProperty().addListener(listenerDe);
                    } else {
                        txtFldDe.textProperty().removeListener(listenerDe);
                    }
                });
        
        txtFldPara.focusedProperty().addListener(
                (ObservableValue<? extends Boolean> observable,
                        Boolean oldValue, Boolean newValue) -> {
                    if (newValue) {
                        ehDe = false;
                        imgSetas.setImage(new Image("/images/SetasEsquerda.png"));
                        txtFldPara.textProperty().addListener(listenerPara);
                    } else {
                        txtFldPara.textProperty().removeListener(listenerPara);
                       }
                });
        //volume
        txtFldDevol.focusedProperty().addListener(
                (ObservableValue<? extends Boolean> observable,
                        Boolean oldValue, Boolean newValue) -> {
                    if (newValue) {
                        ehDevol = true;
                        imgSetasvol.setImage(new Image("/images/SetasDireitaV.png"));
                        txtFldDevol.textProperty().addListener(listenerDevol);
                    } else {
                        txtFldDevol.textProperty().removeListener(listenerDevol);
                    }
                });
        
        txtFldParavol.focusedProperty().addListener(
                (ObservableValue<? extends Boolean> observable,
                        Boolean oldValue, Boolean newValue) -> {
                    if (newValue) {
                        ehDevol = false;
                        imgSetasvol.setImage(new Image("/images/SetasEsquerdaV.png"));
                        txtFldParavol.textProperty().addListener(listenerParavol);
                    } else {
                        txtFldParavol.textProperty().removeListener(listenerParavol);
                       }
                });
    }
}
