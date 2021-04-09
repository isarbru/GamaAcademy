package br.grupo1.javalis.controller;


import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.grupo1.javalis.model.Evento;
import br.grupo1.javalis.repository.EventoRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private EventoRepo repo;

    @GetMapping("/all")
    public List<Evento> getAll(){
        List<Evento> lista = (List<Evento>) repo.findAll();

        return lista;
    }

    @PostMapping("/data")
    public List<Evento> buscarPorData(@RequestBody ObjectNode json) throws ParseException {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate ini = LocalDate.parse(json.get("dataIni").asText(), fmt);
        LocalDate fim = LocalDate.parse(json.get("dataFim").asText(), fmt);

        List<Evento> lista = (List<Evento>) repo.findByDataEvtBetweenOrderByDataEvt(ini, fim);

        return lista;
    }
    
}

