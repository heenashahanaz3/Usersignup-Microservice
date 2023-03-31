package com.Usersignup.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Usersignup.model.UserRetail;

public interface UserRepository extends JpaRepository<UserRetail, String>{
}
