package project.model;

public abstract class Concorrente {

	protected int id;
	protected String nome;
	
	public Concorrente(int id,String nome) {
		this.id = id;
		this.nome = nome;
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
	
	public abstract boolean isPartito();
}
