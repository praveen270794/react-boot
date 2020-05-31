package io.praveen.reactbootapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.praveen.reactbootapp.model.User;
import io.praveen.reactbootapp.model.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Optional<User> findById(String userId) {
		return userRepository.findById(userId);
	}

}
