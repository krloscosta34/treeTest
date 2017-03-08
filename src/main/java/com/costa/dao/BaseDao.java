package com.costa.dao;

import com.costa.entity.BaseEntity;

import java.util.List;

public interface BaseDao<T extends BaseEntity>
{
	/**
	 * Buscar todos os objetos de um tipo
	 *
	 * @param entityName	tipo do objeto
	 * @param orderByField	campo pelo qual se deve ordenar
	 */
	public List<T> findAll(String entityName, String orderByField);

	/**
	 * Encontrar um objeto pelo id
	 *
	 * @param id
	 * @return
	 */
	public T findById(Long id, Class enttityClass);

	/**
	 * Persistir objetos
	 *
	 * @param entity	Entidade a ser persistida
	 */
	public T persist(T entity);

	/**
	 * Atualizar objetos
	 *
	 * @param entity	entidade a ser mergiada
	 */
	public T merge(T entity);

	/**
	 * Remover objetos
	 *
	 * @param enttity	entidade a ser removida
	 */
	public void remove(T enttity);
}
