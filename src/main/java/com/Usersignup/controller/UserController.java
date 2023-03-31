package com.Usersignup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.Usersignup.model.UserRetail;
import com.Usersignup.service.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin("http://localhost:3000")

public class UserController {
	private UserService userservice;
	
	public UserController(UserService userservice) {
		super();
		this.userservice = userservice;
	}
	
	//saving data
	@PostMapping("/add")
	public ResponseEntity<String> saveUserDetails(@RequestBody UserRetail userretail){
		String pass = userretail.getPassword();
        userretail.setPassword(new BCryptPasswordEncoder().encode(pass));
		return new ResponseEntity<String>(userservice.saveUserDetails(userretail),HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{email}")
	public ResponseEntity<UserRetail> getUserRetailbyId(@PathVariable("email") String email){
		return new ResponseEntity<UserRetail>(userservice.getUserRetailbyId(email),HttpStatus.OK);
		
	}
	
	
	@PutMapping("/update/{email}")
	public ResponseEntity<UserRetail> updateUserRetail(@PathVariable("email") String email, @RequestBody UserRetail userretail){
		return new ResponseEntity<UserRetail>(userservice.updateUserRetail(userretail, email),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{email}")
	public String deleteuser(@PathVariable("email") String email){
		
		//delete employee from DB
		try {
		userservice.deleteuser(email);
		return ("User Deleted Successfully!.");
		}catch(Exception e) {
			return e.toString();
		}
	}
	

}
