package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dbconnection.DBConnection;
import project.model.Candidato;
import project.model.Concorrente;
import project.model.Partito;

public class ConcorrenteDAO implements GenericDAO<Concorrente> {

	@Override
	public Concorrente get(String id) {
		Concorrente c=null;
		String query="SELECT * FROM candidato WHERE id=?";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1,  id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getBoolean("is_partito"))
					c=new Partito(rs.getInt(0), rs.getString("nome"), "", -1, 1);
				else
					c=new Candidato(rs.getInt("id"), rs.getString("nome"), rs.getString("cognome"), rs.getInt(3), 0);
			}
		}catch(SQLException e) {
			//VotoLogger.writeToLog("error: ", Level.WARNING, e);
		}
		return c;
	}

	@Override
	public List<Concorrente> getAll() {
		return null;
	}

	@Override
	public void update(Concorrente t, String[] dati) {
		
		
	}

	@Override
	public void delete(Concorrente t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Concorrente t) {
		// TODO Auto-generated method stub
		
	}
	
	public void saveCandidato(Candidato c) {
		String query="INSERT INTO candidato(id, nome, cognome, id_partito, is_partito) VALUES (?,?,?,?,0)";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(0, c.getId());
			ps.setString(1, c.getNome());
			ps.setString(2, c.getCognome());
			ps.setInt(3, c.getPartito());
			ps.setInt(4, 0);
			ps.executeUpdate();
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			//VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
	}

}
