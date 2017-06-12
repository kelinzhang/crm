package com._520it.crm.service.impl;

import com._520it.crm.domain.ProductStock;
import com._520it.crm.domain.StockIncome;
import com._520it.crm.domain.StockIncomeItem;
import com._520it.crm.mapper.StockIncomeMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.service.IProductStockService;
import com._520it.crm.service.IStockIncomeService;
import com._520it.crm.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class StockIncomeServiceImpl implements IStockIncomeService {
    @Autowired
    private StockIncomeMapper stockIncomeMapper;
    @Autowired
    private IProductStockService productStockService;
    @Autowired
    private IEmployeeService employeeService;

    public int deleteByPrimaryKey(Long id) {
        return stockIncomeMapper.deleteByPrimaryKey(id);
    }

    public int insert(StockIncome record) {
        record.setInputUser(UserContext.getCurrentUser());
        record.setVdate(new Date());
        record.setTotalNumber(BigDecimal.ZERO);
        record.setTotalAmount(BigDecimal.ZERO);
        List<StockIncomeItem> items = record.getItems();
        record.setSupplier(items.get(0).getSupplier());
        for (StockIncomeItem item : items) {
            record.setTotalNumber(record.getTotalNumber().add(item.getIncomeNumber()));
            record.setTotalAmount(record.getTotalAmount().add(item.getIncomeAmount()));
        }
        int count = stockIncomeMapper.insert(record);
        ProductStock ps = null;
        for (StockIncomeItem item : items) {
            item.setStockIn(record);
            stockIncomeMapper.insertRelation(item);
            //处理库存
            ps = productStockService.getPsByIncomeItem(item.getProSn(), item.getSupplier());
            if (ps == null) {
                ps = new ProductStock();
                ps.setProSn(item.getProSn());
                ps.setProName(item.getProName());
                ps.setKind(item.getKind());
                ps.setBrand(item.getBrand());
                ps.setSupplier(item.getSupplier());
                ps.setStockNumber(item.getIncomeNumber());
                ps.setStockPrice(item.getIncomePrice());
                ps.setStockAmount(item.getIncomeAmount());
                productStockService.insert(ps);
            } else {
                ps.setStockNumber(ps.getStockNumber().add(item.getIncomeNumber()));
                ps.setStockAmount(ps.getStockAmount().add(item.getIncomeAmount()));
                ps.setStockPrice(ps.getStockAmount().divide(ps.getStockNumber(), 2, BigDecimal.ROUND_HALF_UP));
                productStockService.updateByPrimaryKey(ps);
            }
        }
        return count;
    }

    public StockIncome selectByPrimaryKey(Long id) {
        return stockIncomeMapper.selectByPrimaryKey(id);
    }

    public List<StockIncome> selectAll() {
        return stockIncomeMapper.selectAll();
    }

    public int updateByPrimaryKey(StockIncome record) {
        return stockIncomeMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryByConditionPage(QueryObject qo) {
        Long count = stockIncomeMapper.queryByCondictionCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<StockIncome> result = stockIncomeMapper.queryByConditionResult(qo);
        if (result.size() > 0) {
            for (StockIncome stockIncome : result) {
                Long userId = stockIncome.getInputUser().getId();
                if (userId != null) {
                    stockIncome.setInputUser(employeeService.selectByPrimaryKey(stockIncome.getInputUser().getId()));
                }
            }
        }
        PageResult pageResult = new PageResult(count, result);
        return pageResult;
    }

    @Override
    public PageResult listItem(Long id) {
        Long itemCount = stockIncomeMapper.getItemCount(id);
        if (itemCount <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<StockIncomeItem> items = stockIncomeMapper.getItems(id);
        PageResult result = new PageResult(itemCount, items);
        return result;
    }
}
