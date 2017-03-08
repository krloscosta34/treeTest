package com.costa.dao;

import com.costa.entity.Person;

import java.util.List;

public interface PersonDAO extends BaseDao<Person>
{
	/**
	 * Busca todas as pessoas
	 *
	 */
	public List<Person> findAll();

	/**
	 * Busca uma pessoa pelo ID
	 *
	 * @param id	ID da pessoa
	 */
	public Person findById(Long id);
}
