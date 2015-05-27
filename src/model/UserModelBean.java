package model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserModelBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lastname;
	private String surname; 
	private Integer age; 
	private String mail;
	private String login; 
	private String pwd;
	
	public UserModelBean(){
	}
	
	public UserModelBean(String lastname,String surname,Integer age,String mail,String login,String pwd) { 
		this.lastname = lastname;
		this.surname = surname; 
		this.age = age; 
		this.mail = mail;
		this.login = login;
		this.pwd = pwd;
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
	
	@Override
	public String toString() { 
		return"[SURNAME]:"+this.getSurname()+",[LASTNAME]:"+this.getLastname()+",[AGE]:"+this.getAge()+", [LOGIN]:"+this.getLogin()+",[PWD]:"+this.getPwd();
	}
}