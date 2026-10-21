package com.example.Prestamos.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HerramientaResponseDTO {
    private Long id; // <--- Se agrega el campo ID al inicio
    private String nombre;
    private String categoria;
    private Integer cantidad;
}