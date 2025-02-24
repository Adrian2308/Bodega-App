package com.Bodega.GI_Backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Bodega.GI_Backend.Model.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

	List<Proveedor> findByNombreProveedorContainingAllIgnoreCase(String nombreProveedor);
	
}
