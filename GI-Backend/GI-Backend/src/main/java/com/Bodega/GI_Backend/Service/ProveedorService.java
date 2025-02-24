package com.Bodega.GI_Backend.Service;

import java.util.List;

import com.Bodega.GI_Backend.Model.Proveedor;

public interface ProveedorService {
	
	List<Proveedor> obtenertodoslosProveedores();
	Proveedor obtenerProveedorPorId(Integer proveedorId);
	List<Proveedor> buscarProveedorPorNombre(String nombreProveedor);
	Proveedor guardarProveedor(Proveedor proveedor);
	Proveedor actualizarProveedor(int proveedorId, Proveedor proveedorActualizado);
	void eliminarProveedor(Integer proveedorId);

}
