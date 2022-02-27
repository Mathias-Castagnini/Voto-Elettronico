package project.model;

import java.util.Iterator;
import java.util.List;

public class Partito extends Concorrente implements Iterable<Candidato>{

	public List<Candidato> candidati;
	
	public Partito(int id,String nome,List<Candidato> candidati) {
		super(id,nome);
		this.candidati= candidati;
	}
	
	@Override
	public String toString() {
		return "Partito [id=" + id + ", nome=" + nome + " Candidati="+candidati.toString()+"]";
	}
	
	@Override
	public boolean isPartito() {
		return true;
	}

	@Override
	public Iterator<Candidato> iterator() {
		return candidati.iterator();
	}
	
	
}
