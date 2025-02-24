package com.Bodega.GI_Backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bodega.GI_Backend.Model.UnidadMedida;

public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Integer> {

	List<UnidadMedida>findByNombreUnidadMedidaContainingIgnoreCase(String nombreUnidadMedida);

	
}