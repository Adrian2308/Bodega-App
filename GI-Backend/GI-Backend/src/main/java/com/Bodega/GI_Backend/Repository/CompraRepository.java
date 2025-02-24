package com.Bodega.GI_Backend.Repository;

import com.Bodega.GI_Backend.Model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {
    List<Compra> findByEstadoTrue();

    List<Compra> findByFechaCompraBetweenAndEstadoTrue(LocalDateTime inicio, LocalDateTime fin);

    List<Compra> findByFechaCompraAndEstadoTrue(LocalDateTime fecha);
}
