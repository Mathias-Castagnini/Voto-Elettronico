package factory;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertFactory {
	private static AlertFactory instance = null;
	private AlertFactory() {
		
	}
	
	public static AlertFactory getInstance() {
		if(instance==null) instance = new AlertFactory();
		return instance;
	}
	public Alert getAlert(AlertType t, String s) {
		Alert alert = new Alert(t,s);
		return alert;
	}
	
	public Alert getSlimAlert(AlertType t, String s) {
		Alert alert=new Alert(t,s);
		alert.setHeaderText(null);
		return alert;
	}
	
}
