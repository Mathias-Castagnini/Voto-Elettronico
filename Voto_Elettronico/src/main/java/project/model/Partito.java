package project.model;

import java.util.Iterator;
import java.util.List;

public class Partito extends Concorrente{

	int id;
	String nome;
	String cognome;
	int id_partito;
	int is_partito;
	
	public Partito(int id,String nome, String cognome, int id_partito, int is_partito) {
		super(id,nome, "", -1, 1);
		
	}
	
	@Override
	public String toString() {
		return "Partito : "+this.nome;
	}
	
	@Override
	public int isPartito() {
		return 1;
	}



	
	
}
