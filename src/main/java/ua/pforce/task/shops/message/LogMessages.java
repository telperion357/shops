package ua.pforce.task.shops.message;

public enum LogMessages {
    CONFIG_FAILED("Config failed with exception"),
    SAVE_FAILED("Failed to save product."),
    READ_FAILED("Failed to read product"),
    UPDATE_FAILED("Failed to update product");

    private String message;

    LogMessages(String message) {
        this.message = message;
    }

    public String getMsg() {
        return message;
    }
}
