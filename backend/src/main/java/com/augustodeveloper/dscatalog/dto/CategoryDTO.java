package com.augustodeveloper.dscatalog.dto;

import java.io.Serializable;

import com.augustodeveloper.dscatalog.entities.Category;

public class CategoryDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	public CategoryDTO() {}
	
	public CategoryDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CategoryDTO(Category entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	
}
