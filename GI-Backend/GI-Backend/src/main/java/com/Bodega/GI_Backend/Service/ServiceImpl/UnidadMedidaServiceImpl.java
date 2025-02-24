package com.Bodega.GI_Backend.Service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bodega.GI_Backend.Model.UnidadMedida;
import com.Bodega.GI_Backend.Repository.UnidadMedidaRepository;
import com.Bodega.GI_Backend.Service.UnidadMedidaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UnidadMedidaServiceImpl implements UnidadMedidaService{

	@Autowired
	private UnidadMedidaRepository unidadMedidaRepository;
	
	@Override
	public List<UnidadMedida> obtenerTodasLasUnidadesMedida() {
		return unidadMedidaRepository.findAll();
	}

	@Override
	public UnidadMedida obtenerUnidadMedidaPorId(Integer unidadMedidaId) {
		return unidadMedidaRepository.findById(unidadMedidaId)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + unidadMedidaId));
	}

	@Override
	public List<UnidadMedida> buscarUnidadMedidaPorNombre(String nombreUnidadMedida) {
	        return unidadMedidaRepository.findByNombreUnidadMedidaContainingIgnoreCase(nombreUnidadMedida);
	}

	@Override
	public UnidadMedida guardarUnidadMedida(UnidadMedida unidadMedida) {
		return unidadMedidaRepository.save(unidadMedida);
	}

	@Override
	public UnidadMedida actualizarUnidadMedida(int unidadMedidaId, UnidadMedida unidadMedidaActualizada) {
		UnidadMedida unidadMedida = unidadMedidaRepository.findById(unidadMedidaId)
				.orElseThrow(() -> new EntityNotFoundException("Unidad de Medida no encontrada con ID: " + unidadMedidaId));
		
		unidadMedida.setNombreUnidadMedida(unidadMedidaActualizada.getNombreUnidadMedida());
		
		return unidadMedidaRepository.save(unidadMedida);
	}

	@Override
	public void eliminarUnidad(Integer unidadMedidaId) {
		if (!unidadMedidaRepository.existsById(unidadMedidaId)) {
			throw new EntityNotFoundException("Unidad de Medida no encontrada con ID: " + unidadMedidaId);
		}
		unidadMedidaRepository.deleteById(unidadMedidaId);
	}


}
