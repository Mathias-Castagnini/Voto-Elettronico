package project.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import dao.UtenteDAO;
import factory.AlertFactory;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import project.model.Elettore;
import project.model.Scrutinatore;
import project.model.Utente;
import javafx.scene.control.Alert.AlertType;

public class InserisciUtenteController extends Controller{

    @FXML
    private Button bck;

    @FXML
    private TextField c;

    @FXML
    private TextField cf;

    @FXML
    private CheckBox el;

    @FXML
    private Button ins;

    @FXML
    private TextField n;

    @FXML
    private TextField ps;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/gestioneUtente.fxml",null);
    }

    @FXML
    void inserisci(ActionEvent event) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    	String nome= n.getText();
    	String cognome= c.getText();
    	String codice= cf.getText();
    	String password= ps.getText();
    	boolean elettore=el.isSelected();
    	if (nome.isEmpty() || cognome.isEmpty() || codice.isEmpty() || password.isEmpty()) {
    		AlertFactory.getInstance().getSlimAlert(AlertType.INFORMATION, "i valori inseriti non devono essere di lungezza pari a 0").showAndWait();
    	} else {
    		Utente utente;
    		if(elettore) {
    			utente = new Elettore(nome, cognome, codice, password);
    		}else {
    			utente = new Scrutinatore(nome, cognome, codice, password);
    		}
    		UtenteDAO dao = (UtenteDAO) DAOFactory.getInstance().getUtenteDAO();
        	dao.save(utente, password);
    	}
    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

}
