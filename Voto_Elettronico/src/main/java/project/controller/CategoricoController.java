package project.controller;

import java.util.List;

import dao.ConcorrenteDAO;
import dao.VotoDAO;
import factory.AlertFactory;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import project.model.Voto;

public class CategoricoController extends Controller{

	Elettore log;
	Sessione s;
    @FXML
    private ListView<Candidato> listCandidati;

    @FXML
    private ListView<Partito> listPartiti;

    @FXML
    private Button v;

    @FXML
    private CheckBox votaPartito;

    @FXML
    void selezionaCandidato(MouseEvent event) {

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
    	Partito p = listPartiti.getSelectionModel().getSelectedItem();
    	Candidato c= listCandidati.getSelectionModel().getSelectedItem();
    	VotoDAO dao= (VotoDAO) DAOFactory.getInstance().getVotoDAO();
    	if (p == null && c== null) {
    		dao.schedaBianca(s.getId(), log.getCod_fiscale());
    	}else {
    		if(votaPartito.isSelected()) {
    			Voto v= new Voto(0,s.getId(),p.getId());
        		dao.save(v, log);
    		}else {
    			Voto v= new Voto(0,s.getId(),c.getId());
        		dao.save(v, log);
    		}
    	}
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
