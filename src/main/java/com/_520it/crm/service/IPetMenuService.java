package com._520it.crm.service;

import com._520it.crm.domain.PetMenu;

import java.util.List;

public interface IPetMenuService {
    int insert(PetMenu record);
    PetMenu selectByPrimaryKey(Long id);
    List<PetMenu> selectAll();
	List<PetMenu> queryTree();
    List<PetMenu> queryChildrenTree(Long parentId);
    int update(PetMenu petMenu);
    int delete(Long id);
}
