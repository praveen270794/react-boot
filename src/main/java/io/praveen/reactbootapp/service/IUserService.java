package io.praveen.reactbootapp.service;

import java.util.Optional;

import io.praveen.reactbootapp.model.User;

public interface IUserService {
	Optional<User> findById(String userId);
}
