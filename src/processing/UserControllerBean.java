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

@ManagedBean(name="userControl")
@ApplicationScoped // Utilisation de application scope afin d'offrir un point d'entrée unique à l'ensemble des clients
public class UserControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private UserDao userDao; 
	
	public UserControllerBean() {
		this.userDao=DaoFabric.getInstance().createUserDao();
	}
	
	public void checkUser(LoginBean loginBean){
		UserModelBean user = this.userDao.checkUser(loginBean.getLogin(),loginBean.getPwd());
		
		if( user!=null){
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
	
	public ArrayList<UserModelBean> getAllUsers(){
		return this.userDao.getAllUser();
	}
	
	public boolean deleteUser(Integer id){
		return this.userDao.deleteUser(id);
	}

}
