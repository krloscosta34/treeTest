package com.costa.vo;

public class NodeVO implements java.io.Serializable
{
	private Long id;
	private String code;
	private String description;
	private String note;

	public NodeVO()
	{
	}

	public NodeVO(Long id, String code, String description, String note)
	{
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.note = note;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}
}
