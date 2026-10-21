package com.example.Prestamos.Controller;

import com.example.Prestamos.DTO.Request.HerramientaRequestDTO;
import com.example.Prestamos.DTO.Response.HerramientaResponseDTO;
import com.example.Prestamos.Services.HeramientaServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/Herramienta")
public class HerramientasController {
    private final HeramientaServices heramientaServices;

    // La ruta conserva el typo "crearHerranienta" para no romper la app móvil.
    @PostMapping("/crearHerranienta")
    public ResponseEntity<?> registrarcrearHerramienta(@Valid @RequestBody HerramientaRequestDTO request) {
        try {
            return ResponseEntity.ok(heramientaServices.crearHerramienta(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public List<HerramientaResponseDTO> consultaHerramientas() {
        return heramientaServices.consultaHerramientas();
    }
}