package com.costa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Node extends BaseEntity
{
	private Long id;
	private String code;
	private String description;
	private String note;
	private Node parent;
	private List<Node> children = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Override
	public Long getId()
	{


		return id;
	}

	@Override
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

	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinTable(name = "Node_ParentChild", joinColumns = {
			@JoinColumn(name = "child_id", referencedColumnName = "id")}, inverseJoinColumns = {
			@JoinColumn(name = "parent_id", referencedColumnName = "id")})
//	@JsonBackReference(value = "parent-children")
	public Node getParent()
	{
		return parent;
	}

	public void setParent(Node parent)
	{
		this.parent = parent;
	}

	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	@JoinTable(name = "Node_ParentChild", joinColumns = {
			@JoinColumn(name = "parent_id", referencedColumnName = "id")}, inverseJoinColumns = {
			@JoinColumn(name = "child_id", referencedColumnName = "id")})
//	@JsonManagedReference(value = "parent-children")
	public List<Node> getChildren()
	{
		return children;
	}

	public void setChildren(List<Node> children)
	{
		this.children = children;
	}
}
