package com.dws.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dws.customerservice.dto.Customer;
import com.dws.customerservice.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService; 
	
	@GetMapping 
	public ResponseEntity<Object> getCostumers(){
		return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}") //Para indicar que el metodo recibe un entero id y que NO ES PARTE DE LA URL  se utiiza {id}
	public ResponseEntity<Object> getCustomer(@PathVariable("id") int id){
		return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
	}
	
	@PostMapping //Para indicar que el metodo recibe un entero id y que NO ES PARTE DE LA URL  se utiiza {id}
	public ResponseEntity<Object> createCustomer(@RequestBody Customer customer){
		return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.OK);
	}
	
	@PutMapping("/{id}") 
	public ResponseEntity<Object> upadteCustomer(@PathVariable("id") int id, @RequestBody Customer customer){
		return new ResponseEntity<>(customerService.updateCustomer(customer, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable("id") int id){
		return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
	}
}
