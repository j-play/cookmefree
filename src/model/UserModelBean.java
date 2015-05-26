package model;

import java.io.Serializable;

public class UserModelBean extends UserModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserModelBean(){
		super();
	}
	public UserModelBean(String lastname,String surname,int age,String login,String pwd) { 
		super(lastname, surname, age, login, pwd);
	}
}
