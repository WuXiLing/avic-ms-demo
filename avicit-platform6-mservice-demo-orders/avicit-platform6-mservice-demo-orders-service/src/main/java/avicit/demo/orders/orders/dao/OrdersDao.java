package avicit.demo.orders.orders.dao;

import java.util.List;
import java.util.Map;

import avicit.platform6.core.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.demo.orders.orders.dto.OrdersDTO;

/**
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：avicitdev@avicit.com
 * @创建时间： 2019-06-01 11:23
 * @类说明：请填写
 * @修改记录： 
 */
@MyBatisRepository
public interface OrdersDao {

	/**
	* 分页查询  订单信息
	* @param ordersDTO 查询对象
	* @param orderBy 排序条件
	* @return Page<OrdersDTO>
	*/
	public Page<OrdersDTO> searchOrdersByPage(@Param("bean") OrdersDTO ordersDTO, @Param("pOrderBy") String orderBy);

	/**
	* 按or条件的分页查询 订单信息
	* @param ordersDTO 查询对象
	* @param orderBy 排序条件
	* @return Page<OrdersDTO>
	*/
	public Page<OrdersDTO> searchOrdersByPageOr(@Param("bean") OrdersDTO ordersDTO, @Param("pOrderBy") String orderBy);

	/**
	* 查询订单信息
	* @param ordersDTO 查询对象
	* @return List<OrdersDTO>
	*/
	public List<OrdersDTO> searchOrders(OrdersDTO ordersDTO);

	/**
	 * 查询 订单信息
	 * @param id 主键id
	 * @return OrdersDTO
	 */
	public OrdersDTO findOrdersById(String id);

	/**
	* 新增订单信息
	* @param ordersDTO 保存对象
	* @return int
	*/
	public int insertOrders(OrdersDTO ordersDTO);

	/**
	 * 批量新增 订单信息
	 * @param ordersDTOList 保存对象集合
	 * @return int
	 */
	public int insertOrdersList(List<OrdersDTO> ordersDTOList);

	/**
	 * 更新部分对象 订单信息
	 * @param ordersDTO 更新对象
	 * @return int
	 */
	public int updateOrdersSensitive(OrdersDTO ordersDTO);

	/**
	 * 更新全部对象 订单信息
	 * @param ordersDTO 更新对象
	 * @return int
	 */
	public int updateOrdersAll(OrdersDTO ordersDTO);

	/**
	 * 批量更新对象 订单信息
	 * @param dtoList 批量更新对象集合
	 * @return int
	 */
	public int updateOrdersList(@Param("dtoList") List<OrdersDTO> dtoList);

	/**
	 * 按主键删除 订单信息
	 * @param id 主键id
	 * @return int
	 */
	public int deleteOrdersById(String id);

	/**
	 * 按主键批量删除 订单信息
	 * @param idList 主键集合
	 * @return int
	 */
	public int deleteOrdersList(List<String> idList);

	/**
	 * 更新订单状态
	 * 
	 * @param id 订单主键
	 * @param status  订单状态
	 */
	public int orderStatusUpdate(@Param("id")String id, @Param("status")Integer status);

	/**
	 * 订单支付
	 * 
	 * @param id 订单主键
	 * @return
	 */
	public int ordersPay(String id);
	
	/**
	 * 订单取消
	 * 
	 * @param id 订单主键
	 * @return
	 */
	public int ordersCanel(String id);
	
	/**
	 * 统计订单总数
	 * 
	 * @param params
	 * @return
	 */
	public Map<String,String> totalOrders(@Param("bean")Map<String,Object> params);
}
