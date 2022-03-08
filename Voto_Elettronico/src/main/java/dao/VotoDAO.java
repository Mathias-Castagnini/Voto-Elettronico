package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import dbconnection.DBConnection;
import logger.VotoLogger;
import project.model.Candidato;
import project.model.Concorrente;
import project.model.Elettore;
import project.model.Partito;
import project.model.Scrutinatore;
import project.model.Sessione;
import project.model.Utente;
import project.model.Voto;

public class VotoDAO implements GenericDAO<Voto>{

	@Override
	public Voto get(String id) throws Exception {
		Voto v=null;
		String query="SELECT * form voto WHERE id=?";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				v=new Voto(rs.getInt("id"),rs.getInt("sessione"),rs.getInt("candidato"),rs.getBoolean("esito"));
			}
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("error: ", Level.WARNING, e);
		}
		return v;
	}

	@Override
	public ArrayList<Voto> getAll() {
		ArrayList<Voto> l = new ArrayList<Voto>();
		String query="SELECT * form voto";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				l.add(new Voto(rs.getInt("id"),rs.getInt("sessione"),rs.getInt("candidato"),rs.getBoolean("esito")));
			}
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("error: ", Level.WARNING, e);
		}
		return l;
	}

	@Override
	public void delete(Voto v) {
		//inutilizzato
	}

	@Override
	public void save(Voto v) {
		//inutilizzato
	}
	
	public void save(Voto v, Elettore l){
		if(checkVoto(l.getCod_fiscale(),v.getSessione())) throw new IllegalArgumentException();
		String query="INSERT INTO voto(id,sessione,candidato,esito) VALUES(?,?,?,?)";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, v.getId());
			ps.setInt(2, v.getSessione());
			if(v.getCandidato()==0)	ps.setInt(3, (Integer) null);
			else ps.setInt(3, (Integer) v.getCandidato());
			ps.setBoolean(4, v.getEsito());
			ps.executeUpdate();
			query="INSERT INTO votazione(sessione,elettore) VALUES(?,?)";
			ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, v.getSessione());
			ps.setString(2, l.getCod_fiscale());
			ps.executeUpdate();
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("error: ", Level.WARNING, e);
		}
	}
	
	public int countVotoSessione(int sessione) {
		String query="SELECT COUNT(*) FROM voto WHERE sessione=?";
		int num=0;
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, sessione);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
				num=rs.getInt(0);
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("error: ", Level.WARNING, e);
		}
		return num;
	}

	public int countVotoCandidato(int sessione, int candidato) {
		String query="SELECT COUNT(*) FROM voto WHERE candidato=? AND sessione=?";
		int num=0;
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, candidato);
			ps.setInt(2, sessione);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
				num=rs.getInt(0);
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("error: ", Level.WARNING, e);
		}
		return num;
	}
	
	//ritorna false se non ha votato, true se ha votato
	public Boolean checkVoto(String id, int sessione) {
		String query="SELECT COUNT(*) FROM votazione WHERE sessione=? AND elettore=?";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, sessione);
			ps.setString(2, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				if(rs.getInt(0)==1) return true;
				else return false;
			}
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("error: ", Level.WARNING, e);
		}
		return false;
	}
	
	//
	// VERIFICHE SESSIONI E VITTORIE
	//
	
	
	public Concorrente esitoSessione(Utente c, int idSessione) throws Exception {
		if(!(c instanceof Scrutinatore)) throw new Exception();
		Sessione s = null;
		Concorrente k = null;
		String query="SELECT * FROM sessione where id=?";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1,  idSessione);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				s=new Sessione(rs.getInt("id"),rs.getString("tipologia"), rs.getString("vittoria"),rs.getString("domanda"),rs.getBoolean("stato"));
			}
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("Error:", Level.WARNING, e);
		}
		switch(s.getTipologia()) {
			case "ordinale": //
				k=votoOrdinale(s);
				break;
			case "categorico": //ok
				k=votoCategorico(s);
				break;
			case "categorico preferenza": //ok
				k=votoPreferenza(s);
				break;
			case "referendum": //ok
				k=votoReferendum(s);
				break;
			default:
				throw new Exception();
		}		
		return k;
	}
	
	public Concorrente votoReferendum(Sessione s) throws Exception{
		if(!(s.getVittoria().equals("referendum") || s.getVittoria().equals("referendum quorum"))) throw new Exception();
		String query="";
		List<Voto> l= new ArrayList<Voto>();
		if(s.getVittoria().equals("referendum")) {
			query="SELECT * FROM voto WHERE esito!=NULL AND sessione=?";
			try {
				DBConnection.getInstance().openConnection();
				PreparedStatement ps = DBConnection.getInstance().prepara(query);
				ps.setInt(1, s.getId());
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					l.add(new Voto(rs.getInt("id"),rs.getInt("sessione"),rs.getInt("candidato"),rs.getBoolean("esito")));
				}
				DBConnection.getInstance().closeConnection();
			}catch(SQLException e) {
				VotoLogger.writeToLog("Error:", Level.WARNING, e);
			}
			int numVoti=l.size();
			int yes=0;
			for(int i=0;i<l.size();i++) {
				if(l.get(i).getEsito()==true) yes++;
			}
			if(yes>numVoti/2) return new Partito("yes");
			else return new Partito("no");
		}else {
			query="SELECT * FROM voto WHERE esito!=NULL AND sessione=?";
			try {
				DBConnection.getInstance().openConnection();
				PreparedStatement ps = DBConnection.getInstance().prepara(query);
				ps.setInt(1, s.getId());
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					l.add(new Voto(rs.getInt("id"),rs.getInt("sessione"),rs.getInt("candidato"),rs.getBoolean("esito")));
				}
				DBConnection.getInstance().closeConnection();
			}catch(SQLException e) {
				VotoLogger.writeToLog("Error:", Level.WARNING, e);
			}
			int yes=0;
			for(int i=0;i<l.size();i++) {
				if(l.get(i).getEsito()==true) yes++;
			}
			UtenteDAO u = new UtenteDAO();
			if(yes>u.numeroElettoriTot(u.getAll())) return new Partito("yes");
			else return new Partito("no");
		}
	}

	//il partito che vince poi ritorna il candidato con piu voti, dal candidato si recupera il partito vincente collegato
	public Concorrente votoPreferenza(Sessione s) {
		Concorrente p = votoCategorico(s);
		if(p instanceof Candidato) return p;
		String query="SELECT * FROM candidato WHERE id_partito=?";
		List<Candidato> l=new ArrayList<Candidato>();
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, p.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				l.add(new Candidato(rs.getInt("id"),rs.getString("nome"),rs.getString("cognome"),rs.getInt("id_partito")));
			}
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("Error:", Level.WARNING, e);
		}
		HashMap<Candidato, Integer> map=new HashMap<Candidato, Integer>();
		for(int i=0;i<l.size();i++) {
			map.put(l.get(i), countVotoCandidato(s.getId(), l.get(i).getId()));
		}
		int max=0;
		Candidato key=null;
		for(Candidato i:map.keySet()) {
			if(map.get(i)>max) {
				max=map.get(i); 
				key=i;
			}
		}
		return key;
	}

	public Partito toPartito(int id) {
		String query="SELECT * FROM candidato WHERE id=?";
		Partito p=null;
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				p=new Partito(rs.getString("nome"));
			}
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("Error:", Level.WARNING, e);
		}
		return p;
	}

	public Concorrente votoOrdinale(Sessione s) {
		ConcorrenteDAO c= new ConcorrenteDAO();
		List<Concorrente> l = c.getCandidatiSessione(s);
		HashMap<Concorrente, Integer> map=new HashMap<Concorrente,Integer>();
		for(int i=0;i<l.size();i++) {
			map.put(l.get(i),countVotoCandidato(s.getId(),l.get(i).getId()));
		}
		l.clear();
		while(map.size()!=0) {
			Concorrente d = getMax(map);
			l.add(d);
			map.remove(d);
		}
		for(int i=0;i<l.size();i++) {
			if(l.get(i)instanceof Partito) return l.get(i);
		}

		return l.get(0);
	}
	
	private Concorrente getMax(HashMap<Concorrente, Integer> m) {
		int max=0;
		Concorrente c=null;
		for(Concorrente i:m.keySet()) {
			if(m.get(i)>max) max=m.get(i); c=i;
		}
		return c;
	}

	public Concorrente votoCategorico(Sessione s) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		String query="SELECT * FROM voto where sessione=?";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, s.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(map.get(rs.getInt("sessione"))==null) map.put(rs.getInt("sessione"), 1);
				else map.put(rs.getInt("sessione"), map.get(rs.getInt("sessione"))+1);
			}
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("Error:", Level.WARNING, e);
		}
		int max=0;
		int id=-1;
		for(int i:map.keySet()) {
			if(map.get(i)>max) {
				max=map.get(i);
				id=i;
			}
		}
		ConcorrenteDAO d = new ConcorrenteDAO();
		return d.get(""+id);
	}
	
}
