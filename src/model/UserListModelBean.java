package model;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import processing.UserControllerBean;

/*
 * This model bean represents a list of users
 */
@ManagedBean
@ViewScoped
public class UserListModelBean implements Serializable {

	///////////////ATTRIBUTES
	private static final long serialVersionUID = 1L;
	private UserControllerBean userController;
	private List<UserModelBean> userList;
	private UserModelBean selectedUser;
	
	//////////////CONSTRUCTOR
	public UserListModelBean() {
		super();
		this.userController = new UserControllerBean();
		this.userList = this.userController.getAllUsers();
		this.selectedUser = null;
	}
	
	/////////////MUTATORS
	public List<UserModelBean> getUserList(){
		this.userList = this.userController.getAllUsers();
		return this.userList;
	}

	public UserModelBean getSelectedUser() {
		return selectedUser;
	}
	public void setSelectedUser(UserModelBean selectedUser) {
		this.selectedUser = selectedUser;
	}
	
	/**
	 * Reset the selected user in the datatable
	 */
	public void resetSelectedUser(){
		this.selectedUser = null;
	}
	
	/**
	 * Delete the specified user from the database
	 * @param user
	 */
	public void deleteUser(UserModelBean user){
		
		if(this.userController.deleteUser(user.getId())){
			this.resetSelectedUser();
			FacesMessage msg;
	        msg = new FacesMessage("The user has been deleted.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		else{
			FacesMessage msg;
	        msg = new FacesMessage("There was a problem trying to delete this user.");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	/**
	 * Update the specified user and his data
	 */
	public void updateUser(UserModelBean user){
		if(this.userController.updateUser(user)){
			this.selectedUser = null;
			FacesMessage msg;
	        msg = new FacesMessage("The user has been updated.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		else{
			// Error case
			FacesMessage msg;
	        msg = new FacesMessage("There was a problem trying to update this user.");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	/**
	 * Add the specified user and his data
	 */
	public void registerUser(UserSubmissionModelBean user){
		if(this.userController.registerUserAdmin(user)){
			FacesMessage msg;
	        msg = new FacesMessage("The user has been added.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		else{
			// Error case
			FacesMessage msg;
	        msg = new FacesMessage("There was a problem trying to add this user.");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
}
