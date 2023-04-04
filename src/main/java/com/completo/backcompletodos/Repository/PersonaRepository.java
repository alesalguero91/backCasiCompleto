/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.completo.backcompletodos.Repository;

import com.completo.backcompletodos.Entity.Persona;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author salguero
 */
@Repository
public interface PersonaRepository extends BaseRepository <Persona, Long>{
    
    List<Persona>findByNombreContainingOrApellidoContaining(String nombre, String apellido);
    Page<Persona>findByNombreContainingOrApellidoContaining(String nombre, String apellido, Pageable pageable);
    
    //boolean existsByDni(int dni);
    @Query(value= "SELECT p FROM Persona p WHERE p.nombre LIKE %:filtro% OR p.apellido LIKE %:filtro%")
    List<Persona>search(@Param("filtro")String filtro);
    
    @Query(value= "SELECT p FROM Persona p WHERE p.nombre LIKE %:filtro% OR p.apellido LIKE %:filtro%")
    Page<Persona>search(@Param("filtro")String filtro, Pageable pageable);
    
    @Query(
            value="SELECT persona FROM persona WHERE persona.nombre LIKE %:filtro% OR persona.apellido LIKE %:filtro%",
            nativeQuery= true
    )
    List<Persona>searchNativo(@Param("filtro")String filtro);
    
    @Query(
            value="SELECT persona FROM persona WHERE persona.nombre LIKE %:filtro% OR persona.apellido LIKE %:filtro%",
            countQuery= "SELECT count(*) FROM persona",
            nativeQuery= true
    )       
    Page<Persona>searchNativo(@Param("filtro")String filtro, Pageable pageable);
    
}
