package avicit.demo.orders.orders.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import avicit.demo.goods.goodsbase.api.GoodsBaseApi;
import avicit.demo.goods.goodsbase.dto.GoodsVO;
import avicit.demo.orders.orders.dao.OrdersDao;
import avicit.demo.orders.orders.dto.OrdersDTO;
import avicit.demo.stock.stock.api.StockApi;
import avicit.platform6.api.sysautocode.SysAutoCodeAPI;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.PojoUtil;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.properties.PlatformConstant.OpType;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;

/**
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：avicitdev@avicit.com @创建时间： 2019-06-01 11:23
 * @类说明：请填写 @修改记录：
 */
@Service
public class OrdersService implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(OrdersService.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	private OrdersDao ordersDao;

	@Autowired
	private StockApi stockApi;

	@Autowired
	private GoodsBaseApi goodsBaseApi;

	@Autowired
	private SysAutoCodeAPI sysAutoCodeAPI;

	/**
	 * 按条件分页查询
	 * 
	 * @param queryReqBean 分页
	 * @param orderBy      排序
	 * @return QueryRespBean<OrdersDTO>
	 * @throws Exception
	 */
	@HystrixCommand(fallbackMethod = "searchOrdersByPageFallback")
	public QueryRespBean<OrdersDTO> searchOrdersByPage(QueryReqBean<OrdersDTO> queryReqBean, String orderBy)
			throws Exception {
		logger.info("查询订单列表");
		try {
			PageHelper.startPage(queryReqBean.getPageParameter());
			OrdersDTO searchParams = queryReqBean.getSearchParams();
			Page<OrdersDTO> dataList = ordersDao.searchOrdersByPage(searchParams, orderBy);
			QueryRespBean<OrdersDTO> queryRespBean = new QueryRespBean<OrdersDTO>();

			queryRespBean.setResult(dataList);
			return queryRespBean;
		} catch (Exception e) {
			logger.error("searchOrdersByPage出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	public QueryRespBean<OrdersDTO> searchOrdersByPageFallback(QueryReqBean<OrdersDTO> queryReqBean, String orderBy) {
		logger.info("查询订单列表降级");
		QueryRespBean<OrdersDTO> queryRespBean = new QueryRespBean<OrdersDTO>();
		return queryRespBean;
	}

	/**
	 * 按条件or查询的分页查询
	 * 
	 * @param queryReqBean 分页
	 * @param orderBy      排序
	 * @return QueryRespBean<OrdersDTO>
	 * @throws Exception
	 */
	@HystrixCommand(fallbackMethod = "searchOrdersByPageOrFallback")
	public QueryRespBean<OrdersDTO> searchOrdersByPageOr(QueryReqBean<OrdersDTO> queryReqBean, String orderBy)
			throws Exception {
		logger.info("查询订单列表");
		try {
			PageHelper.startPage(queryReqBean.getPageParameter());
			OrdersDTO searchParams = queryReqBean.getSearchParams();
			Page<OrdersDTO> dataList = ordersDao.searchOrdersByPageOr(searchParams, orderBy);
			QueryRespBean<OrdersDTO> queryRespBean = new QueryRespBean<OrdersDTO>();

			queryRespBean.setResult(dataList);
			return queryRespBean;
		} catch (Exception e) {
			logger.error("searchOrdersByPage出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	public QueryRespBean<OrdersDTO> searchOrdersByPageOrFallback(QueryReqBean<OrdersDTO> queryReqBean, String orderBy) {
		logger.info("查询订单列表降级");
		QueryRespBean<OrdersDTO> queryRespBean = new QueryRespBean<OrdersDTO>();
		return queryRespBean;
	}

	/**
	 * 按条件查询
	 * 
	 * @param queryReqBean 条件
	 * @return List<OrdersDTO>
	 * @throws Exception
	 */
	public List<OrdersDTO> searchOrders(QueryReqBean<OrdersDTO> queryReqBean) throws Exception {
		try {
			OrdersDTO searchParams = queryReqBean.getSearchParams();
			List<OrdersDTO> dataList = ordersDao.searchOrders(searchParams);
			return dataList;
		} catch (Exception e) {
			logger.error("searchOrders出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 通过主键查询单条记录
	 * 
	 * @param id 主键id
	 * @return OrdersDTO
	 * @throws Exception
	 */
	public OrdersDTO queryOrdersByPrimaryKey(String id) throws Exception {
		logger.debug("查询订单：" + id);
		try {
			OrdersDTO dto = ordersDao.findOrdersById(id);
			// 记录日志
			if (dto != null) {
				SysLogUtil.log4Query(dto);
				dto.setThumb(getGoodsThumbByGoodsId(dto.getGoodsId()));
			}
			return dto;
		} catch (Exception e) {
			logger.error("queryOrdersByPrimaryKey出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 获取商品图片
	 * 
	 * @param goodsId 商品ID
	 * @return
	 * @throws Exception
	 */
	public String getGoodsThumbByGoodsId(String goodsId) throws Exception {
		logger.debug("查询图片：" + goodsId);
		return goodsBaseApi.getGoodsBaseThumb(goodsId);
	}

	/**
	 * 新增对象
	 * 
	 * @param dto 保存对象
	 * @return String
	 * @throws Exception
	 */
	public String insertOrders(OrdersDTO dto) throws Exception {
		logger.debug("新增订单：" + dto.getGoodsName());
		try {
			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			// 减去库存
			int count = stockApi.updateStockNum(dto.getGoodsId(), 1);
			if (count == 0) {
				logger.error("库存不足：" + dto.getGoodsId());
				throw new DaoException("库存不足。");
			}
			if (StringUtils.isNotBlank(dto.getGoodsId())) {
				GoodsVO goods = goodsBaseApi.queryGoodsVOByPrimaryKey(dto.getGoodsId());
				dto.setGoodsPrice(goods.getPrice());
				dto.setGoodsSize(goods.getGoodsSize()[0]);
				dto.setGoodsColor(goods.getColor()[0]);
				dto.setGoodsName(goods.getName());
			}
			dto.setBuyerId(dto.getBuyerId());
			dto.setBuyerName(dto.getBuyerName());

			Map<String, String> pa = sysAutoCodeAPI.autoGenerationCodeValue("", "ORDERS", id, true, null);
			dto.setCode(pa.get("autoCodeValue"));
			ordersDao.insertOrders(dto);

			// 记录日志
			if (dto != null) {
				SysLogUtil.log4Insert(dto);
			}
			return id;
		} catch (Exception e) {
			logger.error("insertOrders出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 批量新增对象
	 * 
	 * @param dtoList 保存对象集合
	 * @return int
	 * @throws Exception
	 */
	public int insertOrdersList(List<OrdersDTO> dtoList) throws Exception {
		List<OrdersDTO> beanList = new ArrayList<OrdersDTO>();
		for (OrdersDTO dto : dtoList) {
			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			// 记录日志
			if (dto != null) {
				SysLogUtil.log4Insert(dto);
			}
			beanList.add(dto);
		}
		try {
			return ordersDao.insertOrdersList(beanList);
		} catch (Exception e) {
			logger.error("insertOrdersList出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 修改对象全部字段
	 * 
	 * @param dto 修改对象
	 * @return int
	 * @throws Exception
	 */
	public int updateOrders(OrdersDTO dto) throws Exception {
		// 记录日志
		OrdersDTO old = findById(dto.getId());
		if (old != null) {
			SysLogUtil.log4Update(dto, old);
		}
		PojoUtil.setSysProperties(dto, OpType.update);
		PojoUtil.copyProperties(old, dto, true);
		int ret = ordersDao.updateOrdersAll(old);
		if (ret == 0) {
			throw new DaoException("数据失效，请重新更新");
		}
		return ret;
	}

	/**
	 * 修改对象部分字段
	 * 
	 * @param dto 修改对象
	 * @return int
	 * @throws Exception
	 */
	public int updateOrdersSensitive(OrdersDTO dto) throws Exception {
		try {
			// 记录日志
			OrdersDTO old = findById(dto.getId());
			if (old != null) {
				SysLogUtil.log4Update(dto, old);
			}
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto, true);
			int count = ordersDao.updateOrdersSensitive(old);
			if (count == 0) {
				throw new DaoException("数据失效，请重新更新");
			}
			return count;
		} catch (Exception e) {
			logger.error("updateOrdersSensitive出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 批量更新对象
	 * 
	 * @param dtoList 修改对象集合
	 * @return int
	 * @throws Exception
	 */
	public int updateOrdersList(List<OrdersDTO> dtoList) throws Exception {
		try {
			return ordersDao.updateOrdersList(dtoList);
		} catch (Exception e) {
			logger.error("updateOrdersList出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}

	}

	/**
	 * 按主键单条删除
	 * 
	 * @param id 主键id
	 * @return int
	 * @throws Exception
	 */
	public int deleteOrdersById(String id) throws Exception {
		if (StringUtils.isEmpty(id)) {
			throw new Exception("删除失败！传入的参数主键为null");
		}
		logger.debug("删除订单：" + id);
		try {
			// 记录日志
			OrdersDTO obje = findById(id);
			if (obje != null) {
				SysLogUtil.log4Delete(obje);
			}
			return ordersDao.deleteOrdersById(id);
		} catch (Exception e) {
			logger.error("deleteOrdersById出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 批量删除数据
	 * 
	 * @param ids id的数组
	 * @return int
	 * @throws Exception
	 */
	public int deleteOrdersByIds(String[] ids) throws Exception {
		int result = 0;
		for (String id : ids) {
			deleteOrdersById(id);
			result++;
		}
		return result;
	}

	/**
	 * 批量删除数据2
	 * 
	 * @param idList 主键集合
	 * @return int
	 * @throws Exception
	 */
	public int deleteOrdersList(List<String> idList) throws Exception {
		try {
			return ordersDao.deleteOrdersList(idList);
		} catch (Exception e) {
			logger.error("deleteOrdersList出错：", e);
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 日志专用，内部方法，不再记录日志
	 * 
	 * @param id 主键id
	 * @return OrdersDTO
	 * @throws Exception
	 */
	private OrdersDTO findById(String id) throws Exception {
		try {
			OrdersDTO dto = ordersDao.findOrdersById(id);
			// 记录日志
			if (dto != null) {
				SysLogUtil.log4Query(dto);
			}
			return dto;
		} catch (Exception e) {
			logger.error("findById出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 订单状态信息更新
	 * 
	 * @param id     订单ID
	 * @param status 订单状态
	 * @return
	 */
	public Integer orderStatusUpdate(String id, Integer status) {
		int count = ordersDao.orderStatusUpdate(id, status);
		return count;
	}

	/**
	 * 订单支付
	 * 
	 * @param id 订单ID
	 * @return
	 */
	public Integer ordersPay(String id) {
		logger.debug("支付订单：" + id);
		OrdersDTO dto = ordersDao.findOrdersById(id);
		// 记录日志
		if (dto != null) {
			SysLogUtil.log4Query(dto);
		}

		int count = ordersDao.ordersPay(id);
		return count;
	}

	/**
	 * 订单取消
	 * 
	 * @param id 订单ID
	 * @return
	 */
	public Integer ordersCanel(String id) {
		logger.debug("取消订单：" + id);
		int count = ordersDao.ordersCanel(id);
		return count;
	}

	/**
	 * 重下订单
	 * 
	 * @param id 订单ID
	 * @return
	 */
	public int ordersRenewal(String id) {
		logger.debug("重新订单：" + id);
		return ordersDao.orderStatusUpdate(id, 0);
	}
}
