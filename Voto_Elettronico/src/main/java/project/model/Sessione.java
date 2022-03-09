package project.model;

import java.util.List;

public class Sessione{
	
	/*@   invariant tipologia.equals("referendum") -> domanda!=null @*/
	/*@   invariant !tipologia.equals("referendum") -> domanda==null @*/
	
	private int /*@ non_null; spec_public@*/ id;
	private String /*@ non_null; spec_public@*/ tipologia;
	private String /*@ non_null; spec_public@*/ vittoria;
	private String domanda;
	private boolean /*@ non_null; spec_public@*/ stato;
	private List<Partito> p;
	
	public Sessione(int id,String tipologia, String vittoria,boolean stato, String domanda){
		this.id = id;
		this.tipologia = tipologia;
		this.vittoria = vittoria;
		this.domanda = domanda;
		this.stato=stato;
	}
	
	public Sessione(String tipologia, String vittoria, String domanda,List<Partito> p){
		this.tipologia = tipologia;
		this.vittoria = vittoria;
		this.domanda = domanda;
		this.stato=true;
		this.p=p;
	}
	
	public Sessione(int id,String tipologia, String vittoria, String domanda, Boolean stato) {
		this.id = id;
		this.tipologia = tipologia;
		this.vittoria = vittoria;
		this.domanda = domanda;
		this.stato=stato;
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

	public List<Partito> getPartiti(){
		return p;
	}
}
