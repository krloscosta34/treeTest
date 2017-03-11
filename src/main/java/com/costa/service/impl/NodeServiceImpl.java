package com.costa.service.impl;

import com.costa.dao.NodeDAO;
import com.costa.dao.impl.NodeDAOImpl;
import com.costa.entity.Node;
import com.costa.service.NodeService;

import javax.inject.Named;
import java.util.List;

@Named
public class NodeServiceImpl implements NodeService
{
	private NodeDAO dao = new NodeDAOImpl();

	@Override
	public List<Node> findAll()
	{

		//Aqui vão as regras de negócio

		return dao.findAll();
	}

	@Override
	public Node findById(Long id)
	{
		//Aqui vão as regras de negócio

		return dao.findById(id);
	}

	@Override
	public Node create(Node node)
	{
		//Aqui vão as regras de negócio

		return dao.persist(node);
	}

	@Override
	public Node update(Node node)
	{
		//Aqui vão as regras de negócio

		return dao.merge(node);
	}

	@Override
	public void remove(Node node)
	{
		//Aqui vão as regras de negócio

		dao.remove(node);
	}
}
