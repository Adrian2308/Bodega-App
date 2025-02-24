package com.Bodega.GI_Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bodega.GI_Backend.Model.Categoria;
import com.Bodega.GI_Backend.Service.CategoriaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	// Obtener todas las categorías
    @GetMapping
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaService.obtenerTodasLasCategorias();
    }

    // Obtener una categoría por ID
    @GetMapping("/buscarId/{id}")
    public Categoria obtenerCategoriaPorId(@PathVariable Integer id) {
        return categoriaService.obtenerCategoriaPorId(id);
    }

    // Buscar categorías por nombre
    @GetMapping("/buscar")
    public List<Categoria> buscarPorNombre(@RequestParam String nombre) {
        return categoriaService.buscarCategoriaPorNombre(nombre);
    }

    // Crear una nueva categoría
    @PostMapping("/ingresar")
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriaService.guardarCategoria(categoria);
    }

    // Actualizar una categoría existente
    @PutMapping("/actualizar/{id}")
    public Categoria actualizarCategoria(@PathVariable int id, @RequestBody Categoria categoria) {
        return categoriaService.actualizarCategoria(id, categoria);
    }

    // Eliminar una categoría
    @DeleteMapping("/eliminar/{id}")
    public void eliminarCategoria(@PathVariable Integer id) {
        categoriaService.eliminarCategoria(id);
    }
}
