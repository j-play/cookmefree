package processing;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/*
 * This controller is used by the welcome page and by the template to set the action attributes of the buttons
 */
@ManagedBean(name="accueilControl")
@ApplicationScoped
public class AccueilControllerBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public String goToRecipes(){
		return("searchRecipe.jsf?faces-redirect=true");
	}
	
	public String goToAccueil(){
		return("/view/index.jsf?faces-redirect=true");
	}
	
	public String goToAdmin(){
		return("userAdmin.jsf?faces-redirect=true");
	}
	
	public String goToAdminRecipe(){
		return("recipeAdmin.jsf?faces-redirect=true");
	}
}

