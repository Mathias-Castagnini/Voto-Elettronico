package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ScrutinatoreController extends Controller{

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
    	//changeView("/view/candidato.fxml",null);
    }

    @FXML
    void session(ActionEvent event) {
    	//changeView("/view/session.fxml",null);
    }

    @FXML
    void user(ActionEvent event) {
    	changeView("/view/gestioneUtente.fxml",null);
    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

}
