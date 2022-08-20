package br.com.andersondolce.controllers;

import br.com.andersondolce.model.Person;
import br.com.andersondolce.services.PersonServices;
import br.com.andersondolce.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonServices service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public List<Person> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public Person findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML  },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML  })
    public Person create(@RequestBody Person person) {
        return service.create(person);
    }


    @PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces ={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public Person update(@RequestBody Person person) {
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
