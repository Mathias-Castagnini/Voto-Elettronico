/**
 * questa classe descrive lo scrutinatore
 */
public class Scrutinatore extends Utente{
	
	//COSTRUTTORE
	public Scrutinatore(String cod_fiscale, String nome, String cognome) {
		super(cod_fiscale, nome, cognome);
	}

	@Override
	public String toString() {
		return "Scrutinatore [cod_fiscale=" + cod_fiscale + ", nome=" + nome + ", cognome=" + cognome + "]";
	}
	
}
