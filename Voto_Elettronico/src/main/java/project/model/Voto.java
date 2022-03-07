package project.model;

public class Voto {

	 private int id;
	 private int sessione;
	 private int candidato;
	 private Boolean esito;
	 private String id_elettore;
	 
	 public Voto(int id, int s, int c) {
		 this.id=id;
		 this.sessione=s;
		 this.candidato=c;
		 this.esito=null;
	 }
	 
	 public Voto(int id, int s, int c, Boolean e,String elettore) {
		 this.id=id;
		 this.sessione=s;
		 this.candidato=c;
		 this.esito=e;
		 this.id_elettore=elettore;
	 }

	public int getId() {
		return id;
	}

	public int getSessione() {
		return sessione;
	}

	public int getCandidato() {
		return candidato;
	}

	public Boolean getEsito() {
		return this.esito;
	}
	 
	 
}
