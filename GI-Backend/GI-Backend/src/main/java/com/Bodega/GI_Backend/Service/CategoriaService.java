package com.Bodega.GI_Backend.Service;

import java.util.List;

import com.Bodega.GI_Backend.Model.Categoria;

public interface CategoriaService {
	
	List<Categoria> obtenerTodasLasCategorias();
	Categoria obtenerCategoriaPorId(Integer categoriaId);
	List<Categoria> buscarCategoriaPorNombre(String nombreCategoria);
	Categoria guardarCategoria(Categoria categoria);
	Categoria actualizarCategoria(int categoriaId, Categoria categoriaActualizada); 
	void eliminarCategoria(Integer categoriaId);
	
}
