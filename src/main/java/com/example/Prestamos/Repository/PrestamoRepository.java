package com.example.Prestamos.Repository;

import com.example.Prestamos.Entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByHerramientaId(Long herramientaId);
}

