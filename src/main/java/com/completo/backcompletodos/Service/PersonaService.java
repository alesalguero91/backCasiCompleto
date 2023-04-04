/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.completo.backcompletodos.Service;


import com.completo.backcompletodos.Entity.Persona;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author salguero
 */
public interface PersonaService extends BaseService <Persona, Long> {
    List<Persona>search(String filtro) throws Exception;
    Page<Persona>search(String filtro, Pageable page) throws Exception;
}
