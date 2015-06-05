package processing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import model.RecipeListModelBean;
import model.RecipeModel;
import dao.fabric.DaoFabric;
import dao.instance.RecipesDao;

@ManagedBean(name="recipeControl")
@ApplicationScoped
public class RecipeControllerBean { 
	private RecipesDao recipeDao;
	
	public RecipeControllerBean() {
		this.recipeDao=DaoFabric.getInstance().createRecipesDao();
	}
	
	public void loadAllRecipe(){
		ArrayList<RecipeModel> list = this.recipeDao.getAllRecipes();
		RecipeListModelBean recipeList = new RecipeListModelBean();
		for(RecipeModel recipe:list){
            recipeList.addRecipeList(recipe);
		}
		//reÌ�cupeÌ€re l'espace de meÌ�moire de JSF
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		//place la liste de recette dans l'espace de meÌ�moire de JSF
		sessionMap.put("recipeList", recipeList);
	}
	
	public void loadRecipeByID(){
		RecipeModel recipe = this.recipeDao.getRecipesByID(1);
		if(recipe != null){
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("recipe", recipe);
			FacesContext.getCurrentInstance().addMessage(null, 
			        new FacesMessage(null, "<h1>Recette récupérée avec succès</h1>")
			);
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, 
			        new FacesMessage(null, "<h1>Erreur dans la récupération de la recette</h1>")
			);
		}
	}
	
	public void goToResult(){
		this.loadRecipeByID();
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect("showRecipe.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
