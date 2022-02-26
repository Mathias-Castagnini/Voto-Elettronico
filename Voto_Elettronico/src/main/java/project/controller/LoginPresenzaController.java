package project.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class LoginPresenzaController extends Controller{

    @FXML
    private Button bck;

    @FXML
    private Button log;

    @FXML
    private PasswordField psw;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/home.fxml",null);
    }

    @FXML
    void login(ActionEvent event) {

    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

}
