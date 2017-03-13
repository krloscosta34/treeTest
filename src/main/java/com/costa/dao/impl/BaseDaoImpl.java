package com.costa.dao.impl;

import com.costa.dao.BaseDao;
import com.costa.engine.persist.Persister;
import com.costa.entity.BaseEntity;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T>
{
	private final EntityManager entityManager;

	public BaseDaoImpl()
	{
		this.entityManager = Persister.getEntityManager();
	}

	@Override
	public List<T> findAll(String entityName, String orderByField)
	{
		return this.entityManager
				.createQuery("SELECT entity FROM " + entityName + " entity ORDER BY entity." + orderByField)
				.getResultList();
	}

	@Override
	public List<T> findByQuery(String query)
	{
		return this.entityManager.createQuery(query).getResultList();
	}

	@Override
	public T findById(Long id, Class enttityClass)
	{
		return (T) this.entityManager.find(enttityClass, id);
	}

	@Override
	public T persist(T entity)
	{
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(entity);
		this.entityManager.getTransaction().commit();
		return entity;

	}

	@Override
	public T merge(T entity)
	{
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(entity);
		this.entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public void remove(T entity)
	{
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(entity);
		this.entityManager.getTransaction().commit();
	}
}
