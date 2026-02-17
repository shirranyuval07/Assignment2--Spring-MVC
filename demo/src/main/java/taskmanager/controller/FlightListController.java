package taskmanager.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import taskmanager.service.FlightService;

@Controller
@RequestMapping("/")
public class FlightListController {

    @Autowired
    private FlightService flightService; // The injected instance

    @GetMapping
    public String showMainPage(Model model, HttpSession session) {
        // Simple login check
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }

        model.addAttribute("flights", flightService.getAllFlightsSorted());
        return "main-page";
    }

    // Handles the toolbar buttons (Add, Edit, Delete, Passengers)
    @PostMapping("/action")
    public String handleToolbarAction(@RequestParam String action,
                                      @RequestParam(required = false) Long selectedFlightId,
                                      Model model) {

        // 1. Handle "Add" first (It does NOT require a selected ID)
        if ("Add".equals(action)) {
            return "redirect:/flight/form?mode=add";
        }

        // 2. Safety Check: For everything else, the user MUST pick a flight
        if (selectedFlightId == null) {
            model.addAttribute("errorMessage", "You must select a flight to perform this action.");
            // Ideally, you should reload the main page with an error, but "error" view works too
            return "error";
        }

        // 3. Handle actions
        switch (action) {
            case "Show":
                return "redirect:/flight/form?mode=show&id=" + selectedFlightId;

            case "Edit":
                return "redirect:/flight/form?mode=edit&id=" + selectedFlightId;

            case "Delete":
                // FIX: calls the instance method (lowercase 'f')
                flightService.delete(selectedFlightId);
                return "redirect:/";

            case "Passengers":
                // New logic: Redirects to the Passenger list
                return "redirect:/passengers?flightId=" + selectedFlightId;

            default:
                return "redirect:/";
        }
    }
}