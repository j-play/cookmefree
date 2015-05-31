package dao.instance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserModelBean;

public class UserDao {
	
	private static String dB_HOST;   
	private static String dB_PORT;
	private static String dB_NAME;
	private static String dB_USER;
	private static String dB_PWD;
	private Connection connection;
	
	public UserDao(String DB_HOST,String DB_PORT, String DB_NAME,String DB_USER,String DB_PWD) {
	       dB_HOST = DB_HOST;
	       dB_PORT = DB_PORT;
	       dB_NAME = DB_NAME;
	       dB_USER = DB_USER;
	       dB_PWD = DB_PWD;
	}
	
	public void addUser(UserModelBean user) {
		try {
			//TODO créez la requête permettant d’ajout un utilisateur avec ts ces paramètres //((`surname`, `lastname`, `age`, `login`, `pwd`)
			
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME, dB_USER, dB_PWD);
			
			String sql = "INSERT INTO user (login, pwd, surname, lastname, age, mail) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPwd());
			statement.setString(3, user.getSurname());
			statement.setString(4, user.getLastname());
			statement.setInt(5, user.getAge());
			statement.setString(6, user.getMail());
			statement.setBoolean(7, user.getIsAdmin());
			
			statement.executeUpdate();
			
			connection.close(); 
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<UserModelBean> getAllUser(){ //return value
		ArrayList<UserModelBean> userList=new ArrayList<UserModelBean>();
	    // Création de la requête
		
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME, dB_USER, dB_PWD);
			
			//TODO récupérez l’ensemble des paramètres de tous les utilisateurs de la table ((`surname`, `lastname`, `age`, `login`, `pwd`)
			java.sql.Statement query = connection.createStatement(); 
			ResultSet rs = query.executeQuery("select * from user"); 
			
			while (rs.next()){ 
				userList.add(new UserModelBean(rs.getString("lastname"), rs.getString("surname"), 
						rs.getInt("age"), rs.getString("mail"), rs.getString("login"), rs.getString("pwd"), 
						rs.getBoolean("isAdmin"))
				); 
			}
			
			connection.close();
		} 
		catch (SQLException e) { 
			e.printStackTrace();
		}
		return userList;
	}
	
	/**
	 * Renvoie l'utilisateur avec le login/pwd spécifié si il existe
	 * @param login
	 * @param password
	 * @return UserModelBean si l'utilisateur existe, null sinon
	 */
	public UserModelBean checkUser(String login, String password){
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME, dB_USER, dB_PWD);
			String sql = "select * from user WHERE login = ? AND pwd = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			statement.setString(2, password);
			
			ResultSet rs = statement.executeQuery();
			statement.execute();
			
			while (rs.next()){ 
				return new UserModelBean(rs.getString("lastname"), rs.getString("surname"), 
						rs.getInt("age"), rs.getString("mail"), rs.getString("login"), rs.getString("pwd"), 
						rs.getBoolean("isAdmin")); 
			}
			
			connection.close(); 
		}
		catch (SQLException e) { 
			e.printStackTrace();
		}
		return null;
		
	}
	
}
