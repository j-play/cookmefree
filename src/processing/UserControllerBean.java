package processing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import model.LoginBean;
import model.UserModelBean;
import model.UserSubmissionModelBean;
import dao.fabric.DaoFabric;
import dao.instance.UserDao;

/*
 * This controller is used for every action concerning users
 */
@ManagedBean(name="userControl")
@ApplicationScoped
public class UserControllerBean implements Serializable {

	//////////////ATTRIBUTES
	private static final long serialVersionUID = 1L;
	private UserDao userDao; 
	
	//////////////CONSTRUCTOR
	public UserControllerBean() {
		this.userDao=DaoFabric.getInstance().createUserDao();
	}
	
	//////////////METHODS
	public void checkUser(LoginBean loginBean){
		UserModelBean user = this.userDao.checkUser(loginBean.getLogin(),loginBean.getPwd());
		
		if(user!=null){
			//récupère l'espace de mémoire de JSF
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			//place l'utilisateur dans l'espace de mémoire de JSF
		    sessionMap.put("loggedUser", user);
		    //redirect the current page
		    //return "userdisplay.xhtml"; 
		}
		else{
			//redirect the current page
			//return "userLogin.xhtml";
		}
	}
	
	/**
	 * Register a new user in the database
	 * @param userSubmitted
	 */
	public String registerUser(UserSubmissionModelBean userSubmitted){
		this.userDao.addUser(userSubmitted);
		return "accueil.jsf";
	}
	
	/**
	 * déconnecte l'utilisateur
	 */
	public void disconnectUser(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
	/**
	 * Return the list of the registered users
	 * @return ArrayList<UserModelBean>
	 */
	public ArrayList<UserModelBean> getAllUsers(){
		return this.userDao.getAllUser();
	}
	
	/**
	 * Delete the specified
	 * @param id user identifiant
	 * @return true is the user has been successfully deleted, false either
	 */
	public boolean deleteUser(Integer id){
		return this.userDao.deleteUser(id);
	}
	
	/**
	 * Update the specified
	 * @param user UserModelBean
	 * @return true is the user has been successfully updated, false either
	 */
	public boolean updateUser(UserModelBean user){
		return this.userDao.updateUser(user);
	}
	
	/**
	 * Register a new user in the database from the admin panel
	 * @param userSubmitted
	 */
	public boolean registerUserAdmin(UserSubmissionModelBean userSubmitted){
		return this.userDao.addUser(userSubmitted);
	}
	
	/**
	 *  Retrieve a user thank his id
	 * @param userSubmitted
	 */
	public UserModelBean getUserById(int id){
		return this.userDao.getUserById(id);
	}
	
	

}
