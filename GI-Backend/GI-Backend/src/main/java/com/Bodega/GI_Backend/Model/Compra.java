package com.Bodega.GI_Backend.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int compraId;

    private LocalDateTime fechaCompra;

    private boolean estado = true;

    private double totalCompra; 

    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetalleCompra> detallesCompra;

    @PrePersist
    @PreUpdate
    protected void calcularTotalCompra() {
        this.fechaCompra = (this.fechaCompra == null) ? LocalDateTime.now() : this.fechaCompra;
        this.totalCompra = (detallesCompra != null && !detallesCompra.isEmpty()) 
                ? detallesCompra.stream().mapToDouble(DetalleCompra::getSubtotal).sum()
                : 0.0;
    }
}
