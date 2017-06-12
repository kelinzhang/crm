package com._520it.crm.service.impl;

import com._520it.crm.domain.*;
import com._520it.crm.mapper.CashRecordMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.*;
import com._520it.crm.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
public class CashRecordServiceImpl implements ICashRecordService {
    @Autowired
    private CashRecordMapper cashRecordMapper;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IProductStockService productStockService;
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IVipLevelService vipLevelService;
    @Autowired
    private ICashService cashService;
    @Autowired
    private IPetServiceService petServiceService;

    public int deleteByPrimaryKey(Long id) {
        return cashRecordMapper.deleteByPrimaryKey(id);
    }

    public int insert(CashRecord record) {
        record.setInputUser(UserContext.getCurrentUser());
        record.setInputTime(new Date());
        record.setShopAmount(BigDecimal.ZERO);
        record.setConsume(BigDecimal.ZERO);
        List<CashItem> items = record.getItems();
        //获取当前消费会员
        Member member = memberService.queryByMember(record.getNumber());
        //获取当前消费会员的会员等级,从而获取其商品折扣以及服务折扣
        Long levelId = member.getLevel().getId();
        VipLevel vip = vipLevelService.selectByPrimaryKey(levelId);
        for (CashItem item : items) {
            System.out.println("item = " + item);
            //设置收银单的销售总数以及销售总额
            record.setShopAmount(record.getShopAmount().add(item.getSaleNumber()));
            if (item.getSaleAmount() == null) {
                item.setSaleAmount(item.getSalePrice().multiply(new BigDecimal(1.5 * vip.getLevelServiceDisCount()/100)));
            }
            record.setConsume(record.getConsume().add(item.getSaleAmount()));
        }
        int count = cashRecordMapper.insert(record);
        if(member.getBalance().compareTo(record.getConsume())<0){
            throw new RuntimeException("余额不足,请充值");
        }else{
        member.setBalance(member.getBalance().subtract(record.getConsume()));
        memberService.updateByPrimaryKey(member);
        }
        for (CashItem item : items) {
            petServiceService.updateByPayForPetService(item.getProSn());

            //处理库存
            ProductStock ps = productStockService.getPsByIncomeItem(item.getProSn(), item.getSupplier());
            //根据是服务还是商品对售价进行处理
            if (ps == null) {
                item.setSalePrice(item.getSalePrice().multiply(new BigDecimal(1.5 * vip.getLevelServiceDisCount()/100)));
            } else {
                //处理库存
                if (ps.getStockNumber().compareTo(item.getSaleNumber()) < 0) {
                    throw new RuntimeException(ps.getProName() + "商品库存不足,当前库存数量" + ps.getStockNumber());
                }
                ps.setStockNumber(ps.getStockNumber().subtract(item.getSaleNumber()));
                ps.setStockAmount(ps.getStockAmount().subtract(item.getSalePrice().multiply(item.getSaleNumber())));
                productStockService.updateByPrimaryKey(ps);
                item.setSalePrice(item.getSalePrice().multiply(new BigDecimal(1.5 * vip.getLevelProducteDisCount()/100)));
                item.setProfix(item.getSaleAmount().subtract(ps.getStockPrice().multiply(item.getSaleNumber())));
            }
            item.setRecord(record);
            cashRecordMapper.insertRelation(item);
        }

        cashService.clear(record.getNumber());
        return count;
    }

    public CashRecord selectByPrimaryKey(Long id) {
        return cashRecordMapper.selectByPrimaryKey(id);
    }

    public List<CashRecord> selectAll() {
        return cashRecordMapper.selectAll();
    }

    public int updateByPrimaryKey(CashRecord record) {
        return cashRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryByConditionPage(QueryObject qo) {
        Long count = cashRecordMapper.queryByCondictionCount(qo);
        System.out.println("count = " + count);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<CashRecord> result = cashRecordMapper.queryByConditionResult(qo);
        for (CashRecord cashRecord : result) {
            cashRecord.setInputUser(employeeService.selectByPrimaryKey(cashRecord.getInputUser().getId()));
        }
        PageResult pageResult = new PageResult(count, result);
        return pageResult;
    }

    @Override
    public PageResult listItem(Long id) {
        Long itemCount = cashRecordMapper.getItemCount(id);
        if (itemCount <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<CashItem> recordList = cashRecordMapper.getItem(id);
        PageResult pageResult = new PageResult(itemCount, recordList);
        return pageResult;
    }
}
