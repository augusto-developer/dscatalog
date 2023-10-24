package com.augustodeveloper.dscatalog.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.augustodeveloper.dscatalog.entities.Category;
import com.augustodeveloper.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	private CategoryRepository rep;
	
	@Transactional(readOnly = true)
	public List<Category> findAll(){
		return rep.findAll();
	}
	
}
