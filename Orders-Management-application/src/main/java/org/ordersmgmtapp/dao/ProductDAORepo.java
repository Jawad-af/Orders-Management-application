package org.ordersmgmtapp.dao;

import org.ordersmgmtapp.model.Product;

import java.util.List;

/**
 * The ProductDAORepo interface defines methods for interacting with product data in the database.
 */
public interface ProductDAORepo {

    /**
     * Adds a new product to the database.
     * @param name The name of the product.
     * @param description The description of the product.
     * @param price The price of the product.
     * @param stock The stock quantity of the product.
     */
    void addProduct(String name, String description, double price, int stock);

    /**
     * Modifies an existing product in the database.
     * @param product The product object containing the modified data.
     */
    void modifyProduct(Product product);

    /**
     * Deletes a product from the database.
     * @param id The ID of the product to be deleted.
     */
    void deleteProduct(long id);

    /**
     * Retrieves a product from the database by its ID.
     * @param id The ID of the product to retrieve.
     * @return The Product object representing the retrieved product.
     */
    Product getProductById(long id);

    /**
     * Retrieves all products from the database.
     * @return A list of Product objects representing all products in the database.
     */
    List<Product> getAllProducts();

    /**
     * Decrements the stock quantity of a product in the database.
     * @param product The product object whose stock quantity is to be decremented.
     * @param quantity The quantity by which to decrement the stock.
     */
    void decrementStock(Product product, int quantity);
}
