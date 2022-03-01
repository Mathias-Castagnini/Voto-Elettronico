package project.model;

/**
 *
 * questa classe descrive lo scrutinatore
 *
 */


public class Scrutinatore extends Utente {

	//COSTRUTTORI
	/**
	 * @param cod_fiscale
	 * @param nome
	 * @param cognome
	 */
	public Scrutinatore(String nome, String cognome, String cod_fisc, String password) {
		super(nome, cognome, cod_fisc, password);
	}

	//ToString
	@Override
	public String toString() {
		return "Scrutinatore [cod_fiscale=" + cod_fiscale + ", nome=" + nome + ", cognome=" + cognome + "]";
	}

	@Override
	public boolean isElettore() {
		return false;
	}

	@Override
	public String getPassword() {
		
		return null;
	}

	
}
