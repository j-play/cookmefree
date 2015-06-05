package processing;

import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import model.RecipeListModelBean;
import model.RecipeModel;
import dao.fabric.DaoFabric;
import dao.instance.RecipesDao;

@ManagedBean(name="recipeControl")
@ApplicationScoped
public class RecipeControllerBean {
	@ManagedProperty(value="#{param.idRecipe}")
	private String idRecipe;
	
	private RecipesDao recipeDao;
	
	public String getIdRecipe() {
		return idRecipe;
	}

	public void setIdRecipe(String idRecipe) {
		this.idRecipe = idRecipe;
	}

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
		RecipeModel recipe = this.recipeDao.getRecipesByID(Integer.valueOf(idRecipe));
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getRequestMap();
		requestMap.put("recipe", recipe);
		FacesContext.getCurrentInstance().addMessage(null, 
		        new FacesMessage(null, "<h1>Recette récupérée avec succès</h1>")
		);
	}
	
	public String goToResult(){
		this.loadRecipeByID();
		return("showRecipe.jsf?faces-redirect=true");
	}
}
