package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class TerminaSessioneController extends Controller{

	@FXML
    private Button bck;

    @FXML
    private ListView<?> listAttive;

    @FXML
    private Button termina;

    @FXML
    private ListView<?> terminate;

    @FXML
    private Button visualizza;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/gestioneSessione.fxml",null);
    }

    @FXML
    void selectedAttiva(MouseEvent event) {

    }

    @FXML
    void selectedTerminate(MouseEvent event) {

    }

    @FXML
    void terminaSessione(ActionEvent event) {

    }

    @FXML
    void visualizzaRisutlato(ActionEvent event) {

    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

}
