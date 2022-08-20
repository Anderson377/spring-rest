package br.com.andersondolce.services;

import br.com.andersondolce.controllers.PersonController;
import br.com.andersondolce.exception.ResourceNotFoundException;
import br.com.andersondolce.mapper.DozerMapper;
import br.com.andersondolce.mapper.custom.PersonMapper;
import br.com.andersondolce.model.Person;
import br.com.andersondolce.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    public List<Person> findAll() {

        return repository.findAll();
    }

    public Person findById(Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        Person vo =  DozerMapper.parseObject(entity, Person.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return  vo;
    }

    public Person create(Person person) {
        return repository.save(person);
    }

    public Person update(Person person) {

        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}
