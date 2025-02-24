package com.Bodega.GI_Backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productoId;

    private String nombreProducto;
    private String descripcionProducto; 
    private double precioCompra;
    private double precioVenta;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    
    @ManyToOne
    @JoinColumn(name = "unidad_medida_id", nullable = false)
    private UnidadMedida unidadMedida;
}
