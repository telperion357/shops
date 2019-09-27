package ua.pforce.task.shops;

import ua.pforce.task.shops.connection_factory.DataSourceFactory;
import ua.pforce.task.shops.dao.JdbcShopsDao;
import ua.pforce.task.shops.domain.Category;
import ua.pforce.task.shops.domain.Product;
import ua.pforce.task.shops.shop.AbstractShop;
import ua.pforce.task.shops.shop_factory.BookShopFactory;
import ua.pforce.task.shops.shop_factory.GadgetShopFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static ua.pforce.task.shops.domain.Product.Status.ABSENT;
import static ua.pforce.task.shops.domain.Product.Status.AVAILABLE;
import static ua.pforce.task.shops.domain.Product.Status.EXPECTED;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        JdbcShopsDao dao = new JdbcShopsDao(DataSourceFactory.getDataSource());

        BookShopFactory bookShopFactory = new BookShopFactory();
        AbstractShop bookShop = bookShopFactory.getShop(dao);

        GadgetShopFactory gadgetShopFactory = new GadgetShopFactory();
        AbstractShop gadgetShop = gadgetShopFactory.getShop(dao);

        Task bookTask = new Task(bookShop);
        Task gadgetTask = new Task(gadgetShop);

        Thread bookThread = new Thread(bookTask);
        Thread gadgetThread = new Thread(gadgetTask);

        bookThread.start();
        Thread.sleep(10000);
        gadgetThread.start();

        bookThread.join();
        gadgetThread.join();

        System.out.println("All tasks completed");
    }

    static class Task implements Runnable {

        private AbstractShop shop;

        public Task(AbstractShop shop) {
            this.shop = shop;
        }

        @Override
        public void run() {

            List<Category> categories = new ArrayList<>(shop.getCategories());

            // Write 3-4 products into categories
            //
            Product product = new Product("Some Title", new BigDecimal("100.500"), AVAILABLE);

            for (Category category : categories) {
                for (int i = 0; i < 4; i++) {
                    shop.addProductToCategory(product, category.getName());
                }
            }

            // In some category change the status of all products to "Absent"
            //
            Category someCategory = categories.remove(categories.size() - 1);
            List<Product> products = shop.getProductsByCategory(someCategory.getName());
            products.stream()
                    .forEach(product1 -> product1.setStatus(ABSENT));
            shop.updateProductsStatus(products);

            // In the remained categories, set status to "Expected" for half the products.
            //
            List<Product> updateList = new ArrayList<>();
            for (Category category : categories) {
                products = shop.getProductsByCategory(category.getName());

                for (int i = 0; i <= products.size()/2; i++) {
                    Product product1 = products.get(i);
                    product1.setStatus(EXPECTED);
                    updateList.add(product1);
                }
            }
            shop.updateProductsStatus(updateList);

            // For the available products increase price by 20%
            //
            updateList.clear();
            for (Category category : categories) {
                products = shop.getProductsByCategory(category.getName());

                products.stream()
                        .filter(product1 -> product1.getStatus() == AVAILABLE)
                        .forEach(product1 -> {
                            product1.setPrice(product1.getPrice().multiply(new BigDecimal("1.2")));
                            updateList.add(product1);
                        });
            }
            shop.updateProductsPrice(updateList);
        }
    }
}
