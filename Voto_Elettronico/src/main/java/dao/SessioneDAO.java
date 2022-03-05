package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import dbconnection.DBConnection;
import logger.VotoLogger;
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
				s=new Sessione(rs.getInt("id"),rs.getString("tipologia"), rs.getString("vittoria"), rs.getString("domanda"));
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
		List<Sessione> l=null;
		ResultSet rs= null;
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				l.add(new Sessione(rs.getInt("id"),rs.getString("tipologia"),rs.getString("vittoria"),rs.getString("domanda")));
			}
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
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
		String query="INSERT INTO sessione(id, tipologia, vittoria, domanda, stato) VALUES(NULL,?,?,?,true)";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1, t.getTipologia());
			ps.setString(2, t.getVittoria());
			ps.setString(3, t.getDomanda());
			ps.setBoolean(4, t.getStato());
			ps.executeQuery();
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
			ps.executeQuery();
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("Error: ", Level.WARNING, e);
		}
	}
	
	
	
}
