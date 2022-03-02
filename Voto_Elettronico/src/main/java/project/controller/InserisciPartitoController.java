package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class InserisciPartitoController extends Controller{

    @FXML
    private Button bck;

    @FXML
    private Button cand;

    @FXML
    private Button ins;

    @FXML
    private ListView<?> listPartiti;

    @FXML
    private TextField n;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/gestioneCandidati.fxml",null);
    }

    @FXML
    void inserisci(ActionEvent event) {

    }

    @FXML
    void redirectCandidati(ActionEvent event) {
    	changeView("/view/inserisciCandidato.fxml",null);
    }

    @FXML
    void selected(MouseEvent event) {

    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

}
