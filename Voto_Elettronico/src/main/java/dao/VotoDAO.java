package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import dbconnection.DBConnection;
import logger.VotoLogger;
import project.model.Candidato;
import project.model.Concorrente;
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
				v=new Voto(rs.getInt("id"),rs.getInt("sessione"),rs.getInt("candidato"));
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
				l.add(new Voto(rs.getInt("id"),rs.getInt("sessione"),rs.getInt("candidato")));
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
		String query="INSERT INTO voto(id,sessione,candidato) VALUES(?,?,?)";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, v.getId());
			ps.setInt(2, v.getSessione());
			ps.setInt(3, v.getCandidato());
			ps.executeQuery();
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
		String query="SELECT COUNT(*) FROM votazione WHERE utente=? AND sessione=?";
		int num=1;
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1, id);
			ps.setInt(2, sessione);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				num=rs.getInt(0);
			}
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("error: ", Level.WARNING, e);
		}
		if(num==0) return false;
		return true;
	}
	
	//
	// VERIFICHE SESSIONI E VITTORIE
	//
	
	
	public Concorrente contaVoti(Utente c, int idSessione) throws Exception {
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
			case "ordinale":
				k=votoOrdinale(s);
				break;
			case "categorico":
				k=votoCategorico(s);
				break;
			case "categorico preferenza":
				k=votoPreferenza(s);
				break;
			case "referendum":
				k=votoReferendum(s);
				break;
			default:
				throw new Exception();
		}
		
		
		return k;
	}
	
	private Concorrente votoReferendum(Sessione s) {
		
		return null;
	}

	//il partito che vince poi ritorna il candidato con pi� voti, dal candidato si recupera il partito vincente collegato
	private Concorrente votoPreferenza(Sessione s) {
		Concorrente p = votoCategorico(s);
		if(p instanceof Candidato) return p;
		String query="";
		
		return null;
	}

	/*private Partito toPartito(int id) {
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
	}*/

	private Concorrente votoOrdinale(Sessione s) {
		//
		return null;
	}

	private Concorrente votoCategorico(Sessione s) {
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