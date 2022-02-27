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
	public Elettore(String cod_fiscale, String nome, String cognome) {
		super(cod_fiscale, nome, cognome);
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
	
}
