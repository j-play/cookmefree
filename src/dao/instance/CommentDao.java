package dao.instance;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CommentModelBean;

/*
 * DAO pour récupérer les commentaires liés aux recettes
 */
public class CommentDao implements Serializable {
	
	/////////////////ATTRIBUTES
	private static final long serialVersionUID = 1L;
	private static String dB_HOST;   
	private static String dB_PORT;
	private static String dB_NAME;
	private static String dB_USER;
	private static String dB_PWD;
	private Connection connection;
	
	//////////////////CONSTRUCTOR
	public CommentDao(String DB_HOST,String DB_PORT, String DB_NAME,String DB_USER,String DB_PWD) {
	       dB_HOST = DB_HOST;
	       dB_PORT = DB_PORT;
	       dB_NAME = DB_NAME;
	       dB_USER = DB_USER;
	       dB_PWD = DB_PWD;
	}
	
	/**
	 * Retrieves the list of the comments for a recipe
	 * @return ArrayList<RecipeModel>
	 */
	public List<CommentModelBean> getCommentsByIdRecipe(int idRecipe){ 
		List<CommentModelBean> commentList = new ArrayList<CommentModelBean>();	
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME, dB_USER, dB_PWD);
			String sql = "SELECT * FROM comment WHERE idRecipe = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idRecipe);
			ResultSet rs = statement.executeQuery();
			while (rs.next()){ 
				commentList.add(new CommentModelBean(rs.getInt("id"),rs.getInt("idUser"), rs.getInt("idRecipe"), rs.getString("Content"),
						rs.getInt("mark"), rs.getDate("date"))
				); 
			}
			
			connection.close();
		} 
		catch (SQLException e) { 
			e.printStackTrace();
		}
		return commentList;
	}
	
	
	/**
	 * Add a new comment to the database
	 * @param commentModelBean
	 * @return true if the comment has been added, false otherwise
	 */
	public boolean addComment(CommentModelBean comment) {
		Integer rowCount;
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME, dB_USER, dB_PWD);
			
			String sql = "INSERT INTO comment (idUser, idRecipe, content, mark, date) VALUES(?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, comment.getIdUser());
			statement.setInt(2, comment.getIdRecipe());
			statement.setString(3, comment.getContent());
			statement.setInt(4, comment.getMark());
			statement.setDate(5, new java.sql.Date(comment.getDate().getTime()));
			
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

}
