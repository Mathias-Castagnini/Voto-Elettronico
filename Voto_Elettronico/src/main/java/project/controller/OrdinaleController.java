package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import project.model.Candidato;
import project.model.Elettore;
import project.model.Partito;

public class OrdinaleController extends Controller{

	Elettore log;
    @FXML
    private ListView<Candidato> listCandidati;

    @FXML
    private ListView<Partito> listPartiti;

    @FXML
    private Button v;

    @FXML
    void selezionaCandidato(MouseEvent event) {

    }

    @FXML
    void selezionaPartito(MouseEvent event) {

    }

    @FXML
    void vota(ActionEvent event) {

    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

}
