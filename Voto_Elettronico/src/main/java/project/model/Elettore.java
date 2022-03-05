package project.model;

/**
 * questa classe descrive l'elettore
 */

public class Elettore extends Utente{
	
	//ATTRIBUTI
	String ruolo;
	
	//COSTRUTTORE
	public Elettore(String nome, String cognome, String cod_fisc, String password, String ruolo) throws Exception {
		super(nome, cognome, cod_fisc, password);
		switch(ruolo) {
		case "scrutinatore":
			this.ruolo="Scrutinatore";
			break;
		case "elettore":
			this.ruolo="Elettore";
			break;
		default:
			throw new Exception();
		}
	}
	
	public Elettore(String nome, String cognome, String cod_fisc, String password){
		super(nome, cognome, cod_fisc, password);
		ruolo= "Elettore";
	}

	//METODI
	@Override
	public String toString() {
		return "Elettore : "+nome+" "+cognome+" "+super.cod_fiscale;
	}
	
	public boolean isElettore() {
		return true;
	}

	public String getPassword() {
		return this.password;
	}
	
	public String getRuolo() {
		return "elettore";
	}
}
