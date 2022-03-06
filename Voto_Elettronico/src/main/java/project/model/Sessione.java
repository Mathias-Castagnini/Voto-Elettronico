package project.model;

import java.util.Iterator;
import java.util.List;

public class Sessione implements Iterable{
	
	private int id;
	private String tipologia;
	private String vittoria;
	private String domanda;
	private boolean stato;
	private List<Partito> p;
	
	public Sessione(int id,String tipologia, String vittoria, String domanda) throws Exception{
		this.id = id;
		this.tipologia = tipologia;
		this.vittoria = vittoria;
		if(tipologia.equals("referendum") && domanda==null) throw new Exception();
		if(!(tipologia.equals("referendum") && (vittoria.equals("referendum") || vittoria.equals("referendum quorum")))) throw new Exception();
		if(!(tipologia.equals("referendum")) && (vittoria.equals("referendum") || vittoria.equals("referendum quorum"))) throw new Exception();
		this.domanda = domanda;
		this.stato=true;
	}
	
	public Sessione(String tipologia, String vittoria, String domanda,List<Partito> p){
		this.tipologia = tipologia;
		this.vittoria = vittoria;
		this.domanda = domanda;
		this.stato=true;
		this.p=p;
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
	
	public Boolean getStato() {
		return this.stato;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
