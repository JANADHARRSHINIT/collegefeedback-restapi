package com.project.feedback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.feedback.entity.Userentity;
import com.project.feedback.repository.UserRepository;

@Service
public class UserService {
     @Autowired
    private UserRepository repo;
    public List<Userentity> getAllFeedback() {
        return repo.findAll();
    }

    public Userentity saveFeedback(Userentity feedback) {
        return repo.save(feedback);
    }
   


}
