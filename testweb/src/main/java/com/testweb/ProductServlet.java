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

import com.testweb.dao.ProductDao;
import com.testweb.entities.Product;
import com.testweb.utils.PkNumber;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/PostgresDB")
	private DataSource dataSource;

	private ProductDao productDao;

	@Override
	public void init() throws ServletException {
		System.out.println("Login init method...");
		this.productDao = new ProductDao(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Product doGet is called...");

		List<Product> allProducts = productDao.getAllProducts();
		System.out.println("allProducts: " + allProducts.size());

		req.setAttribute("products", allProducts);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/product.jsp");
		dispatcher.forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Product doPost is called...");

		String productName = req.getParameter("productName");
		String priceStr = req.getParameter("price");
		String quantityStr = req.getParameter("quantity");
		String description = req.getParameter("description");

		double price = 0.0;
		int quantity = 0;

		try {
			if (priceStr != null && !priceStr.isEmpty()) {
				price = Double.parseDouble(priceStr);
			}
			if (quantityStr != null && !quantityStr.isEmpty()) {
				quantity = Integer.parseInt(quantityStr);
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid number format: " + e.getMessage());
		}

		try {
			Product product = new Product();
			product.setProductName(productName);
			product.setPrice(price);
			product.setQuantity(quantity);
			product.setDescription(description);

			if (null != req.getParameter("productId") && !req.getParameter("productId").equals("")) {
				String productId = req.getParameter("productId");
				System.out.println("product productId: " + productId);

				// update product based on productId
				product.setProductId(Integer.parseInt(productId));
				productDao.updateProduct(product);
				System.out.println("product udpated...");
			} else {
				// create new product
				product.setProductId(PkNumber.getId());
				productDao.saveProduct(product);
				System.out.println("New product saved.");
			}

			// Load updated list
			List<Product> allProducts = productDao.getAllProducts();
			req.setAttribute("products", allProducts);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/product.jsp");
			dispatcher.forward(req, res);
		} catch (Exception e) {
			System.err.println("Product doPost Error :" + e.getMessage());
			e.printStackTrace();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
			dispatcher.forward(req, res);
		}
	}

	@Override
	public void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("product deletion from doOptions  is called.....");

		String productId = req.getParameter("productId");
		System.out.println("productId: " + productId);

		int productDeleted = productDao.deleteProduct(productId);
		System.out.println("productDeleted: " + productDeleted);

		List<Product> allProducts = productDao.getAllProducts();
		System.out.println("allProducts: " + allProducts.size());
		req.setAttribute("products", allProducts);

		System.out.println("send redirect with product list to this jsp page...");
		// to send web browser
		res.sendRedirect("/testweb/product.jsp");
	}

	@Override
	public void doHead(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("Product update by doHead is called.....");

		String productId = req.getParameter("productId");
		System.out.println("productId: " + productId);

		Product product = productDao.getProductById(Integer.parseInt(productId));
		System.out.println("product: " + product);
		System.out.println(product.getProductName());
		req.getSession().setAttribute("product", product);

		System.out.println(" request dispatch edit product page...");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/productedit.jsp");
		dispatcher.forward(req, res);
	}

}
