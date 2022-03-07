package project.controller;

import dao.ConcorrenteDAO;
import dao.UtenteDAO;
import factory.AlertFactory;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import logger.VotoLogger;
import project.model.Concorrente;
import project.model.Elettore;
import project.model.Partito;
import project.model.Scrutinatore;
import project.model.Utente;

public class InserisciPartitoController extends Controller{

	Scrutinatore log;
    @FXML
    private Button bck;

    @FXML
    private Button cand;

    @FXML
    private Button ins;

    @FXML
    private TextField n;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/gestioneCandidati.fxml",log);
    }

    @FXML
    void inserisci(ActionEvent event) {
    	String nome= n.getText();
    	if (nome.isEmpty()) {
    		AlertFactory.getInstance().getSlimAlert(AlertType.INFORMATION, "il nome del partito inserito non deve essere di lungezza pari a 0").showAndWait();
    	} else {
    		Concorrente p = new Partito(nome);
    		ConcorrenteDAO dao = (ConcorrenteDAO) DAOFactory.getInstance().getConcorrenteDAO();
    		dao.addPartito((Partito)p);
    		VotoLogger.writeToLog("partito inserito correttamente");
    	}
    }

    @FXML
    void redirectCandidati(ActionEvent event) {
    	changeView("/view/inserisciCandidato.fxml",log);
    }

    @FXML
    void selected(MouseEvent event) {

    }

	@Override
	public void init(Object parameters) {
		log=(Scrutinatore) parameters;
	}

}
