package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController extends Controller{

    @FXML
    private Button back;

    @FXML
    private Button btn;

    @FXML
    private TextField cf;

    @FXML
    private TextField cognome;

    @FXML
    private TextField nome;

    @FXML
    private PasswordField psw;

    @FXML
    private CheckBox scr;

    @FXML
    void login(ActionEvent event) {

    }
    
    @FXML
    void back(ActionEvent event) {
    	changeView("/view/home.fxml",null);
    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

}


