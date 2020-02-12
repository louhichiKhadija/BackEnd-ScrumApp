package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.configSecurity.TokenProvider;
import com.example.entities.ConfirmationToken;
import com.example.entities.User;
import com.example.repositories.ConfirmationTokenRepository;
import com.example.services.IServiceUser;
import com.example.utils.AuthToken;
import com.example.utils.MailService;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	 @Autowired
	 private AuthenticationManager authenticationManager;

	 @Autowired
	 private TokenProvider jwtTokenUtil;

	 @Autowired
	 private IServiceUser userService;
	 
	 @Autowired
	 private ConfirmationTokenRepository confirmationTokenRepository; 
	 
	 @Autowired
	 private MailService mailService;
	    
	   

	    @PostMapping(value = "/login")
	    public ResponseEntity<?> register(@RequestBody User loginUser) throws AuthenticationException {

	        final Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginUser.getEmail(),
	                        loginUser.getPassword()
	                )
	        );
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        final String token = jwtTokenUtil.generateToken(authentication);
	        return ResponseEntity.ok(new AuthToken(token));
	    }
	    
	    @PostMapping(value="/register")
	    public ResponseEntity<?> saveUser(@RequestBody User user){
	    	user.setEnabled(false);
	    	userService.register(user);
	    	
	    	ConfirmationToken confirmationToken = new ConfirmationToken(user);
	        confirmationTokenRepository.save(confirmationToken);
	        mailService.sendConfirmationEmail(user,confirmationToken);
	    	
	        return  new ResponseEntity<String>("Congratulations! Your account has been created"
	        		+ "Please check your email to continue the registration ", HttpStatus.ACCEPTED);
	    }
	    
	    
	    
	    @GetMapping(value = "/confirm-account")
	    public String confirmUserAccount(@RequestParam("token")String confirmationToken)
	    {
			ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

	        if(token != null)
	        {
	            User user = userService.findByEmail(token.getUser().getEmail());
	            user.setEnabled(true);
	            userService.register(user);
	            return "Account is now enabled";
	        }
	        else
	        {
	            return "The link is invalid or broken!";
	        }
	     }


}
