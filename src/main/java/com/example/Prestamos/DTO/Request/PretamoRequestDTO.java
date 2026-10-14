package com.example.Prestamos.DTO.Request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PretamoRequestDTO {

    @NotBlank(message = "El responsable es obligatorio")
    private String responsable;

    @NotNull(message = "La fecha del préstamo es obligatoria")
    private LocalDate fechaPrestamo;

    @Min(value = 1, message = "La cantidad debe ser mayor que cero")
    private int cantidad;
}
