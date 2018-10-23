package com.twd.restapi;

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

import com.twd.model.Feedback;
import com.twd.service.FeedbackService;

@RestController
@CrossOrigin
@RequestMapping("/feedback")
public class FeedbackApi {
	
	@Autowired
	private FeedbackService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Feedback> getFeedbackById(@PathVariable("id") long feedbackId) {
		ResponseEntity<Feedback> resp;
		Feedback fb = service.getFeedbackById(feedbackId);
		
		if(fb == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<>(fb, HttpStatus.OK);
		
		return resp;
	}
	
	@PostMapping
	public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback) {
		ResponseEntity<Feedback> resp = null;
		
		if (resp == null) {
			Feedback fb = service.addFeedback(feedback);
			
			if(fb == null) {
				resp = new ResponseEntity<Feedback>(HttpStatus.BAD_REQUEST);
			}else {
				resp = new ResponseEntity<Feedback>(fb, HttpStatus.OK);
			}
		}
		
		return resp;
	}
	
}
