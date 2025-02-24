package com.Bodega.GI_Backend.Controller;

import com.Bodega.GI_Backend.Model.Compra;
import com.Bodega.GI_Backend.Service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
@CrossOrigin(origins = "*")
public class CompraController {

    @Autowired
    private CompraService compraService;

    // Obtener todas las compras
    @GetMapping
    public ResponseEntity<List<Compra>> obtenerTodasLasCompras() {
        List<Compra> compras = compraService.obtenerTodasLasCompras();
        return ResponseEntity.ok(compras);
    }

    // Obtener una compra por ID
    @GetMapping("/{id}")
    public ResponseEntity<Compra> obtenerCompraPorId(@PathVariable int id) {
        Optional<Compra> compra = compraService.obtenerCompraPorId(id);
        return compra.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener compras por una fecha específica
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Compra>> obtenerComprasPorFecha(@PathVariable String fecha) {
        try {
            LocalDate fechaConvertida = LocalDate.parse(fecha);
            List<Compra> compras = compraService.obtenerComprasPorFecha(fechaConvertida);
            return ResponseEntity.ok(compras);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(null); // O algún mensaje de error más detallado
        }
    }

    // Obtener compras por un rango de fechas
    @GetMapping("/rango")
    public ResponseEntity<List<Compra>> obtenerComprasPorRangoFechas(
            @RequestParam String inicio,
            @RequestParam String fin) {
        LocalDate fechaInicio = LocalDate.parse(inicio);
        LocalDate fechaFin = LocalDate.parse(fin);
        List<Compra> compras = compraService.obtenerComprasPorRangoFechas(fechaInicio, fechaFin);
        return ResponseEntity.ok(compras);
    }

    // Registrar una nueva compra
    @PostMapping("/ingresar")
    public ResponseEntity<Compra> crearCompra(@RequestBody Compra compra) {
        Compra nuevaCompra = compraService.crearCompra(compra);
        return ResponseEntity.ok(nuevaCompra);
    }

    // Anular una compra (cambiar estado a false)
    @PutMapping("/anular/{id}")
    public ResponseEntity<String> anularCompra(@PathVariable int id) {
        boolean anulado = compraService.anularCompra(id);
        if (anulado) {
            return ResponseEntity.ok("Compra anulada correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
