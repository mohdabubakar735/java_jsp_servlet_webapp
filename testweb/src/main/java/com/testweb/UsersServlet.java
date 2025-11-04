package com.testweb;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.testweb.dao.UsersDao;
import com.testweb.entities.Users;
import com.testweb.utils.PkNumber;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/PostgresDB")
	private DataSource dataSource;

	UsersDao usersDao = new UsersDao();

	@Override
	public void init() throws ServletException {
		System.out.println("Login init method...");
		this.usersDao = new UsersDao(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Users doGet is called...");

		List<Users> allUsers = usersDao.getAllUsers();
		System.out.println("allUsers: " + allUsers.size());

		req.setAttribute("users", allUsers);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/users.jsp");
		dispatcher.forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Users doPost is called...");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String role = req.getParameter("role");
		String isActiveStr = req.getParameter("isactive");

		Users user = new Users(); // create instance first

		boolean isActive = false;
		if (isActiveStr != null && (isActiveStr.equalsIgnoreCase("true") || isActiveStr.equals("1"))) {
			isActive = true;
		}

		try {
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setRole(role);
			user.setIsactive(isActive);
			user.setCreatedby("system");
			user.setCreatedat(new Timestamp(System.currentTimeMillis()));

			if (req.getParameter("userid") != null && !req.getParameter("userid").equals("")) {
				String userid = req.getParameter("userid");
				System.out.println("user userid: " + userid);

				// update existing user
				user.setUserid(Integer.parseInt(userid));
				usersDao.updateUser(user);
				System.out.println("user updated...");
			} else {
				// create new user
				user.setUserid(PkNumber.getId());
				usersDao.saveUser(user);
				System.out.println("New user saved.");
			}

			// reload list
			List<Users> allUsers = usersDao.getAllUsers();
			req.setAttribute("users", allUsers);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/users.jsp");
			dispatcher.forward(req, res);

		} catch (Exception e) {
			System.err.println("Users doPost Error: " + e.getMessage());
			e.printStackTrace();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
			dispatcher.forward(req, res);
		}
	}

	@Override
	public void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("User deletion from doOptions is called.....");

		String userid = req.getParameter("userid");
		System.out.println("userid: " + userid);

		int userDeleted = usersDao.deleteUser(userid);
		System.out.println("userDeleted: " + userDeleted);

		List<Users> allUsers = usersDao.getAllUsers();
		System.out.println("allUsers: " + allUsers.size());
		req.setAttribute("users", allUsers);

		System.out.println("send redirect with user list to this jsp page...");
		res.sendRedirect("/testweb/users.jsp");
	}

	@Override
	public void doHead(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("User update by doHead is called.....");

		String userid = req.getParameter("userid");
		System.out.println("userid: " + userid);

		Users user = usersDao.getUserById(Integer.parseInt(userid));
		System.out.println("user: " + user);
		System.out.println(user.getUsername());
		req.getSession().setAttribute("users", user);

		System.out.println("request dispatch edit user page...");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/usersedit.jsp");
		dispatcher.forward(req, res);
	}

}
