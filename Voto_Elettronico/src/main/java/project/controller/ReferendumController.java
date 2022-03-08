package project.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.SessioneDAO;
import dao.VotoDAO;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import logger.VotoLogger;
import project.model.Elettore;
import project.model.Sessione;
import project.model.Voto;

public class ReferendumController extends Controller{

	Elettore log;
	Sessione s=null;
    @FXML
    private Label domanda;

    @FXML
    private Button nv;

    @FXML
    private Button v;

    @FXML
    void favorevole(ActionEvent event) {
    	VotoDAO dao = (VotoDAO) DAOFactory.getInstance().getVotoDAO();
    	Voto v=new Voto(0,s.getId(),0,true);
		dao.save(v, log);
		changeView("/view/home.fxml",null);
    }

    @FXML
    void nonFavorevole(ActionEvent event) {
    	VotoDAO dao = (VotoDAO) DAOFactory.getInstance().getVotoDAO();
    	Voto v=new Voto(0,s.getId(),0,false);
		dao.save(v, log);
		changeView("/view/home.fxml",null);
    }

	@Override
	public void init(Object parameters) {
		List l = (List) parameters;
		log = (Elettore) l.get(0);
		s = (Sessione) l.get(1);
		domanda.setText(s.getDomanda());
		domanda.setWrapText(true);
	}

}
