package view;

import static config.Config.ALTERAR;
import static config.Config.INCLUIR;
import static config.Config.alineaRepository;
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
import model.Alinea;

public class AlineaViewController implements Initializable {

    private List<Alinea> lstAlineas = null;
    private ResourceBundle i18n;
    private char operacao;

    @FXML
    private AnchorPane anchorPaneBanco;

    @FXML
    private TableView<Alinea> tblVwAlinea;

    @FXML
    private TableColumn<Alinea, String> colCodigoAlinea;
    @FXML
    private TableColumn<Alinea, String> colNomeAlinea;

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
    private void acIncluirAlinea(ActionEvent event) {
        txtFldCodigo.setDisable(false);
        vBoxCrud.setDisable(false);
        operacao = INCLUIR;
        lblOperacao.setText(i18n.getString("ac.incluir.text"));
        txtFldCodigo.requestFocus();
    }

    @FXML
    private void acAlterarAlinea(ActionEvent event) {
        Alinea alineaSel = tblVwAlinea.getSelectionModel().getSelectedItem();
        if (alineaSel != null) {
            vBoxCrud.setDisable(false);
            operacao = ALTERAR;
            lblOperacao.setText(i18n.getString("ac.alterar.text"));
            txtFldCodigo.setDisable(true);
            txtFldCodigo.setText(alineaSel.getCodigo());
            txtFldNome.setText(alineaSel.getNome());
            txtFldNome.requestFocus();
        }
    }

    @FXML
    private void acExcluirAlinea(ActionEvent event) {
        Alinea alineaSel;
        alineaSel = tblVwAlinea.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                i18n.getString("msg.confirmacao1.text") + alineaSel.getNome() + "?", ButtonType.YES, ButtonType.CANCEL);
        if (alert.showAndWait().get() == ButtonType.YES) {
//            try {
//                
//            } catch (Exception e) {
//            }
            alineaRepository.delete(alineaSel);
            tblVwAlinea.getItems().remove(alineaSel);
        }
    }

    @FXML
    private void btnConfirmarClick(ActionEvent event) {
        Alinea alinea;

        switch (operacao) {
            case INCLUIR:
                alinea = new Alinea(txtFldCodigo.getText(), txtFldNome.getText());
                try {
                    alineaRepository.insert(alinea);
                    tblVwAlinea.getItems().add(alinea);
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
                Alinea alineaAux;
                Alinea alineaSel = tblVwAlinea.getSelectionModel().getSelectedItem();
                int alineaIndex = tblVwAlinea.getSelectionModel().getSelectedIndex();
                try {
                    alineaAux = new Alinea(txtFldCodigo.getText(), txtFldNome.getText());
                    alineaSel.setNome(alineaAux.getNome());
                    alineaRepository.save(alineaSel);
                    tblVwAlinea.getItems().set(alineaIndex, alineaSel);
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
        tblVwAlinea.requestFocus();
    }

    @FXML
    private void btnCancelarClick(ActionEvent event) {
        vBoxCrud.setDisable(true);
        lblOperacao.setText("");
        txtFldCodigo.clear();
        txtFldNome.clear();
        tblVwAlinea.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAlterar.disableProperty().bind(Bindings.isEmpty(tblVwAlinea.getSelectionModel().getSelectedItems()));
        btnExcluir.disableProperty().bind(Bindings.isEmpty(tblVwAlinea.getSelectionModel().getSelectedItems()));
        
        btnConfirmar.disableProperty().bind(Bindings.or(txtFldCodigo.textProperty().isEmpty(),txtFldNome.textProperty().isEmpty()));
        
        vBoxCrud.setDisable(true);
        i18n = rb;

        lstAlineas = alineaRepository.findAll();

        if (lstAlineas != null) {
            tblVwAlinea.setItems(FXCollections.observableList(lstAlineas));
        }
    }

}
