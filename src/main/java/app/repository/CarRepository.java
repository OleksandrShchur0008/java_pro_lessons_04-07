package app.repository;

import app.domain.Car;

import java.util.List;

public interface CarRepository {

    List<Car> getAll();

    Car getById(Long id);

    Car save(Car car);

    Car update(Car car);

    void deleteById(Long id);
}
