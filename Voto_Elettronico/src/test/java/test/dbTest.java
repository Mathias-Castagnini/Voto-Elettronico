package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.UtenteDAO;
import factory.DAOFactory;
import project.model.Elettore;
import project.model.Scrutinatore;
import project.model.Utente;

class dbTest {

	@Test
    void testLogin() throws Exception {
        UtenteDAO dao = (UtenteDAO) DAOFactory.getInstance().getUtenteDAO();
        assertNotNull(dao.get("flex"));
    }

	@Test
	void testGetElettore() throws Exception {
		Elettore e = new Elettore("nome","cognome","test","password");
		UtenteDAO dao = (UtenteDAO) DAOFactory.getInstance().getUtenteDAO();
		
		dao.save(e,"test");
		
		Utente u =dao.get("test");
		assertTrue(u.isElettore());
		dao.delete(u);
	
	}
	
	@Test
	void testGetScrutinatore() throws Exception {
		Scrutinatore e = new Scrutinatore("nome","cognome","testS","password");
		UtenteDAO dao = (UtenteDAO) DAOFactory.getInstance().getUtenteDAO();
		
		dao.save(e,"testS");
		
		Utente u =dao.get("testS");
		assertFalse(u.isElettore());
		dao.delete(u);
	
	}

	
}

