package factory;

import dao.ConcorrenteDAO;
import dao.GenericDAO;
import dao.SessioneDAO;
import dao.UtenteDAO;
import project.model.Concorrente;
import project.model.Sessione;
import project.model.Utente;

public class DAOFactory {
	private static DAOFactory instance=null;
	
	private DAOFactory() {	
	}
	
	public static DAOFactory getInstance() {
		if(instance==null)	instance= new DAOFactory();
		return instance;
	}
	
	public GenericDAO<Utente> getUtenteDAO(){
		return new UtenteDAO();
	}
	
	public GenericDAO<Concorrente> getConcorrenteDAO(){
		return new ConcorrenteDAO();
	}
	
	public GenericDAO<Sessione> getSessioneDAO(){
		return new SessioneDAO();
	}
	
	
}
