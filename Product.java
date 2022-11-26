package com.spring.core;
public class Product {
	private int id;
	private String name;
	private double price;
	private int unitsInStock;
	private boolean discontinued;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int id, String name, double price, int unitsInStock, boolean discontinued) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.unitsInStock = unitsInStock;
		this.discontinued = discontinued;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public boolean isDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", unitsInStock=" + unitsInStock
				+ ", discontinued=" + discontinued + "]";
	}
	
	}