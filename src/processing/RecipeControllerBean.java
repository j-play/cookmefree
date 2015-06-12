package processing;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import model.RecipeModel;
import model.SearchModelBean;
import dao.fabric.DaoFabric;
import dao.instance.RecipesDao;

@ManagedBean(name="recipeControl")
@SessionScoped
public class RecipeControllerBean implements Serializable {
	//ATTRIBUTES
	private static final long serialVersionUID = 1L;
	private RecipesDao recipeDao;
	private RecipeModel specificRecipe;
	private List<RecipeModel> recipeList;
	private SearchModelBean search;

	//MUTATORS
	public SearchModelBean getSearch() {
		return search;
	}
	public void setSearch(SearchModelBean search) {
		this.search = search;
	}
	public List<RecipeModel> getRecipeList() {
		return recipeList;
	}
	public RecipeModel getSpecificRecipe() {
		return specificRecipe;
	}
	
	//CONSTRUCTORS
	public RecipeControllerBean() {
		this.recipeDao=DaoFabric.getInstance().createRecipesDao();
		this.search = new SearchModelBean();
	}
	
	//METHODS
	public void loadAllRecipe(){
		recipeList = this.recipeDao.getAllRecipes();
		//reÌ�cupeÌ€re l'espace de meÌ�moire de JSF
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		//place la liste de recette dans l'espace de meÌ�moire de JSF
		sessionMap.put("recipeList", recipeList);
	}
	
	public String goToResult(){
		recipeList=recipeDao.getRecipesByCriteria(search);
		return("resultRecipe.jsf?faces-redirect=true");
	}
	
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
