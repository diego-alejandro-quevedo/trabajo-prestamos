package com.example.Prestamos.Services.ServicesImpl;

import com.example.Prestamos.DTO.Request.PrestamoRequestDTO;
import com.example.Prestamos.DTO.Response.PrestamoResponseDTO;
import com.example.Prestamos.Entity.Herramienta;
import com.example.Prestamos.Entity.Prestamo;
import com.example.Prestamos.Exceptions.ResourceNotFoundException;
import com.example.Prestamos.Repository.HeramientaRepository;
import com.example.Prestamos.Repository.PrestamoRepository;
import com.example.Prestamos.Services.PrestamoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class PrestamoServiceImp implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final HeramientaRepository heramientaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PrestamoResponseDTO> consultaPrestamo() {
        return prestamoRepository.findAll().stream()
                .map(p -> new PrestamoResponseDTO(
                        p.getResponsable(),
                        p.getFechaPrestamo(),
                        p.getCantidad(),
                        p.getHerramienta() != null ? p.getHerramienta().getId() : null
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PrestamoResponseDTO crearPrestamo(PrestamoRequestDTO prestamo) {
        if (prestamo.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }

        Herramienta herramientaBD = heramientaRepository.findById(prestamo.getHerramienta())
                .orElseThrow(() -> new RuntimeException("La herramienta especificada no existe"));

        // Validación opcional: Verificar stock si tu entidad Herramienta maneja un campo de cantidad disponible
        if (herramientaBD.getCantidad() < prestamo.getCantidad()) {
            throw new IllegalArgumentException("No hay suficiente stock de la herramienta seleccionada");
        }

        Prestamo nuevoPrestamo = new Prestamo();
        nuevoPrestamo.setResponsable(prestamo.getResponsable());
        nuevoPrestamo.setFechaPrestamo(prestamo.getFechaPrestamo());
        nuevoPrestamo.setCantidad(prestamo.getCantidad());
        nuevoPrestamo.setHerramienta(herramientaBD);

        // Opcional: Actualizar el stock de la herramienta al prestarla
        // herramientaBD.setCantidadDisponible(herramientaBD.getCantidadDisponible() - prestamo.getCantidad());
        // heramientaRepository.save(herramientaBD);

        Prestamo prestamoGuardado = prestamoRepository.save(nuevoPrestamo);

        PrestamoResponseDTO response = new PrestamoResponseDTO();
        response.setResponsable(prestamoGuardado.getResponsable());
        response.setFechaPrestamo(prestamoGuardado.getFechaPrestamo());
        response.setCantidad(prestamoGuardado.getCantidad());
        response.setHerramienta(prestamoGuardado.getHerramienta().getId());

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public PrestamoResponseDTO prestamoId(Long id) {
        if (!prestamoRepository.existsById(id)) {
            throw new RuntimeException(
                    "La herramienta con id " + id + " no existe");
        }
        return prestamoRepository.findById(id)
                .map(p -> new PrestamoResponseDTO(
                        p.getResponsable(),
                        p.getFechaPrestamo(),
                        p.getCantidad(),
                        p.getHerramienta() != null ? p.getHerramienta().getId(): null
                ))
                .orElseThrow(() -> new RuntimeException("El préstamo con ID " + id + " no existe"));
    }
}



