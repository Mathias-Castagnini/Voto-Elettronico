package project.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.ConcorrenteDAO;
import dao.UtenteDAO;
import factory.AlertFactory;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import project.model.Candidato;
import project.model.Concorrente;
import project.model.Elettore;
import project.model.Partito;
import project.model.Scrutinatore;
import project.model.Utente;

public class InserisciCandidatoController extends Controller implements Initializable{

	@FXML
    private Button bck;

    @FXML
    private TextField c;

    @FXML
    private Button ins;
    
    @FXML
    private ListView<Partito> listPartiti;

    @FXML
    private TextField n;

    @FXML
    void back(ActionEvent event) {
    	changeView("/view/inserisciPartito.fxml",null);
    }

    @FXML
    void inserisci(ActionEvent event) {
    	String nome= n.getText();
    	String cognome= c.getText();
    	if (nome.isEmpty() || cognome.isEmpty()) {
    		AlertFactory.getInstance().getSlimAlert(AlertType.INFORMATION, "il nome e il cognome del candidato non deve essere di lungezza pari a 0").showAndWait();
    	} else {	
    		ConcorrenteDAO dao = (ConcorrenteDAO) DAOFactory.getInstance().getConcorrenteDAO();
        	Partito p = listPartiti.getSelectionModel().getSelectedItem();
        	if(p!= null) {
        		Candidato c= new Candidato(nome,cognome,p.getId());
            	dao.addCandidato(c);
        	}else {
        		AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "Selezionare il Partito").showAndWait();
        	}
    	}
    }
    
    @FXML
    void selected(MouseEvent event) {

    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ConcorrenteDAO dao=(ConcorrenteDAO) DAOFactory.getInstance().getConcorrenteDAO();
		List<Partito> l = dao.getPartiti();
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
