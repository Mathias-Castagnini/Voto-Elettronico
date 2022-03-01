package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ModificaUtenteController extends Controller{

    @FXML
    private Button bck;

    @FXML
    private TextField c;

    @FXML
    private TextField cf;

    @FXML
    private CheckBox el;

    @FXML
    private ListView<?> listUtenti;

    @FXML
    private Button mod;

    @FXML
    private TextField n;

    @FXML
    private PasswordField ps;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/gestioneUtente.fxml",null);
    }

    @FXML
    void modifica(ActionEvent event) {

    }

    @FXML
    void selected(MouseEvent event) {

    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

}
