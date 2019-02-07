package view;

import static config.Config.ALTERAR;
import static config.Config.INCLUIR;
import static config.Config.bancoRepository;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.Banco;
import org.controlsfx.control.Notifications;

public class BancoViewController implements Initializable {

    private List<Banco> lstBancos = null;
    private ResourceBundle i18n;
    private char operacao;

    @FXML
    private AnchorPane anchorPaneBanco;

    @FXML
    private TableView<Banco> tblVwBanco;

    @FXML
    private TableColumn<Banco, String> colCodigoBanco;
    @FXML
    private TableColumn<Banco, String> colNomeBanco;

    @FXML
    private Label lblOperacao;

    @FXML
    private TextField txtFldCodigo;
    @FXML
    private TextField txtFldNome;
    
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnConfirmar;
    
    @FXML
    private VBox vBoxCrud;

    @FXML
    private void acIncluirBanco(ActionEvent event) {
        txtFldCodigo.setDisable(false);
        vBoxCrud.setDisable(false);
        operacao = INCLUIR;
        lblOperacao.setText(i18n.getString("ac.incluir.text"));
        txtFldCodigo.requestFocus();
    }

    @FXML
    private void acAlterarBanco(ActionEvent event) {
        Banco bancoSel = tblVwBanco.getSelectionModel().getSelectedItem();
        if (bancoSel != null) {
            vBoxCrud.setDisable(false);
            operacao = ALTERAR;
            lblOperacao.setText(i18n.getString("ac.alterar.text"));
            txtFldCodigo.setDisable(true);
            txtFldCodigo.setText(bancoSel.getCodigo());
            txtFldNome.setText(bancoSel.getNome());
            txtFldNome.requestFocus();
        }
    }

    @FXML
    private void acExcluirBanco(ActionEvent event) {
        Banco bancoSel;
        bancoSel = tblVwBanco.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                i18n.getString("msg.confirmacao1.text") + bancoSel.getNome() + "?", ButtonType.YES, ButtonType.CANCEL);
        if (alert.showAndWait().get() == ButtonType.YES) {
//            try {
//                
//            } catch (Exception e) {
//            }
            bancoRepository.delete(bancoSel);
            tblVwBanco.getItems().remove(bancoSel);
        }
    }

    @FXML
    private void btnConfirmarClick(ActionEvent event) {
        Banco banco;
        Notifications notficacao = Notifications.create().hideAfter(Duration.millis(500)).owner(anchorPaneBanco.getScene().getWindow());
        switch (operacao) {
            case INCLUIR:
                banco = new Banco(txtFldCodigo.getText(), txtFldNome.getText());
                try {
                    bancoRepository.insert(banco);
                    tblVwBanco.getItems().add(banco);
                } catch (Exception e) {
                    Alert alerta;
                    if (e.getMessage().contains("DuplicateKey")) {
                        alerta = new Alert(Alert.AlertType.ERROR, i18n.getString("msg.erro1.text"), ButtonType.OK);
                    } else {
                        alerta = new Alert(Alert.AlertType.ERROR, i18n.getString("msg.erro2.text") + e.getMessage(), ButtonType.OK);
                    }

                    alerta.showAndWait();
                    txtFldCodigo.requestFocus();
                    return;
                }
                break;
            case ALTERAR:
                Banco bancoAux;
                Banco bancoSel = tblVwBanco.getSelectionModel().getSelectedItem();
                int bancoIndex = tblVwBanco.getSelectionModel().getSelectedIndex();
                try {
                    bancoAux = new Banco(txtFldCodigo.getText(), txtFldNome.getText());
                    bancoSel.setNome(bancoAux.getNome());
                    bancoRepository.save(bancoSel);
                    tblVwBanco.getItems().set(bancoIndex, bancoSel);
                } catch (Exception e) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR, i18n.getString("msg.erro2.text") + e.getMessage(), ButtonType.OK);
                    alerta.showAndWait();
                    return;
                }
                break;
        }
        txtFldCodigo.clear();
        txtFldNome.clear();
        vBoxCrud.setDisable(true);
        lblOperacao.setText("");
        tblVwBanco.requestFocus();
    }

    @FXML
    private void btnCancelarClick(ActionEvent event) {
        vBoxCrud.setDisable(true);
        lblOperacao.setText("");
        txtFldCodigo.clear();
        txtFldNome.clear();
        tblVwBanco.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAlterar.disableProperty().bind(Bindings.isEmpty(tblVwBanco.getSelectionModel().getSelectedItems()));
        btnExcluir.disableProperty().bind(Bindings.isEmpty(tblVwBanco.getSelectionModel().getSelectedItems()));
        
        btnConfirmar.disableProperty().bind(Bindings.or(txtFldCodigo.textProperty().isEmpty(),txtFldNome.textProperty().isEmpty()));
        
        vBoxCrud.setDisable(true);
        i18n = rb;

        lstBancos = bancoRepository.findAll();

        if (lstBancos != null) {
            tblVwBanco.setItems(FXCollections.observableList(lstBancos));
        }
        //vBoxCrud.disableProperty().bind(tblVwBanco.focusedProperty());
    }

}
