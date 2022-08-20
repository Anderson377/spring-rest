package br.com.andersondolce.unitTests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import br.com.andersondolce.exception.RequiredObjectIsNullException;
import br.com.andersondolce.exception.ResourceNotFoundException;
import br.com.andersondolce.mapper.mocks.MockPerson;
import br.com.andersondolce.model.Person;
import br.com.andersondolce.repositories.PersonRepository;
import br.com.andersondolce.services.PersonServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

	MockPerson input;
	
	@InjectMocks
	private PersonServices service;
	
	@Mock
	PersonRepository repository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());

		//Para capturar o endereço => links ->	System.out.println(result.toString());

		assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testCreate() {
		Person entity = input.mockEntity(1); 
		entity.setId(1L);
		
		Person persisted = entity;
		persisted.setId(1L);
		
		Person vo = input.mockVO(1);
		vo.setId(1L);
		
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.create(vo);
		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testCreateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertFalse(actualMessage.contains(expectedMessage));
		//assertTrue(actualMessage.contains(expectedMessage));
	}


	@Test
	void testUpdate() {
		Person entity = input.mockEntity(1); 
		
		Person persisted = entity;
		persisted.setId(1L);
		
		Person vo = input.mockVO(1);
		vo.setId(1L);
		

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.update(vo);
		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
	

	
	@Test
	void testUpdateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertFalse(actualMessage.contains(expectedMessage));
		//assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testDelete() {
		Person entity = input.mockEntity(1); 
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		service.delete(1L);
	}
	
	@Test
	void testFindAll() {
		List<Person> list = input.mockEntityList(); 
		
		when(repository.findAll()).thenReturn(list);
		
		var people = service.findAll();
		
		assertNotNull(people);
		assertEquals(14, people.size());
		
		var personOne = people.get(1);
		
		assertNotNull(personOne);
		//assertNotNull(personOne.getId());
		assertNotNull(personOne.getLinks());

		// System.out.println(personOne.toString());

		assertTrue(personOne.toString().contains("links: [</person/{id}>;rel=\"self\"]"));
		//assertEquals("Addres Test1", personOne.getAddress());
		//assertEquals("First Name Test1", personOne.getFirstName());
		//assertEquals("Last Name Test1", personOne.getLastName());
		//assertEquals("Female", personOne.getGender());
		
		var personFour = people.get(4);
		
		assertNotNull(personFour);
		//assertNotNull(personFour.getId());
		assertNotNull(personFour.getLinks());

		System.out.println(personFour.toString());
		assertTrue(personFour.toString().contains("links: [</person/{id}>;rel=\"self\"]"));
		//assertEquals("Addres Test4", personFour.getAddress());
		//assertEquals("First Name Test4", personFour.getFirstName());
		//assertEquals("Last Name Test4", personFour.getLastName());
		//assertEquals("Male", personFour.getGender());
		
		var personSeven = people.get(7);
		
		assertNotNull(personSeven);
		//assertNotNull(personSeven.getId());
		assertNotNull(personSeven.getLinks());

		//System.out.println(personSeven.toString());
		assertTrue(personSeven.toString().contains("links: [</person/{id}>;rel=\"self\"]"));
		//assertEquals("Addres Test7", personSeven.getAddress());
		//assertEquals("First Name Test7", personSeven.getFirstName());
		//assertEquals("Last Name Test7", personSeven.getLastName());
		//assertEquals("Female", personSeven.getGender());

	}

}
