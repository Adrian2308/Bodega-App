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
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detalleCompraId;

    private int cantidad;
    
    private double precioCompra;
    
    private double subtotal; // 

    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    @JsonBackReference
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    public double getSubtotal() {
        return cantidad * precioCompra;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = getSubtotal(); 
    }
}
