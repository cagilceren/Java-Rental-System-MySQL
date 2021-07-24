package com.rental.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rental.app.Exceptions.ResourceNotFoundException;
import com.rental.app.Repository.UserRepository;
import com.rental.app.model.User;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/user")
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	@GetMapping("/user/{id}")
	public User getById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
		User user = userRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("There is no user with the id of " + id));
		return user;
	}
	
	@PostMapping("/user")
	public User create(@Validated @RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PutMapping("/user/{id}")
	public User update(@PathVariable("id") Integer id, @Validated @RequestBody User user) throws ResourceNotFoundException {
		User userToUpdate = userRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("There is no user with the id of " + id));
		
		userToUpdate.name = user.name;
		userToUpdate.email = user.email;
		userToUpdate.password = user.password;
		return userToUpdate;	
	}
	
	@DeleteMapping("/user/{id}")
	public void delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
		User user = userRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("There is no user with the id of " + id));
		userRepository.delete(user);
	}

}
