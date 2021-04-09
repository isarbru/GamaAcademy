package br.grupo1.javalis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.grupo1.javalis.model.Evento;

public interface EventoRepo extends CrudRepository<Evento, Integer>{
    public List<Evento> findByDataEvtBetweenOrderByDataEvt(LocalDate iniDate, LocalDate finalDate);
}