package com.costa.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PersonVO
{

	private int codigo;
	private String nome;
	private String sexo;

	public PersonVO()
	{
	}

	public PersonVO(int codigo, String nome, String sexo)
	{
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.sexo = sexo;
	}

	public int getCodigo()
	{
		return codigo;
	}

	public void setCodigo(int codigo)
	{
		this.codigo = codigo;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getSexo()
	{
		return sexo;
	}

	public void setSexo(String sexo)
	{
		this.sexo = sexo;
	}

}
