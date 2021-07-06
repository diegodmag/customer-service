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

import com.dws.customerservice.dto.Region;
import com.dws.customerservice.service.RegionService;

@RestController

@RequestMapping("/region")
public class RegionController {
	
	@Autowired 
	RegionService regionService; 
	
	@GetMapping 
	public ResponseEntity<Object> getRegions(){
		return new ResponseEntity<>(regionService.getRegions(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}") //Para indicar que el metodo recibe un entero id y que NO ES PARTE DE LA URL  se utiiza {id}
	public ResponseEntity<Object> getRegion(@PathVariable("id") int id){
		return new ResponseEntity<>(regionService.getRegion(id), HttpStatus.OK);
	}
	
	@PostMapping //Para indicar que el metodo recibe un entero id y que NO ES PARTE DE LA URL  se utiiza {id}
	public ResponseEntity<Object> createRegion(@RequestBody Region region){
		return new ResponseEntity<>(regionService.createRegion(region), HttpStatus.OK);
	}
	
	@PutMapping("/{id}") 
	public ResponseEntity<Object> upadteRegion(@PathVariable("id") int id, @RequestBody Region region){
		return new ResponseEntity<>(regionService.updateRegion(region, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteRegion(@PathVariable("id") int id){
		return new ResponseEntity<>(regionService.deleteRegion(id), HttpStatus.OK);
	}
	
}
