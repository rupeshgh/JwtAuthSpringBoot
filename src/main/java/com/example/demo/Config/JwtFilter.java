package com.example.demo.Config;

import com.example.demo.Service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String tokenHeader= request.getHeader("Authorization");
        System.out.println("Do jwtfilter");
       String jwttoken=null;
       String username=null;
       if(tokenHeader!=null && tokenHeader.startsWith("Bearer ")) {
           jwttoken = tokenHeader.substring(7);

           try {
               username = this.jwtUtil.extractUsername(jwttoken);


               UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

               if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                   UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                   usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                   SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
               }


           }catch (Exception e)
           {
               System.out.println(e.getMessage());
           }
       }

        filterChain.doFilter(request,response);

    }
}
