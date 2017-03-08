package com.costa.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.costa.entity.Person;
import com.costa.service.impl.PersonServiceImpl;
import com.costa.vo.PersonVO;
import com.costa.service.PersonService;

@Named

@Path("/person")
public class PersonRest
{
	private PersonService service = new PersonServiceImpl();

	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/findAll")
	public List<PersonVO> findAll()
	{
		List<Person> personList = service.findAll();

		List<PersonVO> personVOList = new ArrayList<PersonVO>();
		for(Person entity : personList)
			personVOList.add(new PersonVO(entity.getCode(), entity.getName(), entity.getSex()));

		return personVOList;
	}

	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/findById/{id}")
	public PersonVO findById(
			@PathParam("id")
					Long id)
	{
		Person entity = service.findById(id);
		if(entity != null)
			return new PersonVO(entity.getCode(), entity.getName(), entity.getSex());
		return null;
	}

	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/create")
	public Person create(PersonVO personVO)
	{
		Person entity = new Person();
		try
		{
			entity.setCode(personVO.getCodigo());
			entity.setName(personVO.getNome());
			entity.setSex(personVO.getSexo());
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
	@Path("/alterar")
	public String update(PersonVO personVO)
	{
		Person entity = new Person();
		try
		{
			entity.setCode(personVO.getCodigo());
			entity.setName(personVO.getNome());
			entity.setSex(personVO.getSexo());
			service.update(entity);

			return "Registro alterado com sucesso!";
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
			Person person = service.findById(id);
			if(person != null)
			{
				service.remove(person);
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
