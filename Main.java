package com.spring.core.jdbc.main;
import java.util.List;
import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.core.Category;
import com.spring.core.Product;
import com.spring.core.jdbc.dao.CategoryDAO;
import com.spring.core.jdbc.dao.ProductDAO;

public class Main {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = null;
		try {
			//xml file
			 context =new ClassPathXmlApplicationContext("spring.xml");
			
			 //CATEGORY OPERATION
			
			 System.out.println("-------***********-------");
			 System.out.println();
			CategoryDAO categoryDAO = context.getBean(CategoryDAO.class);
			
			Category c1 = new Category();
			
			int rand = new Random().nextInt(1000);
			
			c1.setId(rand);
			c1.setName("Toys");
			c1.setDescription("Car toy");
			
			//save category 
			categoryDAO.save(c1);
			
			System.out.println("Category data is saved successfully");
			
			//display category by using id
			Category cat = categoryDAO.getById(rand);
			System.out.println("Category Detail's by using id "+ categoryDAO.getById(rand));
			
			//update category by using name
			c1.setName("Children's Toy");
			categoryDAO.update(c1);
		
			
			//delete the category data by id
			List<Category> cList = categoryDAO.getAll();
			System.out.println("After delete");
			System.out.println("List of category "+cList);
			
			categoryDAO.deleteById(rand);
			
			//Display the list of category
			cList = categoryDAO.getAll();
		    System.out.println("List Of Category"+cList);
			
			//display category by using name	
			Category cat1 = categoryDAO.getByName("Books");
			System.out.println("Category Detail's by using name "+categoryDAO.getByName("Books"));

		    //display the list of category by using names
			cList = categoryDAO.getByNames("To%");
			System.out.println("List Of Categories  by using names "+cList);
		
		    System.out.println("-------***********-------");
		    System.out.println();
		    
		   
		    //PRODUCT OPERATION
			System.out.println("-------***********-------");
			System.out.println();
			ProductDAO productDAO = context.getBean(ProductDAO.class);
			
			Product p1 = new Product();
			
			p1.setId(rand);
			p1.setName("Toy car");
			p1.setPrice(333.30);
			p1.setUnitsInStock(100);
			p1.setDiscontinued(true);
			
			//save product	
			productDAO.save(p1);
			System.out.println("Product data  is saved successfully");
			
			//display product by using id
			Product Prod = productDAO.getById(rand);
			System.out.println("Product Detail's by using id "+((CategoryDAO) productDAO).getById(rand));	
				
			//update product by using price
			p1.setPrice(150.56);
			productDAO.update(p1);
			
			
			//delete the product data by id
			List<Product> pList = productDAO.getAll();
			System.out.println("After delete");
			System.out.println("List of product "+pList);
			
			productDAO.deleteById(rand);
			
			//Display the list of product
			pList = productDAO.getAll();
			
			System.out.println("List of product "+pList);
			

			//display product by using name
			Product prod1 = productDAO.getByName("Smart Watch");
			System.out.println("Product Detail's by using name "+productDAO.getByName("barbie doll"));
			
			 //display the list of product by using names
			pList = productDAO.getByNames("Toy%");
			
			System.out.println("List Of Product's by using names "+pList);
			
			//display the list of product by using price
			pList = productDAO.getByBetweenPrice(300.00,1111.1);
			
			System.out.println("List Of Product's by using price "+pList);
			 
			//display the list of product by using discontinued
			pList = productDAO.getDiscontinuedProducts();
			
			System.out.println("List Of Product's by using discontinued "+pList);


			System.out.println("-------***********-------");
			
			  
 }
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(context != null)
				context.close();
		}

	}
		

	
	}