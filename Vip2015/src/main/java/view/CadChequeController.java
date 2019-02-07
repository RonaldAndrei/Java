package view;

import static config.Config.ALTERAR;
import static config.Config.INCLUIR;
import static config.Config.alineaRepository;
import static config.Config.bancoRepository;
import static config.Config.chequeRepository;
import static config.Config.clienteRepository;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Alinea;
import model.Banco;
import model.Cheque;
import model.Cliente;

public class CadChequeController implements Initializable {

    private ResourceBundle i18n;
    char op;
    Stage stage;
    Cheque chequeSel;
    int chequeIndex;

    @FXML
    private Label lblOperacao;

    @FXML
    private Button btnConfirmar;

    @FXML
    private DatePicker dateEmissao;
    @FXML
    private DatePicker datePagamento;

    @FXML
    private CheckBox cbPago;

    @FXML
    private ComboBox cmbCliente;
    @FXML
    private ComboBox cmbAlinea;
    @FXML
    private ComboBox cmbBanco;

    @FXML
    private TextField txtFldAgencia;
    @FXML
    private TextField txtFldConta;
    @FXML
    private TextField txtFldNumero;
    @FXML
    private TextField txtFldValor;

    @FXML
    private TableView<Cheque> tblVwCheque;

    public void setCheque(char operacao, Stage stage, Cheque chequeSel, int chequeIndex, TableView tblVwCheque) {

        this.stage = stage;
        this.chequeSel = chequeSel;
        this.chequeIndex = chequeIndex;
        this.tblVwCheque = tblVwCheque;

        if (operacao == INCLUIR) {
            op = INCLUIR;
            lblOperacao.setText(i18n.getString("ac.incluir.text"));
        } else {
            op = ALTERAR;
            lblOperacao.setText(i18n.getString("ac.alterar.text"));

            cmbCliente.setValue(chequeSel.getCliente().toString());
            cmbAlinea.setValue(chequeSel.getAlinea().toString());
            cmbBanco.setValue(chequeSel.getBanco().toString());
            txtFldAgencia.setText(chequeSel.getAgencia());
            txtFldConta.setText(chequeSel.getConta());
            txtFldNumero.setText(chequeSel.getNumero());
            txtFldValor.setText(chequeSel.getValor().toString());
            dateEmissao.setValue(chequeSel.getEmissao());
            datePagamento.setValue(chequeSel.getPagamento());

            if (chequeSel.getPago().equals(i18n.getString("lbl.pago.text"))) {
                cbPago.setSelected(true);
            } else {
                cbPago.setSelected(false);
            }
        }
    }

    @FXML
    private void btnConfirmarClick(ActionEvent event) {

        Cheque cheque;
        String pago;

        if (cbPago.isSelected() == true) {
            pago = i18n.getString("lbl.pago.text");
        } else {
            pago = i18n.getString("lbl.naopago.text");
        }

        Cliente clienteTmp = clienteRepository.findByNome(cmbCliente.getValue().toString());
        Alinea alineaTmp = alineaRepository.findByCodigo(cmbAlinea.getValue().toString());
        Banco bancoTmp = bancoRepository.findByNome(cmbBanco.getValue().toString());

        switch (op) {
            case INCLUIR:
                try {

                    cheque = new Cheque(clienteTmp, alineaTmp, bancoTmp,
                            txtFldAgencia.getText(), txtFldConta.getText(), txtFldNumero.getText(),
                            Double.parseDouble(txtFldValor.getText()), dateEmissao.getValue(),
                            pago, datePagamento.getValue());

                    chequeRepository.insert(cheque);
                    tblVwCheque.getItems().add(cheque);

                } catch (Exception e) {
                    Alert alerta;
                    if (e.getMessage().contains("DuplicateKey")) {
                        alerta = new Alert(Alert.AlertType.ERROR, i18n.getString("msg.erro1.text"), ButtonType.OK);
                    } else {
                        alerta = new Alert(Alert.AlertType.ERROR, i18n.getString("msg.erro2.text") + e.getMessage(), ButtonType.OK);
                    }

                    alerta.showAndWait();
                    return;
                }
                break;

            case ALTERAR:

                Cheque chequeAux;
                try {

                    chequeAux = new Cheque(clienteTmp, alineaTmp, bancoTmp,
                            txtFldAgencia.getText(), txtFldConta.getText(), txtFldNumero.getText(),
                            Double.parseDouble(txtFldValor.getText()), dateEmissao.getValue(),
                            pago, datePagamento.getValue());

                    chequeSel.setCliente(chequeAux.getCliente());
                    chequeSel.setAlinea(chequeAux.getAlinea());
                    chequeSel.setBanco(chequeAux.getBanco());
                    chequeSel.setAgencia(chequeAux.getAgencia());
                    chequeSel.setConta(chequeAux.getConta());
                    chequeSel.setNumero(chequeAux.getNumero());
                    chequeSel.setValor(chequeAux.getValor());
                    chequeSel.setEmissao(chequeAux.getEmissao());
                    chequeSel.setPagamento(chequeAux.getPagamento());
                    chequeSel.setPago(chequeAux.getPago());

                    chequeRepository.save(chequeSel);
                    tblVwCheque.getItems().set(chequeIndex, chequeSel);

                } catch (Exception e) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR, i18n.getString("msg.erro2.text") + e.getMessage(), ButtonType.OK);
                    alerta.showAndWait();
                    return;
                }
                break;
        }
        lblOperacao.setText("");
        cmbCliente.setItems(null);
        cmbAlinea.setItems(null);
        cmbBanco.setItems(null);
        txtFldAgencia.clear();
        txtFldConta.clear();
        txtFldNumero.clear();
        txtFldValor.clear();
        dateEmissao.setValue(null);
        datePagamento.setValue(null);
        cbPago.setSelected(false);
        stage.close();
    }

    @FXML
    private void btnCancelarClick(ActionEvent event) {
        lblOperacao.setText("");
        cmbCliente.setItems(null);
        cmbAlinea.setItems(null);
        cmbBanco.setItems(null);
        txtFldAgencia.clear();
        txtFldConta.clear();
        txtFldNumero.clear();
        txtFldValor.clear();
        dateEmissao.setValue(null);
        datePagamento.setValue(null);
        cbPago.setSelected(false);
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        i18n = rb;

        cmbCliente.setItems(FXCollections.observableList(clienteRepository.findAll()));
        cmbAlinea.setItems(FXCollections.observableList(alineaRepository.findAll()));
        cmbBanco.setItems(FXCollections.observableList(bancoRepository.findAll()));
    }

}
