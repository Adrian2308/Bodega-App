package com.Bodega.GI_Backend.Service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bodega.GI_Backend.Model.Producto;
import com.Bodega.GI_Backend.Repository.ProductoRepository;
import com.Bodega.GI_Backend.Service.ProductoService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerProductoPorId(Integer productoId) {
        return productoRepository.findById(productoId)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + productoId));
    }

    @Override
    public List<Producto> buscarProductoPorNombre(String nombreProducto) {
        return productoRepository.findByNombreProductoContainingIgnoreCase(nombreProducto);
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarProducto(int productoId, Producto productoActualizado) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + productoId));

        producto.setNombreProducto(productoActualizado.getNombreProducto());
        producto.setDescripcionProducto(productoActualizado.getDescripcionProducto());
        producto.setPrecioCompra(productoActualizado.getPrecioCompra());
        producto.setPrecioVenta(productoActualizado.getPrecioVenta());
        producto.setStock(productoActualizado.getStock());
        producto.setCategoria(productoActualizado.getCategoria());
        producto.setUnidadMedida(productoActualizado.getUnidadMedida());;

        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Integer productoId) {
        if (!productoRepository.existsById(productoId)) {
            throw new EntityNotFoundException("Producto no encontrado con ID: " + productoId);
        }
        productoRepository.deleteById(productoId);
    }
}
