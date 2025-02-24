package com.Bodega.GI_Backend.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Bodega.GI_Backend.Model.Venta;
import com.Bodega.GI_Backend.Service.VentaService;

@RestController
@RequestMapping("/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private VentaService ventaService;

 // Obtener todas las Ventas
    @GetMapping
    public ResponseEntity<List<Venta>> obtenerTodasLasVentas() {
        List<Venta> ventas = ventaService.obtenerTodasLasVentas();
        return ResponseEntity.ok(ventas);
    }

    // Obtener una Venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVentaPorId(@PathVariable int id) {
        Optional<Venta> venta = ventaService.obtenerVentaPorId(id);
        return venta.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener Ventas por una fecha específica
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Venta>> obtenerVentasPorFecha(@PathVariable String fecha) {
        LocalDate fechaConvertida = LocalDate.parse(fecha);
        List<Venta> ventas = ventaService.obtenerVentasPorFecha(fechaConvertida);
        return ResponseEntity.ok(ventas);
    }

    // Obtener Ventas por un rango de fechas
    @GetMapping("/rango")
    public ResponseEntity<List<Venta>> obtenerVentasPorRangoFechas(
            @RequestParam String inicio,
            @RequestParam String fin) {
        LocalDate fechaInicio = LocalDate.parse(inicio);
        LocalDate fechaFin = LocalDate.parse(fin);
        List<Venta> ventas = ventaService.obtenerVentasPorRangoFechas(fechaInicio, fechaFin);
        return ResponseEntity.ok(ventas);
    }

    // Registrar una nueva Venta
    @PostMapping("/ingresar")
    public ResponseEntity<Venta> crearVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaService.crearVenta(venta);
        return ResponseEntity.ok(nuevaVenta);
    }

    // Anular una Venta (cambiar estado a false)
    @PutMapping("/anular/{id}")
    public ResponseEntity<String> anularVenta(@PathVariable int id) {
        boolean anulado = ventaService.anularVenta(id);
        if (anulado) {
            return ResponseEntity.ok("Venta anulada correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
