package com.Bodega.GI_Backend.Service;

import com.Bodega.GI_Backend.Model.Compra;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CompraService {
    List<Compra> obtenerTodasLasCompras();
    Optional<Compra> obtenerCompraPorId(int compraId);
    List<Compra> obtenerComprasPorFecha(LocalDate fecha);
    List<Compra> obtenerComprasPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin);
    Compra crearCompra(Compra compra);
    boolean anularCompra(int compraId);
}
