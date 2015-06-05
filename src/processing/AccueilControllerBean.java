package processing;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="accueilControl")
@ApplicationScoped
public class AccueilControllerBean {
	
	public String goToRecipes(){
		return("searchRecipe.jsf?faces-redirect=true");
	}
	
	public String goToAccueil(){
		return("index.jsf?faces-redirect=true");
	}
}

