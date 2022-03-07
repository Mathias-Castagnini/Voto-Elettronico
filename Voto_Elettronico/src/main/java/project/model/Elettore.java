package project.model;

/**
 * questa classe descrive l'elettore
 */

public class Elettore extends Utente{
	Boolean ha_votato;
	
	//COSTRUTTORE
	public Elettore(String nome, String cognome, String cod_fisc, String password, Boolean ha_votato){
		super(nome, cognome, cod_fisc, password);
		this.ha_votato=ha_votato;
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
	
	public Boolean getVotato() {
		return this.ha_votato;
	}
}
