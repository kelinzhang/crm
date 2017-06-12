
package com._520it.crm.mapper;

import com._520it.crm.page.PageResult;
import com._520it.crm.query.PetServiceChartQueryObject;
import com._520it.crm.query.ProductStockQueryObject;
import com._520it.crm.vo.PetServiceVO;
import com._520it.crm.vo.StockAllVO;

import java.util.List;

/**
 * 作者:Sean
 * 时间:2017.4.29
 */
//报表的通用
//每个人的报表查询方法都写在这里
public interface BaoBiaoMapper {
    List<PetServiceVO> queryPetServiceData(PetServiceChartQueryObject qo);
    List<PetServiceVO> queryPetServiceDataForPie();
    Long queryCount(PetServiceChartQueryObject qo);
	PageResult queryStockBrandData(ProductStockQueryObject qo);
	Long queryStockCount(ProductStockQueryObject pq);
	List<StockAllVO> queryStockData(ProductStockQueryObject pq);
	Long queryStockKindCount(ProductStockQueryObject pq);
	List<StockAllVO> queryStockKindData(ProductStockQueryObject pq);
	List<StockAllVO> queryBrandDataForPie();
	List<StockAllVO> queryKindDataForPie();
}
