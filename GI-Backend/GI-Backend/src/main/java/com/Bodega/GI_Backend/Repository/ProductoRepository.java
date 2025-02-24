package com.Bodega.GI_Backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Bodega.GI_Backend.Model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	List<Producto>findByNombreProductoContainingIgnoreCase(String nombreProducto);

}