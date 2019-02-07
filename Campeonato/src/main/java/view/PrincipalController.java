package view;

import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.Game;
import model.Time;
import utilit.Dados;
import java.awt.Desktop;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;

import javafx.stage.Stage;

public class PrincipalController implements Initializable {

    @FXML
    Button btnAbrir;

    @FXML
    Button btnJogos;
    
    @FXML
    Label lblTime;
    
    @FXML
    Label labelQtd;
    
    @FXML
    Button btnFechar;

    @FXML
    StackPane pnGames;

    @FXML
    TableView tblVwTimes;

    @FXML
    TableView tblVwGames;

    @FXML
    TableColumn colCla;

    @FXML
    TableColumn colNome;

    @FXML
    TableColumn colPontos;

    @FXML
    TableColumn colVit;

    @FXML
    TableColumn colEmp;

    @FXML
    TableColumn colDer;

    @FXML
    TableColumn colGP;

    @FXML
    TableColumn colGN;

    @FXML
    TableColumn colSG;

    @FXML
    TableColumn colTimeA;

    @FXML
    TableColumn colTimeB;

    @FXML
    TableColumn colGolA;

    @FXML
    TableColumn colGolB;

    @FXML
    RadioButton rbJogosCasa;

    @FXML
    RadioButton rbVitCasa;

    @FXML
    RadioButton rbEmpCasa;

    @FXML
    RadioButton rbDerCasa;

    private Dados dados;
    private List<Time> times;
    List<Time> lstGamesTmp = new ArrayList<>();
    private ObservableList<Time> lstObsTimes;
    private ObservableList<Game> lstObsGames;
    private List<Game> gameTmp = new ArrayList<>();

    int i = 0;

    //mostra o painel e preenche com os jogos
    private void mostraPainel() {
        Time timeSel = (Time) tblVwTimes.getSelectionModel().getSelectedItem(); //mostra o selecionado no label

        labelQtd.setText(String.valueOf(timeSel.getGames().size()));
        pnGames.setVisible(true);
        Platform.runLater(tblVwGames::requestFocus);

        lstObsGames = FXCollections.observableList(timeSel.getGames());
        tblVwGames.setItems(lstObsGames);
        tblVwGames.getSelectionModel().selectFirst();
        lblTime.setText(timeSel.getNome());

        colTimeA.setCellValueFactory(new PropertyValueFactory<Game, String>("TimeA"));
        colGolA.setCellValueFactory(new PropertyValueFactory<Game, String>("GolA"));
        colTimeB.setCellValueFactory(new PropertyValueFactory<Game, String>("TimeB"));
        colGolB.setCellValueFactory(new PropertyValueFactory<Game, String>("GolB"));
        //rbVitCasa.setSelected(true);
        //rbEmpCasa.setSelected(true);
    }

    //oculta o painel
    private void ocultaPainel() {
        rbVitCasa.setSelected(false);
        rbEmpCasa.setSelected(false);
        rbJogosCasa.setSelected(false);
        rbDerCasa.setSelected(false);
        pnGames.setVisible(false);
        Platform.runLater(tblVwTimes::requestFocus);
    }

    @FXML
    private void btnAbrirClick(ActionEvent event) {
        if (i > 0) {
            gameTmp.clear();
            times.clear();
            lstGamesTmp.clear();
            lstObsTimes.clear();
            if(lstObsGames != null){
                lstObsGames.clear();
            }          
        }

        final Stage stage = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escolha o arquivo com os jogos");

        dados = new Dados(String.valueOf(fileChooser.showOpenDialog(stage)));
        times = dados.ler();

        inicializa();
        i++;
    }

