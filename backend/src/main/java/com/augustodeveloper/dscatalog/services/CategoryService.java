package com.augustodeveloper.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.augustodeveloper.dscatalog.dto.CategoryDTO;
import com.augustodeveloper.dscatalog.entities.Category;
import com.augustodeveloper.dscatalog.exceptions.DatabaseException;
import com.augustodeveloper.dscatalog.exceptions.ResourceNotFoundException;
import com.augustodeveloper.dscatalog.repositories.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;



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
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
		return new CategoryDTO(entity);
	}
	
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = rep.save(entity);
		return new CategoryDTO(entity);
	}
	
	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
		Category entity = rep.getReferenceById(id);
		entity.setName(dto.getName());
		entity = rep.save(entity);
		return new CategoryDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!rep.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
	        	rep.deleteById(id);    		
		}
	    	catch (DataIntegrityViolationException e) {
	        	throw new DatabaseException("Falha de integridade referencial");
	   	}
	}
	
}
