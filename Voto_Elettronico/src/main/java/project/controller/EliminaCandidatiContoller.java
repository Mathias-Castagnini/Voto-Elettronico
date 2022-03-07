package project.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.ConcorrenteDAO;
import dao.UtenteDAO;
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
import project.model.Candidato;
import project.model.Concorrente;
import project.model.Partito;
import project.model.Scrutinatore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class EliminaCandidatiContoller extends Controller implements Initializable{

	Scrutinatore log;
    @FXML
    private Button bck;

    @FXML
    private Button elc;

    @FXML
    private Button elp;

    @FXML
    private ListView<Candidato> listCandidati;

    @FXML
    private ListView<Partito> listPartiti;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/gestioneCandidati.fxml",log);
    }

    @FXML
    void eliminaCandidato(ActionEvent event) {
    	ConcorrenteDAO dao = (ConcorrenteDAO) DAOFactory.getInstance().getConcorrenteDAO();
    	Candidato p =listCandidati.getSelectionModel().getSelectedItem();
    	dao.delete(p);
    	listCandidati.getItems().remove(p);
    	VotoLogger.writeToLog("Il candidato e' stato eliminato correttamente");
    }

    @FXML
    void eliminaPartito(ActionEvent event) {
    	ConcorrenteDAO dao = (ConcorrenteDAO) DAOFactory.getInstance().getConcorrenteDAO();
    	Partito p =listPartiti.getSelectionModel().getSelectedItem();
    	dao.delete(p);
    	listPartiti.getItems().remove(p);
    	VotoLogger.writeToLog("Il partito "+p.getNome()+" e' stato eliminato correttamente compresi tutti i suoi candidati");
    }

    @FXML
    void selectedCandidato(MouseEvent event) {

    }

    @FXML
    void selectedPartito(MouseEvent event) {
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
    		AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "Selezionare il Partito").showAndWait();
    	}
    }

	@Override
	public void init(Object parameters) {
		log=(Scrutinatore) parameters;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ConcorrenteDAO dao=(ConcorrenteDAO) DAOFactory.getInstance().getConcorrenteDAO();
		List<Partito> l= dao.getPartiti();
		listPartiti.setCellFactory(new Callback<ListView<Partito>, ListCell<Partito>>(){
			
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
		listPartiti.getItems().addAll(l);
	}

}
