package ua.pforce.task.shops.domain;

public enum Category {
    SHOP1_CAT1(1, "Papers"),
    SHOP1_CAT2(2, "Books"),
    SHOP1_CAT3(3, "Postcards"),
    SHOP2_CAT1(4, "Laptops"),
    SHOP2_CAT2(5, "Smartphones"),
    SHOP2_CAT3(6, "Headsets");

    private long id;
    private String name;

    Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
