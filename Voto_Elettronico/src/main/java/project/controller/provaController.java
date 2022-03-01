package project.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import project.model.Elettore;
import project.model.Scrutinatore;
import project.model.Utente;

public class provaController extends Controller implements Initializable{

    @FXML
    private Button bck;

    @FXML
    private ListView<Utente> listView;

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void selected(MouseEvent event) {
    	System.out.println(listView.getSelectionModel().getSelectedItem());
    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Utente> l=List.of(new Scrutinatore("dsfsfd","dfsa","dsadf", null),new Scrutinatore("dsfsfd","dfsa","dsadf", null),new Elettore("dsfsfd","dfsa","dsadf", null),new Scrutinatore("dsfsfd","dfsa","dsadf", null));
		listView.setCellFactory(new Callback<ListView<Utente>, ListCell<Utente>>() {

            @Override
            public ListCell<Utente> call(ListView<Utente> list) {
                ListCell<Utente> cell = new ListCell<Utente>() {
                    @Override
                    public void updateItem(Utente item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item!= null) {
                            setText(item.getCod_fiscale()+" "+item.getNome() + " " + item.getCognome());
                        }else {
                        	setText(null);
                        }
                    }
                };

                return cell;
            }
        });
		listView.getItems().setAll(l);
	}
}
