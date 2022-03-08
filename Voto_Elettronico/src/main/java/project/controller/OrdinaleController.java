package project.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.ConcorrenteDAO;
import dao.SessioneDAO;
import factory.AlertFactory;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import project.model.Candidato;
import project.model.Concorrente;
import project.model.Elettore;
import project.model.Partito;
import project.model.Sessione;

public class OrdinaleController extends Controller{

	Elettore log;
	Sessione s;
    @FXML
    private ListView<Candidato> listCandidati;

    @FXML
    private ListView<Partito> listPartiti;

    @FXML
    private Button v;

    @FXML
    void selezionaCandidato(MouseEvent event) {
    	ConcorrenteDAO dao=(ConcorrenteDAO) DAOFactory.getInstance().getConcorrenteDAO();
    	Candidato p = listCandidati.getSelectionModel().getSelectedItem();
    	if(p!= null) {
    		List<Candidato> l= dao.getCandidati(p.getId());
    		listCandidati.setCellFactory(new Callback<ListView<Candidato>, ListCell<Candidato>>(){
    			
    			@Override
    		    public ListCell<Candidato> call(ListView<Candidato> list) {
    		        ListCell<Candidato> cell = new ListCell<Candidato>() {
    		            @Override
    		            public void updateItem(Candidato item, boolean empty) {
    		                super.updateItem(item, empty);
    		                if(item!= null) {
    		                	setText(item.getNome());
    		                }else {
    		                	setText(null);
    		                }
    		            }
    		        };

    		        return cell;
    		    }
    		});
    		listCandidati.getItems().setAll(l);
    	}else {
    		AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "Selezionare il Candidato").showAndWait();
    	}
    }

    @FXML
    void selezionaPartito(MouseEvent event) {
    	ConcorrenteDAO dao=(ConcorrenteDAO) DAOFactory.getInstance().getConcorrenteDAO();
    	Partito p = listPartiti.getSelectionModel().getSelectedItem();
    	if(p!= null) {
    		List<Candidato> l= dao.getCandidati(p.getId());
    		listCandidati.setCellFactory(new Callback<ListView<Candidato>, ListCell<Candidato>>(){
    			
    			@Override
    		    public ListCell<Candidato> call(ListView<Candidato> list) {
    		        ListCell<Candidato> cell = new ListCell<Candidato>() {
    		            @Override
    		            public void updateItem(Candidato item, boolean empty) {
    		                super.updateItem(item, empty);
    		                if(item!= null) {
    		                	setText(item.getNome());
    		                }else {
    		                	setText(null);
    		                }
    		            }
    		        };

    		        return cell;
    		    }
    		});
    		listCandidati.getItems().setAll(l);
    	}else {
    		AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "Selezionare il Candidato").showAndWait();
    	}
    }

    @FXML
    void vota(ActionEvent event) {
    	
    }

	@Override
	public void init(Object parameters) {
		List linit = (List) parameters;
		log = (Elettore) linit.get(0);
		s = (Sessione) linit.get(1);
		
		ConcorrenteDAO dao = (ConcorrenteDAO) DAOFactory.getInstance().getConcorrenteDAO();
		List<Concorrente> l=dao.getAll(s);
		for (Concorrente concorrente : l) {
			if(concorrente.isPartito()==1) {
				listPartiti.getItems().add((Partito)concorrente);
			}
		}		
		listPartiti.setCellFactory(new Callback<ListView<Partito>, ListCell<Partito>>() {

		    @Override
		    public ListCell<Partito> call(ListView<Partito> list) {
		        ListCell<Partito> cell = new ListCell<Partito>() {
		            @Override
		            public void updateItem(Partito item, boolean empty) {
		                super.updateItem(item, empty);
		                if(item!= null) {
		                	setText(item.getNome());
		                }else {
		                	setText(null);
		                }
		            }
		        };

		        return cell;
		    }
		});
		
		listCandidati.setCellFactory(new Callback<ListView<Candidato>, ListCell<Candidato>>() {

		    @Override
		    public ListCell<Candidato> call(ListView<Candidato> list) {
		        ListCell<Candidato> cell = new ListCell<Candidato>() {
		            @Override
		            public void updateItem(Candidato item, boolean empty) {
		                super.updateItem(item, empty);
		                if(item!= null) {
		                	setText(item.getNome()+" "+item.getCognome());
		                }else {
		                	setText(null);
		                }
		            }
		        };

		        return cell;
		    }
		});
	}

}
