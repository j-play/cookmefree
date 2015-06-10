package model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserModelBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String lastname;
	private String surname; 
	private Integer age; 
	private String mail;
	private String login; 
	private String pwd;
	private boolean isAdmin;
	
	public UserModelBean(){
	}

	public UserModelBean(String lastname,String surname,Integer age,String mail,String login,String pwd) { 
		this.lastname = lastname;
		this.surname = surname; 
		this.age = age; 
		this.mail = mail;
		this.login = login;
		this.pwd = pwd;
		this.isAdmin = false;
	}

	public UserModelBean(String lastname,String surname,Integer age,String mail,String login,String pwd,boolean isAdmin) { 
		this.lastname = lastname;
		this.surname = surname; 
		this.age = age; 
		this.mail = mail;
		this.login = login;
		this.pwd = pwd;
		this.isAdmin = isAdmin;
	}
	
	public UserModelBean(Integer id, String lastname,String surname,Integer age,String mail,String login,String pwd,boolean isAdmin) { 
		this.id = id;
		this.lastname = lastname;
		this.surname = surname; 
		this.age = age; 
		this.mail = mail;
		this.login = login;
		this.pwd = pwd;
		this.isAdmin = isAdmin;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastname() { 
		return lastname; 
	}
	
	public void setLastname(String lastname) { 
		this.lastname = lastname; 
	} 
	
	public String getSurname() { 
		return surname; 
	}
	
	public void setSurname(String surname) { 
		this.surname = surname;
	} 
	
	public Integer getAge() { 
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getMail() { 
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
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
	
	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}