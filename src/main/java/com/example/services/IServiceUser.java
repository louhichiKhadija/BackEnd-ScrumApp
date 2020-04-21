package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.entities.User;

public interface IServiceUser {

	public void register(User u);
	public List<User>  getAll();
	public void delete(int id);
    public User findById(int id);
    public User findByEmail(String email);
    public void updateImage(int id, String image);
    public void updateUser(int id,User user);
    public void updatePassword(int id, String password);

    //void updateUser(Optional<User> users,User user);

    public Optional<User> findUser(int id);

    public String uploadImage(MultipartFile file);
    public String getFileName(int id);

}
