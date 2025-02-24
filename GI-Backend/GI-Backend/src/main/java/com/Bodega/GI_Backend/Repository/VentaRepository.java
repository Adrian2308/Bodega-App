package com.Bodega.GI_Backend.Repository;

import com.Bodega.GI_Backend.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    List<Venta> findByEstadoTrue();

    List<Venta> findByFechaVentaBetweenAndEstadoTrue(LocalDateTime inicio, LocalDateTime fin);

    List<Venta> findByFechaVentaAndEstadoTrue(LocalDateTime fecha);
}
