package com.costa.engine.startup;

import com.costa.engine.persist.Persister;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServlet;

public class StartupServelet extends HttpServlet
{
	public void init()
	{
		try
		{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("persist_unit");
			Persister.setEntityManagerFactory(emf);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}
}
