package com.dws.customerservice.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dws.customerservice.dto.Region;
import com.dws.customerservice.dto.RespuestaApi;

@Repository 
public class RegionRepository {
	
	@Autowired 
	JdbcTemplate jdbcTemplate; 
	
	public List<Region> getRegions(){
		List<Region> regions = new ArrayList<>();
		
		regions = jdbcTemplate.query("SELECT * FROM region", new BeanPropertyRowMapper<Region>(Region.class)); 
		
		return regions; 
		
	}
	
	public Region getRegion(int id){

		
		Region region = new Region();
		//Para buscar por un objeto especifico 
		region = jdbcTemplate.queryForObject("SELECT * FROM region WHERE id = " + id + ";", new BeanPropertyRowMapper<Region>(Region.class)); 
		
		return region; 
	}
	
	public RespuestaApi createRegion(Region region){
		
		//Update funciona para pasarle sentencias como INSERTE, UPDATE, DELETE 
		//VALUES('') es la sintaxis para agregar los valores, en este caso el nombre de la region 
		//INSERCION : 
		jdbcTemplate.update("INSERT INTO region (region) VALUES ('" + region.getRegion() + "');"); 
		
		RespuestaApi msg = new RespuestaApi(); 
		msg.setMessage("La region ha sido registrada");  

		return msg; 
		
	}
	
	public RespuestaApi updateRegion(Region region, int id){
		
		//Update funciona para pasarle sentencias como INSERTE, UPDATE, DELETE 
		//VALUES('') es la sintaxis para agregar los valores, en este caso el nombre de la region 
		//UPDATE : 
		jdbcTemplate.update("UPDATE region SET region = '"+ region.getRegion()+"' WHERE id = "+ id + ";"); 
		RespuestaApi msg = new RespuestaApi();
		msg.setMessage("La region ha sido actualizada");

		return msg; 
		
	}
	
	public RespuestaApi deleteRegion(int id){
		
		//Update funciona para pasarle sentencias como INSERTE, UPDATE, DELETE 
		//VALUES('') es la sintaxis para agregar los valores, en este caso el nombre de la region 
		//UPDATE : 
		jdbcTemplate.update("DELETE FROM region WHERE id = "+ id +";"); 
		RespuestaApi msg = new RespuestaApi();
		msg.setMessage("La region ha sido eliminada");
		return msg; 
		
	}
	
}
