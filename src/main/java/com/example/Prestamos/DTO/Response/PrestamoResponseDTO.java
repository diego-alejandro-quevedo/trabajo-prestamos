package com.example.Prestamos.DTO.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoResponseDTO {
    private Long id;
    private String responsable;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaPrestamo;
    private Integer cantidad;
    // id de la herramienta prestada
    private Long herramienta;
}