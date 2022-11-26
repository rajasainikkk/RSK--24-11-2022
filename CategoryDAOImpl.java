package com.spring.core.jdbc.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.core.Category;

public class CategoryDAOImpl implements CategoryDAO {

private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Category category) {
		
		//select save operation
				String query = " insert categories(id, name, description) values (?, ?, ?)";
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				
				Object[] values = new Object[] {
					category.getId(),
					category.getName(),
					category.getDescription()
				};
				
				int out = jdbcTemplate.update(query, values);
				

				if(out != 0)
					System.out.println("Category saved with id = " + category.getId());
				else
					System.out.println("Category save failed with id =" + category.getId());
							
				
	}
	
	@Override
	public Category getById(int id) {
		String query = "select id, name, description from categories where id = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
					
		//using RowMapper anonymous class, we can create a separate RowMapper for reuse
		@SuppressWarnings("deprecation")
		Category cat = jdbcTemplate.queryForObject(query, 
				new Object[]{id}, new RowMapper<Category>(){
			@Override
			public Category mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Category c1 = new Category();
				c1.setId(rs.getInt("id"));
				c1.setName(rs.getString("name"));
				c1.setDescription(rs.getString("description"));
				return c1;
			}});
		
		return cat;
	}
	@Override
	public void update(Category category) {
		//update operation
				String query = "update categories set name=?, description=? where id=?";
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				
				Object[] args = new Object[] {
						
						category.getName(),
						category.getDescription(),
						category.getId()
				};
				
				int out = jdbcTemplate.update(query, args);
				
				if(out !=0){
					System.out.println("Category data updated with id="+category.getId());
				}else System.out.println("No category data is found with id="+category.getId());
				
			}


	@Override
	public void deleteById(int id) {
		//delete operation
		String query = "delete from categories where id=?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		int out = jdbcTemplate.update(query, id);
		
		if(out !=0){
			System.out.println("Category data deleted with id="+id);
		}
		else 
			System.out.println("No category data is found with id="+id);
		
	}

	@Override
	public List<Category> getAll() {
		//selecting multiple Category
				String query = "select * from categories";
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				
				List<Category> catList = new ArrayList<Category>();

				List<Map<String,Object>> catRows = jdbcTemplate.queryForList(query);
				
				for(Map<String,Object> catRow : catRows){
					Category cat = new Category();
					
					cat.setId(Integer.parseInt(String.valueOf(catRow.get("id"))));
					
					cat.setName(String.valueOf(catRow.get("name")));
					cat.setDescription(String.valueOf(catRow.get("description")));
					
					catList.add(cat);
	 }
				return catList;
	}
	@Override
	public Category getByName(String name) {
		//select by name
		String query = "select * from categories where name = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
					
		//using RowMapper anonymous class, we can create a separate RowMapper for reuse
		@SuppressWarnings("deprecation")
		Category cat = jdbcTemplate.queryForObject(query, 
				new Object[]{name}, new RowMapper<Category>(){

			@Override
			public Category mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Category c1 = new Category();
				c1.setId(rs.getInt("id"));
				c1.setName(rs.getString("name"));
				c1.setDescription(rs.getString("description"));
				return c1;
			}});
		
		return cat;
	}
	@Override
	public List<Category> getByNames(String substring) {
		//selecting multiple Category
		String query = "select * from categories where name like 'To%'";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Category> catList = new ArrayList<Category>();

		List<Map<String,Object>> catRows = jdbcTemplate.queryForList(query);
		
		for(Map<String,Object> catRow : catRows){
			Category cat = new Category();
			
			cat.setId(Integer.parseInt(String.valueOf(catRow.get("id"))));
			cat.setName(String.valueOf(catRow.get("name")));
			cat.setDescription(String.valueOf(catRow.get("description")));
			
			catList.add(cat);
      }
		return catList;
}

}
