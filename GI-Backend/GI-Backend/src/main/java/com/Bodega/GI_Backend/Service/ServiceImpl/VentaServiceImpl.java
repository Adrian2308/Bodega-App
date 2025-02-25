package com.Bodega.GI_Backend.Service.ServiceImpl;

import com.Bodega.GI_Backend.Model.Venta;
import com.Bodega.GI_Backend.Model.DetalleVenta;
import com.Bodega.GI_Backend.Model.Producto;
import com.Bodega.GI_Backend.Repository.VentaRepository;
import com.Bodega.GI_Backend.Repository.ProductoRepository;
import com.Bodega.GI_Backend.Service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Venta> obtenerTodasLasVentas() {
        LocalDate today = LocalDate.now();
        return ventaRepository.findAll().stream()
                .filter(venta -> venta.isEstado() && venta.getFechaVenta().toLocalDate().isEqual(today))
                .collect(Collectors.toList());
    }

    @Override
    public List<Venta> obtenerVentasPorFecha(LocalDate fecha) {
        return ventaRepository.findAll().stream()
                .filter(venta -> venta.isEstado() && venta.getFechaVenta().toLocalDate().isEqual(fecha))
                .collect(Collectors.toList());
    }

    @Override
    public List<Venta> obtenerVentasPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return ventaRepository.findAll().stream()
                .filter(venta -> venta.isEstado())
                .filter(venta -> {
                    LocalDate fechaVenta = venta.getFechaVenta().toLocalDate();
                    return !fechaVenta.isBefore(fechaInicio) && !fechaVenta.isAfter(fechaFin);
                })
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Venta> obtenerVentasAnuladasPorFecha(LocalDate fecha) {
        return ventaRepository.findAll().stream()
                .filter(venta -> !venta.isEstado() && venta.getFechaVenta().toLocalDate().isEqual(fecha))
                .collect(Collectors.toList());
    }

    @Override
    public List<Venta> obtenerVentasAnuladasPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return ventaRepository.findAll().stream()
                .filter(venta -> !venta.isEstado()) // Solo ventas anuladas
                .filter(venta -> {
                    LocalDate fechaVenta = venta.getFechaVenta().toLocalDate();
                    return !fechaVenta.isBefore(fechaInicio) && !fechaVenta.isAfter(fechaFin);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Venta> obtenerVentasAnuladas() {
        return ventaRepository.findAll().stream()
                .filter(venta -> !venta.isEstado()) // Solo ventas anuladas
                .collect(Collectors.toList());
    }


    @Override
    public Optional<Venta> obtenerVentaPorId(int ventaId) {
        return ventaRepository.findById(ventaId);
    }


    @Override
    public Venta crearVenta(Venta venta) {

        // Asignar la fecha actual si no está definida
        if (venta.getFechaVenta() == null) {
            venta.setFechaVenta(LocalDateTime.now());
        }

        double totalVenta = 0.0;

        // Procesar los detalles de la venta
        if (venta.getDetallesVenta() != null) {
            for (DetalleVenta detalle : venta.getDetallesVenta()) {
                // Obtener el producto desde la base de datos
                Optional<Producto> productoOpt = productoRepository.findById(detalle.getProducto().getProductoId());
                if (productoOpt.isEmpty()) {
                    throw new RuntimeException("Producto no encontrado.");
                }
                Producto producto = productoOpt.get();

                // Asignar datos correctos al detalle
                detalle.setProducto(producto);
                detalle.setPrecioVenta(producto.getPrecioVenta());
                detalle.setSubtotal(detalle.getSubtotal());
                detalle.setVenta(venta);

                // Acumular total de la venta
                totalVenta += detalle.getSubtotal();

                // **Actualizar stock del producto**
                producto.setStock(producto.getStock() + detalle.getCantidad());
                productoRepository.save(producto); // Guardar el producto con el nuevo stock
            }
        }

        // Asignar total de la venta
        venta.setTotalVenta(totalVenta);

        // Guardar la venta
        return ventaRepository.save(venta);
    }

    @Override
    public boolean anularVenta(int ventaId) {
        return ventaRepository.findById(ventaId).map(venta -> {
            if (!venta.isEstado()) {
                throw new RuntimeException("La venta ya está anulada.");
            }

            // Sumar del stock los productos de la venta
            for (DetalleVenta detalle : venta.getDetallesVenta()) {
                Producto producto = detalle.getProducto();
                producto.setStock(producto.getStock() + detalle.getCantidad());
                
                // Asegurar que el stock no sea negativo
                if (producto.getStock() < 0) {
                    producto.setStock(0);
                }

                productoRepository.save(producto);
            }

            // Anular la venta
            venta.setEstado(false);
            ventaRepository.save(venta);
            return true;
        }).orElse(false);
    }
}
