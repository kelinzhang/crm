package com._520it.crm.service;
import com._520it.crm.domain.SystemMenu;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface ISystemMenuService {
	int deleteByPrimaryKey(Long id);
    int insert(SystemMenu record);
    SystemMenu selectByPrimaryKey(Long id);
    List<SystemMenu> selectAll();
    int updateByPrimaryKey(SystemMenu record);
	PageResult queryByConditionPage(QueryObject qo);
	List<SystemMenu> queryTree();

    List<SystemMenu> roleMenu();

    List<SystemMenu> indexMenu();
}
