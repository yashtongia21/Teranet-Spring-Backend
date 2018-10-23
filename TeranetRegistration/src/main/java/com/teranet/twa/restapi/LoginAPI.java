package com.teranet.twa.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teranet.twa.model.Login;
import com.teranet.twa.model.SignUp;
import com.teranet.twa.service.LoginService;
import com.teranet.twa.service.SignUpService;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginAPI {

	@Autowired
	private LoginService loginService;

	@Autowired
	private SignUpService signService;

	@GetMapping("/{email}")
	public ResponseEntity<Login> getUserLogin(@PathVariable("email") String emailCheck) {
		ResponseEntity<Login> resp = null;

		System.out.println(emailCheck);

		Login l1 = loginService.findByEmailId(emailCheck);

		
		if (l1 != null) {
			resp = new ResponseEntity<Login>(l1, HttpStatus.OK);
		} else {
			resp = new ResponseEntity<Login>(HttpStatus.NOT_FOUND);
		}

		return resp;

	}

	@PostMapping
	public ResponseEntity<SignUp> checkUser(@RequestBody Login login) {
		ResponseEntity<SignUp> resp = null;

		SignUp s1 = new SignUp();

		if (loginService.existsByEmailId(login.getEmailId())) {
			if (loginService.checkPassword(login)) {
				loginService.addLoggedUser(login);

				s1 = signService.findByEmailId(login.getEmailId());
				resp = new ResponseEntity<SignUp>(s1, HttpStatus.OK);
			} else {
				resp = new ResponseEntity<SignUp>(HttpStatus.BAD_REQUEST);
			}
		} else {
			resp = new ResponseEntity<SignUp>(HttpStatus.NOT_ACCEPTABLE);
		}
		return resp;

	}

	@DeleteMapping("/{email}")
	public ResponseEntity<Login> deleteUser(@PathVariable("email") String emailDel) {
		ResponseEntity<Login> resp = null;


		if (loginService.deleteUser(emailDel)) {
			resp = new ResponseEntity<Login>(HttpStatus.OK);
		} else {
			resp = new ResponseEntity<Login>(HttpStatus.NOT_FOUND);
		}
		return resp;

	}

}
