package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.VipLevel;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IVipLevelService {
	int deleteByPrimaryKey(Long id);
    int insert(VipLevel record);
    VipLevel selectByPrimaryKey(Long id);
    List<VipLevel> selectAll();
    int updateByPrimaryKey(VipLevel record);
	PageResult queryByConditionPage(QueryObject qo);
}