    //mostra o painel quando pressiona enter
    @FXML
    private void keyPressedTblVwTimes(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            mostraPainel();
        }
        if (event.getCode() == KeyCode.ESCAPE) {
            exit();
        }
    }

    //mostra o painel quando clica 2x
    @FXML
    private void onClickTblVwTimes(MouseEvent event) {
        if (event.getClickCount() == 2) {
            //mostraPainel();
            try {
                Time timeSel = (Time) tblVwTimes.getSelectionModel().getSelectedItem();
                
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/FormJogador.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/Styles.css");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable (false);
                stage.setScene (scene);
                stage.getIcons().add(new Image("/fxml/icon.png"));
                stage.setTitle("Jogadores");
                
                FormJogadorController formJogadorController = loader.getController();
                formJogadorController.setTime(timeSel);
                
                stage.showAndWait();
                
            } catch (IOException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
    }

    //oculta o painel quando pressiona esc
    @FXML
    private void keyPressedTblVwGames(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            ocultaPainel();
        }
    }

    //oculta o painel quando clica no botÃ£o fechar.
    @FXML
    private void btnFecharClick(ActionEvent event) {
        ocultaPainel();
    }

    //sai do programa
    private void exit() {
        System.exit(0);
    }
    
    @FXML
    private void btnJogosClick(ActionEvent event) {
        mostraPainel();
    }
    
    //quando selecionar: derrotas em casa desmarca todas outras opÃ§Ãµes. 
    @FXML
    private void rdDerCasaAction(ActionEvent event) {
        rbVitCasa.setSelected(false);
        rbEmpCasa.setSelected(false);
        rbJogosCasa.setSelected(false);

        Time timeSelct = (Time) tblVwTimes.getSelectionModel().getSelectedItem();

        gameTmp.clear();

        for (Game g : timeSelct.getGames()) {
            if (timeSelct.getNome().equals(g.getTimeA()) && g.getGolA() < g.getGolB()) {
                gameTmp.add(g);
            }
        }

        labelQtd.setText(String.valueOf(gameTmp.size()));

        lstObsGames = FXCollections.observableList(gameTmp);
        tblVwGames.setItems(lstObsGames);
        tblVwGames.getSelectionModel().selectFirst();
    }

    //quando selecionar: jogo em casa desmarca todas outras opÃ§Ãµes.
    @FXML
    private void rbJogosCasaAction(ActionEvent event) {
        rbVitCasa.setSelected(false);
        rbEmpCasa.setSelected(false);
        rbDerCasa.setSelected(false);

        Time timeSelct = (Time) tblVwTimes.getSelectionModel().getSelectedItem();

        gameTmp.clear();

        for (Game g : timeSelct.getGames()) {
            if (timeSelct.getNome().equals(g.getTimeA())) {
                gameTmp.add(g);
            }
        }

        labelQtd.setText(String.valueOf(gameTmp.size()));

        lstObsGames = FXCollections.observableList(gameTmp);
        tblVwGames.setItems(lstObsGames);
        tblVwGames.getSelectionModel().selectFirst();

    }
    //System.out.println(gameTmpLn);

//quando selecionar: vitorias em casa desmarca todas outras opÃ§Ãµes.
    @FXML
    private void rbVitCasaAction(ActionEvent event) {
        rbJogosCasa.setSelected(false);
        rbEmpCasa.setSelected(false);
        rbDerCasa.setSelected(false);

        Time timeSelct = (Time) tblVwTimes.getSelectionModel().getSelectedItem();

        gameTmp.clear();

        for (Game g : timeSelct.getGames()) {
            if (timeSelct.getNome().equals(g.getTimeA()) && g.getGolA() > g.getGolB()) {
                gameTmp.add(g);
            }
        }

        labelQtd.setText(String.valueOf(gameTmp.size()));

        lstObsGames = FXCollections.observableList(gameTmp);
        tblVwGames.setItems(lstObsGames);
        tblVwGames.getSelectionModel().selectFirst();
    }

    //quando selecionar: empates em casa desmarca todas outras opÃ§Ãµes.
    @FXML
    private void rbEmpCasaAction(ActionEvent event) {
        rbVitCasa.setSelected(false);
        rbJogosCasa.setSelected(false);
        rbDerCasa.setSelected(false);

        Time timeSelct = (Time) tblVwTimes.getSelectionModel().getSelectedItem();

        gameTmp.clear();

        for (Game g : timeSelct.getGames()) {
            if (timeSelct.getNome().equals(g.getTimeA()) && g.getGolA() == g.getGolB()) {
                gameTmp.add(g);
            }
        }

        labelQtd.setText(String.valueOf(gameTmp.size()));

        lstObsGames = FXCollections.observableList(gameTmp);
        tblVwGames.setItems(lstObsGames);
        tblVwGames.getSelectionModel().selectFirst();
    }

    private void inicializa() {
        lstObsTimes = FXCollections.observableList(times);
        tblVwTimes.setItems(lstObsTimes);
        tblVwTimes.getSelectionModel().selectFirst();

        //adiciona as linhas de jogos.
        colCla.setCellValueFactory(new PropertyValueFactory<Time, String>("Cla"));
        colNome.setCellValueFactory(new PropertyValueFactory<Time, String>("Nome"));
        colPontos.setCellValueFactory(new PropertyValueFactory<Time, String>("Pontos"));
        colVit.setCellValueFactory(new PropertyValueFactory<Time, String>("Vit"));
        colEmp.setCellValueFactory(new PropertyValueFactory<Time, String>("Emp"));
        colDer.setCellValueFactory(new PropertyValueFactory<Time, String>("Der"));
        colGP.setCellValueFactory(new PropertyValueFactory<Time, String>("GolP"));
        colGN.setCellValueFactory(new PropertyValueFactory<Time, String>("GolN"));
        colSG.setCellValueFactory(new PropertyValueFactory<Time, String>("SaldoGols"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pnGames.setVisible(false);

    }

    
}
