package project.model;

/**
 *
 * questa classe descrive lo scrutinatore
 *
 */

public class Scrutinatore extends Utente {
	
	//ATTRIBUTI
	private String ruolo;
	
	
	//COSTRUTTORI	
	public Scrutinatore(String nome, String cognome, String cod_fisc, String password){
		super(nome, cognome, cod_fisc, password);
		ruolo="Scrutinatore";
	}

	//METODI
	@Override
	public String toString() {
		return "Scrutinatore :"+nome+" "+cognome+" "+super.cod_fiscale;
	}

	public boolean isElettore() {
		return false;
	}

	public String getPassword() {
		return this.password;
	}
	
	public String getRuolo() {
		return "scrutinatore";
	}
}
