package com.costa.dao.impl;

import com.costa.dao.NodeDAO;
import com.costa.entity.Node;

import javax.inject.Named;
import java.util.List;

@Named
public class NodeDAOImpl extends BaseDaoImpl<Node> implements NodeDAO
{
	@Override
	public List<Node> findAll()
	{
		return super.findAll("Node", "description");
	}

	@Override
	public List<Node> findRootNodes()
	{
		return super.findByQuery("SELECT node FROM Node node WHERE node.parent IS NULL ORDER BY node.description");
	}

	@Override
	public Node findById(Long id)
	{
		return (Node) super.findById(id, Node.class);
	}

	@Override
	public List<Node> findByDescription(String description)
	{
		return super.findByQuery("SELECT node FROM Node node WHERE node.description LIKE '%" + description + "%'");
	}
}
