package com.dws.customerservice.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dws.customerservice.dto.Customer;
import com.dws.customerservice.dto.Region;
import com.dws.customerservice.dto.RespuestaApi;


@Repository
public class CustomerRepository {
	
	@Autowired 
	JdbcTemplate jdbcTemplate; 
	
	public List<Customer> getCustomers(){
		List<Customer> customers = new ArrayList<>();
		//Como en el endpoint para ver todos los clientes, el json que se regresa de cada cliente contiene otro json con la region asociada,
		//Tenemos que modificarlo usando solo RowMapper 
		customers = jdbcTemplate.query("SELECT * FROM customer c, region r WHERE c.id_region = r.id;", new RowMapper<Customer>() {
			
			//El ResultSet contiene los datos de una fila, y con rowNumber Accedes a uno de esos datos 
			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Customer customer = new Customer(); 
				//Al objeto customer le estamos asignando la lectura del ResultSet en el indice 1, es decir el Id que se recupera de la Base de Datos
				customer.setId(rs.getInt(1));
				customer.setNombre(rs.getString(2));
				customer.setApellidos(rs.getString(3));
				customer.setRfc(rs.getString(4));
				customer.setCorreo(rs.getString(5));
				
				Region region = new Region();
				region.setId(rs.getInt(7));
				region.setRegion(rs.getString(8));
				customer.setRegion(region);
				
				return customer; 
			}
			

		}); 
		
		return customers; 
		
	}
	
	public Customer getCustomer(int id){
		Customer customer = new Customer();
		//Como en el endpoint para ver todos los clientes, el json que se regresa de cada cliente contiene otro json con la region asociada,
		//Tenemos que modificarlo usando solo RowMapper 
		customer = jdbcTemplate.queryForObject("SELECT * FROM customer c, region r WHERE c.id_region = r.id AND c.id = "+id+";", new RowMapper<Customer>() {
			
			//El ResultSet contiene los datos de una fila, y con rowNumber Accedes a uno de esos datos 
			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Customer customer = new Customer(); 
				//Al objeto customer le estamos asignando la lectura del ResultSet en el indice 1, es decir el Id que se recupera de la Base de Datos
				customer.setId(rs.getInt(1));
				customer.setNombre(rs.getString(2));
				customer.setApellidos(rs.getString(3));
				customer.setRfc(rs.getString(4));
				customer.setCorreo(rs.getString(5));
				//QUITAR 
				System.out.println(rs.getString(5));
				System.out.println(rs.getString(6));
				System.out.println(rs.getString(7));
				System.out.println(rs.getString(8));
				
				Region region = new Region();
				region.setId(rs.getInt(7));
				region.setRegion(rs.getString(8));
				customer.setRegion(region);
				
				
				return customer; 
			}
			

		}); 
		
		return customer; 
		
	}
	
	
	public RespuestaApi createCustomer(Customer customer) {
		
		jdbcTemplate.update("INSERT INTO customer (nombre, apellidos, rfc, correo, id_region) VALUES("
				+ "'"+ customer.getNombre()+"',"
				+ "'"+ customer.getApellidos()+"',"
				+ "'"+ customer.getRfc()+"',"
				+ "'"+ customer.getCorreo()+"',"
				+ "'"+ customer.getRegion().getId()+"'"
				+");");
		RespuestaApi msg = new RespuestaApi();
		msg.setMessage("El cliente ha sido Registrado");
		return msg; 
		
	}
	
	
	public RespuestaApi updateCustomer(Customer customer, int id) {
		
		jdbcTemplate.update("UPDATE customer SET "
				+ "nombre ='"+ customer.getNombre()+"', "
				+ "apellidos ='"+ customer.getApellidos()+"', "
				+ "rfc ='"+ customer.getRfc()+"', "
				+ "correo ='"+ customer.getCorreo()+"', "
				+ "id_region ='"+ customer.getRegion().getId()+"' "
				+" WHERE id = " + id + ";");
		
		RespuestaApi msg = new RespuestaApi();
		msg.setMessage("El cliente ha sido actualizado");
		return msg; 
		
	}
	
	public RespuestaApi deleteCustomer(int id) {
		
		jdbcTemplate.update("DELETE FROM customer WHERE id = "+ id + ";");
		RespuestaApi msg = new RespuestaApi();
		msg.setMessage("El cliente ha sido eliminado");
		return msg; 
		
	}

}
