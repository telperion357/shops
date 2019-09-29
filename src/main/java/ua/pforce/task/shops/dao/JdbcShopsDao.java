package ua.pforce.task.shops.dao;

import ua.pforce.task.shops.domain.Category;
import ua.pforce.task.shops.domain.Product;
import ua.pforce.task.shops.exception.JdbcShopException;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static ua.pforce.task.shops.message.LogMessages.READ_FAILED;
import static ua.pforce.task.shops.message.LogMessages.SAVE_FAILED;
import static ua.pforce.task.shops.message.LogMessages.UPDATE_FAILED;

public class JdbcShopsDao implements IShopsDao {

    private static final String SAVE_PRODUCT = "insert into PRODUCTS (title, price, status, category_id) values (?,?,?,?)";
    private static final String UPDATE_STATUS = "update PRODUCTS set status=? WHERE id=?";
    private static final String UPDATE_PRICE = "update PRODUCTS set price=? WHERE id=?";
    private static final String FIND_BY_ID = "select * from PRODUCTS where id=?";
    private static final String FIND_BY_CATEGORY = "select * from PRODUCTS where category_id=?";

    private static Logger LOGGER = Logger.getLogger(JdbcShopsDao.class.getName());

    private final transient DataSource dataSource;

    public JdbcShopsDao(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Product addProductToCategory(Product product, Category category) {

        try (Connection conn = dataSource.getConnection()) {

            try(PreparedStatement saveStmt = conn.prepareStatement(
                    SAVE_PRODUCT,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {

                prepareStatementWithProduct(saveStmt, product, category);
                saveStmt.executeUpdate();

                try (ResultSet generatedKeys = saveStmt.getGeneratedKeys()) {

                    if (generatedKeys.next()) {
                        final long id = generatedKeys.getLong(1);
                        return new Product(id, product);

                    } else {
                        JdbcShopException e = new JdbcShopException();
                        LOGGER.info(SAVE_FAILED.getMsg());
                        throw e;
                    }
                }
            }

        } catch (SQLException e) {
            LOGGER.info(SAVE_FAILED.getMsg());
            throw new JdbcShopException(e);
        }
    }

    public List<Product> getProductsByCategory(Category category) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_CATEGORY)) {

            ps.setLong(1, category.getId());

            ResultSet rs = ps.executeQuery();

            final List<Product> products = new ArrayList();

            while (rs.next()) {
                products.add(toProduct(rs));
            }
            return products;

        } catch (SQLException e) {
            LOGGER.info(READ_FAILED.getMsg());
            throw new JdbcShopException(e);        }
    }

    @Override
    public Product getProductById(long id) {

        final Product product;

        try (Connection conn = dataSource.getConnection()) {

            try (PreparedStatement findStmt = conn.prepareStatement(FIND_BY_ID)) {
                findStmt.setLong(1, id);

                try (ResultSet rs = findStmt.executeQuery()) {

                    if (rs.next()) {
                        product = toProduct(rs);
                    } else {
                        product = null;
                    }
                }
            }
            return product;

        } catch (SQLException e) {
            LOGGER.info(READ_FAILED.getMsg());
            throw new JdbcShopException(e);
        }
    }

    @Override
    public boolean updateProductsStatus(List<Product> products) {

        try (Connection conn = dataSource.getConnection()) {

            try (PreparedStatement updateStmt = conn.prepareStatement(UPDATE_STATUS)) {

                for (Product product : products) {
                    updateStmt.setString(1, product.getStatus().toString());
                    updateStmt.setLong(2, product.getId());
                    updateStmt.addBatch();
                }

                int[] affectedRecords = updateStmt.executeBatch();

                return Arrays.stream(affectedRecords)
                        .allMatch(affectedRows -> affectedRows == 1);
            }
        } catch (SQLException e) {
            LOGGER.info(UPDATE_FAILED.getMsg());
            throw new JdbcShopException(e);
        }

    }

    @Override
    public boolean updateProductsPrice(List<Product> products) {

        try (Connection conn = dataSource.getConnection()) {

            try (PreparedStatement updateStmt = conn.prepareStatement(UPDATE_PRICE)) {

                for (Product product : products) {
                    updateStmt.setString(1, product.getPrice().toString());
                    updateStmt.setLong(2, product.getId());
                    updateStmt.addBatch();
                }

                int[] affectedRecords = updateStmt.executeBatch();

                return Arrays.stream(affectedRecords)
                        .allMatch(i -> i == 1);
            }

        } catch (SQLException e) {
            LOGGER.info(UPDATE_FAILED.getMsg());
            throw new JdbcShopException(e);
        }
    }

    /**
     * Sets the parameters of prepared statement with the provided product and category.
     * @param ps
     * @param product
     * @param category
     * @throws SQLException
     */
    private void prepareStatementWithProduct(final PreparedStatement ps,
                                             final Product product,
                                             final Category category) throws SQLException {

        // insert into PRODUCTS (title, price, status, category_id) values (?,?,?,?)
        //
        ps.setString(1, product.getTitle());
        ps.setString(2, product.getPrice().toString());
        ps.setString(3, product.getStatus().toString());
        ps.setLong(4, category.getId());
    }

    /**
     * reads a row from the result set and creates a product object accordingly.
     * @param rs
     * @return
     * @throws SQLException
     */
    private Product toProduct(final ResultSet rs) throws SQLException {
        return new Product(
                rs.getLong(1),                                        // id
                rs.getString(2),                                      // title
                new BigDecimal(rs.getString(3)),                      // price
                Enum.valueOf(Product.Status.class, rs.getString(4))   // status
        );
    }
}
