package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class EliminaUtenteController extends Controller{

    @FXML
    private Button bck;

    @FXML
    private Button el;

    @FXML
    private ListView<?> listUtenti;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/gestioneUtente.fxml",null);
    }

    @FXML
    void elimina(ActionEvent event) {

    }

    @FXML
    void selected(MouseEvent event) {

    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

}
