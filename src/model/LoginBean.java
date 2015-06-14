package model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/*
 * This model bean is used to log in
 */
@ManagedBean
@RequestScoped
public class LoginBean implements Serializable{
	
	///////////////ATTRIBUTES
	private static final long serialVersionUID = 1L;
	private String login; 
	private String pwd;

	///////////////CONSTRUCTORS
	public LoginBean() {
		super();
	}
	
	///////////////MUTATORS
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	} 
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
