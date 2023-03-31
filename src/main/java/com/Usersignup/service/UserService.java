package com.Usersignup.service;

import com.Usersignup.model.UserRetail;

public interface UserService {
	String saveUserDetails(UserRetail userretail);
	UserRetail getUserRetailbyId(String email);
	UserRetail updateUserRetail(UserRetail userretail, String email);
	void deleteuser(String email);
}
