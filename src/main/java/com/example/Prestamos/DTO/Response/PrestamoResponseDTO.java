package com.example.Prestamos.DTO.Response;

import com.example.Prestamos.Entity.Herramienta;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoResponseDTO {
    private String responsable;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaPrestamo;
    private Integer cantidad;
    private long Herramienta;

    public PrestamoResponseDTO(String responsable, LocalDate fechaPrestamo, int cantidad, Herramienta herramienta) {
    }

    public PrestamoResponseDTO(String responsable, LocalDate fechaPrestamo, int cantidad, @NotBlank(message = "El nombre es obligatorio") String s) {
    }
}
