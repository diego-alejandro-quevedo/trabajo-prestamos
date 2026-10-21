package com.example.Prestamos.Services.ServicesImpl;

import com.example.Prestamos.DTO.Request.HerramientaRequestDTO;
import com.example.Prestamos.DTO.Response.HerramientaResponseDTO;
import com.example.Prestamos.Entity.Herramienta;
import com.example.Prestamos.Exceptions.ResourceNotFoundException;
import com.example.Prestamos.Repository.HeramientaRepository;
import com.example.Prestamos.Services.HeramientaServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HeramientaServicesImpl implements HeramientaServices {

    private final HeramientaRepository heramientaRepository;

    @Override
    public HerramientaResponseDTO crearHerramienta(HerramientaRequestDTO herramienta) {
        if (herramienta.getCantidad() < 0) {
            throw new ResourceNotFoundException("La cantidad no puede ser negativa");
        }

        Herramienta nuevaHerramienta = new Herramienta();
        nuevaHerramienta.setNombre(herramienta.getNombre());
        nuevaHerramienta.setCategoria(herramienta.getCategoria());
        nuevaHerramienta.setCantidad(herramienta.getCantidad());

        return toDto(heramientaRepository.save(nuevaHerramienta));
    }

    @Override
    public List<HerramientaResponseDTO> consultaHerramientas() {
        return heramientaRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private HerramientaResponseDTO toDto(Herramienta h) {
        return new HerramientaResponseDTO(h.getId(), h.getNombre(), h.getCategoria(), h.getCantidad());
    }
}