package com.Bodega.GI_Backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Bodega.GI_Backend.Model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
	List<Categoria>findByNombreCategoriaContainingIgnoreCase(String nombreCategoria);
	
}