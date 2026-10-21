package com.example.Prestamos.Services;

import com.example.Prestamos.DTO.Request.HerramientaRequestDTO;
import com.example.Prestamos.DTO.Response.HerramientaResponseDTO;

import java.util.List;

public interface HerramientaServices {
    HerramientaResponseDTO crearHerramienta(HerramientaRequestDTO herramienta);
    List<HerramientaResponseDTO> consultaHerramientas();

}
