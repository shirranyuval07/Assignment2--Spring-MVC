package taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskmanager.dao.FlightDao;
import taskmanager.entity.Flight;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightDao flightDao;

    private static final int MAX_FLIGHTS = 100; // לוגיקה מ-Assignment 1

    public List<Flight> getAllFlightsSorted() {
        List<Flight> flights = flightDao.loadAll();
        Collections.sort(flights); // מיון לפי Comparable (יעד)
        return flights;
    }

    public Flight getFlightById(Long id) {
        return flightDao.loadAll().stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void save(Flight flight) {
        List<Flight> flights = flightDao.loadAll();

        // אם זו טיסה חדשה (אין ID), צריך לייצר לה ID
        if (flight.getId() == null) {
            if (flights.size() >= MAX_FLIGHTS) {
                throw new RuntimeException("Max flights limit reached!");
            }
            long nextId = flights.stream().mapToLong(Flight::getId).max().orElse(0) + 1;
            flight.setId(nextId);
        } else {
            // אם זו טיסה קיימת, מסירים את הישנה כדי לעדכן
            flights.removeIf(f -> f.getId().equals(flight.getId()));
        }

        flights.add(flight);
        flightDao.saveAll(flights);
    }

    public void delete(Long id) {
        List<Flight> flights = flightDao.loadAll();
        flights.removeIf(f -> f.getId().equals(id));
        flightDao.saveAll(flights);
    }
}