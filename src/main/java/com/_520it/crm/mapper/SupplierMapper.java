package com._520it.crm.mapper;

import com._520it.crm.domain.Supplier;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplierMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Supplier record);
    Supplier selectByPrimaryKey(Long id);
    List<Supplier> selectAll();
    int updateByPrimaryKey(Supplier record);
	Long queryByCondictionCount(QueryObject qo);
	List<Supplier> queryByConditionResult(QueryObject qo);
}