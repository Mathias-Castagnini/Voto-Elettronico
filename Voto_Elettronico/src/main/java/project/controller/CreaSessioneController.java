package project.controller;

import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import logger.VotoLogger;
import project.model.Partito;
import project.model.Scrutinatore;
import project.model.Sessione;

public class CreaSessioneController extends Controller implements Initializable{

	Scrutinatore log;
	 	@FXML
	    private Button bck;

	    @FXML
	    private Button crea;

	    @FXML
	    private TextField domanda;

	    @FXML
	    private Button ins;

	    @FXML
	    private ListView<Partito> listPartiti;

	    @FXML
	    private ListView<Partito> listPartitiInseriti;

	    @FXML
	    private ComboBox<String> mvittoria;

	    @FXML
	    private ComboBox<String> mvoto;

	    @FXML
	    private Button tg;

	    @FXML
	    void back(ActionEvent event) {
	    	changeView("/view/gestioneSessione.fxml",log);
	    }

	    @FXML
	    void creaSessione(ActionEvent event) {
	    	Sessione s;
			String d = domanda.getText();
			String mVoto = mvoto.getSelectionModel().getSelectedItem();
			String mVittoria= mvittoria.getSelectionModel().getSelectedItem();
			List<Partito> p = listPartitiInseriti.getItems();
			if (mVoto.isEmpty() || mVittoria.isEmpty()) {
				AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "Devi scegliere le modalita' di voto e di vittoria").showAndWait();
			} else {
				if(mVoto.equalsIgnoreCase("referendum")) {
					if (!d.isEmpty()) {
						s=new Sessione( mVoto, mVittoria,d, new ArrayList<Partito>());
						SessioneDAO dao = (SessioneDAO) DAOFactory.getInstance().getSessioneDAO();
		        		dao.save(s);
		        		VotoLogger.writeToLog("Sessione creata correttamente");
		        		changeView("/view/scrutinatore.fxml",log);
					}else {
						AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "Devi inserire la domanda").showAndWait();
					}
				} else {
	    			if(listPartitiInseriti.getItems().size()>=2) {
	    				s=new Sessione(mVoto, mVittoria, d , p); 
	            		SessioneDAO dao = (SessioneDAO) DAOFactory.getInstance().getSessioneDAO();
	            		dao.save(s);
	            		VotoLogger.writeToLog("Sessione creata correttamente");
	            		changeView("/view/scrutinatore.fxml",log);
	    	    	}else {
	    	    		AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "Devi selezionare piu' partiti").showAndWait();
	    	    	}
				}
			}
	    }

	    @FXML
	    void inserisci(ActionEvent event) {
	    	ConcorrenteDAO dao=(ConcorrenteDAO) DAOFactory.getInstance().getConcorrenteDAO();
	    	Partito p= listPartiti.getSelectionModel().getSelectedItem();
	    	if(p == null) {
	    		AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "Selezionare il Partito").showAndWait();
	    	}else {
	    		listPartitiInseriti.getItems().add(p);
        		listPartiti.getItems().remove(p);
	    	}
	    }

	    @FXML
	    void togli(ActionEvent event) {
	    	ConcorrenteDAO dao=(ConcorrenteDAO) DAOFactory.getInstance().getConcorrenteDAO();
	    	Partito p= listPartitiInseriti.getSelectionModel().getSelectedItem();
	    	if(p == null) {
	    		AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "Selezionare il Partito").showAndWait();
	    	}else {
	    		listPartitiInseriti.getItems().remove(p);
        		listPartiti.getItems().add(p);
	    	}
	    }

		@Override
		public void init(Object parameters) {
			log=(Scrutinatore) parameters;
		}

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			//listview
			ConcorrenteDAO dao = (ConcorrenteDAO) DAOFactory.getInstance().getConcorrenteDAO();
			listPartiti.getItems().setAll(dao.getPartiti());
			
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
			
			listPartitiInseriti.setCellFactory(new Callback<ListView<Partito>, ListCell<Partito>>() {

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
			
			//combobox
			mvoto.getItems().setAll(List.of("ordinale","categorico","Categorico preferenza","referendum"));		
			mvoto.getSelectionModel().selectFirst();
			mvittoria.getItems().setAll(List.of("Maggioranza","Maggioranza Assoluta"));
			mvittoria.getSelectionModel().selectFirst();
		}

}
