package dao.fabric;

import dao.instance.RecipesDao;
import dao.instance.UserDao;

public class DaoFabric {

	private static volatile DaoFabric instance = null;
	private static final String DB_HOST = "localhost"; 
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "cookme"; 
	private static final String DB_USER = "root"; 
	private static final String DB_PWD = "root";
	
	private DaoFabric() { 
		super();
		try {
			//Chargement du Driver, puis établissement de la connexion 
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) { 
			e.printStackTrace();
		} 
	}
	
	public final static DaoFabric getInstance(){
		if(DaoFabric.instance == null){
			synchronized(DaoFabric.class){
				if(DaoFabric.instance == null){
					DaoFabric.instance = new DaoFabric();
				}
			}
		}
		return DaoFabric.instance;
	}
	
	public UserDao createUserDao() { 
		UserDao userDao = new UserDao(this.DB_HOST,this.DB_PORT,this.DB_NAME,this.DB_USER,this.DB_PWD); 
		return userDao;
	}	
		
	public RecipesDao createRecipesDao(){ 
		RecipesDao receipesDao = new RecipesDao(this.DB_HOST,this.DB_PORT,this.DB_NAME,this.DB_USER,this.DB_PWD); 
		return receipesDao;
	}
}