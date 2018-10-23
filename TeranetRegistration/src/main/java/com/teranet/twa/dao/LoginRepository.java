package com.teranet.twa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teranet.twa.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {

	boolean existsByEmailId(String emailId);

	
}
