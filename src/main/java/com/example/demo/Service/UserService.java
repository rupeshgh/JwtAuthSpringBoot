package com.example.demo.Service;
import com.example.demo.Model.Roles;


import com.example.demo.Model.User;
import com.example.demo.Repository.RolesRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

 @Autowired
UserRepository userRepository;

 @Autowired
 RolesRepository rolesRepository;

    public void saveUser(User user){
    userRepository.save(user);

    }
  public  void saveRole(Roles role){
        rolesRepository.save(role);

    }

  public  void addRoleToUser(String email,String role){
        User user=userRepository.findByEmail(email);
        Roles roles=rolesRepository.findByName(role);

         user.getRole().add(roles);

         userRepository.save(user);
    }

   public  User getUser(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
