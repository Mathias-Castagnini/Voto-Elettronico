package dao;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import dbconnection.DBConnection;
import logger.VotoLogger;
import project.model.Elettore;
import project.model.Scrutinatore;
import project.model.Utente;

public class UtenteDAO implements GenericDAO<Utente>{

	@Override
	public Utente get(String cd) throws Exception {
		Utente u = null;
		String query = "Select * FROM utente WHERE cod_fisc = ?;";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1,  cd);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString("ruolo").equalsIgnoreCase("Elettore")) {
					u=new Elettore(rs.getString("nome"),rs.getString("cognome"),rs.getString("cod_fisc"),rs.getString("password"),"elettore");
				}else {
					u=new Scrutinatore(rs.getString("nome"),rs.getString("cognome"),rs.getString("cod_fisc"),rs.getString("password"),"scrutinatore");
				}
			}
			DBConnection.getInstance().closeConnection();
		}catch (SQLException e) {
			VotoLogger.writeToLog("Error:", Level.WARNING, e);
		}
		return u;
	}

	public List<Utente> getAll() {
		List<Utente> l= new ArrayList<Utente>();
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara("SELECT * FROM utente");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString("ruolo").equalsIgnoreCase("elettore")) {
					l.add(new Elettore(rs.getString("nome"),rs.getString("cognome"),rs.getString("cod_fisc"),rs.getString("password")));
				}else {
					l.add(new Scrutinatore(rs.getString("nome"),rs.getString("cognome"),rs.getString("cod_fisc"),rs.getString("password")));
				}
				
			}
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("Error:", Level.WARNING, e);
		}
		return l;
	}

	public void update(Utente t, String n, String c, String cf, String p, String r) {
		String query = "UPDATE utente SET nome = ?, cognome = ?, cod_fisc=?, password=?, ruolo=? WHERE cod_fisc = ?;";
		String cod=cf;
		if(n==null) n=t.getNome();
		if(c==null) c=t.getCognome();
		if(cf==null) cf=t.getNome();
		if(p==null) p=t.getNome();
		if(r==null) r=t.getNome();
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1, n);
			ps.setString(2, c);
			ps.setString(3, cf);
			ps.setString(4, p);
			ps.setString(5, r);
			ps.setString(6, cod);
			ps.executeUpdate();
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("Error:", Level.WARNING, e);
		}
	}

	public void delete(Utente t) {
		String query = "DELETE FROM utente WHERE cod_fisc=?;";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1, t.getCod_fiscale());
			ps.executeUpdate();
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e){
			VotoLogger.writeToLog("Error:", Level.WARNING, e);
		}
	}

	public void save(Utente u) {
		String query = "INSERT INTO utente(nome,cognome,cod_fisc,pssword,ruolo) VALUES(?,?,?,?,?)";
		if(u==null) throw new NullPointerException();
		String ruolo="";
		if(u.isElettore()) 
			ruolo="Elettore";
		else
			ruolo="Scrutinatore";
		
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1, u.getNome()); 
			ps.setString(2, u.getCognome());
			ps.setString(3, u.getCod_fiscale());
			ps.setString(4, u.getPassword());
			ps.setString(5, ruolo);
			ps.executeUpdate();
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("Error:", Level.WARNING, e);
		}
	}	
	
	public void save(Utente u, String psw) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest digest;
		String sha1 = null;
		try {
			digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(psw.getBytes("utf8"));
			sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
			String query ="INSERT INTO Utente(nome,cognome,cod_fisc,password,ruolo) VALUES(?,?,?,?,?);";
			if(u==null) throw new NullPointerException();
			String ruolo="";
			if(u.isElettore())
				ruolo="elettore";
			else
				ruolo ="scrutinatore";
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1, u.getNome()); 
			ps.setString(2, u.getCognome());
			ps.setString(3, u.getCod_fiscale());
			ps.setString(4, sha1);
			ps.setString(5, ruolo);
			ps.executeUpdate();
			DBConnection.getInstance().closeConnection();
		}catch(SQLException e) {
			VotoLogger.writeToLog("Error:", Level.WARNING, e);
		}
		
		
	}

	
}
