package project.controller;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import dao.UtenteDAO;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import logger.VotoLogger;
import project.model.Scrutinatore;
import project.model.Utente;

public class EliminaUtenteController extends Controller implements Initializable{

	Scrutinatore l;
	
    @FXML
    private Button bck;

    @FXML
    private Button el;

    @FXML
    private ListView<Utente> listUtenti;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/gestioneUtente.fxml",l);
    }

    @FXML
    void elimina(ActionEvent event) {
    	UtenteDAO dao = (UtenteDAO) DAOFactory.getInstance().getUtenteDAO();
    	Utente u = listUtenti.getSelectionModel().getSelectedItem();
    	if(!Objects.isNull(u)) {
    		dao.delete(u);
    		boolean elimina = listUtenti.getItems().remove(u);
    		if(elimina) {
    			VotoLogger.writeToLog("L'utente "+u.getCod_fiscale()+" e' stato eliminato da "+l.getCod_fiscale() );
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
