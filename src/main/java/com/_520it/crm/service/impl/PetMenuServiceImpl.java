package com._520it.crm.service.impl;

import com._520it.crm.domain.PetMenu;
import com._520it.crm.mapper.PetMenuMapper;
import com._520it.crm.service.IPetMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetMenuServiceImpl implements IPetMenuService {
    @Autowired
    private PetMenuMapper petMenuMapper;
    public int insert(PetMenu record) {
        return petMenuMapper.insert(record);
    }
    public PetMenu selectByPrimaryKey(Long id) {
        return petMenuMapper.selectByPrimaryKey(id);
    }
    public List<PetMenu> selectAll() {return petMenuMapper.selectAll();}
    public List<PetMenu> queryTree() {
        return petMenuMapper.queryTree();
    }
    public List<PetMenu> queryChildrenTree(Long parentId) {
        return petMenuMapper.queryChildrenTree(parentId);
    }
    public int update(PetMenu petMenu) { return petMenuMapper.updateByPrimaryKey(petMenu);}
    public int delete(Long id) { return petMenuMapper.delete(id);}
}
