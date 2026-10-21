package com.example.Prestamos.Services.ServicesImpl;

import com.example.Prestamos.DTO.Request.HerramientaRequestDTO;
import com.example.Prestamos.DTO.Response.HerramientaResponseDTO;
import com.example.Prestamos.Entity.Herramienta;
import com.example.Prestamos.Repository.HerramientaRepository;
import com.example.Prestamos.Services.HerramientaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HerramientaServicesImpl implements HerramientaServices {

    @Autowired
    private HerramientaRepository herramientaRepository;

    @Override
    public HerramientaResponseDTO crearHerramienta(HerramientaRequestDTO herramientaRequest) {
        // 1. Convertir el DTO de entrada en una Entidad JPA
        Herramienta nuevaHerramienta = new Herramienta();
        nuevaHerramienta.setNombre(herramientaRequest.getNombre());
        nuevaHerramienta.setCategoria(herramientaRequest.getCategoria());
        nuevaHerramienta.setCantidad(herramientaRequest.getCantidad());

        // 2. Guardar la Entidad en la base de datos
        Herramienta herramientaGuardada = herramientaRepository.save(nuevaHerramienta);

        // 3. Construir y retornar la respuesta DTO con el ID generado
        HerramientaResponseDTO response = new HerramientaResponseDTO();
        response.setId(herramientaGuardada.getId());
        response.setNombre(herramientaGuardada.getNombre());
        response.setCategoria(herramientaGuardada.getCategoria());
        response.setCantidad(herramientaGuardada.getCantidad());

        return response;
    }

    @Override
    public List<HerramientaResponseDTO> consultaHerramientas() {
        List<Herramienta> herramientas = herramientaRepository.findAll();

        return herramientas.stream()
                .map(h -> new HerramientaResponseDTO(
                        h.getId(),        // <--- 1. Se pasa el ID de la entidad al DTO
                        h.getNombre(),
                        h.getCategoria(),
                        h.getCantidad()
                ))
                .collect(Collectors.toList());
    }

}