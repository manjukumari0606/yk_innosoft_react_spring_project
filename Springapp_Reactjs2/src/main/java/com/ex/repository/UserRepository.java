package com.ex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.entity.User_Info;

public interface UserRepository extends JpaRepository<User_Info, Long>{

	User_Info findByEmail(String email);

}
