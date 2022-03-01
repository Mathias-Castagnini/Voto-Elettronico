package project.controller;

import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import project.model.Elettore;
import project.model.Scrutinatore;
import project.model.Utente;

public class HomeController extends Controller{

    @FXML
    private Button btn;

    @FXML
    private TextField codiceFiscale;

    @FXML
    private Button pr;

    @FXML
    private PasswordField psw;

    @FXML
    void login(ActionEvent event) {
    	String cf = codiceFiscale.getText();
    	String ps = psw.getText();
    	if( cf.isEmpty() || ps.isEmpty()) {
    		Alert l = new Alert(AlertType.ERROR,"la password e o il codice fiscale inserito sono vuoti");
    		l.setHeaderText(null);
    		l.showAndWait();
    	}
    	//uso dao
    	Utente u= new Scrutinatore("giorgio","ripamonti", cf, ps); 
    	if(Objects.isNull(u)) {
    		Alert n = new Alert(AlertType.ERROR,"I dati dell'utente inserito non esistono.");
    		n.showAndWait();
    	} else if(u.isElettore()) {
    			Elettore e= (Elettore) u;
    			changeView("/view/elettore.fxml",e);
    	} else{
    			Scrutinatore scrutinatore = (Scrutinatore) u;
				changeView("/view/scrutinatore.fxml", scrutinatore);
    	}
    }

    @FXML
    void presenza(ActionEvent event) {
    	changeView("/view/loginPresenza.fxml",null);
    }
    
    @Override
    public void init(Object parameters) {
    	// TODO Auto-generated method stub
    	
    }
}


