package com.example.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entities.Role;
import com.example.entities.User;
import com.example.repositories.UserRepository;

@Service(value = "userService")
public class ServiceUserIMPL implements UserDetailsService, IServiceUser{

	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void register(User u) {
	    u.setPassword(bcryptEncoder.encode(u.getPassword()));
		userRepository.save(u);
		
	}

	@Override
	public List<User> getAll() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(int id) {
		userRepository.deleteById(id);		
	}


	@Override
	public User findById(int id) {
		return userRepository.findById(id).get();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), AuthorityUtils.NO_AUTHORITIES );
	}
	
	/*private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		Role role = user.getRole();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		return authorities;
	}*/

	@Override
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

}
