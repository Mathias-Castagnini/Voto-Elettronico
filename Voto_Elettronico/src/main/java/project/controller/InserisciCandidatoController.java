package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InserisciCandidatoController extends Controller{

    @FXML
    private Button bck;

    @FXML
    private TextField c;

    @FXML
    private Button ins;

    @FXML
    private TextField n;

    @FXML
    private TextField partito;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/inserisciPartito.fxml",null);
    }

    @FXML
    void inserisci(ActionEvent event) {

    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

}
