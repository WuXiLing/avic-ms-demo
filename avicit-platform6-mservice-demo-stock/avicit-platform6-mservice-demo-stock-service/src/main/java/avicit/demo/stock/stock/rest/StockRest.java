package avicit.demo.stock.stock.rest;

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

import avicit.demo.goods.goodsbase.api.GoodsBaseApi;
import avicit.demo.stock.stock.dto.StockDTO;
import avicit.demo.stock.stock.service.StockService;
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
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：avicitdev@avicit.com 
 * @创建时间： 2019-06-01 11:21
 * @类说明：请填写 
 * @修改记录：
 */
@RestEasyController
@Path("/api/demo/stock/stock/StockRest")

@RequestMapping("/api/demo/stock/stock/StockRest")
@Api(tags = { "Stocks,stocks" }, description = "库存信息")
public class StockRest {

	/**库存service */
	@Autowired
	private StockService stockService;

	/**商品API */
	@Autowired
	private GoodsBaseApi goodsBaseApi;

	/**
	 * 按条件分页查询
	 * 
	 * @param params
	 *            参数 --queryReqBean 查询条件 --orderBy 排序
	 * @return ResponseMsg<QueryRespBean<StockDTO>>
	 * @throws Exception
	 */
	@POST
	@Path("/searchByPage/v1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/searchByPage/v1", method = RequestMethod.POST)
	@ApiOperation(value = "库存信息分页列表查询", notes = "库存信息分页列表查询", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	@ApiImplicitParams(
			{
				@ApiImplicitParam(name = "分页信息", value = "pageParameter", dataTypeClass = PageParameter.class, required = true),
				@ApiImplicitParam(name = "参数信息", value = "searchParams", dataType = "map", required = true),
				@ApiImplicitParam(name = "排序字段", value = "sfnConditions", dataType = "string", required = true)
			}
		)
	public ResponseMsg<Map<String, Object>> searchByPage(@ApiIgnore QueryReqBean<StockDTO> queryReqBean,@ApiIgnore String sfnConditions) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String orderBy = "";
		QueryRespBean<StockDTO> result = null;
		
		result = stockService.searchStockByPage(queryReqBean, orderBy);
		List<StockDTO> gvos = new ArrayList<StockDTO>();
		if (result.getResult() != null && !result.getResult().isEmpty()) {
			for (StockDTO dto : result.getResult()) {
				dto.setGoodsBaseDTO(goodsBaseApi.queryGoodsBaseByPrimaryKey(dto.getGoodsId()));
				gvos.add(dto);
			}
		}

		map.put("records", result.getPageParameter().getTotalCount());// 总记录数
		map.put("page", result.getPageParameter().getPage()); // 当前页
		map.put("total", result.getPageParameter().getTotalPage()); // 总页数
		map.put("rows", gvos); // 数据

		ResponseMsg<Map<String, Object>> responseMsg = new ResponseMsg<Map<String, Object>>();
		responseMsg.setResponseBody(map);
		return responseMsg;
	}

	/**
	 * 通过主键查询单条记录
	 * 
	 * @param id
	 *            主键id
	 * @return ResponseMsg<StockDTO>
	 * @throws Exception
	 */
	@GET
	@Path("/get/v1/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/get/v1/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "单条库存信息查询", notes = "单条库存信息查询", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<StockDTO> get(@ApiParam(value = "主键", name = "id", required = true) @PathVariable @PathParam("id") String id) throws Exception {
		ResponseMsg<StockDTO> responseMsg = new ResponseMsg<StockDTO>();
		StockDTO dto = null;
		if(StringUtils.isNotBlank(id)){
			dto = stockService.queryStockByPrimaryKey(id);
			dto.setGoodsBaseDTO(goodsBaseApi.queryGoodsBaseByPrimaryKey(dto.getGoodsId()));
		}
		responseMsg.setResponseBody(dto);
		return responseMsg;
	}

	/**
	 * 新增对象
	 * 
	 * @param dto
	 *            保存对象
	 * @return ResponseMsg<String>
	 * @throws Exception
	 */
	@POST
	@Path("/save/v1/{goodsId}/{num}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/save/v1/{goodsId}/{num}", method = RequestMethod.POST)
	@ApiOperation(value = "保存库存信息", notes = "保存库存信息", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<String> save(@ApiParam(value = "商品基本信息ID主键", name = "goodsId", required = true) @PathVariable @PathParam("goodsId") String goodsId,@ApiParam(value = "库存数量", name = "num", required = true) @PathVariable @PathParam("num")Long num) throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<String>();
		String id = "";
		if(StringUtils.isNotBlank(goodsId) && num != null){
			
			List<StockDTO> stockList = this.stockService.searchStockByGoodsId(goodsId);
			if(stockList!=null && !stockList.isEmpty()){
				id = stockList.get(0).getId();
				stockService.updateStock(stockList.get(0).getId(), num);
			} else {
				StockDTO dto = new StockDTO();
				dto.setGoodsId(goodsId);
				dto.setNum(num);
				id = stockService.insertStock(dto);
			}
		}
		responseMsg.setResponseBody(id);
		return responseMsg;
	}

