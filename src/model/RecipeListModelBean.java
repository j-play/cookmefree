package model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.fabric.DaoFabric;
import dao.instance.RecipesDao;

@ManagedBean
@RequestScoped
public class RecipeListModelBean {
	private RecipesDao recipeDao;
	private List<RecipeModel> recipeList;
	
	public RecipeListModelBean() {
		this.recipeDao = DaoFabric.getInstance().createRecipesDao();
		this.recipeList = this.recipeDao.getAllRecipes();
	}
	public void addRecipeList(RecipeModel recipe){
		this.recipeList.add(recipe);
	}
	
	public List<RecipeModel> getRecipeList() { 
		return recipeList;
	} 
}
