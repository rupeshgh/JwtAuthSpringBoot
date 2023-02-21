package com.example.demo.Controller;

import com.example.demo.Config.JwtUtil;
import com.example.demo.Model.JwtRequest;
import com.example.demo.Model.User;
import com.example.demo.Service.CustomUserDetailsService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class mainController  {
    @Autowired
    UserService userService;



    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CustomUserDetailsService customUserDetailsService;


@GetMapping("/")
public void welcomeUser(){
    System.out.println("welcome user");
}
    @PostMapping("/save")
    public void saveUser(@RequestBody User user){

        userService.saveUser(user);

    }

    @GetMapping("/getUser")
    public User user(@RequestParam("email") String email){
        return userService.getUser(email);
    }


    @GetMapping("/getAllUser")
    public ResponseEntity<?> getuserss(){

        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }
    @GetMapping("/dummyUser")
    public String getUser(){

        return "hello user";
    }
    @PostMapping("/addRoles")
    public void addRoles(@RequestParam("email") String email,@RequestParam("role") String role){


        userService.addRoleToUser(email,role);
    }


}
