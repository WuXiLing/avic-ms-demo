package avicit.demo.stock.stock.api;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.GenericType;

import avicit.demo.goods.goodsbase.dto.GoodsBaseDTO;
import avicit.platform6.core.rest.msg.Muti2Bean;
import avicit.platform6.core.rest.msg.QueryReqBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import avicit.demo.stock.stock.dto.StockDTO;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.rest.client.RestClient;
import avicit.platform6.core.rest.client.RestClientConfig;
import avicit.platform6.core.rest.msg.ResponseMsg;
import avicit.platform6.core.rest.msg.ResponseStatus;

/**
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：avicitdev@avicit.com
 * @创建时间： 2019-06-01 11:21
 * @类说明：请填写
 * @修改记录： 
 */
@Service
public class StockApi {

	private static final Logger logger = LoggerFactory.getLogger(StockApi.class);

	/** 订阅服务的服务编码  */
	private static final String ServiceCode = "Stock";

	/**
	 * 通过主键查询单条记录
	 * @param id 主键id
	 * @return StockDTO
	 * @throws Exception
	 */
	public StockDTO queryStockByPrimaryKey(String id) throws Exception {
		StockDTO dto = null;
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/stock/stock/StockRest/get/v1/" + id;
		ResponseMsg<StockDTO> responseMsg = RestClient.doGet(url, new GenericType<ResponseMsg<StockDTO>>() {
		});
		if (responseMsg.getRetCode().equals(ResponseStatus.HTTP_OK)) {
			dto = responseMsg.getResponseBody();
		} else {
			logger.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new DaoException(responseMsg.getErrorDesc());
		}
		return dto;
	}

	/**
	 * 新增对象
	 * @param dto 保存对象
	 * @return String
	 * @throws Exception
	 */
	public String insertStock(StockDTO dto) throws Exception {
		String id = null;
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/stock/stock/StockRest/save/v1";
		ResponseMsg<String> responseMsg = RestClient.doPost(url, dto, new GenericType<ResponseMsg<String>>() {
		});
		if (responseMsg.getRetCode().equals(ResponseStatus.HTTP_OK)) {
			id = responseMsg.getResponseBody();
		} else {
			logger.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new DaoException(responseMsg.getErrorDesc());
		}
		return id;
	}

	/**
	 * 修改对象部分字段
	 * @param dto 修改对象
	 * @return int
	 * @throws Exception
	 */
	public int updateStockSensitive(StockDTO dto) throws Exception {
		int count = 0;
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/stock/stock/StockRest/updateSensitive/v1";
		ResponseMsg<Integer> responseMsg = RestClient.doPost(url, dto, new GenericType<ResponseMsg<Integer>>() {
		});
		if (responseMsg.getRetCode().equals(ResponseStatus.HTTP_OK)) {
			count = responseMsg.getResponseBody();
		} else {
			logger.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new DaoException(responseMsg.getErrorDesc());
		}
		return count;
	}

	/**
	 * 批量删除数据
	 * @param ids id的数组
	 * @return int
	 * @throws Exception
	 */
	public int deleteStockByIds(String[] ids) throws Exception {
		int count = 0;
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/stock/stock/StockRest/deleteByIds/v1";
		ResponseMsg<Integer> responseMsg = RestClient.doPost(url, ids, new GenericType<ResponseMsg<Integer>>() {
		});
		if (responseMsg.getRetCode().equals(ResponseStatus.HTTP_OK)) {
			count = responseMsg.getResponseBody();
		} else {
			logger.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new DaoException(responseMsg.getErrorDesc());
		}
		return count;
	}

	/**
	 * 批量商品的库存
	 * @param goodsId 商品ID
	 * @return int
	 * @throws Exception
	 */
	public int deleteStockByGoodsId(String goodsId) throws Exception {
		int count = 0;

		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/stock/stock/StockRest/deleteByGoodsId/v1/".concat(goodsId);
		ResponseMsg<Integer> responseMsg = RestClient.doPost(url, "", new GenericType<ResponseMsg<Integer>>() {
		});
		if (responseMsg.getRetCode().equals(ResponseStatus.HTTP_OK)) {
			count = responseMsg.getResponseBody();
		} else {
			logger.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new DaoException(responseMsg.getErrorDesc());
		}
		return count;
	}

	/**
	 * 更新库存数量
	 * 
	 * @param id：库存ID
	 * @param num：减去的库存数量
	 * 
	 * @return int
	 */
	public int updateStockNum(String id,Integer num) throws Exception {
		int count = 0;
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/stock/stock/StockRest/updateStockNum/v1/" + id + "/" + num;
		Map<String,Object> params = new HashMap<String,Object>();
		ResponseMsg<Integer> responseMsg = RestClient.doPost(url,params, new GenericType<ResponseMsg<Integer>>() {
		});
		if (responseMsg.getRetCode().equals(ResponseStatus.HTTP_OK)) {
			count = responseMsg.getResponseBody();
		} else {
			logger.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new DaoException(responseMsg.getErrorDesc());
		}
		return count;
	}
	
	/**
	 * 根据商品ID查询库存数量
	 * 
	 * @param params
	 *            goodsId：商品ID
	 * 
	 * @return int 库存数量
	 */
	public int getStockNumByGoodsId(String goodsId) throws Exception {
		int count = 0;
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/stock/stock/StockRest/getStockNumByGoodsId/v1/" + goodsId;
		ResponseMsg<Integer> responseMsg = RestClient.doGet(url, new GenericType<ResponseMsg<Integer>>() {
		});
		if (responseMsg.getRetCode().equals(ResponseStatus.HTTP_OK)) {
			count = responseMsg.getResponseBody();
		} else {
			logger.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new DaoException(responseMsg.getErrorDesc());
		}
		return count;
	}
}
