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

import com.Bodega.GI_Backend.Model.UnidadMedida;
import com.Bodega.GI_Backend.Service.UnidadMedidaService;

@RestController
@RequestMapping("/unidades-medida")
@CrossOrigin(origins = "*")
public class UnidadMedidaController {

    @Autowired
    private UnidadMedidaService unidadMedidaService;

    // Obtener todas las unidades de medida
    @GetMapping
    public List<UnidadMedida> obtenerTodasLasUnidadesMedida() {
        return unidadMedidaService.obtenerTodasLasUnidadesMedida();
    }

    // Obtener una unidad de medida por ID
    @GetMapping("/buscarId/{id}")
    public UnidadMedida obtenerUnidadMedidaPorId(@PathVariable Integer id) {
        return unidadMedidaService.obtenerUnidadMedidaPorId(id);
    }

    // Buscar unidades de medida por nombre
    @GetMapping("/buscar")
    public List<UnidadMedida> buscarPorNombre(@RequestParam String nombre) {
        return unidadMedidaService.buscarUnidadMedidaPorNombre(nombre);
    }

    // Crear una nueva unidad de medida
    @PostMapping("/ingresar")
    public UnidadMedida crearUnidadMedida(@RequestBody UnidadMedida unidadMedida) {
        return unidadMedidaService.guardarUnidadMedida(unidadMedida);
    }

    // Actualizar una unidad de medida existente
    @PutMapping("/actualizar/{id}")
    public UnidadMedida actualizarUnidadMedida(@PathVariable int id, @RequestBody UnidadMedida unidadMedida) {
        return unidadMedidaService.actualizarUnidadMedida(id, unidadMedida);
    }

    // Eliminar una unidad de medida
    @DeleteMapping("/eliminar/{id}")
    public void eliminarUnidadMedida(@PathVariable Integer id) {
        unidadMedidaService.eliminarUnidad(id);
    }
}