package org.ordersmgmtapp.dao;

import org.ordersmgmtapp.model.Product;

import java.util.List;

public interface ProductDAORepo {

    void addProduct(String name, String description, double price, int stock);

    void modifyProduct(Product product);

    void deleteProduct(String id);

    Product getProductById(String id);

    List<Product> getAllProducts();

    void decrementStock(Product product, int quantity);

}