package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class InserisciUtenteController extends Controller{

    @FXML
    private Button bck;

    @FXML
    private TextField c;

    @FXML
    private TextField cf;

    @FXML
    private CheckBox el;

    @FXML
    private Button ins;

    @FXML
    private TextField n;

    @FXML
    private TextField ps;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/gestioneUtente.fxml",null);
    }

    @FXML
    void inserisci(ActionEvent event) {

    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

}
