package com.rental.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rental.app.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{}
