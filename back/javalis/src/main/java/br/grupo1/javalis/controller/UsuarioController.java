package br.grupo1.javalis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.grupo1.javalis.model.Usuario;
import br.grupo1.javalis.repository.UsuarioRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepo repo;

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario userEnviado){
        Usuario userDoBD = repo.findByEmailOrRacf(userEnviado.getEmail(), userEnviado.getRacf());

        if(userDoBD != null){ //usuário existe
            if(userDoBD.getSenha().equals(userEnviado.getSenha())){
                return ResponseEntity.ok(userDoBD);
            }
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/all")
    public List<Usuario> getAllUsers() {
        List<Usuario> lista = (List<Usuario>) repo.findAll();
        return lista;
    }
}
