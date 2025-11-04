package com.testweb;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.testweb.dao.EmployeeDao;
import com.testweb.entities.Employee;
import com.testweb.utils.ConvertDate;
import com.testweb.utils.PkNumber;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/PostgresDB")
	private DataSource dataSource;

	EmployeeDao employeeDao = new EmployeeDao();

	@Override
	public void init() throws ServletException {
		System.out.println("Login init method...");
		this.employeeDao = new EmployeeDao(dataSource);
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("Employee doGet is called...");

		List<Employee> allEmployee = employeeDao.getAllEmployee();
		System.out.println("allEmployee :" + allEmployee.size());

		req.setAttribute("employees", allEmployee);

		System.out.println("Dispatch with employee list to this jsp page.");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/employee.jsp");
		dispatcher.forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("Employee doPost is called...");

		// Note: Use the same parameter name as your HTML form's name attribute
		String fullName = req.getParameter("empFullName");
		System.out.println("Full Name: " + fullName);

		double balance = 0.0;
		String balanceStr = req.getParameter("empBalance");
		if (balanceStr != null && !balanceStr.trim().isEmpty()) {
			try {
				balance = Double.parseDouble(balanceStr.trim());
			} catch (NumberFormatException e) {
				System.out.println("Invalid number format for balance: " + balanceStr);
			}
		}
		System.out.println("Balance: " + balance);

		String employeeType = req.getParameter("empType");
		System.out.println("Employee Type: " + employeeType);

		String sex = req.getParameter("empSex");
		System.out.println("Sex: " + sex);

		String city = req.getParameter("empCity");
		System.out.println("City: " + city);

		String joiningDateStr = req.getParameter("empJoiningDate");
		System.out.println("Joining Date (raw): " + joiningDateStr);

		// String formattedJoiningDate = formatDate(joiningDateStr); //just for
		// understanding

		// to save employee get data from web page
		Employee emp = new Employee();

		emp.setFullName(fullName);
		emp.setBalance(balance);
		emp.setEmpType(employeeType);
		emp.setEmpSex(sex);
		emp.setCity(city);
		emp.setJoiningDt(ConvertDate.getTimestamp(joiningDateStr));

		if (null != req.getParameter("empId") && !req.getParameter("empId").equals("")) {
			String empId = req.getParameter("empId");
			System.out.println("employee empId: " + empId);
			// update employee based on emp id
			emp.setEmpId(Integer.parseInt(empId));
			employeeDao.updateEmpployee(emp);
			System.out.println("employee udpated...");
		} else {
			emp.setEmpId(PkNumber.getId());
			employeeDao.saveEmployee(emp);
			System.out.println("New employee created...");
		}

		List<Employee> allEmployee = employeeDao.getAllEmployee();
		System.out.println("allEmployee :" + allEmployee.size());

		req.setAttribute("employees", allEmployee);

		System.out.println("Dispatch the updated employee list to this page.");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/employee.jsp");
		dispatcher.forward(req, res);

	}

	@Override
	public void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("Employee deletion from doOptions  is called.....");

		String empId = req.getParameter("empId");
		System.out.println("empId: " + empId);

		int employeeDeleted = employeeDao.deleteEmployee(empId);
		System.out.println("employeeDeleted: " + employeeDeleted);

		List<Employee> allEmployees = employeeDao.getAllEmployee();
		System.out.println("allEmployees: " + allEmployees.size());
		req.setAttribute("employees", allEmployees);

		System.out.println("send redirect with employee list to this jsp page...");
		// to send web browser
		res.sendRedirect("/testweb/employee.jsp");
	}

	@Override
	public void doHead(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("Employee update by doHead is called.....");

		String employeeId = req.getParameter("empId");
		System.out.println("employeeId: " + employeeId);

		Employee employee = employeeDao.getEmployeeById(Integer.parseInt(employeeId));
		System.out.println("employee: " + employee);
		System.out.println("Employee Name: " + employee.getFullName());

		// Store employee object in session
		req.getSession().setAttribute("employee", employee);

		req.getSession().setAttribute("joiningDate", ConvertDate.convertTimestamp(employee.getJoiningDt().toString()));

		System.out.println("Request dispatch to edit employee page...");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/employeeedit.jsp");
		dispatcher.forward(req, res);
	}

//	private Timestamp getTimestamp(String dateString) {
//		
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date date = sdf.parse(dateString);
//
//            return  new Timestamp( date.getTime());
//		} catch (Exception e) {
//			System.err.println("Date formatting error :"+e.getMessage());
//			return null;
//		}
//	}
//	

//	private String formatDate(String joiningDateStr) {
//		String formattedJoiningDate = null;
//		if (joiningDateStr != null && !joiningDateStr.trim().isEmpty()) {
//			try {
//				DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//				LocalDate date = LocalDate.parse(joiningDateStr, inputFormat);
//				DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//				formattedJoiningDate = date.format(outputFormat);
//				System.out.println("Formatted Joining Date: " + formattedJoiningDate);
//			} catch (Exception e) {
//				System.out.println("Invalid date format for joiningDate: " + joiningDateStr);
//			}
//		}
//		return formattedJoiningDate;
//	}

//	private String convertTimestamp(String input) {
//		String outputString = "";
//		try {
//	        // Define the input and output formatters
//			DateTimeFormatter inputFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
//	        DateTimeFormatter inputFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
//	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	        // Parse the input string into a LocalDateTime object
//	        if(input.length() <= 21) {
//		        LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter1);
//		        // Format the LocalDateTime object into the desired output string
//		         outputString = dateTime.format(outputFormatter);
//	        }else {
//	        	LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter2);
//		        // Format the LocalDateTime object into the desired output string
//		         outputString = dateTime.format(outputFormatter);
//	        }
//	        System.out.println(outputString);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return outputString;
//	}
}
