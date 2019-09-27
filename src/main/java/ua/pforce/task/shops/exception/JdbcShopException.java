package ua.pforce.task.shops.exception;

public class JdbcShopException extends RuntimeException{
    public JdbcShopException() {
    }

    public JdbcShopException(String message) {
        super(message);
    }

    public JdbcShopException(String message, Throwable cause) {
        super(message, cause);
    }

    public JdbcShopException(Throwable cause) {
        super(cause);
    }

    public JdbcShopException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
