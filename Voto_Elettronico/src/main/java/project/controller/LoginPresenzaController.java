package project.controller;

import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import project.model.Elettore;
import project.model.Scrutinatore;
import project.model.Utente;

public class LoginPresenzaController extends Controller{

    @FXML
    private Button bck;

    @FXML
    private TextField cfe;

    @FXML
    private TextField cfs;

    @FXML
    private Button log;

    @FXML
    private PasswordField psw;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/home.fxml",null);
    }

    @FXML
    void login(ActionEvent event) throws Exception {
    	String cf = cfe.getText();
    	String cfScrutinatore = cfs.getText();
    	String ps = psw.getText();
    	if( cf.isEmpty() || ps.isEmpty() || cfScrutinatore.isEmpty()) {
    		Alert l = new Alert(AlertType.ERROR,"la password e o i codici fiscali inserito sono vuoti");
    		l.setHeaderText(null);
    		l.showAndWait();
    	}
    	//uso dao
    	Utente u= new Scrutinatore("giorgio","ripamonti", cf, ps, "scrutinatore");//scrutinatore che fa accedere
    	if(Objects.isNull(u)) {
    		Alert n = new Alert(AlertType.ERROR,"I dati dell'utente inserito non esistono");
    		n.showAndWait();
    	} else if(u.isElettore()){
    		Alert e = new Alert(AlertType.ERROR, "Devi essere uno scrutinatore per eseguire questa azione.");
			e.showAndWait();
    	}else {
    		//dao per ottenere l'elettore 
    		Utente el= new Elettore("giorgio","ripamonti", cf, ps, "scrutinatore");
    		if (Objects.isNull(el)) {
    			Alert t = new Alert(AlertType.ERROR, "I dati dell'elettore inserito non esistono.");
    			t.showAndWait();
			} else {
				changeView("/view/elettore.fxml", null);
			}
    	}
    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

}
