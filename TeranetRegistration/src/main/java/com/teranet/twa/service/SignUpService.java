package com.teranet.twa.service;

import com.teranet.twa.model.SignUp;

public interface SignUpService {

	boolean existsByEmailId(String emailId);

	SignUp findByEmailId(String emailId);

	boolean checkConfirmPassword(SignUp signup);

	SignUp addUser(SignUp signup);
}
