package com.Bodega.GI_Backend.Repository;

import com.Bodega.GI_Backend.Model.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Integer> {
}
