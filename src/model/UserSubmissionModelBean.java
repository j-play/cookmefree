package model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped //DureÌ�e de vue uniquement lors d'une requeÌ€te 
//meÌ‚me proprieÌ�teÌ�s que UserModelBean mais porteÌ�e diffeÌ�rente
public class UserSubmissionModelBean extends UserModelBean implements Serializable{

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