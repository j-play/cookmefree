package processing;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="accueilControl")
@ApplicationScoped
public class AccueilControllerBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public String goToRecipes(){
		return("searchRecipe.jsf?faces-redirect=true");
	}
	
	public String goToAccueil(){
		return("index.jsf?faces-redirect=true");
	}
	
	public String goToAdmin(){
		return("userAdmin.jsf?faces-redirect=true");
	}
}

