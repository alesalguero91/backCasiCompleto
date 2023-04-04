/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.completo.backcompletodos.MailSend.Controller;


import com.completo.backcompletodos.MailSend.Dto.EmailValueDto;
import com.completo.backcompletodos.MailSend.Service.EmailService;
import com.completo.backcompletodos.Security.Controller.Mensaje;
import com.completo.backcompletodos.Security.Dto.ChangePasswordDto;
import com.completo.backcompletodos.Security.Entity.Usuario;
import com.completo.backcompletodos.Security.Service.UsuarioService;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author salguero
 */
@RestController
@RequestMapping("/email-password")
@CrossOrigin("http://localhost:4200")
public class EmailController {
    @Autowired
    EmailService eService;
    
    @Autowired
    UsuarioService usuarioService;
    
    @Value("${spring.mail.username}")
    private String mailFrom;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    private final String subject="cambio de contraseña";
    
    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValueDto dto){
        Optional<Usuario>usuarioOpt= usuarioService.getByNombreUsuarioOrEmail(dto.getMailTo());
        if(!usuarioOpt.isPresent())
            return new ResponseEntity(new Mensaje("No existe usuario"), HttpStatus.NOT_FOUND);
        Usuario usuario = usuarioOpt.get();
        dto.setMailFrom(mailFrom);
        dto.setMailTo(usuario.getEmail());
        dto.setSubject(subject);
        dto.setUserName(usuario.getNombreUsuario());
        UUID uuid= UUID.randomUUID();
        String tokenPassword = uuid.toString();
        dto.setTokenPassword(tokenPassword);
        usuario.setTokenPassword(tokenPassword);
        usuarioService.save(usuario);
        eService.sendEmailTemplate(dto);
        return new ResponseEntity(new Mensaje ("Hemos enviado un correo"), HttpStatus.OK);
    }
   
    @PostMapping("/change-password")
    public ResponseEntity<?>changePassword(@Valid @RequestBody ChangePasswordDto dto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje ("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        if(!dto.getPassword().equals(dto.getConfirmPassword()))
            return new ResponseEntity(new Mensaje ("Las contraseñas no coinciden"), HttpStatus.BAD_REQUEST);
        Optional<Usuario> usuarioOpt= usuarioService.getByTokenPassword(dto.getTokenPassword());
        if(!usuarioOpt.isPresent())
            return new ResponseEntity(new Mensaje("No existe usuario"), HttpStatus.NOT_FOUND);
        Usuario usuario = usuarioOpt.get();
        String newPassword = passwordEncoder.encode(dto.getPassword());
        usuario.setPassword(newPassword);
        usuario.setTokenPassword(null);
        usuarioService.save(usuario);
        
        return new ResponseEntity(new Mensaje("Contraseña actualizada"), HttpStatus.OK);
    }
}
