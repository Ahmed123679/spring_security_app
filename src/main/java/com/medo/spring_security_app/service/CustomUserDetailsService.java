package com.medo.spring_security_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.medo.spring_security_app.model.CustomUserDetail;
import com.medo.spring_security_app.model.User;
import com.medo.spring_security_app.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{


    private User user;

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = userRepo.findByUsername(username);
         if (user == null)
         {
            throw new UsernameNotFoundException(username + " not found");
         }
         return new CustomUserDetail(user);
         
            
    }
    
}
