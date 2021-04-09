package br.grupo1.javalis.repository;

import org.springframework.data.repository.CrudRepository;

import br.grupo1.javalis.model.Usuario;

public interface UsuarioRepo extends CrudRepository<Usuario, Integer> {
    public Usuario findByEmailOrRacf(String email, String racf);
}
