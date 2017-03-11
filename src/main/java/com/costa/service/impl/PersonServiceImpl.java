package com.costa.service.impl;

import com.costa.dao.PersonDAO;
import com.costa.dao.impl.PersonDAOImpl;
import com.costa.entity.Person;
import com.costa.service.PersonService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class PersonServiceImpl implements PersonService
{
	private PersonDAO dao = new PersonDAOImpl();

	@Override
	public List<Person> findAll()
	{

		//Aqui vão as regras de negócio

		return dao.findAll();
	}

	@Override
	public Person findById(Long id)
	{
		//Aqui vão as regras de negócio

		return dao.findById(id);
	}

	@Override
	public Person create(Person person)
	{
		//Aqui vão as regras de negócio

		return dao.persist(person);
	}

	@Override
	public Person update(Person person)
	{
		//Aqui vão as regras de negócio

		return dao.merge(person);
	}

	@Override
	public void remove(Person person)
	{
		//Aqui vão as regras de negócio

		dao.remove(person);
	}
}
