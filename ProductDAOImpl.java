package com.spring.core.jdbc.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.core.Product;

//ProductDAOImpl class to implements ProductDAO


public class ProductDAOImpl implements ProductDAO {

private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Product product) {

		//select save operation
				String query = " insert products(id, name, price, unitsInStock , discontinued ) values (?, ?, ?,?,?)";
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				
				Object[] values = new Object[] {
						product.getId(),
						product.getName(),
						product.getPrice(),
						product.getUnitsInStock() ,
						product.isDiscontinued()
					
				};
				
				int out = jdbcTemplate.update(query, values);
				

				if(out != 0)
					System.out.println("Product saved with id = " + product.getId());
				else
					System.out.println("Product save failed with id =" + product.getId());
				
		
	}

	@Override
	public Product getById(int id) {
		String query = "select id, name, price,unitsInStock , discontinued from products where id = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
					
		//using RowMapper anonymous class, we can create a separate RowMapper for reuse
		@SuppressWarnings("deprecation")
		Product prod = jdbcTemplate.queryForObject(query, 
				new Object[]{id}, new RowMapper<Product>(){

			@Override
			public Product mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Product p1 = new Product();
				p1.setId(rs.getInt("id"));
				p1.setName(rs.getString("name"));
				p1.setPrice(rs.getDouble("price"));
				p1.setUnitsInStock(rs.getInt("unitsInStock"));
				p1.setDiscontinued(rs.getBoolean("discontinued"));
				return p1;
			}});
		
		return prod;
	}

	@Override
	public void update(Product product) {
		//update operation
		String query = "update products set name=?, price=?,unitsInStock =?, discontinued=? where id=?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		Object[] args = new Object[] {
				product.getName(),
				product.getPrice(),
				product.getUnitsInStock() ,
				product.isDiscontinued(),
				product.getId()
				
		};
		
		int out = jdbcTemplate.update(query, args);
		
		if(out !=0){
			System.out.println("Product data updated with id="+product.getId());
		}else System.out.println("No product data is found with id="+product.getId());
		
	}
		

	@Override
	public void deleteById(int id) {
		//delete operation
		String query = "delete from products where id=?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		int out = jdbcTemplate.update(query, id);
		
		if(out !=0){
			System.out.println("Product data deleted with id="+id);
		}
		else 
			System.out.println("No product data is found with id="+id);
		
	}

	@Override
	public List<Product> getAll() {
		//selecting multiple products
		String query = "select * from products";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Product> prodList = new ArrayList<Product>();

		List<Map<String,Object>> prodRows = jdbcTemplate.queryForList(query);
		
		for(Map<String,Object> prodRow : prodRows){
			Product prod = new Product();
			
			prod.setId(Integer.parseInt(String.valueOf(prodRow.get("id"))));
			prod.setName(String.valueOf(prodRow.get("name")));
			prod.setPrice(Double.parseDouble(String.valueOf(prodRow.get("price"))));
			prod.setUnitsInStock(Integer.parseInt(String.valueOf(prodRow.get("unitsInStock"))));
			prod.setDiscontinued(Boolean.parseBoolean(String.valueOf(prodRow.get("discontinued"))));
			prodList.add(prod);
		}
		return prodList;
	}

	@Override
	public Product getByName(String name) {
		//display by name
		String query = "select * from products where name = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
					
		//using RowMapper anonymous class, we can create a separate RowMapper for reuse
		@SuppressWarnings("deprecation")
		Product prod = jdbcTemplate.queryForObject(query, 
				new Object[]{name}, new RowMapper<Product>(){

			@Override
			public Product mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Product p1 = new Product();
				p1.setId(rs.getInt("id"));
				p1.setName(rs.getString("name"));
				p1.setPrice(rs.getDouble("price"));
				p1.setUnitsInStock(rs.getInt("unitsInStock"));
				p1.setDiscontinued(rs.getBoolean("discontinued"));
				return p1;
			}});
		
		return prod;
	}
	@Override
	public List<Product> getByNames(String substring) {
		//selecting multiple products
				String query = "select * from products where name like 'Toy%'";
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				
				List<Product> prodList = new ArrayList<Product>();

				List<Map<String,Object>> prodRows = jdbcTemplate.queryForList(query);
				
				for(Map<String,Object> prodRow : prodRows){
					Product prod = new Product();
					
					prod.setId(Integer.parseInt(String.valueOf(prodRow.get("id"))));
					prod.setName(String.valueOf(prodRow.get("name")));
					prod.setPrice(Double.parseDouble(String.valueOf(prodRow.get("price"))));
					prod.setUnitsInStock(Integer.parseInt(String.valueOf(prodRow.get("unitsInStock"))));
					prod.setDiscontinued(Boolean.parseBoolean(String.valueOf(prodRow.get("discontinued"))));
					prodList.add(prod);
		}
				return prodList;
		}
	@Override
	public List<Product> getByBetweenPrice(double iPrice, double oPrice) {
		//price operation
		String query = "select * from products where price between 300.00 and 1111.1";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Product> prodList = new ArrayList<Product>();

		List<Map<String,Object>> prodRows = jdbcTemplate.queryForList(query);
		
		for(Map<String,Object> prodRow : prodRows){
			Product prod = new Product();
			
			prod.setId(Integer.parseInt(String.valueOf(prodRow.get("id"))));
			prod.setName(String.valueOf(prodRow.get("name")));
			prod.setPrice(Double.parseDouble(String.valueOf(prodRow.get("price"))));
			prod.setUnitsInStock(Integer.parseInt(String.valueOf(prodRow.get("unitsInStock"))));
			prod.setDiscontinued(Boolean.parseBoolean(String.valueOf(prodRow.get("discontinued"))));
			prodList.add(prod);
		}
		return prodList;
	}
	@Override
	public List<Product> getDiscontinuedProducts() {
		//Discontinued operation
				String query = "select * from products where discontinued = true";
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				
				List<Product> prodList = new ArrayList<Product>();

				List<Map<String,Object>> prodRows = jdbcTemplate.queryForList(query);
				
				for(Map<String,Object> prodRow : prodRows){
					Product prod = new Product();
					
					prod.setId(Integer.parseInt(String.valueOf(prodRow.get("id"))));
					prod.setName(String.valueOf(prodRow.get("name")));
					prod.setPrice(Double.parseDouble(String.valueOf(prodRow.get("price"))));
					prod.setUnitsInStock(Integer.parseInt(String.valueOf(prodRow.get("unitsInStock"))));
					prod.setDiscontinued(Boolean.parseBoolean(String.valueOf(prodRow.get("discontinued"))));
					prodList.add(prod);
				}
				return prodList;
			}

	@Override
	public void save1(Product p1) {
		// TODO Auto-generated method stub
		
	}


}
