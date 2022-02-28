package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GestioneCandidatoController extends Controller{

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
    	changeView("/view/scrutinatore.fxml",null);
    }

    @FXML
    void candidate(ActionEvent event) {

    }

    @FXML
    void session(ActionEvent event) {

    }

    @FXML
    void user(ActionEvent event) {

    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

}