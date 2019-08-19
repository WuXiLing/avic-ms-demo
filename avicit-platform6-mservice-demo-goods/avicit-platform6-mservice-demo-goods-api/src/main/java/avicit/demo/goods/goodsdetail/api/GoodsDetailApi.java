package avicit.demo.goods.goodsdetail.api;

import javax.ws.rs.core.GenericType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import avicit.demo.goods.goodsdetail.dto.GoodsDetailDTO;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.rest.client.RestClient;
import avicit.platform6.core.rest.client.RestClientConfig;
import avicit.platform6.core.rest.msg.Muti2Bean;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.core.rest.msg.ResponseMsg;
import avicit.platform6.core.rest.msg.ResponseStatus;

/**
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：avicitdev@avicit.com
 * @创建时间： 2019-06-01 10:27
 * @类说明：请填写
 * @修改记录： 
 */
@Service
public class GoodsDetailApi {

	private static final Logger logger = LoggerFactory.getLogger(GoodsDetailApi.class);

	/** 订阅服务的服务编码  */
	private static final String ServiceCode = "GoodsDetail";

	/**
	 * 按条件分页查询
	 * @param queryReqBean 分页
	 * @param orderBy 排序
	 * @return QueryRespBean<GoodsDetailDTO>
	 * @throws Exception
	 */
	public QueryRespBean<GoodsDetailDTO> searchGoodsDetailByPage(QueryReqBean<GoodsDetailDTO> queryReqBean,
			String orderBy) throws Exception {
		QueryRespBean<GoodsDetailDTO> result = null;
		Muti2Bean<QueryReqBean<GoodsDetailDTO>, String> params = new Muti2Bean<QueryReqBean<GoodsDetailDTO>, String>();
		params.setDto1(queryReqBean);
		params.setDto2(orderBy);
		String url = RestClientConfig.getRestHost(ServiceCode)
				+ "/api/demo/goods/goodsdetail/GoodsDetailRest/searchByPage/v1";
		ResponseMsg<QueryRespBean<GoodsDetailDTO>> responseMsg = RestClient.doPost(url, params,
				new GenericType<ResponseMsg<QueryRespBean<GoodsDetailDTO>>>() {
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
	 * @return QueryRespBean<GoodsDetailDTO>
	 * @throws Exception
	 */
	public QueryRespBean<GoodsDetailDTO> searchGoodsDetailByPageOr(QueryReqBean<GoodsDetailDTO> queryReqBean,
			String orderBy) throws Exception {
		QueryRespBean<GoodsDetailDTO> result = null;
		Muti2Bean<QueryReqBean<GoodsDetailDTO>, String> params = new Muti2Bean<QueryReqBean<GoodsDetailDTO>, String>();
		params.setDto1(queryReqBean);
		params.setDto2(orderBy);
		String url = RestClientConfig.getRestHost(ServiceCode)
				+ "/api/demo/goods/goodsdetail/GoodsDetailRest/searchOrByPage/v1";
		ResponseMsg<QueryRespBean<GoodsDetailDTO>> responseMsg = RestClient.doPost(url, params,
				new GenericType<ResponseMsg<QueryRespBean<GoodsDetailDTO>>>() {
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
	 * @return GoodsDetailDTO
	 * @throws Exception
	 */
	public GoodsDetailDTO queryGoodsDetailByPrimaryKey(String id) throws Exception {
		GoodsDetailDTO dto = null;
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/goods/goodsdetail/GoodsDetailRest/get/v1/"
				+ id;
		ResponseMsg<GoodsDetailDTO> responseMsg = RestClient.doGet(url, new GenericType<ResponseMsg<GoodsDetailDTO>>() {
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
	public String insertGoodsDetail(GoodsDetailDTO dto) throws Exception {
		String id = null;
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/goods/goodsdetail/GoodsDetailRest/save/v1";
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
	public int updateGoodsDetailSensitive(GoodsDetailDTO dto) throws Exception {
		int count = 0;
		String url = RestClientConfig.getRestHost(ServiceCode)
				+ "/api/demo/goods/goodsdetail/GoodsDetailRest/updateSensitive/v1";
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
	public int deleteGoodsDetailByIds(String[] ids) throws Exception {
		int count = 0;
		String url = RestClientConfig.getRestHost(ServiceCode)
				+ "/api/demo/goods/goodsdetail/GoodsDetailRest/deleteByIds/v1";
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
