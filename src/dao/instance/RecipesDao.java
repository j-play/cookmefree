package dao.instance;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.RecipeModelBean;
import model.SearchModelBean;


public class RecipesDao implements Serializable{
	private static final long serialVersionUID = 1L;
	private static String dB_HOST;   
	private static String dB_PORT;
	private static String dB_NAME;
	private static String dB_USER;
	private static String dB_PWD;
	private Connection connection;
	
	public RecipesDao(String DB_HOST,String DB_PORT, String DB_NAME,String DB_USER,String DB_PWD) {
	       dB_HOST = DB_HOST;
	       dB_PORT = DB_PORT;
	       dB_NAME = DB_NAME;
	       dB_USER = DB_USER;
	       dB_PWD = DB_PWD;
	}
	
	/**
	 * Add a new recipe to the database
	 * @param recipe
	 * @return true if the recipe has been added, false either
	 */
	public boolean addRecipe(RecipeModelBean recipe) {
		
		Integer rowCount;
		
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME, dB_USER, dB_PWD);
			
			String sql = "INSERT INTO recipe (title, description, expertise, duration, nbpeople, type) VALUES(?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, recipe.getTitle());
			statement.setString(2, recipe.getDescription());
			statement.setInt(3, recipe.getExpertise());
			statement.setInt(4, recipe.getDuration());
			statement.setInt(5, recipe.getNbPeople());
			statement.setString(6, recipe.getType());
			
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
	 * Delete the recipe with the specified id
	 * @param id recipe identifiant
	 * @return true if the user has been deleted, false otherwise
	 */
	public boolean deleteRecipe(Integer id) {
		
		Integer rowCount;
		
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME, dB_USER, dB_PWD);
			
			String sql = "DELETE FROM RECIPE WHERE ID = ?";
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
	 * Update the specified recipe
	 * @param user RecipeModel
	 * @return true if the recipe has been updated, false otherwise
	 */
	public boolean updateRecipe(RecipeModelBean recipe) {
		
		Integer rowCount;
		
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME, dB_USER, dB_PWD);
			
			String sql = "UPDATE USER SET title = ?, description = ?, expertise = ?, duration = ?, nbPeople = ?, type = ? WHERE ID = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, recipe.getTitle());
			statement.setString(2, recipe.getDescription());
			statement.setInt(3, recipe.getExpertise());
			statement.setInt(4, recipe.getDuration());
			statement.setInt(5, recipe.getNbPeople());
			statement.setString(6, recipe.getType());
			statement.setInt(7, recipe.getId());
			
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
	 * Retrieve the list of the existing recipes
	 * @return ArrayList<RecipeModel>
	 */
	public ArrayList<RecipeModelBean> getAllRecipes(){ 
		ArrayList<RecipeModelBean> recipeList=new ArrayList<RecipeModelBean>();
		
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME, dB_USER, dB_PWD);
			
			java.sql.Statement query = connection.createStatement(); 
			ResultSet rs = query.executeQuery("select * from recipe"); 
			
			while (rs.next()){ 
				recipeList.add(new RecipeModelBean(rs.getInt("id"),rs.getString("title"), rs.getString("description"), rs.getInt("Expertise"),
						rs.getInt("duration"), rs.getInt("nbPeople"), rs.getString("type"))
				); 
			}
			
			connection.close();
		} 
		catch (SQLException e) { 
			e.printStackTrace();
		}
		return recipeList;
	}
	
	/**
	 * Récupération de la liste des recettes
	 * @return ArrayList contenant des beans RecipeModelBean
	 */
	public RecipeModelBean getRecipesByID(int id){ 
		RecipeModelBean recipe = null;
		
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME, dB_USER, dB_PWD);
			
			java.sql.PreparedStatement query = connection.prepareStatement("select * from recipe where id=?");
			query.setInt(1, id);
			ResultSet rs = query.executeQuery(); 
			
			if(rs.next()){
				recipe = new RecipeModelBean(rs.getString("title"), rs.getString("description"), rs.getInt("Expertise"),
						rs.getInt("duration"), rs.getInt("nbPeople"), rs.getString("type"));
			}
			else{
				recipe = null;
			}
			connection.close();
		} 
		catch (SQLException e) { 
			e.printStackTrace();
		}
		return recipe;
	}
	
	public ArrayList<RecipeModelBean> getRecipesByCriteria(SearchModelBean search){
		ArrayList<RecipeModelBean> returnV=new ArrayList<>();
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME, dB_USER, dB_PWD);
			String queryString="select * from recipe where ";
			boolean preceded=false;
			if(!search.isIndiff1()){
				if(preceded){
					queryString+="and ";
				}
				else{
					preceded=true;
				}
				queryString+="duration=? ";
			}
			if(!search.isIndiff2()){
				if(preceded){
					queryString+="and ";
				}
				else{
					preceded=true;
				}
				queryString+="expertise=? ";
			}
			if(!search.isIndiff3()){
				if(preceded){
					queryString+="and ";
				}
				else{
					preceded=true;
				}
				queryString+="nbPeople=? ";
			}
			if(!search.isIndiff4()){
				if(preceded){
					queryString+="and ";
				}
				else{
					preceded=true;
				}
				queryString+="type=? ";
			}
			
			if(!preceded){
				queryString+="1=1";
			}
			
			java.sql.PreparedStatement query = connection.prepareStatement(queryString);
			int nbParam=1;
			if(!search.isIndiff1()){
				query.setInt(nbParam, search.getDuration());
				nbParam++;
			}
			if(!search.isIndiff2()){
				query.setInt(nbParam, search.getExpertise());
				nbParam++;
			}
			if(!search.isIndiff3()){
				query.setInt(nbParam, search.getPeople());
				nbParam++;
			}
			if(!search.isIndiff4()){
				query.setString(nbParam, search.getType());
				nbParam++;
			}
			
			ResultSet rs = query.executeQuery();
			while (rs.next()){ 
				returnV.add(new RecipeModelBean(rs.getString("title"), rs.getString("description"), rs.getInt("Expertise"),
						rs.getInt("duration"), rs.getInt("nbPeople"), rs.getString("type"))
				);
			}
			if(returnV.isEmpty()){
				return null;
			}
			else{
				return returnV;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
