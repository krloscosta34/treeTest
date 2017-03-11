package com.costa.rest;

import com.costa.entity.Node;
import com.costa.service.NodeService;
import com.costa.service.impl.NodeServiceImpl;
import com.costa.vo.NodeVO;

import javax.inject.Named;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Named

@Path("/entity/node")
public class NodeRest
{
	private NodeService service = new NodeServiceImpl();

	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/findAll")
	public List<NodeVO> findAll()
	{
		List<Node> nodeList = service.findAll();

		List<NodeVO> nodeVOList = new ArrayList<NodeVO>();
		for(Node entity : nodeList)
			nodeVOList.add(new NodeVO(entity.getId(), entity.getCode(), entity.getDescription(),
					entity.getNote()));

		return nodeVOList;
	}

	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/findById/{id}")
	public NodeVO findById(
			@PathParam("id")
					Long id)
	{
		Node entity = service.findById(id);
		if(entity != null)
			return new NodeVO(entity.getId(), entity.getCode(), entity.getDescription(), entity.getNote());
		return null;
	}

	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/create/{parentId}")
	public Node create(NodeVO noteVO,
			@PathParam("parentId")
					Long parentId)
	{
		Node entity = new Node();
		try
		{
			Node parent = service.findById(parentId);

			entity.setId(noteVO.getId());
			entity.setCode(noteVO.getCode());
			entity.setDescription(noteVO.getDescription());
			entity.setNote(noteVO.getNote());
			entity.setParent(parent);
			return service.create(entity);
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/update/{nodeId}")
	public String update(NodeVO noteVO,
			@PathParam("nodeId")
					Long nodeId)
	{
		try
		{
			Node entity = service.findById(nodeId);
			if(entity != null)
			{
				entity.setCode(noteVO.getCode());
				entity.setDescription(noteVO.getDescription());
				entity.setNote(noteVO.getNote());
				service.update(entity);
				return "Registro alterado com sucesso!";
			}
			return "Erro ao alterar o registro!";

		}
		catch(Exception e)
		{
			return "Erro ao alterar o registro " + e.getMessage();
		}
	}

	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/remove/{id}")
	public String remove(
			@PathParam("id")
					Long id)
	{
		try
		{
			Node entity = service.findById(id);
			if(entity != null)
			{
				service.remove(entity);

				return "Registro excluido com sucesso!";
			}
			return "Erro ao excluir o registro!";
		}
		catch(Exception e)
		{
			return "Erro ao excluir o registro! " + e.getMessage();
		}
	}
}
