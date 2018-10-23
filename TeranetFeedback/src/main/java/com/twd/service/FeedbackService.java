package com.twd.service;

import com.twd.model.Feedback;

public interface FeedbackService {
	
	Feedback getFeedbackById(long feedbackId);
	
	Feedback addFeedback(Feedback feedback);
}
