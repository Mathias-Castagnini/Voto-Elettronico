package project.model;

/**
 *
 * questa classe descrive lo scrutinatore
 *
 */

public class Scrutinatore extends Utente {
	
	//ATTRIBUTI
	String nome;
	String cognome;
	String cod_fisc;
	String password;
	String ruolo;
	
	
	//COSTRUTTORI
	public Scrutinatore(String nome, String cognome, String cod_fisc, String password, String ruolo) throws Exception {
		super(nome, cognome, cod_fisc, password);
		switch(ruolo) {
		case "scrutinatore":
			this.ruolo="scrutinatore";
			break;
		case "elettore":
			this.ruolo="elettore";
			break;
		default:
			throw new Exception();
		}
	}

	//METODI
	@Override
	public String toString() {
		return "Scrutinatore :"+nome+" "+cognome+" "+cod_fisc;
	}

	public boolean isElettore() {
		return false;
	}

	public String getPassword() {
		return this.password;
	}

	public String getNome() {
		return this.nome;
	}
	
	public String getCognome() {
		return this.cognome;
	}
	
	public String getCodFisc() {
		return this.cod_fisc;
	}
	
	public String getRuolo() {
		return "scrutinatore";
	}
}
