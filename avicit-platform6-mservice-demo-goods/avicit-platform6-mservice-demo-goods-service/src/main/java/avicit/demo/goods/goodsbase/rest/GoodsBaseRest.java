package avicit.demo.goods.goodsbase.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import avicit.demo.goods.goodsbase.dto.GoodsBaseDTO;
import avicit.demo.goods.goodsbase.dto.GoodsVO;
import avicit.demo.goods.goodsbase.service.GoodsBaseService;
import avicit.demo.goods.goodsdetail.service.GoodsDetailService;
import avicit.platform6.core.rest.msg.PageParameter;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.core.rest.msg.ResponseMsg;
import avicit.platform6.core.rest.resteasy.RestEasyController;
import avicit.platform6.idempotence.commons.annotation.IdemInclude;
import avicit.platform6.idempotence.commons.annotation.Idempotence;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 商品基本信息服务层
 * 
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：avicitdev@avicit.com @创建时间： 2019-06-01 10:25
 * @类说明：请填写 @修改记录：
 */
@RestEasyController
@Path("/api/demo/goods/goodsbase/GoodsBaseRest")

@RequestMapping("/api/demo/goods/goodsbase/GoodsBaseRest")
@Api(tags = { "GoodsBase,goods" }, description = "商品基本信息")
public class GoodsBaseRest {

	/** 商品基本信息Service */
	@Autowired
	private GoodsBaseService goodsBaseService;

	/** 商品详细信息Service */
	@Autowired
	private GoodsDetailService goodsDetailService;

	/**
	 * 获取列表数据
	 * 
	 * @param pageParameter 分页对象
	 * @param keyWord       查询关键词
	 * @param param         查询参数
	 * @param sord          排序字段
	 * @param sidx          排序方式
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/searchByPage/v1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	@RequestMapping(value = "/searchByPage/v1", method = RequestMethod.POST)
	@ApiOperation(value = "查询商品基本信息列表", notes = "商品列表信息查询", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	
	@ApiImplicitParams(
			{
				@ApiImplicitParam(name = "分页信息", value = "pageParameter", dataTypeClass = PageParameter.class, required = true),
				@ApiImplicitParam(name = "参数信息", value = "searchParams", dataType = "map", required = true),
				@ApiImplicitParam(name = "排序字段", value = "sfnConditions", dataType = "string", required = true)
			}
		)
	public ResponseMsg<Map<String, Object>> getGoodsBasesByPage(@ApiIgnore QueryReqBean<GoodsBaseDTO> queryReqBean,
			@ApiIgnore String sfnConditions) throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();
		QueryRespBean<GoodsBaseDTO> result = null;
		result = goodsBaseService.searchGoodsBaseByPage(queryReqBean, sfnConditions);
		List<GoodsVO> gvos = new ArrayList<GoodsVO>();
		if (result.getResult() != null && !result.getResult().isEmpty()) {
			for (GoodsBaseDTO dto : result.getResult()) {
				GoodsVO gvo = new GoodsVO(dto, goodsDetailService.getByGoodIds(dto.getId()));
				gvos.add(gvo);
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
	 * @param id 主键id
	 * @return ResponseMsg<GoodsBaseDTO>
	 * @throws Exception
	 */
	@GET
	@Path("/get/v1/{id}")
	@Produces({ MediaType.APPLICATION_JSON })

	@RequestMapping(value = "/get/v1/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "查询商品基本信息", notes = "商品单条信息查询", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<GoodsBaseDTO> get(
			@ApiParam(value = "主键", name = "id", required = true) @PathVariable @PathParam("id") String id)
			throws Exception {
		ResponseMsg<GoodsBaseDTO> responseMsg = new ResponseMsg<GoodsBaseDTO>();
		GoodsBaseDTO dto = null;
		if (StringUtils.isNotBlank(id)) {
			dto = goodsBaseService.queryGoodsBaseByPrimaryKey(id);
		}
		responseMsg.setResponseBody(dto);
		return responseMsg;
	}

