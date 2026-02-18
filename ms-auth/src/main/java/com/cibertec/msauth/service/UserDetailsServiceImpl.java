package com.cibertec.msauth.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cibertec.gestionmedica.entity.Usuario;
import com.cibertec.msauth.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        Usuario usuario = usuarioRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

  
        return new User(
                usuario.getName(), 
                usuario.getPassword(), 
                Collections.emptyList() 
        );
    }
	
}
