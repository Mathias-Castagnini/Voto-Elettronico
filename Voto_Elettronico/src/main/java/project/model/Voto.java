package project.model;
	
public class Voto {
		
	/*@   invariant esito==null -> candidato!=null @*/
	/*@   invariant esito!=null -> candidato==null @*/
	
	 private int /*@ non_null; spec_public@*/ id;
	 private int /*@ non_null; spec_public@*/ sessione;
	 private int /*@ non_null; spec_public@*/ candidato;
	 private Boolean esito;
		 
	 public Voto(int id, int s, int c) {
		 this.id=id;
		 this.sessione=s;
		 this.candidato=c;
		 this.esito=false;
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
