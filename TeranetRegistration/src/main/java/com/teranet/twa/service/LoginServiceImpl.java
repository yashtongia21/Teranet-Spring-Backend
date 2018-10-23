package com.teranet.twa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teranet.twa.dao.LoginRepository;
import com.teranet.twa.dao.SignUpRepository;
import com.teranet.twa.model.Login;
import com.teranet.twa.model.SignUp;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private SignUpRepository sus;

	@Autowired
	private LoginRepository logRepo;

	@Override
	public boolean existsByEmailId(String emailId) {

		return sus.existsByEmailId(emailId);

	}

	@Override
	public boolean checkPassword(Login login) {
		boolean isValid = false;

		SignUp s1 = sus.findByEmailId(login.getEmailId());

		if (s1.getPassword().equals(login.getPassword())) {
			isValid = true;
		}
		return isValid;

	}

	@Override
	public Login addLoggedUser(Login login) {
		return logRepo.save(login);
	}

	@Override
	public boolean deleteUser(String email) {
		boolean isDeleted = false;
		if (logRepo.existsByEmailId(email)) {
			logRepo.deleteById(email);
			isDeleted = true;
		}
		return isDeleted;
	}

	@Override
	public Login findByEmailId(String emailId) {

		Login l1 = new Login();
		Optional<Login> optLogin = logRepo.findById(emailId);
		if (optLogin.isPresent()) {
			l1 = optLogin.get();
		}
		System.out.println(l1.getEmailId());
		return l1;
	}

}
