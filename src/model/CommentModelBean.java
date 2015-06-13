package model;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CommentModelBean implements Serializable{
	
	int id;
	int idUser;
	int idRecipe;
	String content;
	int mark;
	Date date;

	public CommentModelBean(){
	}

	
	public CommentModelBean(int id, int idUser, int idRecipe, String content, int mark,
			Date date) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.idRecipe = idRecipe;
		this.content = content;
		this.mark = mark;
		this.date = date;
	}


	public CommentModelBean(int idUser, int idRecipe, String content, int mark,
			Date date) {
		super();
		this.idUser = idUser;
		this.idRecipe = idRecipe;
		this.content = content;
		this.mark = mark;
		this.date = date;
	}
	
	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdRecipe() {
		return idRecipe;
	}

	public void setIdRecipe(int idRecipe) {
		this.idRecipe = idRecipe;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
