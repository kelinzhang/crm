package com._520it.crm.service;

import com._520it.crm.page.PageResult;
import com._520it.crm.query.PetServiceChartQueryObject;
import com._520it.crm.query.ProductStockQueryObject;
import com._520it.crm.vo.PetServiceVO;
import com._520it.crm.vo.StockAllVO;

import java.util.List;

/**
 * Created by Sean on 2017/4/29.
 */
public interface IBaoBiaoService {
    PageResult queryPetServiceData(PetServiceChartQueryObject qo);
    List<PetServiceVO> queryPetServiceDataForPie();
	PageResult queryStockBrandData(ProductStockQueryObject qo);
	PageResult queryStockKindData(ProductStockQueryObject qo);
	List<StockAllVO> queryStockBrandDataForPie();
	List<StockAllVO> queryStockKindDataForPie();
}
