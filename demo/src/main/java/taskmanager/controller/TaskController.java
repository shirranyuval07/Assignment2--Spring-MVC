package taskmanager.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import taskmanager.entity.Task;
import taskmanager.service.TaskService;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Handles loading the form for Add, Edit, or Show
    @GetMapping("/form")
    public String showTaskForm(@RequestParam String mode,
                               @RequestParam(required = false) String id,
                               Model model,
                               HttpSession session) {

        if (session.getAttribute("username") == null) return "redirect:/login";

        Task task;
        if ("add".equalsIgnoreCase(mode)) {
            task = new Task(); // Empty task for new entries [cite: 9]
        } else {
            // Load existing task for Edit/Show
            task = taskService.getTaskById(id);
        }

        model.addAttribute("task", task);
        model.addAttribute("mode", mode); // 'add', 'edit', or 'show'
        return "task-form"; // Single view for all 3 states
    }

    // Handles the "Save" button
    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task) {
        // Save (or update) and redirect to main screen [cite: 10, 12]
        taskService.save(task);
        return "redirect:/";
    }
}