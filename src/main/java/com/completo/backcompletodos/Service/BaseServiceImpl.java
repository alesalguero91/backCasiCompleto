/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.completo.backcompletodos.Service;


import com.completo.backcompletodos.Entity.Base;
import com.completo.backcompletodos.Repository.BaseRepository;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author salguero
 */
public abstract class BaseServiceImpl <E extends Base, ID extends Serializable> implements BaseService<E, ID>{
    
    protected BaseRepository<E, ID> baseRepository;
    
    public BaseServiceImpl(BaseRepository<E,ID>baseRepository){
        this.baseRepository=baseRepository;
    }
    
    @Override
    @Transactional
    public List<E> findAll() throws Exception {
       
        try{
            List<E> entities = baseRepository.findAll();
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    

    @Override
    @Transactional
    public Page<E> findAll(Pageable pageable) throws Exception{
        try{
            Page<E> entities = baseRepository.findAll(pageable);
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findById(ID id) throws Exception {
        
         try{
            Optional<E> entityOptional = baseRepository.findById(id);
            return entityOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        
        try{
            entity = baseRepository.save(entity);
            return entity;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
        
        try{
            Optional<E> entityOptional = baseRepository.findById(id);
            E persona = entityOptional.get();
            persona = baseRepository.save(entity);
            return persona;
            
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        
        try{
            if(baseRepository.existsById(id)){
               baseRepository.deleteById(id);
               return true;
            }else{
                throw new Exception();
            }
            
            
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}

