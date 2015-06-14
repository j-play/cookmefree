package dao.fabric;

import java.io.Serializable;

import dao.instance.CommentDao;
import dao.instance.RecipesDao;
import dao.instance.UserDao;

/*
 * Cette classe sert à se connecter à la base de données et à créer des DAO concrets. C'est un singleton.
 */
public class DaoFabric implements Serializable{

	///////////////////ATTRIBUTES
	private static final long serialVersionUID = 1L;
	private static volatile DaoFabric instance = null;
	//////////////////CONNECTION INFO
	private static final String DB_HOST = "localhost"; 
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "cookme"; 
	private static final String DB_USER = "root"; 
	private static final String DB_PWD = "root";
	
	//////////////////PRIVATE CONSTRUCTOR
	private DaoFabric() { 
		super();
		try {
			//Chargement du Driver, puis eÌ�tablissement de la connexion 
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) { 
			e.printStackTrace();
		} 
	}
	
	//////////////////GET UNIQUE INSTANCE
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
	
	///////////////////CREATE DAO INSTANCES
	public UserDao createUserDao() { 
		UserDao userDao = new UserDao(this.DB_HOST,this.DB_PORT,this.DB_NAME,this.DB_USER,this.DB_PWD); 
		return userDao;
	}	
		
	public RecipesDao createRecipesDao(){ 
		RecipesDao receipesDao = new RecipesDao(this.DB_HOST,this.DB_PORT,this.DB_NAME,this.DB_USER,this.DB_PWD); 
		return receipesDao;
	}
	public CommentDao createCommentDao(){ 
		CommentDao commentDao = new CommentDao(this.DB_HOST,this.DB_PORT,this.DB_NAME,this.DB_USER,this.DB_PWD); 
		return commentDao;
	}
	
}