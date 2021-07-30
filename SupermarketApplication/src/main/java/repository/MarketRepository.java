package repository;

import entity.Product;
import types.ProductType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarketRepository {

    private DBHandler dbHandler = new DBHandler();

    public void addProductToDB(Product product) throws SQLException {
        Connection connection = dbHandler.getConnection();
        String query = "INSERT INTO products (name, price, quantity, product_Type)"
                + "VALUES(?, ?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setInt(3, product.getQuantity());
        preparedStatement.setString(4, product.getProductType().toString());

        preparedStatement.execute();

    }

    public ArrayList<Product> getAllProductsFromDB() throws SQLException {
        ArrayList<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        ResultSet result = preparedStatement.executeQuery();

        while(result.next()){
            Product product = new Product(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getDouble("price"),
                    result.getInt("quantity"),
                    ProductType.valueOf(result.getString("product type"))

            );

            productList.add(product);
        }
        return productList;
    }
}
