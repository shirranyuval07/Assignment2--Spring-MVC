package taskmanager.service;

import org.springframework.stereotype.Service;
import taskmanager.entity.Passenger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerService {
    private List<Passenger> passengerList = new ArrayList<>();
    private long idCounter = 1;

    public PassengerService() {
        // דאמי דאטה עם מספרי מושבים
        passengerList.add(new Passenger(idCounter++, "Yuval", "Shirran", "123456789", 1L, 5));
        passengerList.add(new Passenger(idCounter++, "Marco", "Polo", "987654321", 1L, 6));
    }

    public List<Passenger> getPassengersByFlightId(Long flightId) {
        List<Passenger> result = passengerList.stream()
                .filter(p -> p.getFlightId().equals(flightId))
                .collect(Collectors.toList());
        Collections.sort(result);
        return result;
    }

    // מחזיר רשימה של מספרים המייצגים מושבים תפוסים
    public List<Integer> getOccupiedSeats(Long flightId) {
        return passengerList.stream()
                .filter(p -> p.getFlightId().equals(flightId))
                .filter(p -> p.getSeatNumber() != null)
                .map(Passenger::getSeatNumber)
                .collect(Collectors.toList());
    }

    public void save(Passenger p) {
        if (p.getId() == null) p.setId(idCounter++);
        passengerList.add(p);
    }

    public void delete(Long id) {
        passengerList.removeIf(p -> p.getId().equals(id));
    }
}