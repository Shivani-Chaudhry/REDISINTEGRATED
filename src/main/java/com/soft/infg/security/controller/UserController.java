package com.soft.infg.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.soft.infg.dao.entity.User;
import com.soft.infg.security.model.UserDto;
import com.soft.infg.security.service.UserService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController("signUpController")

public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public User saveUser(@RequestBody UserDto user) {

		return userService.save(user);
	}

	@PreAuthorize("hasRole('ADMIN')")

	@RequestMapping(value = "/removeUser/{name}", method = RequestMethod.DELETE)

	public void removeUser(@PathVariable(value = "name") String username) {
		User user = userService.findOne(username);
		if (user != null) {
			userService.delete(user.getId());
		} else
			System.out.println("No user for this ID exists");

	}
	
	/*@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "All Users registered")
	@RequestMapping(value="/showAll", method = RequestMethod.GET)
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public List<UserDto> showAllUsers(){
		List<UserDto> lst = new ArrayList<>();
		List<User> lst2 = userService.findAll();
		ModelMapper model = new ModelMapper();
		for (User user : lst2) {
			lst.add(model.map(user, UserDto.class));
		}
		return lst;
		*/
		
	}


