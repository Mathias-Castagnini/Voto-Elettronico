package project.model;

public abstract class Concorrente {

	protected int id;
	protected String nome;
	protected String cognome;
	protected int id_partito;
	
	public Concorrente(int id,String nome, String cognome, int id_partito) {
		this.id = id;
		this.nome = nome;
		this.cognome=cognome;
		this.id_partito=id_partito;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome=nome;
	}
	
}

