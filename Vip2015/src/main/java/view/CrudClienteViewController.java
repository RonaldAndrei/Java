package view;

import static config.Config.INCLUIR;
import static config.Config.clienteRepository;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Cliente;
import org.controlsfx.control.PopOver;

public class CrudClienteViewController implements Initializable {

    @FXML
    private TextField txtFldCodigo;
    @FXML
    private TextField txtFldNome;
    @FXML
    private TextField txtFldFone;

    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnConfirmar;

    private Cliente cliente;
    private PopOver popOverCliente;
    private char operacao;
    private int itemSel;
    private TableView<Cliente> tblVwCliente;

    public void setCliente(Cliente cliente, int itemSel, char operacao, TableView tblVwCliente, PopOver popOverCliente) {

        this.cliente = cliente;
        this.operacao = operacao;
        this.itemSel = itemSel;
        this.tblVwCliente = tblVwCliente;
        this.popOverCliente = popOverCliente;
        txtFldCodigo.setText(cliente.getCnpj());
        txtFldFone.setText(cliente.getFone());
        txtFldNome.setText(cliente.getNome());
        
//        listener();

        if (operacao == INCLUIR) {
            txtFldCodigo.setDisable(false);
            txtFldCodigo.requestFocus();
        } else {
            txtFldCodigo.setDisable(true);
            txtFldFone.requestFocus();
        }
    }

    @FXML
    private void btnConfirmarClick(ActionEvent event) {
        
//        if (txtFldCodigo.getText().isEmpty() || txtFldFone.getText().isEmpty() || txtFldNome.getText().isEmpty()) {
//
//            if (txtFldCodigo.getText().isEmpty()) {
//                txtFldCodigo.setStyle("-fx-border-color: Red");
//            }
//            if (txtFldFone.getText().isEmpty()) {
//                txtFldFone.setStyle("-fx-border-color: Red");
//            }
//            if (txtFldNome.getText().isEmpty()) {
//                txtFldNome.setStyle("-fx-border-color: Red");
//            }
//            return;
//        }

        cliente.setCnpj(txtFldCodigo.getText());
        cliente.setFone(txtFldFone.getText());
        cliente.setNome(txtFldNome.getText());

        if (operacao == INCLUIR) {
            clienteRepository.insert(cliente);
            tblVwCliente.getItems().add(cliente);
        } else {
            clienteRepository.save(cliente);
            tblVwCliente.getItems().set(itemSel, cliente);
        }
        popOverCliente.hide();
    }

    @FXML
    private void btnCancelarClick(ActionEvent event) {
        txtFldCodigo.clear();
        txtFldNome.clear();
        txtFldFone.clear();
        popOverCliente.hide();
    }

//    public void listener() {
//
//        txtFldCodigo.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue.isEmpty()) {
//                txtFldCodigo.setStyle("-fx-border-color: Red");
//            } else {
//                txtFldCodigo.setStyle("");
//            }
//        });
//        txtFldNome.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue.isEmpty()) {
//                txtFldNome.setStyle("-fx-border-color: Red");
//            } else {
//                txtFldNome.setStyle("");
//            }
//        });
//        txtFldFone.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue.isEmpty()) {
//                txtFldFone.setStyle("-fx-border-color: Red");
//            } else {
//                txtFldFone.setStyle("");
//            }
//        });
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
