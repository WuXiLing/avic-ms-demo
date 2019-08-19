package avicit.demo.orders.orders.api;

import javax.ws.rs.core.GenericType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import avicit.demo.orders.orders.dto.OrdersDTO;
import avicit.platform6.api.sysautocode.SysAutoCodeAPI;
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
 * @创建时间： 2019-06-01 11:23
 * @类说明：请填写
 * @修改记录： 
 */
@Service
public class OrdersApi {

	private static final Logger logger = LoggerFactory.getLogger(OrdersApi.class);

	/** 订阅服务的服务编码  */
	private static final String ServiceCode = "Orders";
	
	SysAutoCodeAPI sysAutoCodeApi;

	/**
	 * 按条件分页查询
	 * @param queryReqBean 分页
	 * @param orderBy 排序
	 * @return QueryRespBean<OrdersDTO>
	 * @throws Exception
	 */
	public QueryRespBean<OrdersDTO> searchOrdersByPage(QueryReqBean<OrdersDTO> queryReqBean, String orderBy)
			throws Exception {
		QueryRespBean<OrdersDTO> result = null;
		Muti2Bean<QueryReqBean<OrdersDTO>, String> params = new Muti2Bean<QueryReqBean<OrdersDTO>, String>();
		params.setDto1(queryReqBean);
		params.setDto2(orderBy);
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/orders/orders/OrdersRest/searchByPage/v1";
		ResponseMsg<QueryRespBean<OrdersDTO>> responseMsg = RestClient.doPost(url, params,
				new GenericType<ResponseMsg<QueryRespBean<OrdersDTO>>>() {
				});
		if (responseMsg.getRetCode().equals(ResponseStatus.HTTP_OK)) {
			result = responseMsg.getResponseBody();
		} else {
			logger.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new DaoException(responseMsg.getErrorDesc());
		}
		
//		sysAutoCodeApi.generateAutoCodeValue(arg0, arg1, arg2, arg3, arg4)
		return result;
	}

	/**
	 * 按条件or查询的分页查询
	 * @param queryReqBean 分页 
	 * @param orderBy 排序
	 * @return QueryRespBean<OrdersDTO>
	 * @throws Exception
	 */
	public QueryRespBean<OrdersDTO> searchOrdersByPageOr(QueryReqBean<OrdersDTO> queryReqBean, String orderBy)
			throws Exception {
		QueryRespBean<OrdersDTO> result = null;
		Muti2Bean<QueryReqBean<OrdersDTO>, String> params = new Muti2Bean<QueryReqBean<OrdersDTO>, String>();
		params.setDto1(queryReqBean);
		params.setDto2(orderBy);
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/orders/orders/OrdersRest/searchOrByPage/v1";
		ResponseMsg<QueryRespBean<OrdersDTO>> responseMsg = RestClient.doPost(url, params,
				new GenericType<ResponseMsg<QueryRespBean<OrdersDTO>>>() {
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
	 * @return OrdersDTO
	 * @throws Exception
	 */
	public OrdersDTO queryOrdersByPrimaryKey(String id) throws Exception {
		OrdersDTO dto = null;
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/orders/orders/OrdersRest/get/v1/" + id;
		ResponseMsg<OrdersDTO> responseMsg = RestClient.doGet(url, new GenericType<ResponseMsg<OrdersDTO>>() {
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
	public String insertOrders(OrdersDTO dto) throws Exception {
		String id = null;
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/orders/orders/OrdersRest/save/v1";
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
	public int updateOrdersSensitive(OrdersDTO dto) throws Exception {
		int count = 0;
		String url = RestClientConfig.getRestHost(ServiceCode)
				+ "/api/demo/orders/orders/OrdersRest/updateSensitive/v1";
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
	public int deleteOrdersByIds(String[] ids) throws Exception {
		int count = 0;
		String url = RestClientConfig.getRestHost(ServiceCode) + "/api/demo/orders/orders/OrdersRest/deleteByIds/v1";
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
