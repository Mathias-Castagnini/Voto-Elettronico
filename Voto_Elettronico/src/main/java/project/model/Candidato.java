package project.model;

public class Candidato extends Concorrente{
	
	public Candidato(int id,String nome,String cognome, int id_partito) {
		super(id, nome, cognome, id_partito);
	}
	
	public Candidato(String nome,String cognome, int id_partito) {
		super(0, nome, cognome, id_partito);
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
	
	@Override
	public String toString() {
		return "Candidato : "+this.nome+" "+this.cognome+", partito: "+this.id_partito;
	}
	
	public int isPartito() {
		return 0;
	}
}
