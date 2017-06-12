package com._520it.crm.mapper;

import com._520it.crm.domain.PetMenu;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface PetMenuMapper {
    int deleteByPrimaryKey(Long id);
    int insert(PetMenu record);
    PetMenu selectByPrimaryKey(Long id);
    List<PetMenu> selectAll();
    int updateByPrimaryKey(PetMenu record);
	List<PetMenu> queryByConditionResult(QueryObject qo);
	List<PetMenu> queryTree();
    List<PetMenu> queryChildrenTree(Long parentId);
    int delete(Long id);
}