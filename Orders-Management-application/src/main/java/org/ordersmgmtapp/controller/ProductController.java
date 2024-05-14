package org.ordersmgmtapp.controller;

import org.ordersmgmtapp.dao.ProductDAO;
import org.ordersmgmtapp.model.Product;

import java.util.List;

/**
 * The ProductController class manages operations related to products.
 */
public class ProductController {

    private ProductDAO productDAO = new ProductDAO();

    /**
     * Adds a new product.
     *
     * @param name        The name of the product.
     * @param description The description of the product.
     * @param price       The price of the product.
     * @param stock       The stock quantity of the product.
     */
    public void addProduct(String name, String description, double price, int stock) {
        productDAO.addProduct(name, description, price, stock);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return The product with the specified ID.
     */
    public Product getProductById(long id) {
        return productDAO.getProductById(id);
    }

    /**
     * Retrieves all products.
     *
     * @return A list of all products.
     */
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete.
     */
    public void deleteProduct(long id) {
        productDAO.deleteProduct(id);
    }

    /**
     * Updates an existing product.
     *
     * @param product The product to update.
     */
    public void updateProduct(Product product) {
        productDAO.modifyProduct(product);
    }
}
