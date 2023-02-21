package com.example.demo.Controller;

import com.example.demo.Config.CustomUserDetail;
import com.example.demo.Config.JwtUtil;
import com.example.demo.Model.JwtRequest;
import com.example.demo.Model.User;
import com.example.demo.Model.UserDto;
import com.example.demo.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class HomeController {

    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CustomUserDetailsService customUserDetailsService;



    @RequestMapping("/loginProcess")
    public void processlogin(){
        System.out.println("Processing login");
    }
    @GetMapping("/")
        public void mainPage(){
            System.out.println("hello");
        }


    @GetMapping("/check")
    public ResponseEntity<?> check(@RequestParam("test") String ok){
        System.out.println(ok);
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/check").toUriString());
        return new ResponseEntity<String>("todo.html", HttpStatus.ACCEPTED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> checklogin(@RequestBody UserDto userDto){
        String email= userDto.getEmail();
        String pass=userDto.getPassword();
        System.out.println("login" +email + pass);


        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, pass);
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();

            System.out.println(authentication.getPrincipal());
            String jwt = jwtUtil.generateToken(userDetails);

            System.out.println("hello"+jwt);
            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            System.out.println("details:"+authentication.getDetails());
            return new ResponseEntity<>(jwt,HttpStatus.OK);

        }catch (Exception e){

            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }




    }





}
