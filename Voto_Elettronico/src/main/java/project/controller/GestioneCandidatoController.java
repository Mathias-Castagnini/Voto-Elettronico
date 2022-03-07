package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import project.model.Scrutinatore;

public class GestioneCandidatoController extends Controller{

	Scrutinatore log;
    @FXML
    private Button bck;

    @FXML
    private Button el;

    @FXML
    private Button ins;

    @FXML
    private Button mod;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/scrutinatore.fxml",log);
    }

    @FXML
    void elimina(ActionEvent event) {
    	changeView("/view/eliminaCandidati.fxml",log);
    }

    @FXML
    void inserisci(ActionEvent event) {
    	changeView("/view/inserisciPartito.fxml",log);
    }

	@Override
	public void init(Object parameters) {
		log=(Scrutinatore) parameters;
	}

}