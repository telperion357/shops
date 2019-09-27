package ua.pforce.task.shops.shop_factory;

import ua.pforce.task.shops.dao.IShopsDao;
import ua.pforce.task.shops.shop.AbstractShop;
import ua.pforce.task.shops.shop.BookShop;

public class BookShopFactory implements ShopFactory {

    public AbstractShop getShop(IShopsDao dao) {
        AbstractShop shop = BookShop.getInstance();
        shop.setShopsDao(dao);
        return shop;
    }
}
