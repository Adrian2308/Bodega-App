package com.Bodega.GI_Backend.Service;

import java.util.List;

import com.Bodega.GI_Backend.Model.Producto;

public interface ProductoService {
	
	List<Producto> obtenerTodosLosProductos();
	Producto obtenerProductoPorId(Integer productoId);
	List<Producto> buscarProductoPorNombre(String nombreProducto);
	Producto guardarProducto(Producto producto);
	Producto actualizarProducto(int productoId, Producto productoActualizado);
	void eliminarProducto(Integer productoId);

}
