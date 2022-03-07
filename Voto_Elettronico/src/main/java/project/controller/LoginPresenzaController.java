package project.controller;

import java.util.Objects;

import dao.UtenteDAO;
import factory.AlertFactory;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import logger.VotoLogger;
import project.model.Elettore;
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
    		AlertFactory.getInstance().getSlimAlert(AlertType.INFORMATION, "la password e il codice fiscale non devono essere di lungezza pari a 0").showAndWait();
    	}else {
    		UtenteDAO dao = (UtenteDAO) DAOFactory.getInstance().getUtenteDAO();
    		Utente u =dao.get(cfScrutinatore);
    		System.out.println(u);
    		if(Objects.isNull(u)) {
    			AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "il codice fiscale e o la password sono errati").showAndWait();
    		} else if(u.isElettore()){
    			AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "Devi essere uno scrutinatore per eseguire questa azione.").showAndWait();
    		}else {
    			Elettore el = (Elettore) dao.get(cf);
    			if (Objects.isNull(el)) {
    				AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "il codice fiscale e o la password sono errati").showAndWait();
    			} else {
    				VotoLogger.writeToLog("Loggato:"+u.getNome());
    				changeView("/view/AccediSessioneController.fxml", el);
    			}
    		}
    	}
    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

}
