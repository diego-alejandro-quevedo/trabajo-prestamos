package com.example.Prestamos.Controller;


import com.example.Prestamos.DTO.Request.HerramientaRequestDTO;
import com.example.Prestamos.DTO.Response.HerramientaResponseDTO;
import com.example.Prestamos.Entity.Herramienta;
import com.example.Prestamos.Services.HeramientaServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

@RequestMapping("api/Herramienta")
public class HerramientasController {
    private final HeramientaServices heramientaServices;

    @PostMapping("/crearHerranienta")
    public ResponseEntity<HerramientaResponseDTO> registrarcrearHerramienta(@RequestBody HerramientaRequestDTO request) {
        try {
            return ResponseEntity.ok(heramientaServices.crearHerramienta(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
