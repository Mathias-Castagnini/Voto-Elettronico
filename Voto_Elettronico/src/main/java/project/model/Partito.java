package project.model;

public class Partito extends Concorrente{

	public Partito(int id,String nome,int partito) {
		super(id,nome, null, partito);
		
	}
	
	public Partito(String nome) {
		super(0,nome, null, -1);
		
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
