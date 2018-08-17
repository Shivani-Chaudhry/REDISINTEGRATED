package com.soft.infg.security.controller;

import static com.soft.infg.security.model.Constants.*;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.soft.infg.security.config.TokenProvider;
import com.soft.infg.security.model.AuthToken;
import com.soft.infg.security.model.Constants;
import com.soft.infg.security.model.LoginUser;
import com.soft.infg.security.service.RedisService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

public class AuthenticationController {

	@Autowired
    private RedisTemplate<String,String> redisTemplate;
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

	@Autowired
	private RedisService redis;
  
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        redisTemplate.opsForValue().set(loginUser.getUsername(),token);
        redisTemplate.expire(loginUser.getUsername(), Constants.ACCESS_TOKEN_VALIDITY_SECONDS, TimeUnit.SECONDS);
        return ResponseEntity.ok(new AuthToken(token));
    }
    
    

}
