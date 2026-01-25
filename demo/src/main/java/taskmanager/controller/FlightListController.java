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
    private FlightService flightService;

    @GetMapping
    public String showMainPage(Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }

        // שינוי שם המשתנה ל-flights כדי לשקף את התוכן
        model.addAttribute("flights", flightService.getAllFlightsSorted());
        return "main-page";
    }

    @PostMapping("/action")
    public String handleToolbarAction(@RequestParam String action,
                                      @RequestParam(required = false) Long selectedFlightId, // שינוי ל-Long
                                      Model model) {

        if ("Add".equals(action)) return "redirect:/flight/form?mode=add";

        if (selectedFlightId == null) {
            model.addAttribute("errorMessage", "You must select a flight to perform this action.");
            return "error";
        }

        switch (action) {
            case "Show":
                return "redirect:/flight/form?mode=show&id=" + selectedFlightId;

            case "Edit":
                return "redirect:/flight/form?mode=edit&id=" + selectedFlightId;

            case "Delete":
                flightService.delete(selectedFlightId);
                return "redirect:/";

            default:
                return "redirect:/";
        }
    }
}