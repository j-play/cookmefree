package processing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import model.RecipeListModelBean;
import model.RecipeModel;
import model.UserModelBean;
import model.UserSubmissionModelBean;
import dao.fabric.DaoFabric;
import dao.instance.RecipesDao;

@ManagedBean(name="recipeControl")
@SessionScoped
public class RecipeControllerBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private RecipeModel specificRecipe;
	private RecipesDao recipeDao;

	public RecipeModel getSpecificRecipe() {
		return specificRecipe;
	}

	/**
	 * retrieve a recipe from its id
	 * @return RecipeModel
	 */
	public RecipeModel getRecipeById(int id) {
		return recipeDao.getRecipesByID(id);
	}
	
	public RecipeControllerBean() {
		this.recipeDao=DaoFabric.getInstance().createRecipesDao();
	}
	
	/**
	 * 
	 */
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
	
	/**
	 * 
	 * @return
	 */
	public String goToResult(){

		//specificRecipe = this.recipeDao.getRecipesByID(1);
		//return("showRecipe.jsf?faces-redirect=true");
		return("resultRecipe.jsf?faces-redirect=true");

	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String goToShowRecipe(int id){
		specificRecipe = this.recipeDao.getRecipesByID(id);
		return("showRecipe.jsf?faces-redirect=true");
	}
	
	/**
	 * Retrieve the list of the existing recipes
	 * @return List<RecipeModel>
	 */
	public List<RecipeModel> getAllRecipes(){
		return this.recipeDao.getAllRecipes();
	}
	
	/**
	 * Delete the specified recipe
	 * @param id recipe identifiant
	 * @return true is the recipe has been successfully deleted, false otherwise
	 */
	public boolean deleteRecipe(Integer id){
		return this.recipeDao.deleteRecipe(id);
	}
	
	/**
	 * Update the specified recipe
	 * @param user UserModelBean
	 * @return true is the recipe has been successfully updated, false otherwise
	 */
	public boolean updateRecipe(RecipeModel recipe){
		return this.recipeDao.updateRecipe(recipe);
	}
	
	/**
	 * Add a new recipe in the database
	 * @param recipe 
	 * @return true is the recipe has been successfully added, false otherwise
	 */
	public boolean addRecipe(RecipeModel recipe){
		return this.recipeDao.addRecipe(recipe);
	}
}
