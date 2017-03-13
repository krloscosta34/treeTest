package com.costa.entity;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by Carlos on 05/03/2017.
 */
public abstract class BaseEntity implements Serializable
{
	@Transient
	public abstract Long getId();

	public abstract void setId(Long id);
}
