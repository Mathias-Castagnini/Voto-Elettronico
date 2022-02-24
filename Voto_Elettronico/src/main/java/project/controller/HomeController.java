package project.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HomeController extends Controller{

    @FXML
    private Button btn;

    @FXML
    private TextField codiceFiscale;

    @FXML
    private PasswordField psw;

    @FXML
    void login(ActionEvent event) {
    	String cf = codiceFiscale.getText();
    	String ps = psw.getText();
    	if( cf.isEmpty() || ps.isEmpty()) {
    		Alert l = new Alert(AlertType.ERROR,"Password e o Codice fiscale errato");
    		l.setHeaderText(null);
    		l.showAndWait();
    	}
    	//changeView("/view/",parameters); per passare alla seconda pagina
    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

}
