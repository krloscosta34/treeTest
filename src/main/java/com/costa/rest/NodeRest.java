package com.costa.rest;

import com.costa.engine.exception.CustomException;
import com.costa.entity.Node;
import com.costa.service.NodeService;
import com.costa.service.impl.NodeServiceImpl;
import com.costa.vo.NodeVO;
import com.costa.vo.ResponseVO;

import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Named

@Path("/entity/node")
public class NodeRest
{
	private NodeService service = new NodeServiceImpl();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
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
	@Produces(MediaType.APPLICATION_JSON)
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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/{parentId}")
	public ResponseVO create(NodeVO nodeVO,
			@PathParam("parentId")
					Long parentId)
	{
		ResponseVO response = new ResponseVO();
		try
		{
			Node entity = new Node();
			Node parent = null;
			if(parentId != -1)
			{
				parent = service.findById(parentId);
				if(parent == null)
					throw new CustomException("Não foi possível encontrar o registro de nó pai para a criação!");
			}

			entity.setCode(nodeVO.getCode());
			entity.setDescription(nodeVO.getDescription());
			entity.setNote(nodeVO.getNote());
			entity.setParent(parent);
			entity = service.create(entity);

			nodeVO.setId(entity.getId());
//			response.setEntity(nodeVO);
			response.setEntity(entity.getId());
			return response;
		}
		catch(CustomException e)
		{
			response.setSuccess(false);
			response.setErrorMsg(e.getMessage());
			return response;
		}
		catch(Exception e)
		{
			response.setSuccess(false);
			response.setErrorMsg("Erro ao criar o registro! " + e.getMessage());
			return response;
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update/{nodeId}")
	public ResponseVO update(NodeVO nodeVO,
			@PathParam("nodeId")
					Long nodeId)
	{
		ResponseVO response = new ResponseVO();
		try
		{
			Node entity = service.findById(nodeId);
			if(entity == null)
				throw new CustomException("Não foi possível encontrar o registro para a atualização!");

			entity.setCode(nodeVO.getCode());
			entity.setDescription(nodeVO.getDescription());
			entity.setNote(nodeVO.getNote());
			service.update(entity);
			return response;
		}
		catch(CustomException e)
		{
			response.setSuccess(false);
			response.setErrorMsg(e.getMessage());
			return response;
		}
		catch(Exception e)
		{
			response.setSuccess(false);
			response.setErrorMsg("Erro ao atualizar o registro! " + e.getMessage());
			return response;
		}
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/remove/{id}")
	public ResponseVO remove(
			@PathParam("id")
					Long id)
	{
		ResponseVO response = new ResponseVO();
		try
		{
			Node entity = service.findById(id);
			if(entity == null)
				throw new CustomException("Não foi possível encontrar o registro para a remoção!");

			service.remove(entity);
			return response;
		}
		catch(CustomException e)
		{
			response.setSuccess(false);
			response.setErrorMsg(e.getMessage());
			return response;
		}
		catch(Exception e)
		{
			response.setSuccess(false);
			response.setErrorMsg("Erro ao excluir o registro! " + e.getMessage());
			return response;
		}
	}
}
