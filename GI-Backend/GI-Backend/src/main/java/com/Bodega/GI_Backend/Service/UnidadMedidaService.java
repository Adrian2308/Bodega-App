package com.Bodega.GI_Backend.Service;

import java.util.List;

import com.Bodega.GI_Backend.Model.UnidadMedida;

public interface UnidadMedidaService {
    List<UnidadMedida> obtenerTodasLasUnidadesMedida();
    UnidadMedida obtenerUnidadMedidaPorId(Integer id);
    List<UnidadMedida> buscarUnidadMedidaPorNombre(String nombreUnidadMedida);
    UnidadMedida guardarUnidadMedida(UnidadMedida unidadMedida);
    UnidadMedida actualizarUnidadMedida(int unidadMedidaId, UnidadMedida unidadMedidaActualizada);
    void eliminarUnidad(Integer id);
}