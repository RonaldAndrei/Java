package view;

import static config.Config.ALTERAR;
import static config.Config.INCLUIR;
import static config.Config.chequeRepository;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cheque;

public class ChequeViewController implements Initializable {

    private List<Cheque> lstCheques = null;
    private ResourceBundle i18n;
    private char operacao;

    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnAlterar;

    @FXML
    private Label lblOperacao;

    @FXML
    private TableView<Cheque> tblVwCheque;

    @FXML
    private void acIncluirCheque(ActionEvent event) {
        operacao = INCLUIR;
        cadCheque();
    }

    @FXML
    private void acAlterarCheque(ActionEvent event) {
        Cheque chequeSel = tblVwCheque.getSelectionModel().getSelectedItem();
        if (chequeSel != null) {
            operacao = ALTERAR;
            cadCheque();
        }
    }

    @FXML
    private void acExcluirCheque(ActionEvent event) {
        Cheque chequeSel = tblVwCheque.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                i18n.getString("msg.confirmacao1.text") + chequeSel.getNumero() + "?", ButtonType.YES, ButtonType.CANCEL);
        if (alert.showAndWait().get() == ButtonType.YES) {
//            try {
//                
//            } catch (Exception e) {
//            }
            chequeRepository.delete(chequeSel);
            tblVwCheque.getItems().remove(chequeSel);
        }
    }

    private void cadCheque() {
        try {
            Cheque chequeSel = tblVwCheque.getSelectionModel().getSelectedItem();
            int chequeIndex = tblVwCheque.getSelectionModel().getSelectedIndex();
            
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/CadCheque.fxml"));
            loader.setResources(i18n);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(true);
            stage.setScene(scene);

            //stage.getIcons().add(new Image("/fxml/icon.png"));
            stage.setTitle("");

            CadChequeController cadChequeController = loader.getController();
            cadChequeController.setCheque(operacao,stage,chequeSel,chequeIndex,tblVwCheque);

            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(PrincipalControllerVip.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAlterar.disableProperty().bind(Bindings.isEmpty(tblVwCheque.getSelectionModel().getSelectedItems()));
        btnExcluir.disableProperty().bind(Bindings.isEmpty(tblVwCheque.getSelectionModel().getSelectedItems()));

        i18n = rb;

        lstCheques = chequeRepository.findAll();

        if (lstCheques != null) {
            tblVwCheque.setItems(FXCollections.observableList(lstCheques));
        }
    }

}
