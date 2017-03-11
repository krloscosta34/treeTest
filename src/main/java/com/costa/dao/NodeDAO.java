package com.costa.dao;

import com.costa.entity.Node;

import java.util.List;

public interface NodeDAO extends BaseDao<Node>
{
	/**
	 * Busca todas os nós
	 *
	 */
	public List<Node> findAll();

	/**
	 * Busca um nó pelo ID
	 *
	 * @param id	ID do nó
	 */
	public Node findById(Long id);
}
