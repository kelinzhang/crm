package com._520it.crm.mapper;

import com._520it.crm.domain.Employee;
import com._520it.crm.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    Employee getUser(@Param("username") String username, @Param("password") String password);

    List<Employee> queryList(EmployeeQueryObject qo);

    Long queryCount(EmployeeQueryObject qo);

    void changeState(@Param("id") Long id,@Param("state") int stateQuit);

    void insertRelation(@Param("roleId") Long roleId, @Param("empId") Long empId);

    void deleteRelation(Long empId);
}