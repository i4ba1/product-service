package com.product.productservice;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

public class Product implements Persistable<Integer> {
	@Id
	private Integer id;
	private String description;
	private Double price;
	
	@Transient
	private boolean newProduct;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	@Transient
	public boolean isNew() {
		return this.newProduct || id == null;
	}
	
	public Product setAsNew() {
		this.newProduct = true;
		return this;
	}
}
