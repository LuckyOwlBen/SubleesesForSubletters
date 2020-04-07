package com.slsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slsl.entities.Password;

@Repository
public interface PasswordRepo extends JpaRepository<Password, Long> {

}
