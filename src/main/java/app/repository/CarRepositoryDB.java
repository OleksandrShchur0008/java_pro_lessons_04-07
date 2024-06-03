package app.repository;

import app.constans.Constans;
import app.domain.Car;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

import static app.constans.Constans.*;

public class CarRepositoryDB implements CarRepository {

    private Connection getConnection() {
        try {
            Class.forName(DB_DRIVER_PATH);

            String dbUrl = String.format("%s%s?user=%s&password=%s",
                    DB_ADDRESS, DB_NAME, DB_USERNAME, DB_PASSWORD);

            return DriverManager.getConnection(dbUrl);
            // http://10.2.3.4:8080/cars?id=3
            // jdbc:postgresql://10.2.3.4:5432/g_33_cars?user=postgres&password=ppp77777
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Car> getAll() {
        return List.of();
    }

    @Override
    public Car getById(Long id) {
        try (Connection connection = getConnection();) {

            String query = String.format("SELECT * FROM car WHERE id = %d", id);
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            if (resultSet.next()) {
                String brand = resultSet.getString("brand");
                BigDecimal price = resultSet.getBigDecimal("price");
                int year = resultSet.getInt("year");

                return new Car(id, brand, price, year);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Car save(Car car) {
        try (Connection connection = getConnection();) {

            String query = String.format("INSERT INTO car (brand, price, year) VALUES ('%s', %s, %d);",
                    car.getBrand(), car.getPrice(), car.getYear());

            Statement statement = connection.createStatement();
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

            Long id = resultSet.getLong(1);
            car.setId(id);
            return car;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car update(Car car) {
        try (Connection connection = getConnection();) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = getConnection();) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
