package avicit.demo.goods.goodsdetail.rest;

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

import avicit.demo.goods.goodsdetail.dto.GoodsDetailDTO;
import avicit.demo.goods.goodsdetail.dto.GoodsDetailVO;
import avicit.demo.goods.goodsdetail.service.GoodsDetailService;
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
 * @创建时间： 2019-06-01 10:27
 * @类说明：请填写
 * @修改记录： 
 */
@RestEasyController
@Path("/api/demo/goods/goodsdetail/GoodsDetailRest")

@RequestMapping("/api/demo/goods/goodsdetail/GoodsDetailRest")
@Api(tags = { "GoodsDetail,goods" }, description = "商品详细信息")
public class GoodsDetailRest {

	@Autowired
	private GoodsDetailService goodsDetailService;

	/**
	 * 通过主键查询单条记录
	 * 
	 * @param id 主键id
	 * @return ResponseMsg<GoodsDetailDTO>
	 * @throws Exception
	 */
	@GET
	@Path("/get/v1/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/get/v1/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "查询商品详细信息", notes = "商品单条详细信息查询", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<GoodsDetailDTO> get(@ApiParam(value = "主键", name = "id", required = true) @PathVariable @PathParam("id") String id) throws Exception {
		ResponseMsg<GoodsDetailDTO> responseMsg = new ResponseMsg<GoodsDetailDTO>();
		GoodsDetailDTO dto = goodsDetailService.queryGoodsDetailByPrimaryKey(id);
		responseMsg.setResponseBody(dto);
		return responseMsg;
	}
	
	/**
	 * 通过商品信息主键查询商品明细
	 * @param id 主键id
	 * @return ResponseMsg<GoodsDetailDTO>
	 * @throws Exception
	 */
	@GET
	@Path("/getByGoodIds/v1/{goodsId}")
	@Produces(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/getByGoodIds/v1/{goodsId}", method = RequestMethod.GET)
	@ApiOperation(value = "通过商品主键查询详细", notes = "商品单条详细信息查询", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	
	public ResponseMsg<GoodsDetailVO> getByGoodIds(@ApiParam(value = "商品基本信息主键", name = "goodsId", required = true) @PathVariable(value="goodsId") @PathParam("goodsId") String goodsId) throws Exception {
		ResponseMsg<GoodsDetailVO> responseMsg = new ResponseMsg<GoodsDetailVO>();
		GoodsDetailVO dto = goodsDetailService.getByGoodIds(goodsId);
		responseMsg.setResponseBody(dto);
		return responseMsg;
	}
	
	/**
	 * 查询商品详细信息，包含库存数量
	 * 
	 * @param goodsId 商品ID
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/getAndStockNumByGoodIds/v1/{goodsId}")
	@Produces(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/getAndStockNumByGoodIds/v1/{goodsId}", method = RequestMethod.GET)
	@ApiOperation(value = "通过商品主键查询详细 (包含库存信息)", notes = "商品单条详细信息查询(包含库存信息)", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	
	public ResponseMsg<GoodsDetailVO> getAndStockNumByGoodIds(@ApiParam(value = "商品基本信息主键", name = "goodsId", required = true) @PathVariable(value="goodsId") @PathParam("goodsId") String goodsId) throws Exception {
		ResponseMsg<GoodsDetailVO> responseMsg = new ResponseMsg<GoodsDetailVO>();
		GoodsDetailVO dto = goodsDetailService.getAndStockNumByGoodIds(goodsId);
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
	
	@RequestMapping(value = "/save/v1", method = RequestMethod.POST)
	@ApiOperation(value = "保存商品详细信息", notes = "商品详细信息保存", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	@ApiImplicitParams(
		{
			@ApiImplicitParam(name = "商品颜色", value = "color", dataType = "array", required = true),
			@ApiImplicitParam(name = "商品型号", value = "goodsSize", dataType = "array", required = true),
			@ApiImplicitParam(name = "商品描述", value = "description", dataType = "string", required = true),
			@ApiImplicitParam(name = "商品商家", value = "merchants", dataType = "string", required = true),
			@ApiImplicitParam(name = "是否自营", value = "proprietary", dataType = "string", required = true)
		}
	)
	public ResponseMsg<String> save(@ApiIgnore GoodsDetailDTO dto) throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<String>();
		String id = goodsDetailService.insertGoodsDetail(dto);
		responseMsg.setResponseBody(id);
		return responseMsg;
	}
	
	/**
	 * 修改部分对象字段
	 * @param dto 修改对象
	 * @return ResponseMsg<Integer>
	 * @throws Exception
	 */
	@POST
	@Path("/updateSensitive/v1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	@RequestMapping(value = "/updateSensitive/v1", method = RequestMethod.POST)
	@ApiOperation(value = "修改商品详细信息", notes = "商品详细信息部分字段修改", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	@ApiImplicitParams(
		{
			@ApiImplicitParam(name = "商品详细信息主键", value = "id", dataType = "string", required = true),
			@ApiImplicitParam(name = "商品基本信息主键", value = "goodsId", dataType = "string", required = true),
			@ApiImplicitParam(name = "商品颜色", value = "color", dataType = "array", required = true),
			@ApiImplicitParam(name = "商品型号", value = "goodsSize", dataType = "array", required = false),
			@ApiImplicitParam(name = "商品描述", value = "description", dataType = "string", required = false),
			@ApiImplicitParam(name = "商品商家", value = "merchants", dataType = "string", required = false),
			@ApiImplicitParam(name = "是否自营", value = "proprietary", dataType = "string", required = false)
		}
	)
	public ResponseMsg<Integer> updateSensitive(@ApiIgnore GoodsDetailDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = goodsDetailService.updateGoodsDetailSensitive(dto);
		responseMsg.setResponseBody(count);
		return responseMsg;
	}

	/**
	 * 修改全部对象字段
	 * @param dto 修改对象
	 * @return ResponseMsg<Integer>
	 * @throws Exception
	 */
	@POST
	@Path("/updateAll/v1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	public ResponseMsg<Integer> updateAll(@ApiIgnore GoodsDetailDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = goodsDetailService.updateGoodsDetail(dto);
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
	@ApiOperation(value = "删除商品详细信息", notes = "通过主键删除商品详细信息", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<Integer> deleteById(@ApiParam(name = "商品详细信息主键", value = "id", required = false) @RequestParam("id") String id) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = goodsDetailService.deleteGoodsDetailById(id);
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
	
	@RequestMapping(value = "/deleteByIds/v1", method = RequestMethod.POST)
	@ApiOperation(value = "批量删除商品详细信息", notes = "商品详细信息批量删除", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<Integer> deleteByIds(@ApiParam(name = "商品详细信息主键字符串，多个主键用','隔开", value = "ids", required = false) @RequestParam("ids") @QueryParam("ids") String ids) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0;
		if(StringUtils.isNotBlank(ids)){
			count = goodsDetailService.deleteGoodsDetailByIds(ids.split(","));
			
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}
}
