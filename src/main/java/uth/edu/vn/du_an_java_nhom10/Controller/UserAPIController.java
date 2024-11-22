package uth.edu.vn.du_an_java_nhom10.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import uth.edu.vn.du_an_java_nhom10.Model.OrderFish;
import uth.edu.vn.du_an_java_nhom10.Model.OrderTrip;
import uth.edu.vn.du_an_java_nhom10.Model.User;
import uth.edu.vn.du_an_java_nhom10.Repository.UserRepository;
import uth.edu.vn.du_an_java_nhom10.Service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class UserAPIController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    @PostMapping("/user/remove")
    public ResponseEntity<String> removeUser(@RequestBody User user) {
        Long userId = user.getId();
        userService.deleteUser(userId);
        return ResponseEntity.ok("Removed successfully");
    }
}