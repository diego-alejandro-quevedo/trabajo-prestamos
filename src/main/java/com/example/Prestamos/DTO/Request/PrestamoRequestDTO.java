package com.example.Prestamos.DTO.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrestamoRequestDTO {

    @NotBlank(message = "El responsable es obligatorio")
    private String responsable;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La fecha del préstamo es obligatoria")
    private LocalDate fechaPrestamo;

    @Min(value = 1, message = "La cantidad debe ser mayor que cero")
    private int cantidad;

    @NotNull(message = "La herramienta es obligatoria")
    private Long herramienta;
}