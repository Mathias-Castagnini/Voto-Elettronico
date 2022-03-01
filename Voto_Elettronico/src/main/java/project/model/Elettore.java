package project.model;

/**
 * 
 * questa classe descrive l'elettore
 * 
 */


public class Elettore extends Utente{
	//ATTRIBUTI

	//COSTRUTTORE
	/**
	 * @param cod_fiscale
	 * @param nome
	 * @param cognome
	 */
	public Elettore(String nome, String cognome, String cod_fisc, String password) {
		super(nome, cognome, cod_fisc, password);
	}

	//METODI
	//ToString
	@Override
	public String toString() {
		return "Elettore [cod_fiscale=" + cod_fiscale + ", nome=" + nome + ", cognome=" + cognome +"]";
	}
	
	public boolean isElettore() {
		return true;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
