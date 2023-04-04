/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.completo.backcompletodos.Util;


import com.completo.backcompletodos.Security.Entity.Rol;
import com.completo.backcompletodos.Security.Enums.RolNombre;
import com.completo.backcompletodos.Security.Service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author salguero
 */
 @Component
public class createRoles implements CommandLineRunner{

    @Autowired RolService rolService;
    
    @Override
    public void run(String... args) throws Exception {
       /*Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
        Rol rolUser = new Rol(RolNombre.ROLE_USER);
        
        rolService.save(rolAdmin);
        rolService.save(rolUser);
*/
    }
    
}
