package com.ms.dao.sku.face;

import java.util.List;

import com.ms.domain.sku.dao.SkuDAO;

/**
 * 商品操作接口
 * @author zhoushanjie
 *
 */
public interface ISkuDAO {
	
	/**
	 * 添加商品
	 * @param skuDAO
	 * @return
	 */
	long addSku(SkuDAO skuDAO) throws Exception;
	
	/**
	 * 更新商品信息
	 * @param skuDAO
	 * @return
	 */
	boolean updateSkuInfo(SkuDAO skuDAO) throws Exception;
	
	/**
	 * 删除商品信息
	 * @param id
	 * @return
	 */
	boolean delSkuInfo(Long id) throws Exception;
	
	/**
	 * 根据商品id查询商品信息
	 * @param skuId
	 * @return
	 */
	SkuDAO querySkuById(Long skuId) throws Exception;
	
	/**
	 * 根据商品id列表批量查询商品信息
	 * @param skuIds
	 * @return
	 */
	List<SkuDAO> querySkuListByIds(List<Long> skuIds) throws Exception;
	
	/**
	 * 根据综合条件查询某一类商品信息
	 * @param skuDAO
	 * @return
	 * @throws Exception
	 */
	List<SkuDAO> querySkuListByCondition(SkuDAO skuDAO) throws Exception;
	
	/**
	 * 分页查询商品列表信息
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	List<SkuDAO> querySkuListByPageNum(int page,int pageSize) throws Exception;

}
