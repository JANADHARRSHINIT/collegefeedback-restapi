package com.project.feedback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.feedback.entity.Userentity;
import com.project.feedback.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<Userentity> getAllUsers() {
        return repo.findAll();
    }

    public Userentity saveUser(Userentity user) {
        return repo.save(user);
    }

    public Optional<Userentity> getUserById(Long id) {
        return repo.findById(id);
    }

    public void deleteUser(Long id) {
        repo.deleteById(id);
    }
}
