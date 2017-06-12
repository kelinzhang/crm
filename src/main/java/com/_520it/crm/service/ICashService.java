package com._520it.crm.service;
import com._520it.crm.domain.Cash;
import com._520it.crm.domain.CashRecord;
import com._520it.crm.domain.Member;
import com._520it.crm.domain.PetService;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.math.BigDecimal;
import java.util.List;

public interface ICashService {
	int deleteByPrimaryKey(Long id);
    int insert(Cash record);
    Cash selectByPrimaryKey(Long id);
    List<Cash> selectAll();
    int updateByPrimaryKey(Cash record);
	PageResult queryByConditionPage(QueryObject qo);


    Member queryByMember(String number);

    CashRecord selectSingleRowById(Long id);


    BigDecimal listPrice();
    BigDecimal listPriceSale();

    PetService queryOnePetService(Long id);

    int saveBill(PetService ps);

    void clear(String number);

    List<Cash> listNumber(String number);
}
