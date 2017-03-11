package com.costa.service;

import com.costa.entity.Person;
import java.util.List;

public interface PersonService
{
	/**
	 * Busca todas as pessoas do banco
	 */
	public List<Person> findAll();

	/**
	 * Busca uma pessoa no banco pelo ID
	 */
	public Person findById(Long id);

	/**
	 * Cria/persist uma pessoa no banco
	 *
	 * @param person
	 */
	public Person create(Person person);

	/**
	 * Atualiza/mergeia uma pessoa no banco
	 *
	 * @param person
	 */
	public Person update(Person person);

	/**
	 * Remove uma pessoa do banco
	 *
	 * @param person
	 */
	public void remove(Person person);
}
