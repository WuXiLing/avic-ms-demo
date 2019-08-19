package avicit.demo.goods.goodsbase.api;

import java.util.List;

import javax.ws.rs.core.GenericType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import avicit.demo.goods.goodsbase.dto.GoodsBaseDTO;
import avicit.demo.goods.goodsbase.dto.GoodsVO;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.rest.client.RestClient;
import avicit.platform6.core.rest.client.RestClientConfig;
import avicit.platform6.core.rest.msg.Muti1Bean;
import avicit.platform6.core.rest.msg.Muti2Bean;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.core.rest.msg.ResponseMsg;
import avicit.platform6.core.rest.msg.ResponseStatus;

/**
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：avicitdev@avicit.com
 * @创建时间： 2019-06-01 10:25
 * @类说明：请填写
 * @修改记录： 
 */
@Service
public class GoodsBaseApi {

	private static final Logger logger = LoggerFactory.getLogger(GoodsBaseApi.class);

	/** 订阅服务的服务编码  */
	private static final String ServiceCode = "GoodsBase";

	/**
	 * 按条件分页查询
	 * @param queryReqBean 分页
	 * @param orderBy 排序
	 * @return QueryRespBean<GoodsBaseDTO>
	 * @throws Exception
	 */
	public QueryRespBean<GoodsBaseDTO> searchGoodsBaseByPage(QueryReqBean<GoodsBaseDTO> queryReqBean, String orderBy)
			throws Exception {
		QueryRespBean<GoodsBaseDTO> result = null;
		Muti2Bean<QueryReqBean<GoodsBaseDTO>, String> params = new Muti2Bean<QueryReqBean<GoodsBaseDTO>, String>();
		params.setDto1(queryReqBean);
		params.setDto2(orderBy);
		String url = RestClientConfig.getRestHost(ServiceCode)
				+ "/api/demo/goods/goodsbase/GoodsBaseRest/searchByPage/v1";
		ResponseMsg<QueryRespBean<GoodsBaseDTO>> responseMsg = RestClient.doPost(url, params,
				new GenericType<ResponseMsg<QueryRespBean<GoodsBaseDTO>>>() {
				});
		if (responseMsg.getRetCode().equals(ResponseStatus.HTTP_OK)) {
			result = responseMsg.getResponseBody();
		} else {
			logger.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new DaoException(responseMsg.getErrorDesc());
		}
		return result;
	}

	/**
	 * 按条件or查询的分页查询
	 * @param queryReqBean 分页 
	 * @param orderBy 排序
	 * @return QueryRespBean<GoodsBaseDTO>
	 * @throws Exception
	 */
	public QueryRespBean<GoodsBaseDTO> searchGoodsBaseByPageOr(QueryReqBean<GoodsBaseDTO> queryReqBean, String orderBy)
			throws Exception {
		QueryRespBean<GoodsBaseDTO> result = null;
		Muti2Bean<QueryReqBean<GoodsBaseDTO>, String> params = new Muti2Bean<QueryReqBean<GoodsBaseDTO>, String>();
		params.setDto1(queryReqBean);
		params.setDto2(orderBy);
		String url = RestClientConfig.getRestHost(ServiceCode)
				+ "/api/demo/goods/goodsbase/GoodsBaseRest/searchOrByPage/v1";
		ResponseMsg<QueryRespBean<GoodsBaseDTO>> responseMsg = RestClient.doPost(url, params,
				new GenericType<ResponseMsg<QueryRespBean<GoodsBaseDTO>>>() {
				});
		if (responseMsg.getRetCode().equals(ResponseStatus.HTTP_OK)) {
			result = responseMsg.getResponseBody();
		} else {
			logger.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new DaoException(responseMsg.getErrorDesc());
		}
		return result;
	}

	/**
	* 按条件查询
	* @param queryReqBean 条件
	* @return List<GoodsBaseDTO>
	* @throws Exception
	*/
	public List<GoodsBaseDTO> searchGoodsBase(QueryReqBean<GoodsBaseDTO> queryReqBean) throws Exception {
		List<GoodsBaseDTO> result = null;
		Muti1Bean<QueryReqBean<GoodsBaseDTO>> params = new Muti1Bean<QueryReqBean<GoodsBaseDTO>>();
		params.setDto1(queryReqBean);
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/goods/goodsbase/GoodsBaseRest/search/v1";
		ResponseMsg<List<GoodsBaseDTO>> responseMsg = RestClient.doPost(url, params,
				new GenericType<ResponseMsg<List<GoodsBaseDTO>>>() {
				});
		if (responseMsg.getRetCode().equals(ResponseStatus.HTTP_OK)) {
			result = responseMsg.getResponseBody();
		} else {
			logger.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new DaoException(responseMsg.getErrorDesc());
		}
		return result;
	}

