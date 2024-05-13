package org.ordersmgmtapp.controller;

import org.ordersmgmtapp.dao.ProductDAO;
import org.ordersmgmtapp.model.Product;

import java.util.List;

public class ProductController {

    private ProductDAO productDAO = new ProductDAO();

    public void addProduct(String name, String description, double price, int stock) {
        productDAO.addProduct(name, description, price, stock);
    }

    public List<Product> getAllproducts() {
        return productDAO.getAllProducts();
    }

    public void deleteproduct(String id) {
        productDAO.deleteProduct(id);
    }

    public void updateproduct(Product product) {
        productDAO.modifyProduct(product);
    }
}
