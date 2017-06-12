package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter@ObjectProp("宠物品种")
public class PetBreed {
	private Long id;
	@ObjectProp("品种名称")
	private String text;
	private PetBreed parent;
}
