package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


import dbconnection.DBConnection;
import logger.VotoLogger;
import project.model.Concorrente;
import project.model.Partito;
import project.model.Sessione;

public class SessioneDAO implements GenericDAO<Sessione>{

	@Override
	public Sessione get(String id) throws Exception {
		String query="SELECT * FROM sessione WHERE id=?";
		Sessione s=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			DBConnection.getInstance().openConnection();
			ps=DBConnection.getInstance().prepara(query);
			ps.setInt(1, Integer.parseInt(id));
			rs=ps.executeQuery();
			while(rs.next()) {
				s=new Sessione(rs.getInt("id"),rs.getString("tipologia"), rs.getString("vittoria"),rs.getBoolean("stato"), rs.getString("domanda"));
			}			
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("Error: ", Level.WARNING, e);
		}
		return s;
	}

	@Override
	public List<Sessione> getAll(){
		String query="SELECT * FROM sessione";
		List<Sessione> l=new ArrayList<Sessione>();
		ResultSet rs= null;
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				l.add(new Sessione(rs.getInt("id"),rs.getString("tipologia"),rs.getString("vittoria"),rs.getBoolean("stato"),rs.getString("domanda")));
			}
			DBConnection.getInstance().closeConnection();
		}catch(Exception e) {
			VotoLogger.writeToLog("Error: ", Level.WARNING, e);
		}
		return l;
	}

	@Override
	public void delete(Sessione t) {
		String query="DELETE FROM sessione WHERE id=?";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, t.getId());
			ps.executeQuery();
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("Error: ", Level.WARNING, e);
		}
	}

	@Override
	public void save(Sessione t) {
		ConcorrenteDAO c = new ConcorrenteDAO();
		List<Partito> l =t.getPartiti();
		String query="INSERT INTO sessione(tipologia, vittoria, domanda, stato) VALUES(?,?,?,?)";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, t.getTipologia());
			ps.setString(2, t.getVittoria());
			ps.setString(3, t.getDomanda());
			ps.setBoolean(4, t.getStato());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			int x=0;
			while(rs.next()) {
				x =(rs.getInt(1));
			}
			for(int i=0;i<l.size();i++) {
				query="INSERT INTO partecipazione(candidato,sessione) VALUES(?,?)";
				ps=DBConnection.getInstance().prepara(query);
				ps.setInt(1, l.get(i).getId());
				ps.setInt(2, x);
				ps.executeUpdate();
			}
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("Error: ", Level.WARNING, e);
		}
	}
	
	public void close(int id) {
		String query="UPDATE sessione SET stato=false WHERE id=?";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("Error: ", Level.WARNING, e);
		}
	}
	
	
	
	
	
}
