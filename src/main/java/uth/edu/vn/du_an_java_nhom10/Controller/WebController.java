package uth.edu.vn.du_an_java_nhom10.Controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uth.edu.vn.du_an_java_nhom10.Model.User;
import uth.edu.vn.du_an_java_nhom10.Service.UserService;

import java.util.Optional;

@Controller
public class WebController {
    private UserService userService;
    @Autowired
    public void ProductController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/Home")
    public String homePage(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        model.addAttribute("userId", userId);
        model.addAttribute("currentUrl", "/Home");
        return "index"; // or your relevant view
    }
    @GetMapping("/Introduce")
    public String introduce(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        model.addAttribute("userId", userId);
        model.addAttribute("currentUrl", "/Introduce");
        return "introduce";
    }
    @GetMapping("/Trip")
    public String trip(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        model.addAttribute("userId", userId);
        model.addAttribute("currentUrl", "/Trip");
        return "trip";
    }
    @GetMapping("/Fish")
    public String Fish(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        model.addAttribute("userId", userId);
        model.addAttribute("currentUrl", "/Fish");
        return "Fish";
    }
    @GetMapping("/Blog")
    public String blog( HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        model.addAttribute("userId", userId);
        model.addAttribute("currentUrl", "/Blog");
        return "blog";
    }
    @GetMapping("/Contact")
    public String contact(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        model.addAttribute("userId", userId);
        model.addAttribute("currentUrl", "/Contact");
        return "contact";
    }
    @GetMapping("/Pay")
    public String Pay(Model model) {
        model.addAttribute("currentUrl", "/Pay");
        return "pay";
    }
    @GetMapping("/Add")
    public String add(Model model) {
        return "Add";
    }
    @GetMapping("/Delete")
    public String delete(Model model) {
        return "Delete";
    }
    @GetMapping("/Edit")
    public String edit(Model model) {
        return "Edit";
    }
    @GetMapping("/Search")
    public String search(Model model) {
        return "Search";
    }
    @GetMapping("/Header")
    public String Header(@PathVariable("id") Long id, HttpSession session, Model model) {
        // Lấy userId từ session
        Long userId = (Long) session.getAttribute("loggedInUserId");

        if (userId != null) {
            // Lấy thông tin người dùng từ service bằng userId
            Optional<User> userOptional = userService.findById(userId);

            if (userOptional.isPresent()) {
                // Thêm đối tượng user vào model
                model.addAttribute("user", userOptional.get());
            }
        }
        return "header";  // Trả về view (thường là một file HTML)
    }
}
