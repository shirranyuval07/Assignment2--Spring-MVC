package taskmanager.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import taskmanager.entity.Flight;
import taskmanager.service.FlightService;

@Controller
@RequestMapping("/flight")
public class FlightFormController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/form")
    public String showFlightForm(@RequestParam String mode,
                                 @RequestParam(required = false) Long id,
                                 Model model,
                                 HttpSession session) {

        if (session.getAttribute("username") == null) return "redirect:/login";

        Flight flight;
        if ("add".equalsIgnoreCase(mode)) {
            flight = new Flight(); // טיסה ריקה חדשה
        } else {
            flight = flightService.getFlightById(id);
        }

        model.addAttribute("flight", flight);
        model.addAttribute("mode", mode);
        return "flight-form"; // תצטרך לשנות את שם הקובץ task-form ל flight-form
    }

    @PostMapping("/save")
    public String saveFlight(@ModelAttribute Flight flight) {
        flightService.save(flight);
        return "redirect:/";
    }
}