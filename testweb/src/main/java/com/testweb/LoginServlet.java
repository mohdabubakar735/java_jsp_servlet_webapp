package com.testweb;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.testweb.dao.LoginDao;
import com.testweb.entities.Users;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/PostgresDB")
	private DataSource dataSource;

	private LoginDao loginDao;

	@Override
	public void init() throws ServletException {
		System.out.println("Login init method...");
		this.loginDao = new LoginDao(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Login doGet is called...");

		RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Login doPost is called...");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println("username:" + username);
		try {
			Users user = this.loginDao.getUserByUsername(username);
			System.out.println("user:" + user.toString());

			if (user.getPassword().equals(password)) {

				System.out.println("user login successful. Forwarding to main page");
				RequestDispatcher dispatcher = req.getRequestDispatcher("/main.jsp");
				dispatcher.forward(req, res);

			} else {
				System.out.println("user login not valid. Either username or password is wrong.");
				req.setAttribute("error", "Either username or password is wrong.");
				RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
				dispatcher.forward(req, res);
			}

		} catch (Exception e) {
			System.err.println("Users doPost Error: " + e.getMessage());
			e.printStackTrace();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
			dispatcher.forward(req, res);
		}
	}

}
