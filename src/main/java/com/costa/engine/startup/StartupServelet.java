package com.costa.engine.startup;

import com.costa.dao.BaseDao;
import com.costa.engine.persist.PersistEngine;
import com.costa.entity.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServlet;
import java.util.List;

public class StartupServelet extends HttpServlet
{
	public void init()
	{
		try
		{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("persist_unit");
			PersistEngine.setEntityManagerFactory(emf);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}
}
