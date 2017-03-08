package com.costa.engine.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Carlos on 05/03/2017.
 */
public class PersistEngine
{
	private static EntityManagerFactory entityManagerFactory;

	public static EntityManagerFactory getEntityManagerFactory()
	{
		return entityManagerFactory;
	}

	public static void setEntityManagerFactory(EntityManagerFactory entityManagerFactory)
	{
		PersistEngine.entityManagerFactory = entityManagerFactory;
	}

	public static EntityManager getEntityManager()
	{
		return PersistEngine.entityManagerFactory.createEntityManager();
	}


}
