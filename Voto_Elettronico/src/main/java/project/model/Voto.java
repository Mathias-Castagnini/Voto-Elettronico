package project.model;

public class Voto {

	 private int id;
	 private int sessione;
	 private int candidato;
	 private Boolean esito;
	 
	 public Voto(int id, int s, int c) {
		 this.id=id;
		 this.sessione=s;
		 this.candidato=c;
		 this.esito=null;
	 }
	 
	 public Voto(int id, int sessione, int candidato, Boolean esito) {
		 this.id=id;
		 this.sessione=sessione;
		 this.candidato=candidato;
		 this.esito=esito;
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
