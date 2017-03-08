package com.costa.dao.impl;

import com.costa.dao.PersonDAO;
import com.costa.entity.Person;

import javax.inject.Named;
import java.util.List;

@Named
public class PersonDAOImpl extends BaseDaoImpl<Person> implements PersonDAO
{
	public List<Person> findAll()
	{
		return super.findAll("Person", "name");
	}

	public Person findById(Long id)
	{
		return (Person) super.findById(id, Person.class);
	}
}
