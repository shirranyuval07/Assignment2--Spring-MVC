package taskmanager.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    // 1. Create a storage for users (Simulating a database)
    // Map<Username, Password>
    private static final Map<String, String> users = new HashMap<>();

    // 2. Initialize the users in a static block
    static {
        users.put("Yuval", "216782565"); // The specific user you asked for
        users.put("admin", "admin123");  // Example admin user
        users.put("guest", "123456");    // Example guest user
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              HttpSession session) {

        // 3. Check if the username exists AND if the password matches
        if (users.containsKey(username) && users.get(username).equals(password)) {
            session.setAttribute("username", username);
            return "redirect:/";
        }

        // If login fails
        return "redirect:/login?error=Invalid Credentials";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}