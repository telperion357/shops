package ua.pforce.task.shops.shop_factory;

import ua.pforce.task.shops.dao.IShopsDao;
import ua.pforce.task.shops.shop.AbstractShop;

public interface ShopFactory {

    /**
     * The factory method to get the instance of the shop.
     * This method should be used instead of shop classes getInstance methods.
     * @param dao
     * @return
     */
    AbstractShop getShop(IShopsDao dao);
}
