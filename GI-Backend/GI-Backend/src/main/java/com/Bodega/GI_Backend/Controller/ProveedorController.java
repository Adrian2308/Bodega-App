package com.Bodega.GI_Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Bodega.GI_Backend.Model.Proveedor;
import com.Bodega.GI_Backend.Service.ProveedorService;

@RestController
@RequestMapping("/proveedores")
@CrossOrigin(origins = "*") 
public class ProveedorController {

	@Autowired
    private ProveedorService proveedorService;

    // Obtener todos los proveedores
    @GetMapping
    public ResponseEntity<List<Proveedor>> obtenerTodos() {
        return ResponseEntity.ok(proveedorService.obtenertodoslosProveedores());
    }

    // Obtener un proveedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(proveedorService.obtenerProveedorPorId(id));
    }

    // Buscar proveedor por nombre (parcial o completo)
    @GetMapping("/buscar")
    public ResponseEntity<List<Proveedor>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(proveedorService.buscarProveedorPorNombre(nombre));
    }

    // Guardar un nuevo proveedor
    @PostMapping("/ingresar")
    public ResponseEntity<Proveedor> guardar(@RequestBody Proveedor proveedor) {
        return new ResponseEntity<>(proveedorService.guardarProveedor(proveedor), HttpStatus.CREATED);
    }

    // Actualizar un proveedor
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Proveedor> actualizar(@PathVariable int id, @RequestBody Proveedor proveedorActualizado) {
        return ResponseEntity.ok(proveedorService.actualizarProveedor(id, proveedorActualizado));
    }

    // Eliminar un proveedor
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}
