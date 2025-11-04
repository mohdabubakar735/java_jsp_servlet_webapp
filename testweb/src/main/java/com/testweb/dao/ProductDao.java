package com.testweb.dao;

import java.sql.*;		
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.testweb.entities.Product;

public class ProductDao {

    private DataSource dataSource;

    public ProductDao() {}

    public ProductDao(DataSource dataSource) {
    	this.dataSource= dataSource;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM products ORDER BY product_id DESC");

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("stock_quantity"));
                product.setDescription(rs.getString("description"));
                products.add(product);
            }

            rs.close();
            stmt.close();
//            closeConn(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product saveProduct(Product product) {
        String insertSQL = "INSERT INTO products (product_id, product_name, price, stock_quantity, description) VALUES (?, ?, ?, ?, ?)";

        try {
        	Connection conn =dataSource. getConnection();
            PreparedStatement ps = conn.prepareStatement(insertSQL);
            ps.setInt(1, product.getProductId());
            ps.setString(2, product.getProductName());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setString(5, product.getDescription());

            int rows = ps.executeUpdate();
            System.out.println("Inserted " + rows + " row(s) into products table");

            ps.close();
            closeConn(conn);
        } catch (Exception e) {
            System.out.println("Error saving product: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return product;
    }

    public int deleteProduct(String productId) {
        System.out.println("Proceed to delete product...");

        String deleteSQL = "delete from products where product_id= ?";
        int rowsDeleted = 0;

        try {
        	Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(deleteSQL);
            ps.setInt(1,Integer.parseInt (productId));
            rowsDeleted = ps.executeUpdate();

            System.out.println("Deleted " + rowsDeleted + " row(s) from products table");
            ps.close();
            closeConn(conn);

        } catch (Exception e) {
            System.out.println("Error deleting product: " + e.getMessage());
            e.printStackTrace();
        }

        return rowsDeleted;
    }
    public Product getProductById(Integer productId) {
    	Product product = new Product();
        try {
            Connection conn =dataSource. getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM products where product_id="+productId);

            while (rs.next()) {
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("stock_quantity"));
                product.setDescription(rs.getString("description"));
            }

            rs.close();
            stmt.close();
//            closeConn(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    private void closeConn(Connection connection) {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.out.println("Error while closing connection: " + e.getMessage());
        }
    }

	public Product updateProduct(Product product) {
		System.out.println("proceed to delete redcord..");
    	String deleteSQL = "update products set product_name=?, description=?, price=?, stock_quantity=? "
    			+ "where product_id=?";

        try { 
        	  Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(deleteSQL);

             ps.setString(1, product.getProductName());
             ps.setString(2, product.getDescription());
             ps.setDouble(3, product.getPrice());
             ps.setInt(4, product.getQuantity());
             ps.setInt(5, product.getProductId());
             ps.execute();
             System.out.println("Update product(s) for the product_id :"+product.getProductId());
        } catch (SQLException e) {
            System.out.println("Error in deleting products: " + e.getMessage());
            e.printStackTrace();
        }
        return product;
		
	}
}
