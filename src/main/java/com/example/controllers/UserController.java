package com.example.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.entities.User;
import com.example.services.IServiceUser;
import com.example.utils.FileStorageService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;


@CrossOrigin(origins = "*")
@RestController
public class UserController {

	@Autowired
    private IServiceUser userService;
	
	@Autowired
	private FileStorageService fs;

    @GetMapping(value="/users")
    public List<User> listUser(){
        return userService.getAll();
    }

    @GetMapping(value = "/users/{id}")
    public User getOne(@PathVariable(value = "id") int id){
        return userService.findById(id);
    }
    
    @GetMapping(value = "/user/{id}/photo")
    public Resource getPhoto(@PathVariable int id) throws IOException {
    	String fileName = userService.getFileName(id);
    	return fs.loadFileAsResource(fileName);
    }

}
