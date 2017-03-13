package com.costa.service;

import com.costa.entity.Node;
import java.util.List;

public interface NodeService
{
	/**
	 * Busca todas os nós do banco
	 */
	public List<Node> findAll();

	/**
	 * Busca os nós raízes
	 */
	public List<Node> findRootNodes();

	/**
	 * Busca os nós por descrição
	 */
	public List<Node> findByDescription(String description);

	/**
	 * Busca um nó no banco pelo ID
	 */
	public Node findById(Long id);

	/**
	 * Cria/persist um nó no banco
	 *
	 * @param node
	 */
	public Node create(Node node);

	/**
	 * Atualiza/mergeia um nó no banco
	 *
	 * @param node
	 */
	public Node update(Node node);

	/**
	 * Remove um nó do banco
	 *
	 * @param node
	 */
	public void remove(Node node);
}
