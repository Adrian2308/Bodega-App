package com.Bodega.GI_Backend.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detalleVentaId;

    private int cantidad;
    
    private double precioVenta;
    
    private double subtotal;
    
    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    @JsonBackReference
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    public double getSubtotal() {
        return cantidad * precioVenta;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = getSubtotal();
    }
}
