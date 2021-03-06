package ua.pforce.task.shops.dao;

import ua.pforce.task.shops.domain.Category;
import ua.pforce.task.shops.domain.Product;

import java.util.List;

public interface IShopsDao {

    /**
     * Saves the product to the database, under the provided category.
     * @param product the product to save.
     * @param category The category, under which the product is saved.
     * @return The new product object with autogenerated id.
     */
    Product addProductToCategory(Product product, Category category);

    /**
     * Reads the list of products under provided category from the database.
     * @param category
     * @return
     */
    List<Product> getProductsByCategory(Category category);

    /**
     * Reads the product with the provided id.
     * If there is no product with such id, returns null
     * @param id the id of the product to read.
     * @return New product object, or null, if there is no product with provided id.
     */
    Product getProductById(long id);

    /**
     * Updates the status of the provided products in the database.
     * @param products the list of products to update the status.
     * @return true, if all individual updates were successful; false otherwise.
     */
    boolean updateProductsStatus(List<Product> products);

    /**
     * Updates the price of the provided products in the database.
     * @param products the list of products to update the price.
     * @return true, if all individual updates were successful; false otherwise.     *
     */
    boolean updateProductsPrice(List<Product> products);
}
