package uth.java.duan_java.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uth.java.duan_java.models.dtos.UserDTO;
import uth.java.duan_java.repositories.UserRepo;
import uth.java.duan_java.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;


    // 1. Lấy danh sách tất cả người dùng
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // 2. Đăng ký người dùng mới
    @PostMapping
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO newUser = userService.createUser(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


    // 3. Lấy người dùng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // 4. Cập nhật thông tin người dùng
    @PutMapping("{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUser) {
        UserDTO user = userService.updateUser(updatedUser,id);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    // 5. Xóa người dùng
    @DeleteMapping("{id}")
    public ResponseEntity<String> removeUser(@PathVariable Long id) {
        boolean user = userService.deleteUser(id);
        if(user){
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("User fail to delete", HttpStatus.OK);
    }
}
