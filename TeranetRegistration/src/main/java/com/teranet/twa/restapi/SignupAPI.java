package com.teranet.twa.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teranet.twa.model.SignUp;
import com.teranet.twa.service.SignUpService;

@RestController
@CrossOrigin
@RequestMapping("/signup")
public class SignupAPI {

	@Autowired
	private SignUpService signService;
	
	@GetMapping("/{email}")
	public ResponseEntity<SignUp> getUser(@PathVariable("email") String emailSearching) {
		ResponseEntity<SignUp> resp = null;

		SignUp s1 = signService.findByEmailId(emailSearching);

		if (s1!=null) {
			resp = new ResponseEntity<SignUp>(s1, HttpStatus.OK);
		} else {
			resp = new ResponseEntity<SignUp>(HttpStatus.NOT_FOUND);
		}

		return resp;
	}
	

	@GetMapping("/{email}/{mobile}")
	public ResponseEntity<SignUp> forgotPassword(@PathVariable("email") String emailSearching,
			@PathVariable("mobile") String mobileSearching) {
		ResponseEntity<SignUp> resp = null;

		SignUp s1 = signService.findByEmailId(emailSearching);

		if (s1.getEmailId().equalsIgnoreCase(emailSearching) && s1.getMobileNumber().equals(mobileSearching)) {
			resp = new ResponseEntity<SignUp>(s1, HttpStatus.OK);
		} else {
			resp = new ResponseEntity<SignUp>(HttpStatus.NOT_FOUND);
		}

		return resp;
	}

	@PostMapping
	public ResponseEntity<SignUp> addUser(@RequestBody SignUp signup) {
		ResponseEntity<SignUp> resp = null;

		if (signService.existsByEmailId(signup.getEmailId())) {
			resp = new ResponseEntity<SignUp>(HttpStatus.ALREADY_REPORTED);
		}

		if (resp == null) {
			if (signService.checkConfirmPassword(signup)) {
				SignUp s1 = signService.addUser(signup);

				if (s1 == null) {
					resp = new ResponseEntity<SignUp>(HttpStatus.BAD_REQUEST);
				} else {
					resp = new ResponseEntity<SignUp>(s1, HttpStatus.OK);
				}
			} else {
				resp = new ResponseEntity<SignUp>(HttpStatus.EXPECTATION_FAILED);
			}
		}
		return resp;

	}

}
