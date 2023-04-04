/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.completo.backcompletodos.Controller;


import com.completo.backcompletodos.Entity.Base;
import java.io.Serializable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author salguero
 */
public interface BaseController <E extends Base, ID extends Serializable>{
    public ResponseEntity<?>getAll();
    public ResponseEntity<?>getAll(Pageable pageable);
    public ResponseEntity<?>getOne(@PathVariable ID id);
    public ResponseEntity<?>save(@RequestBody E entity);
    public ResponseEntity<?>update(@PathVariable ID id, @RequestBody E entity);
    public ResponseEntity<?>delete(@PathVariable ID id);
    
}