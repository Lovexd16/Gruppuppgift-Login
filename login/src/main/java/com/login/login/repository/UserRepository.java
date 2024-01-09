package com.login.login.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.login.login.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}