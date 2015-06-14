package model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/*
 * This model bean represents a comment input
 */
@ManagedBean
@RequestScoped
public class InputCommentBean implements Serializable {

	/////////////////ATTRIBUTES
	private static final long serialVersionUID = 1L;
	private int mark;
	private String content;
	
	////////////////CONSTRUCTORS
	public InputCommentBean() {
		super();
	}
	public InputCommentBean(int mark, String content) {
		super();
		this.mark = mark;
		this.content = content;
	}

	//////////////////MUTATORS
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
