package uth.java.duan_java.services;

import uth.java.duan_java.models.dtos.UserDTO;

import java.util.List;

public interface IUserService {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user,Long id);

    boolean deleteUser(Long id);

}
