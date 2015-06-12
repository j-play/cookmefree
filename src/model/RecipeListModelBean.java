package model;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import processing.RecipeControllerBean;

@ManagedBean
@ViewScoped
public class RecipeListModelBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private RecipeControllerBean recipeController;
	private List<RecipeModel> recipeList;
	private RecipeModel selectedRecipe;
	
	// Constructor
	public RecipeListModelBean() {
		this.recipeController = new RecipeControllerBean();
		this.recipeList = this.recipeController.getAllRecipes();
		this.selectedRecipe = null;
	}
	
	/*
	 * GETTERS / SETTERS
	 */
	public List<RecipeModel> getRecipeList(){
		this.recipeList = this.recipeController.getAllRecipes();
		return this.recipeList;
	}

	public RecipeModel getSelectedRecipe() {
		return selectedRecipe;
	}

	public void setSelectedUser(RecipeModel selectedRecipe) {
		this.selectedRecipe = selectedRecipe;
	}
	
	/**
	 * Reset the selected recipe in the datatable
	 */
	public void resetSelectedUser(){
		this.selectedRecipe = null;
	}
	
	/**
	 * Delete the specified recipe from the database
	 * @param RecipeModel
	 */
	public void deleteUser(RecipeModel recipe){
		
		if(this.recipeController.deleteRecipe(recipe.getId())){
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
	 * Update the specified recipe and his data
	 * @param RecipeModel
	 */
	public void updateUser(RecipeModel recipe){
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
	 * Add the specified recipe and his data
	 * @param RecipeModel
	 */
	public void registerUser(RecipeModel recipe){
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
