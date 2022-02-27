package project.model;

import java.util.Iterator;
import java.util.List;

public class Sessione implements Iterable{
	
	int id;
	String tipologia;
	String vittoria;
	String domanda;
	List <Partito> partiti;
	
	public Sessione(int id,String tipologia, String vittoria, String domanda, List <Partito> partiti) {
		this.id = id;
		this.tipologia = tipologia;
		this.vittoria = vittoria;
		this.domanda = domanda;
		this.partiti = partiti;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getVittoria() {
		return vittoria;
	}

	public void setVittoria(String vittoria) {
		this.vittoria = vittoria;
	}

	public String getDomanda() {
		return domanda;
	}

	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}

	@Override
	public Iterator<Partito> iterator() {
		return partiti.iterator();
	}
}
