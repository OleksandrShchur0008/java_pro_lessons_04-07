package app.repository;

import app.domain.Car;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarRepositoryMap implements CarRepository {

    private final Map<Long, Car> database = new HashMap<>();
    private long currentId;

    public CarRepositoryMap() {
        save(new Car("Volkswagen", new BigDecimal(10000), 2010));
        save(new Car("BMW", new BigDecimal(30000), 2015));
        save(new Car("Mercedes Benz", new BigDecimal(50000), 2020));
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        for (Car car : database.values()) {
            cars.add(car);
        }
        return new ArrayList<>(cars);
    }

    @Override
    public Car getById(Long id) {
        return null;
    }

    @Override
    public Car save(Car car) {
        car.setId(++currentId);
        database.put(currentId, car);
        return car;
    }

    @Override
    public Car update(Car car) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
