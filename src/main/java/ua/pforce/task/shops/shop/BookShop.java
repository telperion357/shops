package ua.pforce.task.shops.shop;

import ua.pforce.task.shops.connection_factory.DataSourceFactory;
import ua.pforce.task.shops.dao.JdbcShopsDao;

import java.util.HashMap;

import static ua.pforce.task.shops.domain.Category.SHOP1_CAT1;
import static ua.pforce.task.shops.domain.Category.SHOP1_CAT2;
import static ua.pforce.task.shops.domain.Category.SHOP1_CAT3;

public class BookShop extends AbstractShop{

    private static final BookShop BOOK_SHOP = new BookShop();

    // Initializes the shop. Hardcoded relationship between the shop and its categories.
    private BookShop() {
        categories = new HashMap<>();
        categories.put(SHOP1_CAT1.getName(), SHOP1_CAT1);
        categories.put(SHOP1_CAT2.getName(), SHOP1_CAT2);
        categories.put(SHOP1_CAT3.getName(), SHOP1_CAT3);

        shopsDao = new JdbcShopsDao(DataSourceFactory.getDataSource());
    }

    /**
     * This method returns an incomplete object and should not be used!
     * Use the proper factory instead!
     * @return
     */
    public static BookShop getInstance() {
        return BOOK_SHOP;
    }
}