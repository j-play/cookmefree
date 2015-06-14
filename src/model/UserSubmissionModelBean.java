package model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/*
 * This model bean represents a user connection when someone tries to log in on the website
 */
@ManagedBean
@RequestScoped
public class UserSubmissionModelBean extends UserModelBean implements Serializable{

	//////////////ATTRIBUTES
	private static final long serialVersionUID = 1L;
	private String pwdVerify;

	//////////////CONSTRUCTORS
	public UserSubmissionModelBean(){
	}
	public UserSubmissionModelBean(String pwdVerify) {
		super();
		this.pwdVerify = pwdVerify;
	}

	//////////////MUTATORS
	public String getPwdVerify() {
		return pwdVerify;
	}
	public void setPwdVerify(String pwdVerify) {
		this.pwdVerify = pwdVerify;
	}
	
}