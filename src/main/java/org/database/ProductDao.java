package org.database;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.model.Product;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ProductDao {

    private static final String GET_ALL = "SELECT * FROM product";

    public static Optional<List<Product>> getAll() {
        try(var resultSet = PoolConnectionBuilder.getConnection()
                .createStatement()
                .executeQuery(GET_ALL)) {
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(Product.builder()
                        .id(resultSet.getString("id"))
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("desc"))
                        .build());
            }
            return Optional.of(products);
        } catch (SQLException | NamingException e) {
            log.info(ExceptionUtils.getMessage(e));
            return Optional.empty();
        }
    }
}
