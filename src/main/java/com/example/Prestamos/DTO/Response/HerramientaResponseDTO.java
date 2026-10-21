package com.example.Prestamos.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HerramientaResponseDTO {
    private String nombre;
    private String categoria;
    private Integer cantidad;

}
