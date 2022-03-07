package project.model;

public class Voto {

	 int id;
	 int sessione;
	 int candidato;
	 
	 public Voto(int id, int s, int c) {
		 this.id=id;
		 this.sessione=s;
		 this.candidato=c;
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

	 
	 
}
