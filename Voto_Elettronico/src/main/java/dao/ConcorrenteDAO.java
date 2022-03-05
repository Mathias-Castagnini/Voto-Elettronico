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
import project.model.Candidato;
import project.model.Concorrente;
import project.model.Partito;

public class ConcorrenteDAO implements GenericDAO<Concorrente> {

	@Override
	public Concorrente get(String id) {
		Concorrente c = null;
		String query = "SELECT * FROM candidato WHERE id=?";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getBoolean("is_partito"))
					c = new Partito(rs.getInt("id"), rs.getString("nome"), -1);
				else
					c = new Candidato(rs.getInt("id"), rs.getString("nome"),rs.getString("cognome"),rs.getInt("id_partito"));
			}
		} catch (SQLException e) {
			VotoLogger.writeToLog("error: ", Level.WARNING, e);
		}
		return c;
	}

	@Override
	public List<Concorrente> getAll() {
		List<Concorrente> l=null;
		String query="SELECT * FROM candidato";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if(rs.getInt("is_partito")==1) {
					l.add(new Partito(rs.getInt("id"),rs.getString("nome"),-1));
				}else {
					l.add(new Partito(rs.getInt("id"),rs.getString("nome"),rs.getInt("id_partito")));
				}
			}
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("error: ", Level.WARNING, e);
		}
		return l;
	}

	public void update(Concorrente t, int id, String n, String c, int idp, int isp) {
		String query="UPDATE candidato SET nome=?, cognome = ?, id_partito=?, is_partito=? WHERE id = ?";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1, n);
			ps.setString(2, c);
			ps.setInt(3, idp);
			ps.setInt(4, isp);
			ps.executeUpdate();
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("error: ", Level.WARNING, e);
		}
	}

	@Override
	public void delete(Concorrente t) {	
	}

	@Override
	public void save(Concorrente t) {
	}

	public void saveCandidato(Candidato c) {
		String query = "INSERT INTO candidato(id, nome, cognome, id_partito, is_partito) VALUES (?,?,?,?,0)";
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
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
	}

	public List<Candidato> getCandidati(int id_partito) {
		List<Candidato> l = new ArrayList<Candidato>();
		String query = "SELECT * FROM candidato WHERE id_partito=? AND is_partito=0";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, id_partito);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Candidato c = new Candidato(rs.getInt("id"), rs.getString("nome"), rs.getString("cognome"), id_partito);
				l.add(c);
			}
			DBConnection.getInstance().closeConnection();
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		return l;
	}

	public List<Partito> getPartiti() {
		List<Partito> l = new ArrayList<Partito>();
		String query = "SELECT * FROM candidato WHERE is_partito=1";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Partito p = new Partito(rs.getInt("id"), rs.getString("nome"), -1);
				l.add(p);
			}
			DBConnection.getInstance().closeConnection();
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		return l;
	}

	public Partito getPartito(int id) {
		Partito p = null;
		String query = "SELECT * FROM candidato WHERE is_partito=1 AND candidato.id=NULL";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new Partito(rs.getInt("id"), rs.getString("nome"), -1);
			}
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		return p;
	}

	public void addPartito(Partito p) {
		String query = "INSERT INTO candidato(id, nome, cognome,id_partito, is_partito) VALUES(NULL, ?, NULL, NULL, 1)";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, p.getNome());
			ps.executeUpdate();
			//ResultSet rs = ps.getGeneratedKeys();
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
	}

	public void addCandidato(Candidato c) {
		String query = "INSERT INTO candidato(id,nome,cognome,id_partito,is_partito) VALUES(NULL,?,?,?,0)";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1, c.getNome());
			ps.setString(2, c.getCognome());
			ps.setInt(3, c.getPartito());
			ps.executeUpdate();
			DBConnection.getInstance().closeConnection();
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
	}

	public void updateCoandidato(Candidato c, int p) {
		String query = "UPDATE candidato SET nome=?, cognome=?, id_partito=? WHERE id=?";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1, c.getNome());
			ps.setString(2, c.getCognome());
			ps.setInt(3, p);
			ps.setInt(4, c.getId());
			ps.executeUpdate();
			DBConnection.getInstance().openConnection();
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
	}

}
