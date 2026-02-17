package taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import taskmanager.entity.Passenger;
import taskmanager.service.PassengerService;
import java.util.List;
import java.util.stream.IntStream;

@Controller
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/passengers")
    public String showPassengers(@RequestParam Long flightId, Model model) {
        model.addAttribute("passengerList", passengerService.getPassengersByFlightId(flightId));
        model.addAttribute("flightId", flightId);

        // שליחת נתוני מושבים
        model.addAttribute("occupiedSeats", passengerService.getOccupiedSeats(flightId));
        model.addAttribute("allSeats", IntStream.rangeClosed(1, 28).boxed().toList());

        return "passenger-list";
    }

    @PostMapping("/passengers/add")
    public String addPassenger(@RequestParam Long flightId,
                               @RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String passportId,
                               @RequestParam(required = false) Integer seatNumber) {

        // ולידציה לתעודת זהות
        if (!passportId.matches("\\d{9}")) {
            return "redirect:/passengers?flightId=" + flightId + "&error=Invalid Passport ID (9 digits required)";
        }

        Passenger newPassenger = new Passenger(null, firstName, lastName, passportId, flightId, seatNumber);
        passengerService.save(newPassenger);

        return "redirect:/passengers?flightId=" + flightId;
    }

    @GetMapping("/passengers/delete")
    public String deletePassenger(@RequestParam Long passengerId, @RequestParam Long flightId) {
        passengerService.delete(passengerId);
        return "redirect:/passengers?flightId=" + flightId;
    }
}