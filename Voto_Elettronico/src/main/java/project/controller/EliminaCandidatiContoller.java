package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class EliminaCandidatiContoller extends Controller{

    @FXML
    private Button bck;

    @FXML
    private Button elc;

    @FXML
    private Button elp;

    @FXML
    private ListView<?> listCandidati;

    @FXML
    private ListView<?> listPartiti;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/gestioneCandidati.fxml",null);
    }

    @FXML
    void eliminaCandidato(ActionEvent event) {
    	
    }

    @FXML
    void eliminaPartito(ActionEvent event) {

    }

    @FXML
    void selectedCandidato(MouseEvent event) {

    }

    @FXML
    void selectedPartito(MouseEvent event) {

    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

}
