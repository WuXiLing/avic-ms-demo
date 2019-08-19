package avicit.demo.orders.orders.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import avicit.demo.orders.orders.dto.OrdersDTO;
import avicit.demo.orders.orders.service.OrdersService;
import avicit.platform6.core.rest.msg.PageParameter;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.core.rest.msg.ResponseMsg;
import avicit.platform6.core.rest.resteasy.RestEasyController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 订单rest接口
 * 
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：avicitdev@avicit.com
 * @创建时间： 2019-06-01 11:23
 * @类说明：请填写
 * @修改记录： 
 */
@RestEasyController
@Path("/api/demo/orders/orders/OrdersRest")

@RequestMapping("/api/demo/orders/orders/OrdersRest")
@Api(tags = { "Orders,orders" }, description = "订单信息")
public class OrdersRest {

	@Autowired
	private OrdersService ordersService;
	
	/**
	 * 获取列表数据
	 * 
	 * @param pageParameter
	 *            分页对象
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/searchByPage/v1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/searchByPage/v1", method = RequestMethod.POST)
	@ApiOperation(value = "订单信息分页列表查询", notes = "订单信息查询", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	@ApiImplicitParams(
			{
				@ApiImplicitParam(name = "分页信息", value = "pageParameter", dataTypeClass = PageParameter.class, required = true),
				@ApiImplicitParam(name = "参数信息", value = "searchParams", dataType = "map", required = true),
				@ApiImplicitParam(name = "排序字段", value = "sfnConditions", dataType = "string", required = true)
			}
		)
	public ResponseMsg<Map<String, Object>> getOrdersByPage(@ApiIgnore QueryReqBean<OrdersDTO> queryReqBean,@ApiIgnore String sfnConditions) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();

		String orderBy = "";
		QueryRespBean<OrdersDTO> result = null;
		result = ordersService.searchOrdersByPage(queryReqBean, orderBy);
		List<OrdersDTO> orderss = new ArrayList<OrdersDTO>();
		for (OrdersDTO dto : result.getResult()) {
			orderss.add(dto);
		}

		map.put("records", result.getPageParameter().getTotalCount());// 总记录数
		map.put("page", result.getPageParameter().getPage()); // 当前页
		map.put("total", result.getPageParameter().getTotalPage()); // 总页数
		map.put("rows", orderss); // 数据

		ResponseMsg<Map<String, Object>> responseMsg = new ResponseMsg<Map<String, Object>>();
		responseMsg.setResponseBody(map);
		return responseMsg;
	}
	
	@POST
	@Path("/searchMobileByPage/v1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/searchMobileByPage/v1", method = RequestMethod.POST)
	@ApiOperation(value = "移动端订单信息分页列表查询", notes = "订单信息查询", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<Map<String, Object>> getMobileOrdersByPage(QueryReqBean<OrdersDTO> queryReqBean,String sfnConditions) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();

		String orderBy = "";
		QueryRespBean<OrdersDTO> result = null;
		result = ordersService.searchOrdersByPage(queryReqBean, orderBy);
		List<OrdersDTO> orderss = new ArrayList<OrdersDTO>();
		for (OrdersDTO dto : result.getResult()) {
			dto.setThumb(ordersService.getGoodsThumbByGoodsId(dto.getGoodsId()));
			orderss.add(dto);
		}

		map.put("records", result.getPageParameter().getTotalCount());// 总记录数
		map.put("page", result.getPageParameter().getPage()); // 当前页
		map.put("total", result.getPageParameter().getTotalPage()); // 总页数
		map.put("rows", orderss); // 数据

		ResponseMsg<Map<String, Object>> responseMsg = new ResponseMsg<Map<String, Object>>();
		responseMsg.setResponseBody(map);
		return responseMsg;
	}

	/**
	 * 通过主键查询单条记录
	 * @param id 主键id
	 * @return ResponseMsg<OrdersDTO>
	 * @throws Exception
	 */
	@GET
	@Path("/get/v1/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/get/v1/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "根据主键查询单条订单信息", notes = "单条订单信息查询", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseMsg<OrdersDTO> get(@ApiParam(value = "主键", name = "id", required = true) @PathVariable @PathParam("id") String id) throws Exception {
		ResponseMsg<OrdersDTO> responseMsg = new ResponseMsg<OrdersDTO>();
		OrdersDTO dto = null;
		if(StringUtils.isNotBlank(id)){
			dto = ordersService.queryOrdersByPrimaryKey(id);
		}
		responseMsg.setResponseBody(dto);
		return responseMsg;
	}

	/**
	 * 新增对象
	 * @param dto 保存对象
	 * @return ResponseMsg<String>
	 * @throws Exception
	 */
	@POST
	@Path("/save/v1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseMsg<String> save(OrdersDTO dto) throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<String>();
		String id = "";
		if(dto != null){
			id = ordersService.insertOrders(dto);
		}
		responseMsg.setResponseBody(id);
		return responseMsg;
	}
	
