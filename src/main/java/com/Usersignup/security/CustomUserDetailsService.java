package com.Usersignup.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Usersignup.Repository.UserRepository;
import com.Usersignup.model.UserRetail;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	  @Autowired
	    private UserRepository userRepo;
	  
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
    	UserRetail userlogin = userRepo.findById(username).get();
        if(userlogin == null) {
            return null;
        }
        
        return new CustomUserDetails(userlogin);
    }
    
}
