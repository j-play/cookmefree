package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.UserModel;

public class DB {
	private static final String DB_HOST="localhost";   
	private static final String DB_PORT="3306";
	private static final String DB_NAME="cookme";
	private static final String DB_USER="root";
	private static final String DB_PWD="root";
	private Connection connection;
	public DB() { 
		try {
			// Chargement du Driver, puis établissement de la connexion
			Class.forName("com.mysql.jdbc.Driver");
			//create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME, DB_USER, DB_PWD);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
	        e.printStackTrace();
		} 
	}
	
	public ArrayList<UserModel> getData(){ //return value
		ArrayList<UserModel> userList=new ArrayList<UserModel>();
	    // Création de la requête
		java.sql.Statement query = null; 
		try {
			//TODO récupérez l’ensemble des paramètres de tous les utilisateurs de la table ((`surname`, `lastname`, `age`, `login`, `pwd`)
			
			ResultSet rs = query.executeQuery("select * from user"); 
			
			while (rs.next()){ 
				userList.add(new UserModel(rs.getString("lastname"), rs.getString("surname"),
						rs.getInt("age"), rs.getString("login"), rs.getString("pwd"))
				); 
			}
			
			connection.close();
		} 
		catch (SQLException e) { 
			e.printStackTrace();
		}
		return userList;
	}
	
	public void addUser(UserModel user) {
	     // Création de la requête
		java.sql.Statement query; 
		try {
			//Creation de l'élément de requète
			query = connection.createStatement();
			//TODO créez la requête permettant d’ajout un utilisateur avec ts ces paramètres //((`surname`, `lastname`, `age`, `login`, `pwd`)
			connection.close(); 
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
