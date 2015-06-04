package processing;

import java.io.IOException;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name="accueilControl")
@ApplicationScoped
public class AccueilControllerBean {
	
	public void goToRecipes(){
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
		try {
			context.redirect("searchRecipe.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void goToAccueil(){
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect("accueil.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

