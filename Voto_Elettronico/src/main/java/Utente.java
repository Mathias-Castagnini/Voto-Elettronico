/**
 * 
 * questa classe descrive l'utente
 *
 */


public abstract class Utente {
	//ATTRIBUTI
	protected String cod_fiscale;
	protected String nome;
	protected String cognome;
	
	//COSTRUTTORI
	/**
	 * @param cod_fiscale
	 * @param nome
	 * @param cognome
	 */
	public Utente(String cod_fiscale, String nome, String cognome) {
		this.cod_fiscale = cod_fiscale;
		this.nome = nome;
		this.cognome = cognome;
	}

	//METODI
	/**
	 * @return the cod_fiscale
	 */
	public String getCod_fiscale() {
		return cod_fiscale;
	}

	/**
	 * @param cod_fiscale the cod_fiscale to set
	 */
	public void setCod_fiscale(String cod_fiscale) {
		this.cod_fiscale = cod_fiscale;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	//ToString
	@Override
	public String toString() {
		return "Utente [cod_fiscale=" + cod_fiscale + ", nome=" + nome + ", cognome=" + cognome + "]";
	}
		
	
}
