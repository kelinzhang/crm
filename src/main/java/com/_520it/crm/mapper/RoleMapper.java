package com._520it.crm.mapper;

import com._520it.crm.domain.Role;
import com._520it.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Role record);
    Role selectByPrimaryKey(Long id);
    List<Role> selectAll();
    int updateByPrimaryKey(Role record);
	Long queryByCondictionCount(QueryObject qo);
	List<Role> queryByConditionResult(QueryObject qo);

    int handleRelation(@Param("perId") Long perId,@Param("roleId")Long roleId);

    void deleteRelation(Long id);

    List<Long> queryRoleByEmp(Long empId);

    void addMenu(@Param("menuId") Long menuId, @Param("roleId") Long roleId);

    List<Long> getRoleMenuId(Long roleId);

    void deleteMenu(Long roleId);
}