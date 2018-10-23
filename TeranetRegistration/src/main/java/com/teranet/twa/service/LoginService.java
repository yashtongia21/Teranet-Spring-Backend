package com.teranet.twa.service;

import com.teranet.twa.model.Login;

public interface LoginService {

	boolean existsByEmailId(String emailId);

	boolean checkPassword(Login login);
	
	Login addLoggedUser(Login login);

	boolean deleteUser(String email);
	
	Login findByEmailId(String emailId);
}
