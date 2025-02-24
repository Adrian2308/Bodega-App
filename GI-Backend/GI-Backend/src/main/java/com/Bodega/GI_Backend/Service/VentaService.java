package com.Bodega.GI_Backend.Service;

import com.Bodega.GI_Backend.Model.Venta;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VentaService {
    List<Venta> obtenerTodasLasVentas();
    Optional<Venta> obtenerVentaPorId(int ventaId);
    List<Venta> obtenerVentasPorFecha(LocalDate fecha);
    List<Venta> obtenerVentasPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin);
    Venta crearVenta(Venta venta);
    boolean anularVenta(int ventaId);
}
