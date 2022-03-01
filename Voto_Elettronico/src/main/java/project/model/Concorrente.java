package project.model;

public abstract class Concorrente {

	protected int id;
	protected String nome;
	protected String cognome;
	protected int id_partito;
	protected int is_partito;
	
	public Concorrente(int id,String nome, String cognome, int id_partito, int is_partito) {
		this.id = id;
		this.nome = nome;
		this.cognome="";
		this.id_partito=0;
		this.is_partito=0;
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
	
	public int isPartito() {
		return this.is_partito;
	}
}

