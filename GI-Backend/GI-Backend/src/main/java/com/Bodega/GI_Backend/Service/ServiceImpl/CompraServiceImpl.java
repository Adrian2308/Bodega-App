package com.Bodega.GI_Backend.Service.ServiceImpl;

import com.Bodega.GI_Backend.Model.Compra;
import com.Bodega.GI_Backend.Model.DetalleCompra;
import com.Bodega.GI_Backend.Model.Producto;
import com.Bodega.GI_Backend.Model.Proveedor;
import com.Bodega.GI_Backend.Repository.CompraRepository;
import com.Bodega.GI_Backend.Repository.ProductoRepository;
import com.Bodega.GI_Backend.Repository.ProveedorRepository;
import com.Bodega.GI_Backend.Service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Compra> obtenerTodasLasCompras() {
        LocalDate today = LocalDate.now(); // Fecha actual
        return compraRepository.findAll().stream()
                .filter(compra -> compra.isEstado() && compra.getFechaCompra().toLocalDate().isEqual(today))
                .collect(Collectors.toList());
    }

    @Override
    public List<Compra> obtenerComprasAnuladas() {
        return compraRepository.findAll().stream()
                .filter(compra -> !compra.isEstado()) // Solo compras anuladas
                .collect(Collectors.toList());
    }


    @Override
    public Optional<Compra> obtenerCompraPorId(int compraId) {
        return compraRepository.findById(compraId);
    }

    @Override
    public List<Compra> obtenerComprasPorFecha(LocalDate fecha) {
        return compraRepository.findAll().stream()
                .filter(compra -> compra.getFechaCompra().toLocalDate().isEqual(fecha))
                .collect(Collectors.toList());
    }

    @Override
    public List<Compra> obtenerComprasPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return compraRepository.findAll().stream()
                .filter(compra -> {
                    LocalDate fechaCompra = compra.getFechaCompra().toLocalDate();
                    return !fechaCompra.isBefore(fechaInicio) && !fechaCompra.isAfter(fechaFin);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Compra crearCompra(Compra compra) {
        // Obtener el proveedor desde la base de datos
        Optional<Proveedor> proveedorOpt = proveedorRepository.findById(compra.getProveedor().getProveedorId());
        if (proveedorOpt.isEmpty()) {
            throw new RuntimeException("Proveedor no encontrado.");
        }
        compra.setProveedor(proveedorOpt.get());

        // Asignar la fecha actual si no está definida
        if (compra.getFechaCompra() == null) {
            compra.setFechaCompra(LocalDateTime.now());
        }

        double totalCompra = 0.0;

        // Procesar los detalles de la compra
        if (compra.getDetallesCompra() != null) {
            for (DetalleCompra detalle : compra.getDetallesCompra()) {
                // Obtener el producto desde la base de datos
                Optional<Producto> productoOpt = productoRepository.findById(detalle.getProducto().getProductoId());
                if (productoOpt.isEmpty()) {
                    throw new RuntimeException("Producto no encontrado.");
                }
                Producto producto = productoOpt.get();

                // Asignar datos correctos al detalle
                detalle.setProducto(producto);
                detalle.setPrecioCompra(producto.getPrecioCompra());
                detalle.setSubtotal(detalle.getSubtotal());
                detalle.setCompra(compra);

                // Acumular total de la compra
                totalCompra += detalle.getSubtotal();

                // **Actualizar stock del producto**
                producto.setStock(producto.getStock() + detalle.getCantidad());
                productoRepository.save(producto); // Guardar el producto con el nuevo stock
            }
        }

        // Asignar total de la compra
        compra.setTotalCompra(totalCompra);

        // Guardar la compra
        return compraRepository.save(compra);
    }

    @Override
    public boolean anularCompra(int compraId) {
        return compraRepository.findById(compraId).map(compra -> {
            if (!compra.isEstado()) {
                throw new RuntimeException("La compra ya está anulada.");
            }

            // Restar del stock los productos de la compra
            for (DetalleCompra detalle : compra.getDetallesCompra()) {
                Producto producto = detalle.getProducto();
                producto.setStock(producto.getStock() - detalle.getCantidad());
                
                // Asegurar que el stock no sea negativo
                if (producto.getStock() < 0) {
                    producto.setStock(0);
                }

                productoRepository.save(producto);
            }

            // Anular la compra
            compra.setEstado(false);
            compraRepository.save(compra);
            return true;
        }).orElse(false);
    }
}
