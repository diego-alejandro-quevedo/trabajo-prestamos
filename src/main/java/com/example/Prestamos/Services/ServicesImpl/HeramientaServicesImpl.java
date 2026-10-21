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

        // 1. Convertir DTO de entrada (Request) a Entidad JPA
        Herramienta nuevaHerramienta = new Herramienta();
        nuevaHerramienta.setNombre(herramienta.getNombre());
        nuevaHerramienta.setCategoria(herramienta.getCategoria());
        nuevaHerramienta.setCantidad(herramienta.getCantidad());

        Herramienta herramientaGuardada = heramientaRepository.save(nuevaHerramienta);

        // 3. Convertir la entidad guardada a DTO de respuesta (Response)
        HerramientaResponseDTO response = new HerramientaResponseDTO();
        response.setNombre(herramientaGuardada.getNombre());
        response.setCategoria(herramientaGuardada.getCategoria());
        response.setCantidad(herramientaGuardada.getCantidad());
        // Mapea los demás campos hacia el DTO de respuesta

        return response;
    }

    @Override
    public List<HerramientaResponseDTO> consultaHerramientas() {
        return heramientaRepository.findAll().stream().map(h -> new HerramientaResponseDTO(
                h.getNombre(),
                h.getCategoria(),
                h.getCantidad()
        )).collect(Collectors.toList());
    }
}
