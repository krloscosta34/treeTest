package com.costa.dao.impl;

import com.costa.dao.NodeDAO;
import com.costa.entity.Node;

import javax.inject.Named;
import java.util.List;

@Named
public class NodeDAOImpl extends BaseDaoImpl<Node> implements NodeDAO
{
	public List<Node> findAll()
	{
		return super.findAll("Node", "description");
	}

	public Node findById(Long id)
	{
		return (Node) super.findById(id, Node.class);
	}
}
