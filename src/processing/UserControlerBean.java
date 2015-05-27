package processing;

import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import model.LoginBean;
import model.UserModelBean;
import model.UserSubmissionModelBean;
import dao.fabric.DaoFabric;
import dao.instance.UserDao;

@ManagedBean
@ApplicationScoped // Utilisation de application scope afin d'offrir un point d'entrée unique à l'ensemble des clients
public class UserControlerBean {
	private UserDao userDao; 
	
	public UserControlerBean() {
		this.userDao=DaoFabric.getInstance().createUserDao();
	}
	
	public String checkUser(LoginBean loginBean){
		UserModelBean user = this.userDao.checkUser(loginBean.getLogin(),loginBean.getPwd());
		
		if( user!=null){
			//récupère l'espace de mémoire de JSF
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			//place l'utilisateur dans l'espace de mémoire de JSF
		    sessionMap.put("loggedUser", user);
		    //redirect the current page
		    return "userdisplay.xhtml"; 
		}
		else{
			//redirect the current page
			return "userLogin.xhtml";
		}
	}
	
	/**
	 * Vérifie qu'un utilisateur n'existe pas encore en base de données puis l'ajoute
	 * @param userSubmitted utilisateur à ajouter
	 */
	public void checkAndAddUser(UserSubmissionModelBean userSubmitted){
		// On vérifie si l'utilisateur n'existe pas déjà
		if(!this.userDao.checkUserForSub(userSubmitted.getMail())){
			this.userDao.addUser(userSubmitted);
		}
	}

}
