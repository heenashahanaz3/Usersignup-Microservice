package com.Usersignup.service;

import org.springframework.stereotype.Service;

import com.Usersignup.Repository.UserRepository;
import com.Usersignup.exception.ResourceNotFoundException;
import com.Usersignup.model.UserRetail;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userrepository;

	public UserServiceImpl(UserRepository userrepository) {
		super();
		this.userrepository=userrepository;
	}

	@Override
	public String saveUserDetails(UserRetail userretail) {
		if(!userrepository.existsById(userretail.getEmail())) {
			userrepository.save(userretail);
			return userretail.getFirstname() + " your registration is successfull";
		}
		else {
			return "Email id already exist!!";
		}
	}

	@Override
	public UserRetail getUserRetailbyId(String email) {
		// TODO Auto-generated method stub
		
		return userrepository.findById(email).orElseThrow(()-> new ResourceNotFoundException("UserRetail", "Id", email));
	}
	

	@Override
	public UserRetail updateUserRetail(UserRetail userretail, String email) {
		// TODO Auto-generated method stub
		UserRetail existingUserRetail = userrepository.findById(email).orElseThrow(()-> new ResourceNotFoundException("UserRetail", "Id", email));
		existingUserRetail.setFirstname(userretail.getFirstname());
		existingUserRetail.setLastname(userretail.getLastname());
		existingUserRetail.setEmail(userretail.getEmail());
		existingUserRetail.setPhonenumber(userretail.getPhonenumber());
		userrepository.save(existingUserRetail);
		return existingUserRetail;
	}

	@Override
	public void deleteuser(String email) {
		userrepository.findById(email);
		userrepository.deleteById(email);
		
	}

}
