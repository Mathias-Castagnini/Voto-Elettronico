package project.model;

public class Partito extends Concorrente{

	int id;
	String nome;
	String cognome;
	int id_partito;
	int is_partito;
	
	public Partito(int id,String nome, String cognome, int id_partito) {
		super(id,nome, "", -1);
		this.is_partito=1;
		
	}
	
	@Override
	public String toString() {
		return "Partito : "+this.nome;
	}
	
	public int isPartito() {
		return 1;
	}

	public int getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}

	
	
}
