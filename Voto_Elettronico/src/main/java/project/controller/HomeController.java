package project.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.Objects;

import dao.UtenteDAO;
import factory.AlertFactory;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import logger.VotoLogger;
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
    void login(ActionEvent event) throws Exception {
    	String cf = codiceFiscale.getText();
    	String ps = psw.getText();
    	if( cf.isEmpty() || ps.isEmpty()) {
    		AlertFactory.getInstance().getSlimAlert(AlertType.INFORMATION, "la password e il codice fiscale non devono essere di lungezza pari a 0").showAndWait();
    	}else {
    		UtenteDAO dao = (UtenteDAO) DAOFactory.getInstance().getUtenteDAO();
    		Utente u =dao.get(cf);
    		MessageDigest digest;
    		String sha1="";
    		digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(ps.getBytes("utf8"));
			sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
    		if(Objects.isNull(u) || !(u.getPassword().equals(sha1))) {
    			AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "il codice fiscale e o la password sono errati").showAndWait();
    		} else if(u.isElettore()) {
    				Elettore elettore= (Elettore) u;
    				VotoLogger.writeToLog("Loggato:"+u.getNome());
    				changeView("/view/accediSessione.fxml",elettore);
    		} else{
    				Scrutinatore scrutinatore = (Scrutinatore) u;
    				VotoLogger.writeToLog("Loggato:"+u.getNome());
    				changeView("/view/scrutinatore.fxml", scrutinatore);
    		}
    	}
    }

    @FXML
    void presenza(ActionEvent event) {
    	changeView("/view/loginPresenza.fxml",null);
    }
    
    @Override
    public void init(Object parameters) {
    	
    }
}


