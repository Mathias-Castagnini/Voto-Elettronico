package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GestioneUtenteController extends Controller{

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
    	changeView("/view/scrutinatore.fxml",null);
    }

    @FXML
    void elimina(ActionEvent event) {

    }

    @FXML
    void inserisci(ActionEvent event) {
    	changeView("/view/inserisciUtente.fxml",null);
    }

    @FXML
    void modifica(ActionEvent event) {

    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

}
