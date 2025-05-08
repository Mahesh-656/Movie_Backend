package com.movie.movieRest.service;

import com.movie.movieRest.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    public MyUserDetailsService() {
        System.out.println("MyUserDetailsService constructor");
    }

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
