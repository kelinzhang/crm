package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.SystemMenu;
import com._520it.crm.mapper.SystemMenuMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ISystemMenuService;
import com._520it.crm.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SystemMenuServiceImpl implements ISystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;

    public int deleteByPrimaryKey(Long id) {
        return systemMenuMapper.deleteByPrimaryKey(id);
    }

    public int insert(SystemMenu record) {
        return systemMenuMapper.insert(record);
    }

    public SystemMenu selectByPrimaryKey(Long id) {
        return systemMenuMapper.selectByPrimaryKey(id);
    }

    public List<SystemMenu> selectAll() {
        return systemMenuMapper.selectAll();
    }

    public int updateByPrimaryKey(SystemMenu record) {
        return systemMenuMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryByConditionPage(QueryObject qo) {
        Long count = systemMenuMapper.queryByCondictionCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<SystemMenu> result = systemMenuMapper.queryByConditionResult(qo);
        PageResult pageResult = new PageResult(count, result);
        return pageResult;
    }

    @Override
    public List<SystemMenu> queryTree() {
        return systemMenuMapper.queryTree();
    }

    @Override
    public List<SystemMenu> roleMenu() {
        return systemMenuMapper.queryTree();
    }

    @Override
    public List<SystemMenu> indexMenu() {
        Employee current = UserContext.getCurrentUser();
        if (current.isAdmin()) {
            System.out.println("管理员");
            return systemMenuMapper.queryTree();
        } else {
            List<Long> menuIds = systemMenuMapper.getEmpMenuIds(current.getId());
            List<SystemMenu> allMenus = systemMenuMapper.queryTree();
            getPermissionTree(menuIds, allMenus);
            return allMenus;
        }
    }

    private void getPermissionTree(List<Long> menuIds, List<SystemMenu> allMenus) {
        for (int i = allMenus.size() - 1; i >=0 ; i--) {
            if (!menuIds.contains(allMenus.get(i).getId())) {
                allMenus.remove(i);
            } else {
                getPermissionTree(menuIds, allMenus.get(i).getChildren());
            }
        }
    }
}
