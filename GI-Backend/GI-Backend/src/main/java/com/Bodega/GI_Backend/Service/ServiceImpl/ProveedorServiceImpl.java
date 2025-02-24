package com.Bodega.GI_Backend.Service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bodega.GI_Backend.Model.Proveedor;
import com.Bodega.GI_Backend.Repository.ProveedorRepository;
import com.Bodega.GI_Backend.Service.ProveedorService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProveedorServiceImpl implements ProveedorService{

	@Autowired
	private ProveedorRepository proveedorRepository;
	
	@Override
	public List<Proveedor> obtenertodoslosProveedores() {
		return proveedorRepository.findAll();
	}

	@Override
	public Proveedor obtenerProveedorPorId(Integer proveedorId) {
		 return proveedorRepository.findById(proveedorId)
	                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + proveedorId));
	}

	@Override
	public List<Proveedor> buscarProveedorPorNombre(String nombreProveedor) {
		return proveedorRepository.findByNombreProveedorContainingAllIgnoreCase(nombreProveedor);
	}

	@Override
	public Proveedor guardarProveedor(Proveedor proveedor) {
		return proveedorRepository.save(proveedor);
	}

	@Override
	public Proveedor actualizarProveedor(int proveedorId, Proveedor proveedorActualizado) {
		Proveedor proveedor = proveedorRepository.findById(proveedorId)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + proveedorId));

        proveedor.setNombreProveedor(proveedorActualizado.getNombreProveedor());
        proveedor.setNumeroContacto(proveedorActualizado.getNumeroContacto());

        return proveedorRepository.save(proveedor);
	}

	@Override
	public void eliminarProveedor(Integer proveedorId) {
		 if (!proveedorRepository.existsById(proveedorId)) {
	            throw new EntityNotFoundException("Proveedor no encontrado con ID: " + proveedorId);
	        }
	        proveedorRepository.deleteById(proveedorId);
	    }
	

}
