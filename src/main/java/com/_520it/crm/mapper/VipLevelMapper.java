package com._520it.crm.mapper;

import com._520it.crm.domain.VipLevel;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VipLevelMapper {
    int deleteByPrimaryKey(Long id);
    int insert(VipLevel record);
    VipLevel selectByPrimaryKey(Long id);
    List<VipLevel> selectAll();
    int updateByPrimaryKey(VipLevel record);
	Long queryByCondictionCount(QueryObject qo);
	List<VipLevel> queryByConditionResult(QueryObject qo);
}