	/**
	 * 订单生成<br>
	 * <pre>
	 * 生成订单后减去库存
	 * </pre>
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/orderGeneration/v1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/orderGeneration/v1", method = RequestMethod.POST)
	@ApiOperation(value = "订单生成", notes = "订单生成", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	@ApiImplicitParams(
			{
				@ApiImplicitParam(name = "商品基本信息主键", value = "goodsId", dataType = "string", required = true),
				@ApiImplicitParam(name = "购买人ID", value = "buyerId", dataType = "string", required = true),
				@ApiImplicitParam(name = "购买人名称", value = "buyerName", dataType = "string", required = true)
			}
		)
	public ResponseMsg<String> orderGeneration(@ApiIgnore OrdersDTO dto) throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<String>();
		String id = "";
		if(dto != null){
			dto.setStatus("0");
			try {
				id = ordersService.insertOrders(dto);
			} catch(Exception e) {
				responseMsg.setErrorDesc(e.getMessage());
			}
		}
		responseMsg.setResponseBody(id);
		return responseMsg;
	}
	
	/**
	 * 错误订单生成
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/errorOrderGeneration/v1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/errorOrderGeneration/v1", method = RequestMethod.POST)
	@ApiOperation(value = "错误订单生成", notes = "错误订单生成", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	@ApiImplicitParams(
		{
			@ApiImplicitParam(name = "订单主键", value = "id", dataType = "string", required = true),
			@ApiImplicitParam(name = "商品基本信息主键", value = "goodsId", dataType = "string", required = true),
			@ApiImplicitParam(name = "购买人ID", value = "buyerId", dataType = "string", required = true),
			@ApiImplicitParam(name = "购买人名称", value = "buyerName", dataType = "string", required = true)
		}
	)
	public ResponseMsg<String> errorOrderGeneration(@ApiIgnore OrdersDTO dto) throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<String>();
		String id = "";
		if(dto != null){
			dto.setStatus("1");
			id = ordersService.insertOrders(dto);
		}
		responseMsg.setResponseBody(id);
		return responseMsg;
	}
	
	/**
	 * 订单状态信息更新
	 * 
	 * @param id 订单ID
	 * @param status 订单状态
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/orderStatusUpdate/v1/{id}/{status}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/orderStatusUpdate/v1/{id}/{status}", method = RequestMethod.POST)
	@ApiOperation(value = "更新订单状态", notes = "更新订单状态", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<Integer> orderStatusUpdate(@ApiParam(value = "主键", name = "id", required = true) @PathVariable("id") @PathParam("id") String id,@ApiParam(value = "订单状态", name = "status", required = true) @PathVariable("status") @PathParam("status") Integer status) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0;
		if(StringUtils.isNotBlank(id) && status != null){
			count = ordersService.orderStatusUpdate(id,status);
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}
	
	/**
	 * 订单支付
	 * 
	 * @param id 订单ID
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/ordersPay/v1/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/ordersPay/v1/{id}", method = RequestMethod.POST)
	@ApiOperation(value = "订单支付", notes = "订单支付(修改订单状态)", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<Integer> ordersPay(@ApiParam(value = "主键", name = "id", required = true) @PathVariable("id") @PathParam("id") String id) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0;
		if(StringUtils.isNotBlank(id)){
			count = ordersService.ordersPay(id);
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}
	
	/**
	 * 订单重新生成
	 * 
	 * @param id 订单ID
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/ordersRenewal/v1/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/ordersRenewal/v1/{id}", method = RequestMethod.POST)
	@ApiOperation(value = "订单重新生成", notes = "订单重新生成", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<Integer> ordersRenewal(@ApiParam(value = "主键", name = "id", required = true) @PathVariable("id") @PathParam("id") String id) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0;
		if(StringUtils.isNotBlank(id)){
			count = ordersService.ordersRenewal(id);
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}
	
	/**
	 * 订单取消
	 * 
	 * @param id 订单ID
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/ordersCanel/v1/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/ordersCanel/v1/{id}", method = RequestMethod.POST)
	@ApiOperation(value = "订单取消", notes = "订单取消", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<Integer> ordersCanel(@ApiParam(value = "主键", name = "id", required = true) @PathVariable("id") @PathParam("id") String id) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0;
		if(StringUtils.isNotBlank(id)){
			count = ordersService.ordersCanel(id);
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}

	/**
	 * 按主键单条删除
	 * @param id 主键id
	 * @return ResponseMsg<Integer>
	 * @throws Exception
	 */
	@POST
	@Path("/deleteById/v1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/deleteById/v1", method = RequestMethod.POST)
	@ApiOperation(value = "订单删除", notes = "订单删除", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<Integer> deleteById(@ApiParam(name = "订单信息主键", value = "id", required = false) @QueryParam("id") String id) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0;
		if(StringUtils.isNotBlank(id)){
			ordersService.deleteOrdersById(id);
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}

	/**
	 * 按主键数组多条删除
	 * @param ids 主键id的数组
	 * @return ResponseMsg<Integer>
	 * @throws Exception
	 */
	@POST
	@Path("/deleteByIds/v1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
//	@RequestMapping(value = "/deleteByIds/v1", method = RequestMethod.POST)
//	@ApiOperation(value = "删除", notes = "订单信息批量删除", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<Integer> deleteByIds(@ApiParam(name = "订单信息主键字符串，多个主键用','隔开", value = "ids", required = false) @RequestParam("ids") @QueryParam("ids") String ids) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0;
		if(StringUtils.isNotBlank(ids)){
			count = ordersService.deleteOrdersByIds(ids.split(","));
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}
}
