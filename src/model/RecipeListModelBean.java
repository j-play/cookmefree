package model;

import java.util.ArrayList;
import java.util.List;

public class RecipeListModelBean {
	private List<RecipeModel> recipeList;
	
	public RecipeListModelBean() {
		recipeList=new ArrayList<RecipeModel>();
	}
	public void addRecipeList(RecipeModel recipe){
		this.recipeList.add(recipe);
	}
	
	public List<RecipeModel> getRecipeList() { 
		return recipeList;
	} 
}

