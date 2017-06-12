package com._520it.crm.service.impl;

import com._520it.crm.mapper.BaoBiaoMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.PetServiceChartQueryObject;
import com._520it.crm.query.ProductStockQueryObject;
import com._520it.crm.service.IBaoBiaoService;
import com._520it.crm.vo.PetServiceVO;
import com._520it.crm.vo.StockAllVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BaoBiaoServiceImpl implements IBaoBiaoService {
    @Autowired
    private BaoBiaoMapper baoBiaoMapper;
    @Override
    public PageResult queryPetServiceData(PetServiceChartQueryObject qo) {

        Long count = baoBiaoMapper.queryCount(qo);
        if(count <= 0){
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<PetServiceVO> result = baoBiaoMapper.queryPetServiceData(qo);
        return new PageResult(count,result);

    }

    @Override
    public List<PetServiceVO> queryPetServiceDataForPie() {
        return baoBiaoMapper.queryPetServiceDataForPie();
    }

	@Override
	public PageResult queryStockBrandData(ProductStockQueryObject pq) {
		 Long count = baoBiaoMapper.queryStockCount(pq);
		 if(count <= 0){
	            return new PageResult(0L, Collections.EMPTY_LIST);
	        }
	    List<StockAllVO> result = baoBiaoMapper.queryStockData(pq);
		return new PageResult(count,result);
	}

	@Override
	public PageResult queryStockKindData(ProductStockQueryObject pq) {
		 Long count = baoBiaoMapper.queryStockKindCount(pq);
		 if(count <= 0){
	            return new PageResult(0L, Collections.EMPTY_LIST);
	     }
		 List<StockAllVO> result = baoBiaoMapper.queryStockKindData(pq);
		return new PageResult(count,result);
	}

	@Override
	public List<StockAllVO> queryStockBrandDataForPie() {
		
		return baoBiaoMapper.queryBrandDataForPie();
	}

	@Override
	public List<StockAllVO> queryStockKindDataForPie() {
		
		return baoBiaoMapper.queryKindDataForPie();
	}
}
