package com.costa.engine.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by Carlos on 05/03/2017.
 */
public class Persister
{
	private static EntityManagerFactory entityManagerFactory;

	public static EntityManagerFactory getEntityManagerFactory()
	{
		return entityManagerFactory;
	}

	public static void setEntityManagerFactory(EntityManagerFactory entityManagerFactory)
	{
		Persister.entityManagerFactory = entityManagerFactory;
	}

	public static EntityManager getEntityManager()
	{
		return Persister.entityManagerFactory.createEntityManager();
	}


}
