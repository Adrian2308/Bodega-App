package com.Bodega.GI_Backend.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ventaId;

    private LocalDateTime fechaVenta;

    private boolean estado = true;

    private double totalVenta; 

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetalleVenta> detallesVenta;

    @PrePersist
    @PreUpdate
    protected void calcularTotalVenta() {
        this.fechaVenta = (this.fechaVenta == null) ? LocalDateTime.now() : this.fechaVenta;
        this.totalVenta = (detallesVenta != null && !detallesVenta.isEmpty()) 
                ? detallesVenta.stream().mapToDouble(DetalleVenta::getSubtotal).sum()
                : 0.0;
    }
}
