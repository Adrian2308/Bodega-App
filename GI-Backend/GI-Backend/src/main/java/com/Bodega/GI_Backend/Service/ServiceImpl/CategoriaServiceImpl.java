package com.Bodega.GI_Backend.Service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bodega.GI_Backend.Model.Categoria;
import com.Bodega.GI_Backend.Repository.CategoriaRepository;
import com.Bodega.GI_Backend.Service.CategoriaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public List<Categoria> obtenerTodasLasCategorias() {
		return categoriaRepository.findAll();
	}

	@Override
	public Categoria obtenerCategoriaPorId(Integer categoriaId) {
		return categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada con Id: " + categoriaId));
	}

	@Override
	public List<Categoria> buscarCategoriaPorNombre(String nombreCategoria) {
		return categoriaRepository.findByNombreCategoriaContainingIgnoreCase(nombreCategoria);
	}

	@Override
	public Categoria guardarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Override
	public Categoria actualizarCategoria(int categoriaId, Categoria categoriaActualizada) {
		Categoria categoria = categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada con ID: " + categoriaId));
		categoria.setNombreCategoria(categoriaActualizada.getNombreCategoria());
		
		return categoriaRepository.save(categoria);
	}

	@Override
	public void eliminarCategoria(Integer categoriaId) {
		if (!categoriaRepository.existsById(categoriaId)) {
			throw new EntityNotFoundException("Producto no encontrado con ID " + categoriaId);
		}
		categoriaRepository.deleteById(categoriaId);
	}

}
