package br.grupo1.javalis.repository;

import org.springframework.data.repository.CrudRepository;

import br.grupo1.javalis.model.Alarme;

public interface AlarmeRepo extends CrudRepository<Alarme, Integer> {
}