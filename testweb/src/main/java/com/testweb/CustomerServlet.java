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

import com.testweb.dao.CustomerDao;
import com.testweb.entities.Customer;
import com.testweb.utils.ConvertDate;
import com.testweb.utils.PkNumber;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/PostgresDB")
	private DataSource dataSource;

	private CustomerDao customerDao;

	@Override
	public void init() throws ServletException {
		System.out.println("Login init method...");
		this.customerDao = new CustomerDao(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Customer doGet is called...");

		List<Customer> customers = customerDao.getAllCustomers();
		System.out.println("allCustomers: " + customers.size());

		req.setAttribute("customers", customers);
		System.out.println("Set customers attribute in request...");

		RequestDispatcher dispatcher = req.getRequestDispatcher("/customer.jsp");
		System.out.println("Dispatching to /customer.jsp...");
		dispatcher.forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Customer doPost is called...");

		try {
			String custFirstName = req.getParameter("custFirstName");
			System.out.println("custFirstName: " + custFirstName);

			String customerLastName = req.getParameter("custLastName");
			System.out.println("customerLastName: " + customerLastName);

			String customerEmail = req.getParameter("custEmail");
			System.out.println("customerEmail: " + customerEmail);

			String customerAddress = req.getParameter("custAddress");
			if (customerAddress == null)
				customerAddress = "";
			System.out.println("customerAddress: " + customerAddress);

			String customerAgeStr = req.getParameter("custAge");
			int customerAge = 0;
			if (customerAgeStr != null && !customerAgeStr.isEmpty()) {
				customerAge = Integer.parseInt(customerAgeStr);
			}
			System.out.println("customerAge: " + customerAge);

			String customerType = req.getParameter("custType");
			if (customerType == null)
				customerType = "";
			System.out.println("customerType: " + customerType);

			String custPhone = req.getParameter("custPhone");
			if (custPhone == null)
				custPhone = "";
			System.out.println("custPhone: " + custPhone);

			String custBalanceStr = req.getParameter("custBalance");
			double custBalance = 0.0;
			if (custBalanceStr != null && !custBalanceStr.isEmpty()) {
				custBalance = Double.parseDouble(custBalanceStr);
			}
			System.out.println("custBalance: " + custBalance);

			String custSex = req.getParameter("custSex");
			if (custSex == null)
				custSex = "";
			System.out.println("custSex: " + custSex);

			String custCity = req.getParameter("custCity");
			if (custCity == null)
				custCity = "";
			System.out.println("custCity: " + custCity);

			String custJoinDate = req.getParameter("custJoinDate");
			System.out.println("custJoinDate: " + custJoinDate);

			Customer customer = new Customer();
			customer.setCustomerFirstName(custFirstName);
			customer.setCustomerLastName(customerLastName);
			customer.setCustomerEmail(customerEmail);
			customer.setCustomerAddress(customerAddress);
			customer.setCustomerAge(customerAge);
			customer.setCustomerType(customerType);
			customer.setCustomerPhoneNumber(custPhone);
			customer.setCustBalance(custBalance);
			customer.setCustSex(custSex);
			customer.setCity(custCity);
			customer.setJoinDate(ConvertDate.convertDate(custJoinDate));

			// Check if updating or creating
			String customerIdStr = req.getParameter("customerId");
			if (customerIdStr != null && !customerIdStr.isEmpty()) {
				int customerId = Integer.parseInt(customerIdStr);
				customer.setCustomerId(customerId);
				System.out.println("Updating existing customer with ID: " + customer.toString());
				customerDao.updateCustomer(customer);
				System.out.println("Customer updated successfully.");
			} else {
				customer.setCustomerId(PkNumber.getId());
				System.out.println("Creating new customer with generated ID: " + customer.getCustomerId());
				customerDao.saveCustomer(customer);
				System.out.println("New customer saved successfully.");
			}

			List<Customer> allCustomers = customerDao.getAllCustomers();
			req.setAttribute("customers", allCustomers);
			System.out.println("Set updated customers list in request...");

			RequestDispatcher dispatcher = req.getRequestDispatcher("/customer.jsp");
			System.out.println("Dispatching to /customer.jsp...");
			dispatcher.forward(req, res);

		} catch (Exception e) {
			System.err.println("Customer doPost Error: " + e.getMessage());
			e.printStackTrace();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
			System.out.println("Dispatching to /error.jsp due to exception...");
			dispatcher.forward(req, res);
		}
	}

	@Override
	public void doHead(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("Customer doHead (edit) is called...");

		String customerIdStr = req.getParameter("customerId");
		System.out.println("customerId: " + customerIdStr);

		if (customerIdStr != null && !customerIdStr.isEmpty()) {
			Customer customer = customerDao.getCustomerById(Integer.parseInt(customerIdStr));
			req.getSession().setAttribute("customer", customer);
			System.out.println("Customer set in session: " + customer);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/customeredit.jsp");
			System.out.println("Dispatching to /customeredit.jsp...");
			dispatcher.forward(req, res);
		}
	}

	@Override
	public void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("Employee deletion from doOptions  is called.....");

		String customerId = req.getParameter("customerId");
		System.out.println("customerId: " + customerId);

		int customerDeleted = customerDao.deleteCustomer(customerId);
		System.out.println("customerDeleted: " + customerDeleted);

		List<Customer> allCustomers = customerDao.getAllCustomers();
		System.out.println("allCustomers: " + allCustomers.size());
		req.setAttribute("employees", allCustomers);

		System.out.println("send redirect with customer list to this jsp page...");
		// to send web browser
		res.sendRedirect("/testweb/customer.jsp");
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

}
