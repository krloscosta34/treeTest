package com.costa.vo;

import com.costa.entity.Node;

import java.util.ArrayList;
import java.util.List;

public class NodeVO implements java.io.Serializable
{
	private Long id;
	private String code;
	private String description;
	private String note;
	private NodeVO parent;
	private List<NodeVO> children = new ArrayList<>();

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

	public NodeVO getParent()
	{
		return parent;
	}

	public void setParent(NodeVO parent)
	{
		this.parent = parent;
	}

	public List<NodeVO> getChildren()
	{
		return children;
	}

	public void setChildren(List<NodeVO> children)
	{
		this.children = children;
	}

	@Override
	public boolean equals(Object o)
	{
		NodeVO x = (NodeVO) o;
		if(x.id == this.id)
			return true;
		return false;
	}

	public static List<NodeVO> buildList(List<Node> nodes, boolean fectChildren)
	{
		List<NodeVO> list = new ArrayList<>();

		for(Node n : nodes)
		{
			NodeVO vo = new NodeVO(n.getId(), n.getCode(), n.getDescription(), n.getNote());
			if(fectChildren && n.getChildren() != null && n.getChildren().size() > 0)
			{
				vo.setChildren(NodeVO.buildList(n.getChildren(), true));
			}
			list.add(vo);
		}
		return list;
	}

	public static List<NodeVO> buildHierarchicalTree(List<Node> nodes)
	{
		List<NodeVO> list = new ArrayList<>();

		for(Node n : nodes)
		{
			List<NodeVO> sequence = new ArrayList<>();
			Node aux = n;
			while(aux != null)
			{
				NodeVO vo = new NodeVO(aux.getId(), aux.getCode(), aux.getDescription(), aux.getNote());
				sequence.add(0, vo);
				aux = aux.getParent();
			}
			int pos = list.indexOf(sequence.get(0));
			if(pos == -1)
			{
				list.add(sequence.get(0));
				pos = list.size() - 1;
			}
			NodeVO vo = list.get(pos);
			for(int i = 1; i < sequence.size(); i++)
			{
				if(vo.getChildren().contains(sequence.get(i)))
				{
					int p = vo.getChildren().indexOf(sequence.get(i));
					vo = vo.getChildren().get(p);
				}
				else
				{
					vo.getChildren().add(sequence.get(i));
					vo = sequence.get(i);
				}
			}
		}
		return list;
	}
}
