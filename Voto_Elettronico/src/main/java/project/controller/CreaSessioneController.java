package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CreaSessioneController extends Controller{

	 @FXML
	    private Button bck;

	    @FXML
	    private Button crea;

	    @FXML
	    private TextField domanda;

	    @FXML
	    private Button ins;

	    @FXML
	    private ListView<?> listPartiti;

	    @FXML
	    private ListView<?> listPartitiInseriti;

	    @FXML
	    private ComboBox<?> mvittoria;

	    @FXML
	    private ComboBox<?> mvoto;

	    @FXML
	    private Button tg;

	    @FXML
	    void back(ActionEvent event) {
	    	changeView("/view/gestioneSessione.fxml",null);
	    }

	    @FXML
	    void creaSessione(ActionEvent event) {

	    }

	    @FXML
	    void inserisci(ActionEvent event) {

	    }

	    @FXML
	    void togli(ActionEvent event) {

	    }

		@Override
		public void init(Object parameters) {
			// TODO Auto-generated method stub
			
		}

}
