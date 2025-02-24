package com.Bodega.GI_Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Bodega.GI_Backend.Model.Producto;
import com.Bodega.GI_Backend.Service.ProductoService;


@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Obtener todos los productos
    @GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    // Obtener un producto por ID
    @GetMapping("/buscarId/{id}")
    public Producto obtenerProductoPorId(@PathVariable Integer id) {
        return productoService.obtenerProductoPorId(id);
    }

    // Buscar productos por nombre
    @GetMapping("/buscar")
    public List<Producto> buscarPorNombre(@RequestParam String nombre) {
        return productoService.buscarProductoPorNombre(nombre);
    }

    // Crear un nuevo producto
    @PostMapping("/ingresar")
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    // Actualizar un producto existente
    @PutMapping("/actualizar/{id}")
    public Producto actualizarProducto(@PathVariable int id, @RequestBody Producto producto) {
        return productoService.actualizarProducto(id, producto);
    }

    // Eliminar un producto
    @DeleteMapping("/eliminar/{id}")
    public void eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
    }
}