package view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Jogador;

public class CadJogadorController implements Initializable {

    @FXML
    Label lblCadJogador;

    @FXML
    Button btnConfirmar;
    @FXML
    Button btnCancelar;

    @FXML
    TextField txtFldNomeJogador;
    @FXML
    TextField txtFldPosicaoJogador;
    @FXML
    TextField txtFldNumJogador;

    Jogador jogador;
    String nomeJogador;
    Stage stageCad;
    private ObservableList<Jogador> lstObsJogadores;
    boolean incluir = false;
    boolean alterar = false;

    public void setJogador(Jogador jogadorSel, String bot, ObservableList lstObsJogadores, Stage stageCad) {

        this.jogador = jogadorSel;
        this.stageCad = stageCad;

        if (!lstObsJogadores.isEmpty()) {
            nomeJogador = jogador.getNome();
        }

        this.lstObsJogadores = lstObsJogadores;

        if (bot.equals("alterar")) {
            incluir = false;

            txtFldNomeJogador.setText(nomeJogador);
            txtFldPosicaoJogador.setText(jogador.getPosicao());
            txtFldNumJogador.setText(String.valueOf(jogador.getNumCam()));

            lblCadJogador.setText("Altere as informações do jogador selecionado:");
            alterar = true;

        } else {
            alterar = false;

            lblCadJogador.setText("Adicione um novo jogador ao time:");
            incluir = true;
        }

    }

    @FXML
    private void btnConfirmarClick(ActionEvent event) {
        if (incluir) {
            try {
                listener();
                if (txtFldNomeJogador.getText().isEmpty() || txtFldNumJogador.getText().isEmpty() || txtFldPosicaoJogador.getText().isEmpty()) {
            
                    if (txtFldNomeJogador.getText().isEmpty()) {
                        txtFldNomeJogador.setStyle("-fx-border-color: Red");
                    } 
                    if (txtFldNumJogador.getText().isEmpty()) {
                        txtFldNumJogador.setStyle("-fx-border-color: Red");
                    } 
                    if (txtFldPosicaoJogador.getText().isEmpty()) {
                        txtFldPosicaoJogador.setStyle("-fx-border-color: Red");
                    }
                    return;
                } else {
                    lstObsJogadores.add(new Jogador(txtFldNomeJogador.getText(), txtFldPosicaoJogador.getText(), Integer.parseInt(txtFldNumJogador.getText())));
                    stageCad.close();
                }

            } catch (Exception e) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, e);
            }
            incluir = false;
        } else if (alterar) {
            try {
                if (txtFldNomeJogador.getText().isEmpty() || txtFldNumJogador.getText().isEmpty() || txtFldPosicaoJogador.getText().isEmpty()) {
                    
                    if (txtFldNomeJogador.getText().isEmpty()) {
                        txtFldNomeJogador.setStyle("-fx-border-color: Red");
                    } 
                    if (txtFldNumJogador.getText().isEmpty()) {
                        txtFldNumJogador.setStyle("-fx-border-color: Red");
                    } 
                    if (txtFldPosicaoJogador.getText().isEmpty()) {
                        txtFldPosicaoJogador.setStyle("-fx-border-color: Red");
                    }
                    return;
                } else {
                    lstObsJogadores.remove(jogador);
                    lstObsJogadores.add(new Jogador(txtFldNomeJogador.getText(), txtFldPosicaoJogador.getText(), Integer.parseInt(txtFldNumJogador.getText())));
                }
                stageCad.close();

            } catch (Exception e) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, e);
            }
            alterar = false;
        }
    }

    @FXML
    private void btnCancelarClick(ActionEvent event) {
        stageCad.close();
    }

    public void listener() {
        txtFldNomeJogador.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                txtFldNomeJogador.setStyle("-fx-border-color: Red");
            } else {
                txtFldNomeJogador.setStyle("");
            }
        });
        txtFldNumJogador.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                txtFldNumJogador.setStyle("-fx-border-color: Red");
            } else {
                txtFldNumJogador.setStyle("");
            }
        });
        txtFldPosicaoJogador.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                txtFldPosicaoJogador.setStyle("-fx-border-color: Red");
            } else {
                txtFldPosicaoJogador.setStyle("");
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listener();
    }
}
