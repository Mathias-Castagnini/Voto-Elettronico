package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import project.model.Scrutinatore;

public class GestioneUtenteController extends Controller{

	Scrutinatore l;
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
    	changeView("/view/scrutinatore.fxml",l);
    }

    @FXML
    void elimina(ActionEvent event) {
    	changeView("/view/eliminaUtente.fxml",l);
    }

    @FXML
    void inserisci(ActionEvent event) {
    	changeView("/view/inserisciUtente.fxml",l);
    }

    @FXML
    void modifica(ActionEvent event) {
    	changeView("/view/modificaUtente.fxml",l);
    }

	@Override
	public void init(Object parameters) {
		l = (Scrutinatore) parameters;
	}

}
