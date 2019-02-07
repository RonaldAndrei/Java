package view;

import static config.Config.ALTERAR;
import static config.Config.INCLUIR;
import static config.Config.clienteRepository;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Cliente;
import org.controlsfx.control.PopOver;

public class ClienteViewController implements Initializable {
    //
    private final PopOver popOverCRUDCliente = new PopOver();
    private Parent viewCRUDCliente;
    private double mouseX;
    private double mouseY;
    private Cliente clienteSel;
    private int itemSel;
    //
    private List<Cliente> lstClientes = null;
    private ResourceBundle i18n;
    private char operacao;
    
    @FXML
    private AnchorPane anchorPaneCliente;
    
    @FXML
    private TableView<Cliente> tblVwCliente;

    @FXML
    private TableColumn<Cliente, String> colCodigoCliente;
    @FXML
    private TableColumn<Cliente, String> colNomeCliente;    
    
    @FXML
    private Button btnIncluir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnConfirmar;
    
    private void showCRUDCliente(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/CrudClienteView.fxml"));
        loader.setResources(i18n);
        
        try {
            viewCRUDCliente = loader.load();
            CrudClienteViewController controller = loader.getController();
            
            popOverCRUDCliente.setContentNode(viewCRUDCliente);           
            popOverCRUDCliente.setAutoFix(true);
            popOverCRUDCliente.setAutoHide(true);
            popOverCRUDCliente.setHideOnEscape(true);
            popOverCRUDCliente.setHeaderAlwaysVisible(true);
            
            if(operacao == INCLUIR){
                
                clienteSel = new Cliente();
                controller.setCliente(clienteSel, -1, operacao, tblVwCliente, popOverCRUDCliente);                
                popOverCRUDCliente.setArrowLocation(PopOver.ArrowLocation.TOP_LEFT);
                popOverCRUDCliente.setTitle(i18n.getString("ac.incluir.text"));
                popOverCRUDCliente.show(btnIncluir);                
                
            } else {
                clienteSel = tblVwCliente.getSelectionModel().getSelectedItem();
                itemSel = tblVwCliente.getSelectionModel().getSelectedIndex();
                
                controller.setCliente(clienteSel, itemSel, operacao, tblVwCliente, popOverCRUDCliente);
                popOverCRUDCliente.setArrowLocation(PopOver.ArrowLocation.LEFT_TOP);
                popOverCRUDCliente.setTitle(i18n.getString("ac.alterar.text"));
                popOverCRUDCliente.show(tblVwCliente,mouseX,mouseY);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteViewController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    @FXML
    private void tblVwMouseClick(MouseEvent event){
        mouseX = event.getScreenX();
        mouseY = event.getScreenY();
        
        if(event.getClickCount() == 2){
            operacao = ALTERAR;
            showCRUDCliente();
        }
    }
    
    @FXML
    private void acIncluirCliente(ActionEvent event) {
        operacao = INCLUIR;       
        showCRUDCliente();
    }
    
    @FXML
    private void acAlterarCliente(ActionEvent event) {
        Cliente clienteSel = tblVwCliente.getSelectionModel().getSelectedItem();
        
        if (clienteSel != null) {            
            operacao = ALTERAR;
            showCRUDCliente();
        }
    }
    
    @FXML
    private void acExcluirCliente(ActionEvent event) {
        Cliente clienteSel;
        clienteSel = tblVwCliente.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                i18n.getString("msg.confirmacao1.text") + clienteSel.getNome() + "?", ButtonType.YES, ButtonType.CANCEL);
        if(alert.showAndWait().get() == ButtonType.YES){
//            try {
//                
//            } catch (Exception e) {
//            }
            clienteRepository.delete(clienteSel);
            tblVwCliente.getItems().remove(clienteSel);
        }
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAlterar.disableProperty().bind(Bindings.isEmpty(tblVwCliente.getSelectionModel().getSelectedItems()));
        btnExcluir.disableProperty().bind(Bindings.isEmpty(tblVwCliente.getSelectionModel().getSelectedItems()));
        
//        btnConfirmar.disableProperty().bind();
                
        i18n = rb;

        lstClientes = clienteRepository.findAll();

        if (lstClientes != null) {
            tblVwCliente.setItems(FXCollections.observableList(lstClientes));
        } 
    }    
    
}
