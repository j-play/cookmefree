package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserModel;
import db.DB;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DB db;

    /**
     * @see HttpServlet#HttpServlet()
     */
	public Servlet1() { 
		super();
		db=new DB();
	}
	
	/**
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO Lors de lâ€™appel de http get utiliser les classes preÌ�ceÌ�dement creÌ�er pour reÌ�cupeÌ�rer la liste des utilisateurs
		ArrayList<UserModel> listUser = db.getData();
		//TODO eÌ�crire la liste des utilisateurs dans le flux de reÌ�ponse HttpServletResponse
		for(UserModel i : listUser){
			response.getWriter().print("prenom "+i.getSurname()+"\nnom "+i.getLastname()+"\nlogin "+i.getLogin()+"\npassword "+i.getPwd()+"\nage "+i.getAge()+"\n\n");
		}
		
	}
	/**
	* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
