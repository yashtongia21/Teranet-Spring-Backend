package com.teranet.twa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teranet.twa.model.SignUp;

@Repository
public interface SignUpRepository extends JpaRepository<SignUp, Long> {

	boolean existsByEmailId(String emailId);

	SignUp findByEmailId(String emailId);


}
