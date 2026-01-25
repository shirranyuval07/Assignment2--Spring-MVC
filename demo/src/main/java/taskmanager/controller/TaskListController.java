package taskmanager.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import taskmanager.service.TaskService;

@Controller
@RequestMapping("/")
public class TaskListController {

    @Autowired
    private TaskService taskService; // Injecting the service [cite: 45]

    @GetMapping
    public String showMainPage(Model model, HttpSession session) {
        // Session Management: Redirect to login if no user found
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }

        // Load tasks sorted by priority (High -> Low)
        model.addAttribute("tasks", taskService.getAllTasksSorted());
        return "main-page"; // This maps to main-page.html
    }

    // Handles the Toolbar buttons: Show, Add, Edit, Delete
    @PostMapping("/action")
    public String handleToolbarAction(@RequestParam String action,
                                      @RequestParam(required = false) String selectedTaskId,
                                      Model model) { // Add Model here

        if ("Add".equals(action)) return "redirect:/task/form?mode=add";

        // If no task is selected, show the dedicated Error Page
        if (selectedTaskId == null) {
            model.addAttribute("errorMessage", "You must select a task to perform this action.");
            return "error"; // Returns error.html [cite: 36]
        }

        switch (action) {
            case "Show":
                // Show details in a new screen [cite: 7]
                return "redirect:/task/form?mode=show&id=" + selectedTaskId;

            case "Edit":
                // Open full form for editing [cite: 11]
                return "redirect:/task/form?mode=edit&id=" + selectedTaskId;

            case "Delete":
                // Delete and reload the main page [cite: 13]
                taskService.delete(selectedTaskId);
                return "redirect:/";

            default:
                return "redirect:/";
        }
    }
}