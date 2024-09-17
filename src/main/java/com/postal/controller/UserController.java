package com.postal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.postal.serviceimplementation.UserServiceImplementation;
import com.postal.model.User;


@RestController
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserServiceImplementation service;
	
	@PostMapping("/addUser")
	public String userAdd(@RequestBody User user) {
		service.addUser(user);
		return "added";

	}

	
	@GetMapping("/Login/{email}/{password}")
	public ResponseEntity<?> validateLogin(@PathVariable("email") String email,
			@PathVariable("password") String password) {
		try {
			User user = service.Login(email, password);
			if (user != null) {
				return ResponseEntity.ok(user);
			}
		} catch (Exception e) {
			System.out.println("Error user login");

		}

		return (ResponseEntity<?>) ResponseEntity.badRequest();
	}

	@GetMapping("/getUsers")
	public List<User> getAll() {
		return service.getAllUsers();

	}
	
	 @GetMapping("/getuser/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
	        User user = service.findById(id);
	        if (user != null) {
	            return new ResponseEntity<>(user, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	@PutMapping("/userUpdate")
	public String userUpadet(@RequestBody User user) {
		service.updateUser(user);
		return "User Object Updated";

	}
	
}
