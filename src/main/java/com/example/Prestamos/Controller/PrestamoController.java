package com.example.Prestamos.Controller;

import com.example.Prestamos.DTO.Request.HerramientaRequestDTO;
import com.example.Prestamos.DTO.Request.PrestamoRequestDTO;
import com.example.Prestamos.DTO.Response.HerramientaResponseDTO;
import com.example.Prestamos.DTO.Response.PrestamoResponseDTO;
import com.example.Prestamos.Services.PrestamoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("api/Prestamos")
public class PrestamoController {

    private final PrestamoService prestamoServices;

    @GetMapping("/listar")
    public ResponseEntity<List<PrestamoResponseDTO>> consultaPrestamo() {
        List<PrestamoResponseDTO> prestamos = prestamoServices.consultaPrestamo();
        return ResponseEntity.ok(prestamos);
    }

    @PostMapping("/crearPrestamo")
    public ResponseEntity<?> crearPrestamo(@Valid @RequestBody PrestamoRequestDTO request) {
        try {
            PrestamoResponseDTO response = prestamoServices.crearPrestamo(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // Imprime el error real en la consola de IntelliJ
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage()); // Devuelve el mensaje en el JSON de respuesta
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<PrestamoResponseDTO> prestamoId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(prestamoServices.prestamoId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
