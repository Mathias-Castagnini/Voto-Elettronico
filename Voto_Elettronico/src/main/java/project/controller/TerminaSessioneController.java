package project.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.SessioneDAO;
import dao.VotoDAO;
import factory.AlertFactory;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import logger.VotoLogger;
import project.model.Concorrente;
import project.model.Scrutinatore;
import project.model.Sessione;

public class TerminaSessioneController extends Controller implements Initializable{

	Scrutinatore log;
	@FXML
    private Button bck;

    @FXML
    private ListView<Sessione> listAttive;

    @FXML
    private Button termina;

    @FXML
    private ListView<Sessione> terminate;

    @FXML
    private Button visualizza;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/gestioneSessione.fxml",log);
    }

    @FXML
    void selectedAttiva(MouseEvent event) {

    }

    @FXML
    void selectedTerminate(MouseEvent event) {

    }

    @FXML
    void terminaSessione(ActionEvent event) {
    	Sessione s =listAttive.getSelectionModel().getSelectedItem();
    	if(s!= null) {
    		SessioneDAO dao = (SessioneDAO) DAOFactory.getInstance().getSessioneDAO();
    		dao.close(s.getId());
    		VotoLogger.writeToLog("Sessione "+s.getId()+" terminata correttamente");
    		changeView("/view/scrutinatore.fxml", log);
    		
    	}else {
    		AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "Seleziona una sessione da terminare").showAndWait();
    	}
    }

    @FXML
    void visualizzaRisutlato(ActionEvent event) {
    	Sessione s =terminate.getSelectionModel().getSelectedItem();
    	if(s!=null) {
    		VotoDAO dao = (VotoDAO) DAOFactory.getInstance().getVotoDAO();
    		Concorrente c=dao.esitoSessione(s.getId());
    		AlertFactory.getInstance().getSlimAlert(AlertType.INFORMATION,"il vincitore e'"+c.getNome()).showAndWait();
    	}else {
    		AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "Seleziona una sessione da terminare").showAndWait();
    	}
    }

	@Override
	public void init(Object parameters) {
		log=(Scrutinatore) parameters;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listAttive.setCellFactory(new Callback<ListView<Sessione>, ListCell<Sessione>>() {

		    @Override
		    public ListCell<Sessione> call(ListView<Sessione> list) {
		        ListCell<Sessione> cell = new ListCell<Sessione>() {
		            @Override
		            public void updateItem(Sessione item, boolean empty) {
		                super.updateItem(item, empty);
		                if(item!= null) {
		                	setText(item.getId()+" "+item.getTipologia());
		                }else {
		                	setText(null);
		                }
		            }
		        };

		        return cell;
		    }
		});
		
		terminate.setCellFactory(new Callback<ListView<Sessione>, ListCell<Sessione>>() {

		    @Override
		    public ListCell<Sessione> call(ListView<Sessione> list) {
		        ListCell<Sessione> cell = new ListCell<Sessione>() {
		            @Override
		            public void updateItem(Sessione item, boolean empty) {
		                super.updateItem(item, empty);
		                if(item!= null) {
		                	setText(item.getId()+" "+item.getTipologia());
		                }else {
		                	setText(null);
		                }
		            }
		        };

		        return cell;
		    }
		});
		
		SessioneDAO dao = (SessioneDAO) DAOFactory.getInstance().getSessioneDAO();
		List<Sessione> l=dao.getAll();
		for (Sessione sessione : l) {
			if(sessione.getStato()) {
				listAttive.getItems().add(sessione);
			}else {
				terminate.getItems().add(sessione);
			}
		}	
	}

}
