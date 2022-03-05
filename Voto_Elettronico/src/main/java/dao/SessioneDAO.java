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
				s=new Sessione(rs.getInt("id"),rs.getString("tipologia"), rs.getString("vittoria"), rs.getString("domanda"), true);
			}
			/*if(!rs.getString("tipologia"),equalsIgnoreCase("referendum")) {
				DBConnection.getInstance().openConnection();
				
			}*/
			
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("Error: ", Level.WARNING, e);
		}
		return s;
	}

	@Override
	public List<Sessione> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Sessione t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Sessione t) {
		// TODO Auto-generated method stub
		
	}

}
