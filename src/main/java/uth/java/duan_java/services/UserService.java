package uth.java.duan_java.services;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uth.java.duan_java.models.dtos.CartDTO;
import uth.java.duan_java.models.dtos.UserDTO;
import uth.java.duan_java.models.entities.User;
import uth.java.duan_java.repositories.UserRepo;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CartService cartService;

    @Override
    public List<UserDTO> getAllUsers(){
        List<User> users = userRepo.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }
        return modelMapper.map(users, new TypeToken<List<UserDTO>>(){}.getType());
    }
    @Override
    public UserDTO getUserById(Long id){
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserDTO user){
        User user1 = modelMapper.map(user, User.class);
        User existingUser = userRepo.findByEmail(user1.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("User already exists");
        }
        User newUser = userRepo.save(user1);
        CartDTO newCartDTO = cartService.createCart(modelMapper.map(newUser,UserDTO.class));
        if (newCartDTO == null) {
            throw new RuntimeException("New cart fail to created");
        }

        return modelMapper.map(newUser, UserDTO.class);
    }

    public UserDTO updateUser(UserDTO user,Long id){
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found"));
        existingUser.setUsername(user.getUsername());
        existingUser = userRepo.save(existingUser);
        return modelMapper.map(existingUser, UserDTO.class);
    }

    public boolean deleteUser(Long id){
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found"));
        userRepo.delete(existingUser);
        return true;
    }


    //change password
    //forgot password
}
