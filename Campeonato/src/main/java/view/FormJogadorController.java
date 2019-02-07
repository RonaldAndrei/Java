package view;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Game;
import model.Jogador;
import model.Time;

public class FormJogadorController implements Initializable {

    private Time time;
    private List<Jogador> lstJogadores = new ArrayList<>();
    private ObservableList<Jogador> lstObsJogadores;
    private int selectedIndex;
    private BufferedReader br = null;

    Jogador lstJogTmp;
    String nomeArquivo;
    String caminho = "C:\\Users\\ronald\\Documents\\UEPG\\2º Ano\\Linguagens de Programação\\Aulas_Java\\Campeonato\\src\\main\\resources\\times\\";
    String nome, posicao;
    int numCam;

    @FXML
    Label lblNmTime;

    @FXML
    TableColumn colNome;
    @FXML
    TableColumn colPos;
    @FXML
    TableColumn colNum;

    @FXML
    Button btnExcluir;
    @FXML
    Button btnGravar;
    @FXML
    Button btnIncluir;
    @FXML
    Button btnAlterar;

    @FXML
    TableView tblVwJogadores;

    public void setTime(Time time) {
        this.time = time;
        lblNmTime.setText(time.getNome());
        nomeArquivo = time.getNome();
        mostraJog();
    }

    @FXML
    private void btnExcluirClick(ActionEvent event) {
        selectedIndex = tblVwJogadores.getSelectionModel().getSelectedIndex();

        if (selectedIndex != -1) {
            tblVwJogadores.getItems().remove(selectedIndex);
        }
    }

    public void gravar() {
        String nomeArquivo = time.getNome();
        File file = new File(caminho + nomeArquivo + ".txt");

        try {
            file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            String jogDados;

            for (Jogador j : lstJogadores) {
                jogDados = j.getNome() + ";" + j.getNumCam() + ";" + j.getPosicao();
                bw.write(jogDados + "\n");
            }

            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void btnGravarClick(ActionEvent event) {
        gravar();
    }

    public void mostraCad(String bot, ObservableList lstObsJog) {
        try {
            Jogador jogadorSel = (Jogador) tblVwJogadores.getSelectionModel().getSelectedItem();

            Stage stageCad = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/CadJogador.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            stageCad.setScene(scene);
            stageCad.initModality(Modality.APPLICATION_MODAL);
            stageCad.setResizable(false);
            stageCad.setScene(scene);
            stageCad.getIcons().add(new Image("/fxml/icon.png"));
            stageCad.setTitle("Cadastro de Jogadores");
            
            scene.getWindow().setOnCloseRequest((WindowEvent we) -> {
                System.out.println("Janela esta fechando");
            });
            
            CadJogadorController formCadastroJogadorController = loader.getController();
            formCadastroJogadorController.setJogador(jogadorSel, bot, lstObsJog, stageCad);
            stageCad.showAndWait();
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnIncluirClick(ActionEvent event) {
        if (lstObsJogadores.isEmpty()) {
            String nomeTime = time.getNome();
            File file = new File(caminho + nomeArquivo + ".txt");
            try {
                String linha;

                file.createNewFile();
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.close();

            } catch (IOException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String incluir = "incluir";
        mostraCad(incluir, lstObsJogadores);
    }

    public void btnAlterarClick(ActionEvent event) {
        String alterar = "alterar";
        mostraCad(alterar, lstObsJogadores);
    }

    public void mostraJog() {
        String[] dadoJog;
        String linhaLida;

        try {
            BufferedReader br = new BufferedReader(new FileReader(caminho + nomeArquivo + ".txt"));

            while ((linhaLida = br.readLine()) != null) {
                dadoJog = linhaLida.split(";");

                nome = dadoJog[0];
                numCam = Integer.parseInt(dadoJog[1]);
                posicao = dadoJog[2];

                lstJogTmp = new Jogador(nome, posicao, numCam);
                lstJogadores.add(lstJogTmp);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
            }
        }

        Platform.runLater(tblVwJogadores::requestFocus);

        lstObsJogadores = FXCollections.observableList(lstJogadores);
        tblVwJogadores.setItems(lstObsJogadores);
        tblVwJogadores.getSelectionModel().selectFirst();

        colNome.setCellValueFactory(new PropertyValueFactory<Game, String>("nome"));
        colPos.setCellValueFactory(new PropertyValueFactory<Game, String>("posicao"));
        colNum.setCellValueFactory(new PropertyValueFactory<Game, String>("numCam"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