	/**
	 * 通过主键查询单条记录(VO中包含基本信息和详细信息)
	 * 
	 * @param id 主键id
	 * @return GoodsVO
	 * @throws Exception
	 */
	@GET
	@Path("/getVO/v1/{id}")
	@Produces({ MediaType.APPLICATION_JSON })

	@RequestMapping(value = "/getVO/v1/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "查询商品信息", notes = "商品单条信息查询(包含基本信息、详细信息)", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<GoodsVO> queryGoodsVOByPrimaryKey(
			@ApiParam(value = "主键", name = "id", required = true) @PathVariable @PathParam("id") String id)
			throws Exception {
		ResponseMsg<GoodsVO> responseMsg = new ResponseMsg<GoodsVO>();
		GoodsVO dto = null;
		if (StringUtils.isNotBlank(id)) {
			dto = goodsBaseService.queryGoodsVOByPrimaryKey(id);
		}
		responseMsg.setResponseBody(dto);
		return responseMsg;
	}

	/**
	 * 新增对象
	 * 
	 * @param dto 商品信息
	 * @return ResponseMsg<String>
	 * @throws Exception
	 */
	@POST
	@Path("/save/v1")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)

	@RequestMapping(value = "/save/v1", method = RequestMethod.POST)
	@ApiOperation(value = "保存商品信息", notes = "商品基本信息保存(包含基本信息、详细信息)", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	@ApiImplicitParams(
		{
			@ApiImplicitParam(name = "商品编码", value = "code", dataType = "string", required = true),
			@ApiImplicitParam(name = "商品名称", value = "name", dataType = "string", required = true),
			@ApiImplicitParam(name = "商品价格", value = "price", dataType = "string", required = true),
			@ApiImplicitParam(name = "商品简介", value = "descriptionShort", dataType = "double", required = false),
			@ApiImplicitParam(name = "商品图片", value = "thumb", dataType = "string", required = false),
			@ApiImplicitParam(name = "商品状态", value = "status", dataType = "array", required = true),
			@ApiImplicitParam(name = "商品颜色", value = "color", dataType = "array", required = true),
			@ApiImplicitParam(name = "商品型号", value = "goodsSize", dataType = "array", required = false),
			@ApiImplicitParam(name = "商品描述", value = "description", dataType = "string", required = false),
			@ApiImplicitParam(name = "商品商家", value = "merchants", dataType = "string", required = false),
			@ApiImplicitParam(name = "是否自营", value = "proprietary", dataType = "string", required = false)
		}
	)
	@Idempotence(expireTime = 8)
	public ResponseMsg<String> save(@IdemInclude @ApiIgnore GoodsVO dto) throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<String>();
		String id = "";
		Thread.sleep(3000);
		if (dto != null) {
			// modify by lgl add try
			id = goodsBaseService.insertGoodsBaseAndDetail(dto.getGoodsBaseDto(), dto.getGoodsDetailDTO());
		}
		responseMsg.setResponseBody(id);
		return responseMsg;
	}

	/**
	 * 修改商品信息，商品明细部分对象字段
	 * 
	 * @param dto 商品信息，包含商品基本信息、详细信息
	 * @return ResponseMsg<Integer>
	 * @throws Exception
	 */
	@POST
	@Path("/updateSensitiveDetail/v1")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)

	@RequestMapping(value = "/updateSensitiveDetail/v1", method = RequestMethod.POST)
	@ApiOperation(value = "修改商品信息", notes = "商品信息修改(包含基本信息、详细信息)", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	@ApiImplicitParams(
			{
				@ApiImplicitParam(name = "商品主键", value = "id", dataType = "string", required = true),
				@ApiImplicitParam(name = "商品编码", value = "code", dataType = "string", required = true),
				@ApiImplicitParam(name = "商品名称", value = "name", dataType = "string", required = true),
				@ApiImplicitParam(name = "商品价格", value = "price", dataType = "string", required = true),
				@ApiImplicitParam(name = "商品简介", value = "descriptionShort", dataType = "double", required = false),
				@ApiImplicitParam(name = "商品图片", value = "thumb", dataType = "string", required = true),
				@ApiImplicitParam(name = "商品状态", value = "status", dataType = "array", required = true),
				@ApiImplicitParam(name = "商品颜色", value = "color", dataType = "array", required = true),
				@ApiImplicitParam(name = "商品型号", value = "goodsSize", dataType = "array", required = false),
				@ApiImplicitParam(name = "商品描述", value = "description", dataType = "string", required = false),
				@ApiImplicitParam(name = "商品商家", value = "merchants", dataType = "string", required = false),
				@ApiImplicitParam(name = "是否自营", value = "proprietary", dataType = "string", required = false)
			}
		)
	public ResponseMsg<Integer> updateSensitiveDetail(@ApiIgnore GoodsVO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0;
		if (dto != null) {
			// modify by lgl add try
			goodsBaseService.updateSensitiveDetail(dto.getGoodsBaseDto(), dto.getGoodsDetailDTO());
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}

	/**
	 * 更新图片
	 * 
	 * @param id    商品ID
	 * @param thumb 图片的base64
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/updateGoodsBaseThumb/v1/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	@RequestMapping(value = "/updateGoodsBaseThumb/v1/{id}", method = RequestMethod.POST)
	@ApiOperation(value = "更新商品图片", notes = "商品图片修改", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<String> updateGoodsBaseThumb(
			@ApiParam(value = "商品主键值", name = "id", required = true) @PathVariable @PathParam("id") String id,
			@ApiParam(name = "图片的base64编码", value = "thumb", required = true) @RequestParam("thumb") @QueryParam("thumb") String thumb)
			throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<String>();
		goodsBaseService.updateGoodsBaseThumb(id, thumb.getBytes());
		responseMsg.setResponseBody(id);
		return responseMsg;
	}

	/**
	 * 获取图片base64
	 * 
	 * @param id 商品ID
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/getGoodsBaseThumb/v1/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	@RequestMapping(value = "/getGoodsBaseThumb/v1", method = RequestMethod.POST)
	@ApiOperation(value = "查询商品图片", notes = "查询商品图片base64编码", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<String> getGoodsBaseThumb(
			@ApiParam(value = "商品主键值", name = "id", required = true) @PathVariable("id") @PathParam("id") String id)
			throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<String>();
		if (StringUtils.isNotBlank(id)) {
			GoodsBaseDTO dto = this.goodsBaseService.queryGoodsBaseByPrimaryKey(id);
			if (dto != null) {
				byte[] thumb = dto.getThumb();
				if (thumb != null) {
					responseMsg.setResponseBody(new String(thumb));
				}
			}
		}
		return responseMsg;
	}

	/**
	 * 商品发布
	 * 
	 * @param id 商品ID
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/goodsReleaseById/v1/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	@RequestMapping(value = "/goodsReleaseById/v1/{id}", method = RequestMethod.POST)
	@ApiOperation(value = "商品发布", notes = "根据商品主键发布商品", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<Integer> goodsReleaseById(
			@ApiParam(value = "商品主键值", name = "id", required = true) @PathVariable("id") @PathParam("id") String id)
			throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0;
		if (StringUtils.isNotBlank(id)) {
			count = this.goodsBaseService.goodsReleaseById(id);
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}

	/**
	 * 批量商品发布
	 * 
	 * @param id 商品ID集合
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/goodsReleaseByIds/v1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	@RequestMapping(value = "/goodsReleaseByIds/v1", method = RequestMethod.POST)
	@ApiOperation(value = "商品批量发布", notes = "商品批量发布", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	@ApiImplicitParams(@ApiImplicitParam(name = "商品基本信息主键字符串，多个主键用','隔开", value = "ids", dataType = "string", required = true))
	public ResponseMsg<Integer> goodsReleaseByIds(@ApiIgnore MultivaluedMap<String, String> form) throws Exception {
		String ids = form.getFirst("ids");
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0;
		if (StringUtils.isNotBlank(ids)) {
			count = this.goodsBaseService.goodsReleaseByIds(ids.split(","));
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}

	/**
	 * 按主键单条删除
	 * 
	 * @param id 主键id
	 * @return ResponseMsg<Integer>
	 * @throws Exception
	 */
	@DELETE
	@Path("/deleteById/v1")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)

	@RequestMapping(value = "/deleteById/v1", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除商品", notes = "商品信息删除，包含删除基本信息、详细信息", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<Integer> deleteById(
			@ApiParam(name = "商品基本信息主键", value = "id", required = false) @RequestParam("ids") @QueryParam("id") String id)
			throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0;
		if (StringUtils.isNotBlank(id)) {
			goodsBaseService.deleteGoodsBaseById(id);
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}

	/**
	 * 按主键数组多条删除
	 * 
	 * @param ids 主键id的集合
	 * @return ResponseMsg<Integer>
	 * @throws Exception
	 */
	@DELETE
	@Path("/deleteByIds/v1")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)

	@RequestMapping(value = "/deleteByIds/v1", method = RequestMethod.DELETE)
	@ApiOperation(value = "批量删除商品", notes = "商品基本信息批量删除", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<Integer> deleteByIds(
			@ApiParam(name = "商品基本信息主键字符串，多个主键用','隔开", value = "ids", required = false) @RequestParam("ids") @QueryParam("ids") String ids)
			throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = 0;
		if (StringUtils.isNotBlank(ids)) {
			count = goodsBaseService.deleteGoodsBaseByIds(ids.split(","));
		}
		responseMsg.setResponseBody(count);
		return responseMsg;
	}

	@POST
	@Path("/getGoodsIdBaseByCode/v1")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)

	@RequestMapping(value = "/getGoodsIdBaseByCode/v1", method = RequestMethod.POST)
	@ApiOperation(value = "通过编码查询商品", notes = "通过商品编码查询商品基本信息主键", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<String> getGoodsIdBaseByCode(
			@ApiParam(name = "商品编码", value = "code", required = false) @RequestParam("code") String code)
			throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<String>();
		String res = "";
		if (StringUtils.isNotBlank(code)) {
			res = goodsBaseService.getGoodsIdBaseByCode(code);
		}
		responseMsg.setResponseBody(res);
		return responseMsg;
	}

	/**
	 * 查询大批量数据<br>
	 * 用于演示弹性伸缩,占用内存直到报异常(栈)
	 * 
	 * @return
	 */
	@GET
	@Path("/queryBulkGoodsData/v1")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)

	@RequestMapping(value = "/queryBulkGoodsData/v1", method = RequestMethod.GET)
	@ApiOperation(value = "查询大批量商品", notes = "查询大批量商品基本信息、详细信息", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<Object> queryBulkGoodsData() {
		ResponseMsg<Object> responseMsg = new ResponseMsg<Object>();
		responseMsg.setResponseBody(goodsBaseService.queryBulkGoodsData());
		return responseMsg;
	}

	/**
	 * 取消查询大批量数据<br>
	 * 用于演示弹性伸缩,释放被占用的内存(栈)
	 * 
	 * @return
	 */
	@GET
	@Path("/cancelQueryBulkGoodsData/v1")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)

	@RequestMapping(value = "/cancelQueryBulkGoodsData/v1", method = RequestMethod.GET)
	@ApiOperation(value = "取消商品大批量查询", notes = "取消查询大批量商品信息", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public ResponseMsg<Object> cancelQueryBulkGoodsData() {
		ResponseMsg<Object> responseMsg = new ResponseMsg<Object>();
		responseMsg.setResponseBody(goodsBaseService.cancelQueryBulkGoodsData());
		return responseMsg;
	}
}
