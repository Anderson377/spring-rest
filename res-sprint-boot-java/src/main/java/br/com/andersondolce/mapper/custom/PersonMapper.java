package br.com.andersondolce.mapper.custom;

import br.com.andersondolce.model.Person;
import org.springframework.stereotype.Service;


@Service
public class PersonMapper {

    public Person convertEntityToVo(Person person){
        Person vo = new Person();
        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setGender(person.getGender());
        return vo;
    }

    public Person convertVoTOEntity(Person person){
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setAddress(person.getAddress());
        //vo.setBithDay(new Date());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        return entity;
    }

}
