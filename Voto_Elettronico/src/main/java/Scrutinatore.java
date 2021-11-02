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
	public Scrutinatore(String cod_fiscale, String nome, String cognome) {
		super(cod_fiscale, nome, cognome);
		// TODO Auto-generated constructor stub
	}

	//ToString
	@Override
	public String toString() {
		return "Scrutinatore [cod_fiscale=" + cod_fiscale + ", nome=" + nome + ", cognome=" + cognome + "]";
	}

	
}