	/**
	 * 修改部分对象字段
	 * 
	 * @param dto
	 *            修改对象
	 * @return ResponseMsg<Integer>
	 * @throws Exception
	 */
	@POST
	@Path("/updateStock/v1/{id}/{num}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/updateStock/v1/{id}/{num}", method = RequestMethod.POST)
	@ApiOperation(value = "根据库存主键更新库存数量", notes = "根据库存主键修改库存数量", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<Integer> updateSensitive(@ApiParam(value = "库存主键", name = "id", required = true) @PathVariable @PathParam("id") String id,@ApiParam(value = "库存数量", name = "num", required = true) @PathVariable @PathParam("num")Long num) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0;
		if(StringUtils.isNotBlank(id)){
			count = stockService.updateStock(id,num);
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}

	/**
	 * 修改全部对象字段
	 * 
	 * @param dto
	 *            修改对象
	 * @return ResponseMsg<Integer>
	 * @throws Exception
	 */
	@POST
	@Path("/updateAll/v1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseMsg<Integer> updateAll(StockDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0 ;
		if(dto != null){
			count = stockService.updateStock(dto);
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}

	/**
	 * 按主键单条删除
	 * 
	 * @param id
	 *            主键id
	 * @return ResponseMsg<Integer>
	 * @throws Exception
	 */
	@POST
	@Path("/deleteById/v1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseMsg<Integer> deleteById(String id) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0;
		if(StringUtils.isNotBlank(id)){
			count = stockService.deleteStockById(id);
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}

	/**
	 * 按主键数组多条删除
	 * 
	 * @param ids
	 *            主键id的数组
	 * @return ResponseMsg<Integer>
	 * @throws Exception
	 */
	@POST
	@Path("/deleteByIds/v1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseMsg<Integer> deleteByIds(@QueryParam("ids") String ids) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0;
		if(StringUtils.isNotBlank(ids)){
			count = stockService.deleteStockByIds(ids.split(","));
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}

	/**
	 *  删除商品库存
	 * @return ResponseMsg<Integer>
	 * @throws Exception
	 */
	@POST
	@Path("/deleteByGoodsId/v1/{goodsId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseMsg<Integer> deleteByGoodsId(@PathParam("goodsId") String goodsId) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0;
		if(StringUtils.isNotEmpty(goodsId)){
			count = stockService.deleteByGoodsId(goodsId.trim());
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}

	/**
	 * 更新库存数量
	 * 
	 * @param params
	 *            参数集合<br>
	 *            <pre>
	 *       <li>id：库存ID</li>
	 *       <li>num：减去的库存数量</li>
	 *            </pre>
	 * 
	 * @return int
	 */
	@POST
	@Path("/updateStockNum/v1/{goodsId}/{num}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/updateStockNum/v1/{goodsId}/{num}", method = RequestMethod.POST)
	@ApiOperation(value = "根据商品主键更新库存数量", notes = "根据商品基本信息主键修改库存数量", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)


	public ResponseMsg<Integer> updateStockNum(@ApiParam(value = "商品基本信息主键", name = "goodsId", required = true) @PathVariable @PathParam("goodsId") String goodsId,@ApiParam(value = "库存数量", name = "num", required = true) @PathVariable @PathParam("num") Integer num) {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0;
		if(StringUtils.isNotBlank(goodsId) && num != null){
			count = stockService.updateStockNum(goodsId, num);
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}
	
	/**
	 * 根据商品ID查询库存数量
	 * 
	 * @param params
	 *            goodsId：商品ID
	 * 
	 * @return int 库存数量
	 */
	@GET
	@Path("/getStockNumByGoodsId/v1/{goodsId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/getStockNumByGoodsId/v1/{goodsId}", method = RequestMethod.GET)
	@ApiOperation(value = "根据商品主键查询库存数量", notes = "根据商品基本信息主键查询库存数量", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<Integer> getStockNumByGoodsId(@ApiParam(value = "商品基本信息主键", name = "goodsId", required = true) @PathVariable @PathParam("goodsId") String goodsId) {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0;
		if(StringUtils.isNotBlank(goodsId)){
			count = stockService.getStockNumByGoodsId(goodsId);
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}
}
