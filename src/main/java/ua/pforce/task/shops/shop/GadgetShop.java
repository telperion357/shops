package ua.pforce.task.shops.shop;

import java.util.HashMap;

import static ua.pforce.task.shops.domain.Category.SHOP2_CAT1;
import static ua.pforce.task.shops.domain.Category.SHOP2_CAT2;
import static ua.pforce.task.shops.domain.Category.SHOP2_CAT3;

public class GadgetShop extends AbstractShop{

    private static GadgetShop GADGET_SHOP = new GadgetShop();

    /**
     * This method returns an incomplete object and should not be used!
     * Use the proper factory instead!
     * @return
     */
    public static GadgetShop getInstance() {
        return GADGET_SHOP;
    }

    // Initializes the shop. Hardcoded relationship between the shop and its categories.
    private GadgetShop() {
        categories = new HashMap<>();
        categories.put(SHOP2_CAT1.getName(), SHOP2_CAT1);
        categories.put(SHOP2_CAT2.getName(), SHOP2_CAT2);
        categories.put(SHOP2_CAT3.getName(), SHOP2_CAT3);
    }
}
