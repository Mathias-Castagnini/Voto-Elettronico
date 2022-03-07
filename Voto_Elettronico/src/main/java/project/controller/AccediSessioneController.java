package project.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.ConcorrenteDAO;
import dao.SessioneDAO;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import project.model.Elettore;
import project.model.Sessione;

public class AccediSessioneController extends Controller implements Initializable{

	Elettore log;
    @FXML
    private Button bck;

    @FXML
    private ListView<Sessione> listSessioni;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/home.fxml",null);
    }

    @FXML
    void vota(ActionEvent event) {
    	
    }

	@Override
	public void init(Object parameters) {
		log=(Elettore) parameters;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listSessioni.setCellFactory(new Callback<ListView<Sessione>, ListCell<Sessione>>() {

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
			if (sessione.getStato()) {
				listSessioni.getItems().add(sessione);
			}
		}		
	}

}
