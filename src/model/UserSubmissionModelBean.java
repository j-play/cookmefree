package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped //Durée de vue uniquement lors d'une requète 
//même propriétés que UserModelBean mais portée différente
public class UserSubmissionModelBean extends UserModelBean{

	private static final long serialVersionUID = 1L;
	private String pwdVerify;

	public UserSubmissionModelBean(){
		
	}
	
	public UserSubmissionModelBean(String pwdVerify) {
		super();
		this.pwdVerify = pwdVerify;
	}

	public String getPwdVerify() {
		return pwdVerify;
	}

	public void setPwdVerify(String pwdVerify) {
		this.pwdVerify = pwdVerify;
	}
	
	
	
	
}