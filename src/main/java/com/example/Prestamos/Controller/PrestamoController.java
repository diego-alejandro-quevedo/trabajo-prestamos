package com.example.Prestamos.Controller;

import com.example.Prestamos.DTO.Request.PrestamoRequestDTO;
import com.example.Prestamos.DTO.Response.PrestamoResponseDTO;
import com.example.Prestamos.Exceptions.ResourceNotFoundException;
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
        return ResponseEntity.ok(prestamoServices.consultaPrestamo());
    }

    @PostMapping("/crearPrestamo")
    public ResponseEntity<?> crearPrestamo(@Valid @RequestBody PrestamoRequestDTO request) {
        try {
            return ResponseEntity.ok(prestamoServices.crearPrestamo(request));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<PrestamoResponseDTO> prestamoId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(prestamoServices.prestamoId(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}