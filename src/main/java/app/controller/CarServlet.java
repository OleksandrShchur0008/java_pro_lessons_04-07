package app.controller;

import app.domain.Car;
import app.repository.CarRepository;
import app.repository.CarRepositoryDB;
import app.repository.CarRepositoryHibernate;
import app.repository.CarRepositoryMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
//@WebServlet (name = "carServlet", value = "/cars")
public class CarServlet extends HttpServlet {

    private CarRepository repository = new CarRepositoryHibernate();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Для получения из БД всех или одного авто по id

        if(parameterMap.isEmpty)
        List<Car> cars = repository.getAll();
        cars.forEach(x -> {
            try {
                resp.getWriter().write(x.toString() + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    } else {
        Long id = Long.parseLong(parameterMap.get("id"[0]));
        Car car = repository.getById(id);
        resp.getWriter().write(car == null ? "Car not found" : car.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Для сохранения нового авто в БД

        ObjectMapper mapper = new ObjectMapper();
        Car car = mapper.readValue(req.getReader(), Car.class);
        repository.save(car);
        resp.getWriter().write(car.toString());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Для изменения существующего авто в БД
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Для удаления существующего авто из БД
    }
}
