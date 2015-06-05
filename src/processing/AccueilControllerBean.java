package processing;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="accueilControl")
@ApplicationScoped
public class AccueilControllerBean {
	
	public String goToRecipes(){
		return("searchRecipe.jsf");
	}
	
	public String goToAccueil(){
		return("accueil.jsf");
	}
}

