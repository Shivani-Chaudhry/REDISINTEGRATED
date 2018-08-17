package com.soft.infg.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.soft.infg.dao.entity.BeanClass;
import com.soft.infg.demo.service.ServiceInterface;
@Controller


public class ControllerClass {
	@Autowired
	private ServiceInterface medservice;
	private AuthenticationManager authenticationManager;



	
	
	@GetMapping("medicine/{id}")
	public ResponseEntity<BeanClass> getMedicineById(@PathVariable("id") Integer id) {
		boolean x=medservice.checkMedicineById(id);
		if(x==true)
		{
			BeanClass med =medservice.getMedicineById1(id);
			return new ResponseEntity<BeanClass>(med, HttpStatus.OK);
		}
		else
		return new ResponseEntity<BeanClass>(HttpStatus.CONFLICT);		
	}

	@GetMapping("allmedicine")
	public ResponseEntity<List<BeanClass>> getAllMedicine(){
		List<BeanClass> l =medservice.getAllMedicine();
		return new ResponseEntity<List<BeanClass>>(l, HttpStatus.OK);

	}

	@PostMapping("add")
	public ResponseEntity<String> addMedicine(@Valid @RequestBody BeanClass medicine, UriComponentsBuilder builder) {
		boolean flag = medservice.addMedicine(medicine);
		if (flag == false) {
			return new ResponseEntity<String>("Medicine already exists",HttpStatus.CONFLICT);
		}

		return new ResponseEntity<String>("Medicine Inserted",HttpStatus.CREATED);
	}

	@PutMapping("update")
	public ResponseEntity<BeanClass> updateMedicine(@RequestBody BeanClass medicine) {
		medservice.updateMedicine(medicine);
		return new ResponseEntity<BeanClass>(medicine, HttpStatus.OK);
	}
	
	@DeleteMapping("medicine/{id}")
	public ResponseEntity<String> deleteMedicine(@PathVariable("id") Integer id) {
		boolean x=medservice.checkMedicineById(id);
		if(x==false)
		{
			BeanClass med =medservice.getMedicineById1(id);
			return new ResponseEntity<String>("Medicine does not exists", HttpStatus.OK);
		}
		else {
		medservice.deleteMedicine(id);
		return new ResponseEntity<String>("Medicine Deleted",HttpStatus.NO_CONTENT);
		}
	}	
	
	
	/*@DeleteMapping("medicine/{id}")
	public ResponseEntity<String> deleteMedicine(@PathVariable("id") Integer id) {
		medservice.deleteMedicine(id);
		return new ResponseEntity<String>("Medicine Deleted",HttpStatus.NO_CONTENT);
	}*/	
	

	/*@PostMapping("addUser")
	public ResponseEntity<String> addUser(@RequestBody User user,UriComponentsBuilder builder)
	{	boolean flag = medservice.addUser(user);
		if (flag == false) {
			return new ResponseEntity<String>("user does not exists",HttpStatus.CONFLICT);
		}

		return new ResponseEntity<String>("Welcome User",HttpStatus.CREATED);
	}*/
/*	@Get
	@Secured
	@Path("/id")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	
public UserAuh*/
	
	 @RequestMapping("/users")
	  public @ResponseBody String getUsers() {
	    return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
	           "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
	  }
	 
	 /*ControllerClass(AuthenticationManager authenticationManager) {
	        this.authenticationManager = authenticationManager;
	    }
	 
	 @GetMapping
	    ResponseEntity index(HttpServletRequest request, HttpSession session) {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
	    }
	 
	 @PostMapping("/loginuser")
	 ResponseEntity login(@RequestBody UserDetails loginRequest) {
	     String username = loginRequest.getUsername();
	     String password = loginRequest.getPassword();
	     UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
	     Authentication authentication = this.authenticationManager.authenticate(token);
	     // vvv THIS vvv
	     SecurityContextHolder
	         .getContext()
	         .setAuthentication(authentication);
	     return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
	 }*/

	}
 


