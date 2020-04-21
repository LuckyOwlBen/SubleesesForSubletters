package com.slsl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slsl.entities.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {
	
	List<UserModel> findByEmail(String email);
}
