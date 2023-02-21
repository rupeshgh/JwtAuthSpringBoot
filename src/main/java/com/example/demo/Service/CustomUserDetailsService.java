package com.example.demo.Service;


import com.example.demo.Config.CustomUserDetail;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import org.springframework.stereotype.Component;



@Component
public class CustomUserDetailsService implements UserDetailsService {

@Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username+" loadby");
        User user= userRepository.findByEmail(username);
        System.out.println(user.getEmail() +"");


        return new CustomUserDetail(user);
    }
}
