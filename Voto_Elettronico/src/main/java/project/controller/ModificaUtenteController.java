package project.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.UtenteDAO;
import factory.AlertFactory;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import logger.VotoLogger;
import project.model.Elettore;
import project.model.Scrutinatore;
import project.model.Utente;

public class ModificaUtenteController extends Controller implements Initializable{

	Scrutinatore l;
    @FXML
    private Button bck;

    @FXML
    private TextField c;

    @FXML
    private TextField cf;

    @FXML
    private CheckBox el;

    @FXML
    private ListView<Utente> listUtenti;

    @FXML
    private Button mod;

    @FXML
    private TextField n;

    @FXML
    private PasswordField ps;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/gestioneUtente.fxml",l);
    }

    @FXML
    void modifica(ActionEvent event) {
    	String nome= n.getText();
    	String cognome= c.getText();
    	String password= ps.getText();
    	boolean elettore=el.isSelected();
    	if (nome.isEmpty() || cognome.isEmpty() || password.isEmpty()) {
    		AlertFactory.getInstance().getSlimAlert(AlertType.INFORMATION, "i valori inseriti non devono essere di lungezza pari a 0").showAndWait();
    	} else {
    		UtenteDAO dao = (UtenteDAO) DAOFactory.getInstance().getUtenteDAO();
        	Utente u = listUtenti.getSelectionModel().getSelectedItem();
    		if(elettore) {
    			dao.update(u, nome, cognome, u.getCod_fiscale(), password, "elettore");
    			VotoLogger.writeToLog("Utente "+u.getCod_fiscale()+" modificato correttamente");
    			changeView("/view/scrutinatore.fxml",l);
    		}else {
    			dao.update(u, nome, cognome, u.getCod_fiscale(), password, "scrutinatore");
    			VotoLogger.writeToLog("Utente "+u.getCod_fiscale()+" modificato correttamente");
    		}      	
    	}
    }

    @FXML
    void selected(MouseEvent event) {
    }

	@Override
	public void init(Object parameters) {
		l = (Scrutinatore) parameters;	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Utente> l = DAOFactory.getInstance().getUtenteDAO().getAll();
		listUtenti.setCellFactory(new Callback<ListView<Utente>, ListCell<Utente>>(){
			
			@Override
		    public ListCell<Utente> call(ListView<Utente> list) {
		        ListCell<Utente> cell = new ListCell<Utente>() {
		            @Override
		            public void updateItem(Utente item, boolean empty) {
		                super.updateItem(item, empty);
		                if(item!= null) {
		                	setText(item.getCod_fiscale());
		                }else {
		                	setText(null);
		                }
		            }
		        };

		        return cell;
		    }
		});
		listUtenti.getItems().addAll(l);
	}

}
