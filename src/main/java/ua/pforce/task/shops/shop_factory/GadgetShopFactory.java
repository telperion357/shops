package ua.pforce.task.shops.shop_factory;

import ua.pforce.task.shops.dao.IShopsDao;
import ua.pforce.task.shops.shop.AbstractShop;
import ua.pforce.task.shops.shop.GadgetShop;

public class GadgetShopFactory implements ShopFactory {

    public AbstractShop getShop(IShopsDao dao) {
        AbstractShop shop = GadgetShop.getInstance();
        shop.setShopsDao(dao);
        return shop;
    }
}
