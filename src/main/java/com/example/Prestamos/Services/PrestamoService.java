package com.example.Prestamos.Services;

import com.example.Prestamos.DTO.Request.PrestamoRequestDTO;
import com.example.Prestamos.DTO.Response.PrestamoResponseDTO;

import java.util.List;

public interface PrestamoService {
    List<PrestamoResponseDTO> consultaPrestamo();
    PrestamoResponseDTO crearPrestamo(PrestamoRequestDTO prestamo);
    PrestamoResponseDTO prestamoId(Long id);

}