	/**
	 * 通过主键查询单条记录
	 * @param id 主键id
	 * @return GoodsBaseDTO
	 * @throws Exception
	 */
	public GoodsBaseDTO queryGoodsBaseByPrimaryKey(String id) throws Exception {
		GoodsBaseDTO dto = null;
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/goods/goodsbase/GoodsBaseRest/get/v1/" + id;
		ResponseMsg<GoodsBaseDTO> responseMsg = RestClient.doGet(url, new GenericType<ResponseMsg<GoodsBaseDTO>>() {
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
	 * 通过主键查询单条GoodsVO记录
	 * @param id 主键id
	 * @return GoodsBaseDTO
	 * @throws Exception
	 */
	public GoodsVO queryGoodsVOByPrimaryKey(String id) throws Exception {
		GoodsVO dto = null;
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/goods/goodsbase/GoodsBaseRest/getVO/v1/" + id;
		ResponseMsg<GoodsVO> responseMsg = RestClient.doGet(url, new GenericType<ResponseMsg<GoodsVO>>() {
		
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
	 * 获取商品缩略图
	 * 
	 * @param id 商品ID
	 * @return
	 * @throws Exception
	 */

	public String getGoodsBaseThumb(String id) throws Exception {
		String thumb = null;
		
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/goods/goodsbase/GoodsBaseRest/getGoodsBaseThumb/v1/" + id;
		ResponseMsg<String> responseMsg = RestClient.doGet(url,new GenericType<ResponseMsg<String>>() {
		
		});
		
		if (responseMsg.getRetCode().equals(ResponseStatus.HTTP_OK)) {
			thumb = responseMsg.getResponseBody();
		} else {
			logger.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new DaoException(responseMsg.getErrorDesc());
		}
		return thumb;
	}

	/**
	 * 根据商品编码查询商品ID
	 * 
	 * @param code 商品编码
	 * @return
	 * @throws Exception
	 */
	public String getGoodsIdBaseByCode(String code) throws Exception {
		String thumb = null;
		
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/goods/goodsbase/GoodsBaseRest/getGoodsIdBaseByCode/v1";
		ResponseMsg<String> responseMsg = RestClient.doPost(url,code,new GenericType<ResponseMsg<String>>() {
		
		});
		
		if (responseMsg.getRetCode().equals(ResponseStatus.HTTP_OK)) {
			thumb = responseMsg.getResponseBody();
		} else {
			logger.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new DaoException(responseMsg.getErrorDesc());
		}
		return thumb;
	}
	
	/**
	 * 新增对象
	 * @param dto 保存对象
	 * @return String
	 * @throws Exception
	 */
	public String insertGoodsBase(GoodsBaseDTO dto) throws Exception {
		String id = null;
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/goods/goodsbase/GoodsBaseRest/save/v1";
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
	public int updateGoodsBaseSensitive(GoodsBaseDTO dto) throws Exception {
		int count = 0;
		String url = RestClientConfig.getRestHost(ServiceCode)
				+ "/api/demo/goods/goodsbase/GoodsBaseRest/updateSensitive/v1";
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
	 * 修改对象全部字段
	 * @param dto 修改对象
	 * @return int
	 * @throws Exception
	 */
	public int updateGoodsBase(GoodsBaseDTO dto) throws Exception {
		int count = 0;
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/goods/goodsbase/GoodsBaseRest/updateAll/v1";
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
	 * 按主键单条删除
	 * @param id 主键id
	 * @return int
	 * @throws Exception
	 */
	public int deleteGoodsBaseById(String id) throws Exception {
		int count = 0;
		String url = RestClientConfig.getRestHost(ServiceCode)
				+ "/api/demo/goods/goodsbase/GoodsBaseRest/deleteById/v1";
		ResponseMsg<Integer> responseMsg = RestClient.doPost(url, id, new GenericType<ResponseMsg<Integer>>() {
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
	public int deleteGoodsBaseByIds(String[] ids) throws Exception {
		int count = 0;
		String url = RestClientConfig.getRestHost(ServiceCode)
				+ "/api/demo/goods/goodsbase/GoodsBaseRest/deleteByIds/v1";
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

}
