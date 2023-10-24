package com.augustodeveloper.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.augustodeveloper.dscatalog.dto.CategoryDTO;
import com.augustodeveloper.dscatalog.entities.Category;
import com.augustodeveloper.dscatalog.exceptions.EntityNotFoundException;
import com.augustodeveloper.dscatalog.repositories.CategoryRepository;



@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository rep;
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll(){
		List<Category> list = rep.findAll();
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id){
		Optional<Category> obj = rep.findById(id);
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not Found"));
		return new CategoryDTO(entity);
	}
	
}
