package model;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import dao.fabric.DaoFabric;
import dao.instance.UserDao;

@ManagedBean
@RequestScoped
public class UserListModelBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private List<UserModelBean> userList;
	
	public UserListModelBean() {
		this.userDao = DaoFabric.getInstance().createUserDao();
		this.userList = this.userDao.getAllUser();
	}
	
	public List<UserModelBean> getUserList(){
		return this.userList;
	}
}
