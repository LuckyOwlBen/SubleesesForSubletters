package com.slsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slsl.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
