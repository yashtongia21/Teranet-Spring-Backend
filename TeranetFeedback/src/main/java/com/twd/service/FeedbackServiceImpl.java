package com.twd.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twd.dao.FeedbackRepository;
import com.twd.model.Feedback;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	FeedbackRepository feedbackRepo;
	
	@Override
	public Feedback addFeedback(Feedback feedback) {
		return feedbackRepo.save(feedback);
	}

	@Override
	public Feedback getFeedbackById(long feedbackId) {
		Feedback fb = null;
		
		Optional<Feedback> optFb = feedbackRepo.findById(feedbackId);
		
		if( optFb.isPresent()) {
			fb = optFb.get();
		}
		return fb;
	}

}
