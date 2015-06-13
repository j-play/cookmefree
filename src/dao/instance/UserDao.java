package dao.instance;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.RecipeModelBean;
import model.UserModelBean;

public class UserDao implements Serializable{
	
	private static final long serialVersionUID = 1L;
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
	
	/**
	 * Add a new user in the database
	 * @param user UserModelBean
	 * @return true is the user has been successfully registered, false either
	 */
	public boolean addUser(UserModelBean user) {
		
		Integer rowCount;
		
		try {

			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME, dB_USER, dB_PWD);
			
			String sql = "INSERT INTO user (login, pwd, surname, lastname, age, mail, isAdmin) VALUES(?,?,?,?,?,?,false)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPwd());
			statement.setString(3, user.getSurname());
			statement.setString(4, user.getLastname());
			statement.setInt(5, user.getAge());
			statement.setString(6, user.getMail());
			
			rowCount = statement.executeUpdate();
			
			connection.close(); 
			
			if(rowCount != 1){
				return false;
			}
			else{
				return true;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Delete the user with the specified id
	 * @param id user identifiant
	 * @return true if the user has been deleted, false either
	 */
	public boolean deleteUser(Integer id) {
		
		Integer rowCount;
		
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME, dB_USER, dB_PWD);
			
			String sql = "DELETE FROM USER WHERE ID = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			rowCount = statement.executeUpdate();
			
			connection.close(); 
			
			if(rowCount != 1){
				return false;
			}
			else{
				return true;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Update the specified user
	 * @param user UserModelBean
	 * @return true if the user has been updated, false either
	 */
	public boolean updateUser(UserModelBean user) {
		
		Integer rowCount;
		
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME, dB_USER, dB_PWD);
			
			String sql = "UPDATE USER SET login = ?, surname = ?, lastname = ?, age = ?, mail = ?, isAdmin = ? WHERE ID = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getSurname());
			statement.setString(3, user.getLastname());
			statement.setInt(4, user.getAge());
			statement.setString(5, user.getMail());
			statement.setBoolean(6, user.getIsAdmin());
			statement.setInt(7, user.getId());
			
			rowCount = statement.executeUpdate();
			
			connection.close(); 
			
			if(rowCount != 1){
				return false;
			}
			else{
				return true;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Return all users from the database
	 * @return ArrayList<UserModelBean> 
	 */
	public ArrayList<UserModelBean> getAllUser(){ 
		ArrayList<UserModelBean> userList=new ArrayList<UserModelBean>();
	    // CreÌ�ation de la requeÌ‚te
		
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME, dB_USER, dB_PWD);
			
			java.sql.Statement query = connection.createStatement(); 
			ResultSet rs = query.executeQuery("select * from user"); 
			
			while (rs.next()){ 
				userList.add(new UserModelBean(rs.getInt("id"), rs.getString("lastname"), rs.getString("surname"), 
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
	 * Return the specified user
	 * @param login
	 * @param password
	 * @return UserModelBean if the user exists, null either
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
				return new UserModelBean(rs.getInt("id"),rs.getString("lastname"), rs.getString("surname"), 
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
	

	public UserModelBean getUserById(int id){ 
		UserModelBean user = null;
		
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME, dB_USER, dB_PWD);
			
			java.sql.PreparedStatement query = connection.prepareStatement("select * from user where id=?");
			query.setInt(1, id);
			ResultSet rs = query.executeQuery(); 
			
			if(rs.next()){
				user = new UserModelBean(rs.getInt("id"),rs.getString("lastname"),rs.getString("surname"), rs.getInt("age"),rs.getString("mail"), rs.getString("login"),
						rs.getString("pwd"), rs.getBoolean("isAdmin"));
			}
			else{
				user = null;
			}
			connection.close();
		} 
		catch (SQLException e) { 
			e.printStackTrace();
		}
		return user;
	}
	
	
}
