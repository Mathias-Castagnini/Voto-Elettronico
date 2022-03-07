package project.model;

public class Voto {

	 int id;
	 int sessione;
	 int candidato;
	 Boolean esito;
	 
	 public Voto(int id, int s, int c) {
		 this.id=id;
		 this.sessione=s;
		 this.candidato=c;
		 this.esito=null;
	 }
	 
	 public Voto(int id, int s, int c, Boolean e) {
		 this.id=id;
		 this.sessione=s;
		 this.candidato=c;
		 this.esito=e;
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
