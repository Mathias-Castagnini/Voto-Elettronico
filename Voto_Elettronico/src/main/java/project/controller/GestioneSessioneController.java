package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import logger.VotoLogger;
import project.model.Scrutinatore;

public class GestioneSessioneController extends Controller{

	Scrutinatore log;
	@FXML
    private Button bck;

    @FXML
    private Button el;

    @FXML
    private Button ins;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/scrutinatore.fxml",log);
    }

    @FXML
    void elimina(ActionEvent event) {
    	changeView("/view/terminaSessione.fxml",log);
    }

    @FXML
    void inserisci(ActionEvent event) {
    	changeView("/view/creaSessione.fxml",log);
    }

	@Override
	public void init(Object parameters) {
		log=(Scrutinatore) parameters;
	}

}