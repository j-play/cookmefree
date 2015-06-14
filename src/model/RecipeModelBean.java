package model;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/*
 * This model bean represents a recipe
 */
@ManagedBean
@RequestScoped
public class RecipeModelBean implements Serializable{

	///////////////ATTRIBUTES
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String description; 
	private int expertise;
	private int duration;
	private int nbPeople;
	private String type;
	
	/////////////////CONSTRUCTORS
	public RecipeModelBean(){
		super();
	}
	public RecipeModelBean(String title, String description, int expertise, int duration, int nbPeople, String type) {
		this.title = title;
		this.description = description;
		this.expertise = expertise;
		this.duration = duration;
		this.nbPeople = nbPeople;
		this.type = type;
	}
	public RecipeModelBean(int id, String title, String description, int expertise, int duration, int nbPeople, String type) {
		this.id= id;
		this.title = title;
		this.description = description;
		this.expertise = expertise;
		this.duration = duration;
		this.nbPeople = nbPeople;
		this.type = type;
	}

	//////////////////MUTATORS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getExpertise() {
		return expertise;
	}
	public void setExpertise(int expertise) {
		this.expertise = expertise;
	}

	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getNbPeople() {
		return nbPeople;
	}
	public void setNbPeople(int nbPeople) {
		this.nbPeople = nbPeople;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	///////////////METHODS
	public String shortDescription(){
		return description.substring(0, 200);
	}

	@Override
	public String toString() { 
		return"[TITLE]:"+this.getTitle()+",[DESCRIPTION]:"+this.getDescription()+",[EXPERTISE]:"+this.getExpertise()+", [DURATION]:"+this.getDuration()+",[NBPEOPLE]:"+this.getNbPeople()+",[TYPE]:"+this.getType();
	}
}