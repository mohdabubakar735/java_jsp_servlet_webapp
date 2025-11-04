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

import com.testweb.dao.OrderDao;
import com.testweb.entities.Order;
import com.testweb.utils.ConvertDate;
import com.testweb.utils.PkNumber;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/PostgresDB")
	private DataSource dataSource;

	private OrderDao orderDao;

	@Override
	public void init() throws ServletException {
		System.out.println("Login init method...");
		this.orderDao = new OrderDao(dataSource);
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("Order doGet is called...");

		List<Order> allOrders = orderDao.getAllOrders();
		System.out.println("allOrders: " + allOrders.size());

		req.setAttribute("orders", allOrders);

		System.out.println("Dispatch with order list to this jsp page...");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/order.jsp");
		dispatcher.forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("Order doPost is called...");

		// this will create new order as well as update the old order
		String orderDateStr = req.getParameter("orderDate");
		System.out.println("Order Date: " + orderDateStr);

		double totalAmount = 0.0;
		String totalAmountStr = req.getParameter("totalAmount");
		if (totalAmountStr != null && !totalAmountStr.trim().isEmpty()) {
			try {
				totalAmount = Double.parseDouble(totalAmountStr.trim());
			} catch (NumberFormatException e) {
				System.out.println("Invalid number format for totalAmount: " + totalAmountStr);
			}
		}
		System.out.println("Total Amount: " + totalAmount);
		String description = req.getParameter("description");
		System.out.println("Description: " + description);

		Order order = new Order();
		order.setOrderDate(ConvertDate.getTimestamp(orderDateStr));
		order.setTotalAmount(totalAmount);
		order.setDescription(description);

		if (null != req.getParameter("orderId") && !req.getParameter("orderId").equals("")) {
			String orderId = req.getParameter("orderId");
			System.out.println("Order orderId: " + orderId);

			// update order based on order id
			order.setOrderId(Integer.parseInt(orderId));
			orderDao.updateOrder(order);
			System.out.println("Order udpated...");
		} else {
			// Prepare Order object
			order.setOrderId(PkNumber.getId());
			orderDao.saveOrder(order);
			System.out.println("New order created...");
		}

		List<Order> allOrders = orderDao.getAllOrders();
		System.out.println("allOrders: " + allOrders.size());

		req.setAttribute("orders", allOrders);

		System.out.println("Dispatch the updated order list to this page.");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/order.jsp");
		dispatcher.forward(req, res);
	}

	@Override
	public void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("Order deletion from doOptions  is called.....");

		String orderId = req.getParameter("orderId");
		System.out.println("orderId: " + orderId);

		int orderDeleted = orderDao.deleteOrder(orderId);
		System.out.println("orderDeleted: " + orderDeleted);

		List<Order> allOrders = orderDao.getAllOrders();
		System.out.println("allOrders: " + allOrders.size());
		req.setAttribute("orders", allOrders);

		System.out.println("send redirect with order list to this jsp page...");
		// to send web browser
		res.sendRedirect("/testweb/order.jsp");
	}

	@Override
	public void doHead(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("Order update by doHead is called.....");

		String orderId = req.getParameter("orderId");
		System.out.println("orderId: " + orderId);

		Order order = orderDao.getOrderById(Integer.parseInt(orderId));
		System.out.println("order: " + order);
		req.getSession().setAttribute("order", order);
		req.getSession().setAttribute("orderDate", ConvertDate.convertTimestamp(order.getOrderDate().toString()));

		System.out.println(" request dispatch edit order page...");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/orderedit.jsp");
		dispatcher.forward(req, res);
	}

//	private Timestamp getTimestamp(String dateString) {
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = sdf.parse(dateString);
//			return new Timestamp(date.getTime());
//		} catch (Exception e) {
//			System.err.println("Date formatting error :" + e.getMessage());
//			return null;
//		}
//	}

//	private String convertTimestamp(String input) {
//		String outputString = "";
//		try {
//			// Define the input and output formatters
//			DateTimeFormatter inputFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
//			DateTimeFormatter inputFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
//			DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//			// Parse the input string into a LocalDateTime object
//			if (input.length() <= 21) {
//				LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter1);
//				// Format the LocalDateTime object into the desired output string
//				outputString = dateTime.format(outputFormatter);
//			} else {
//				LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter2);
//				// Format the LocalDateTime object into the desired output string
//				outputString = dateTime.format(outputFormatter);
//			}
//			System.out.println(outputString);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return outputString;
//	}

}
