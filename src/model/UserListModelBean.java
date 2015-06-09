package model;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.fabric.DaoFabric;
import dao.instance.UserDao;

@ManagedBean
@ViewScoped
public class UserListModelBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private List<UserModelBean> userList;
	private UserModelBean selectedUser;
	
	public UserListModelBean() {
		this.userDao = DaoFabric.getInstance().createUserDao();
		this.userList = this.userDao.getAllUser();
		this.selectedUser = null;
	}
	
	public List<UserModelBean> getUserList(){
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
	
}
