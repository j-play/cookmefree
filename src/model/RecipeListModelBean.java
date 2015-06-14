package model;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import processing.RecipeControllerBean;

/*
 * This model bean represents a list of recipes
 */
@ManagedBean
@ViewScoped
public class RecipeListModelBean implements Serializable {

	///////////////ATTRIBUTES
	private static final long serialVersionUID = 1L;
	private RecipeControllerBean recipeController;
	private List<RecipeModelBean> recipeList;
	private RecipeModelBean selectedRecipe;
	
	/////////////CONSTRUCTOR
	public RecipeListModelBean() {
		this.recipeController = new RecipeControllerBean();
		this.recipeList = this.recipeController.getAllRecipes();
		this.selectedRecipe = null;
	}
	
	//////////////MUTATORS
	public List<RecipeModelBean> getRecipeList(){
		this.recipeList = this.recipeController.getAllRecipes();
		return this.recipeList;
	}

	public RecipeModelBean getSelectedRecipe() {
		return selectedRecipe;
	}
	public void setSelectedRecipe(RecipeModelBean selectedRecipe) {
		this.selectedRecipe = selectedRecipe;
	}
	
	/**
	 * Reset the selected recipe in the datatable
	 */
	public void resetSelectedRecipe(){
		this.selectedRecipe = null;
	}
	
	/**
	 * Delete the specified recipe from the database
	 * @param RecipeModelBean
	 */
	public void deleteRecipe(RecipeModelBean recipe){
		
		if(this.recipeController.deleteRecipe(recipe.getId())){
			this.resetSelectedRecipe();
			FacesMessage msg;
	        msg = new FacesMessage("The recipe has been deleted.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		else{
			// Error case
			FacesMessage msg;
	        msg = new FacesMessage("There was a problem trying to delete this recipe.");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	/**
	 * Update the specified recipe and its data
	 * @param RecipeModelBean
	 */
	public void updateRecipe(RecipeModelBean recipe){
		if(this.recipeController.updateRecipe(recipe)){
			this.selectedRecipe = null;
			FacesMessage msg;
	        msg = new FacesMessage("The recipe has been updated.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		else{
			// Error case
			FacesMessage msg;
	        msg = new FacesMessage("There was a problem trying to update this recipe.");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	/**
	 * Add the specified recipe and its data
	 * @param RecipeModelBean
	 */
	public void addRecipe(RecipeModelBean recipe){
		if(this.recipeController.addRecipe(recipe)){
			FacesMessage msg;
	        msg = new FacesMessage("The recipe has been added.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		else{
			// Error case
			FacesMessage msg;
	        msg = new FacesMessage("There was a problem trying to add this recipe.");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
}
