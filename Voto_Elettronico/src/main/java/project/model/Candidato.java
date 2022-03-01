package project.model;

public class Candidato extends Concorrente{

	int id;
	String nome;
	String cognome;
	int id_partito;
	int is_partito;
	
	public Candidato(int id,String nome,String cognome, int id_partito, int is_partito) {
		super(id,nome, cognome, id_partito, 0);
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public int getPartito() {
		return this.id_partito;
	}

	public void setNome(String n) {
		this.nome=n;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	@Override
	public String toString() {
		return "Candidato : "+this.nome+" "+this.cognome+", partito: "+this.id_partito;
	}
	
	@Override
	public int isPartito() {
		return 0;
	}
}
