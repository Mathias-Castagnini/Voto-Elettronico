/**
 * questa classe descrive l'elettore
 */
public class Elettore extends Utente {
	
	//ATTRIBTI
	public int eta;

	//COSTRUTTORE
	/**
	 * @param cod_fiscale
	 * @param nome
	 * @param cognome
	 * @param eta
	 */
	public Elettore(String cod_fiscale, String nome, String cognome, int eta) {
		super(cod_fiscale, nome, cognome);
		this.eta = eta;
	}

	//METODI
	/**
	 * @return the eta
	 */
	public int getEta() {
		return eta;
	}

	/**
	 * @param eta the eta to set
	 */
	public void setEta(int eta) {
		this.eta = eta;
	}

	@Override
	public String toString() {
		return "Elettore [cod_fiscale=" + cod_fiscale + ", nome=" + nome + ", cognome=" + cognome
				+ "eta=" + eta+"]";
	}
	
}
