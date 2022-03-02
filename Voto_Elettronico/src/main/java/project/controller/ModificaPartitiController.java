package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ModificaPartitiController extends Controller{

    @FXML
    private Button bck;

    @FXML
    private TextField cc;

    @FXML
    private ListView<?> listCandidati;

    @FXML
    private ListView<?> listPartiti;

    @FXML
    private Button mip;

    @FXML
    private Button mlc;

    @FXML
    private TextField nc;

    @FXML
    private TextField np;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/gestioneCandidati.fxml",null);
    }

    @FXML
    void modificaCandidato(ActionEvent event) {

    }

    @FXML
    void modificaPartito(ActionEvent event) {

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
