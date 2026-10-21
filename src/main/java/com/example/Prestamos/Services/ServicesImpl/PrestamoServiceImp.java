package com.example.Prestamos.Services.ServicesImpl;

import com.example.Prestamos.DTO.Request.PrestamoRequestDTO;
import com.example.Prestamos.DTO.Response.PrestamoResponseDTO;
import com.example.Prestamos.Entity.Herramienta;
import com.example.Prestamos.Entity.Prestamo;
import com.example.Prestamos.Exceptions.ResourceNotFoundException;
import com.example.Prestamos.Repository.HerramientaRepository;
import com.example.Prestamos.Repository.PrestamoRepository;
import com.example.Prestamos.Services.PrestamoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PrestamoServiceImp implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final HerramientaRepository heramientaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PrestamoResponseDTO> consultaPrestamo() {
        return prestamoRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PrestamoResponseDTO crearPrestamo(PrestamoRequestDTO prestamo) {
        if (prestamo.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }

        Herramienta herramientaBD = heramientaRepository.findById(prestamo.getHerramienta())
                .orElseThrow(() -> new ResourceNotFoundException("La herramienta especificada no existe"));

        if (herramientaBD.getCantidad() < prestamo.getCantidad()) {
            throw new IllegalArgumentException("No hay suficiente stock de la herramienta seleccionada");
        }

        Prestamo nuevoPrestamo = new Prestamo();
        nuevoPrestamo.setResponsable(prestamo.getResponsable());
        nuevoPrestamo.setFechaPrestamo(prestamo.getFechaPrestamo());
        nuevoPrestamo.setCantidad(prestamo.getCantidad());
        nuevoPrestamo.setHerramienta(herramientaBD);

        // Nota: el stock NO se descuenta al prestar (ver comentario del resumen).

        return toDto(prestamoRepository.save(nuevoPrestamo));
    }

    @Override
    @Transactional(readOnly = true)
    public PrestamoResponseDTO prestamoId(Long id) {
        return prestamoRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("El préstamo con ID " + id + " no existe"));
    }

    private PrestamoResponseDTO toDto(Prestamo p) {
        return new PrestamoResponseDTO(
                p.getId(),
                p.getResponsable(),
                p.getFechaPrestamo(),
                p.getCantidad(),
                p.getHerramienta() != null ? p.getHerramienta().getId() : null
        );
    }
}