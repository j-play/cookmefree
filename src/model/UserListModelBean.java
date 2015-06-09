package model;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import processing.UserControllerBean;

@ManagedBean
@ViewScoped
public class UserListModelBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private UserControllerBean userController;
	private List<UserModelBean> userList;
	private UserModelBean selectedUser;
	
	public UserListModelBean() {
		this.userController = new UserControllerBean();
		this.userList = this.userController.getAllUsers();
		this.selectedUser = null;
	}
	
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
	
	public void resetSelectedUser(){
		this.selectedUser = null;
	}
	
	public void deleteUser(UserModelBean user){
		
		if(this.userController.deleteUser(user.getId())){
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
	
}
