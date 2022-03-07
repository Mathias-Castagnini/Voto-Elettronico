package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import project.model.Scrutinatore;

public class ScrutinatoreController extends Controller{

	Scrutinatore l;
    @FXML
    private Button bck;

    @FXML
    private Button cnd;

    @FXML
    private Button sess;

    @FXML
    private Button usr;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/home.fxml",null);
    }

    @FXML
    void candidate(ActionEvent event) {
    	changeView("/view/gestioneCandidati.fxml",l);
    }

    @FXML
    void session(ActionEvent event) {
    	changeView("/view/gestioneSessione.fxml",l);
    }

    @FXML
    void user(ActionEvent event) {
    	changeView("/view/gestioneUtente.fxml",l);
    }

	@Override
	public void init(Object parameters) {
		l = (Scrutinatore) parameters;	
	}

}
