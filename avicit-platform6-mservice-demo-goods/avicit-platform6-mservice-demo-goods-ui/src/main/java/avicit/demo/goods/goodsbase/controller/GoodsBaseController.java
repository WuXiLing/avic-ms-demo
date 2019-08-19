package avicit.demo.goods.goodsbase.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import avicit.platform6.api.session.SessionHelper;
import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.platform6.commons.utils.JsonHelper;
import avicit.platform6.core.properties.PlatformConstant.OpResult;
import avicit.platform6.core.rest.msg.PageParameter;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.api.application.SysApplicationAPI;
import avicit.platform6.commons.utils.ComUtil;

import avicit.demo.goods.goodsbase.dto.GoodsBaseDTO;
import avicit.demo.goods.goodsbase.api.GoodsBaseApi;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：avicitdev@avicit.com
 * @创建时间： 2019-06-01 13:58
 * @类说明：请填写
 * @修改记录： 
 */
@Controller
@Scope("prototype")
@RequestMapping("demo/goods/goodsbase/goodsBaseController")
public class GoodsBaseController implements LoaderConstant {
	private static final Logger logger = LoggerFactory.getLogger(GoodsBaseController.class);

	@Autowired
	private SysApplicationAPI sysApplicationAPI;
	@Autowired
	private GoodsBaseApi goodsBaseApi;

	/**
	 * 跳转到管理页面
	 * @param request 请求
	 * @param reponse 响应
	 * @return ModelAndView
	 */
	@RequestMapping(value = "toGoodsBaseManage")
	public ModelAndView toGoodsBaseManage(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("avicit/demo/goods/goodsbase/GoodsBaseManage");
		mav.addObject("url", "platform/demo/goods/goodsbase/goodsBaseController/operation/");
		return mav;
	}

	/**
	* 分页查询方法
	* @param pageParameter 查询条件
	* @param request 请求
	* @return Map<String,Object>
	*/
	@RequestMapping(value = "/operation/getGoodsBasesByPage")
	@ResponseBody
	public Map<String, Object> togetGoodsBaseByPage(PageParameter pageParameter, HttpServletRequest request) {
		QueryReqBean<GoodsBaseDTO> queryReqBean = new QueryReqBean<GoodsBaseDTO>();
		queryReqBean.setPageParameter(pageParameter);
		HashMap<String, Object> map = new HashMap<String, Object>();
		String keyWord = ServletRequestUtils.getStringParameter(request, "keyWord", "");//关键字
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		String sord = ServletRequestUtils.getStringParameter(request, "sord", "");
		String sidx = ServletRequestUtils.getStringParameter(request, "sidx", "");
		if (!StringUtils.isEmpty(keyWord)) {
			json = keyWord;
		}
		String orderBy = "";
		String cloumnName = "";
		if (sidx != null && sord != null && !"".equals(sord) && !"".equals(sidx)) {
			cloumnName = ComUtil.getColumn(GoodsBaseDTO.class, sidx);
			if (cloumnName != null) {//这里先进行判断是否有对应的数据库字段
				orderBy = " " + cloumnName + " " + sord;
			} else {
				//判断是否为特殊控件导致字段无法匹配
				if (sidx.indexOf("Alias") != -1) {
					cloumnName = ComUtil.getColumn(GoodsBaseDTO.class, sidx.substring(0, sidx.lastIndexOf("Alias")));
				} else if (sidx.indexOf("Name") != -1) {
					cloumnName = ComUtil.getColumn(GoodsBaseDTO.class, sidx.substring(0, sidx.lastIndexOf("Name")));
				}
				if (cloumnName != null) {
					orderBy = " " + cloumnName + " " + sord;
				}
			}
		}
		GoodsBaseDTO param = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		QueryRespBean<GoodsBaseDTO> result = null;
		if (json != null && !"".equals(json)) {
			param = JsonHelper.getInstance().readValue(json, dateFormat, new TypeReference<GoodsBaseDTO>() {
			});
			queryReqBean.setSearchParams(param);
		}
		try {
			if (!"".equals(keyWord)) {//根据keyWord查询的通过or查询
				result = goodsBaseApi.searchGoodsBaseByPageOr(queryReqBean, orderBy);
			} else {
				result = goodsBaseApi.searchGoodsBaseByPage(queryReqBean, orderBy);
			}
		} catch (Exception ex) {
			return map;
		}
		for (GoodsBaseDTO dto : result.getResult()) {
		}
		map.put("records", result.getPageParameter().getTotalCount());//总记录数
		map.put("page", result.getPageParameter().getPage()); //当前页
		map.put("total", result.getPageParameter().getTotalPage()); //总页数
		map.put("rows", result.getResult()); //数据
		logger.info("成功获取GoodsBaseDTO分页数据");
		return map;
	}

	/**
	* 根据id选择跳转到新建页还是编辑页
	* @param type 操作类型新建还是编辑
	* @param id 编辑数据的id
	* @param request 请求
	* @return ModelAndView
	* @throws Exception
	*/
	@RequestMapping(value = "/operation/{type}/{id}")
	public ModelAndView toOpertionPage(@PathVariable String type, @PathVariable String id, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		if (!"Add".equals(type)) {//编辑窗口或者详细页窗口
			//主表数据
			GoodsBaseDTO dto = goodsBaseApi.queryGoodsBaseByPrimaryKey(id);
			mav.addObject("goodsBaseDTO", dto);
		}
		mav.setViewName("avicit/demo/goods/goodsbase/" + "GoodsBase" + type);
		return mav;
	}

	/**
	 * 保存数据
	 * @param id 主键id
	 * @param request 请求
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/operation/save", method = RequestMethod.POST)
	public ModelAndView toSaveGoodsBaseDTO(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String jsonData = ServletRequestUtils.getStringParameter(request, "data", "");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String returnId = "";
		try {
			GoodsBaseDTO goodsBaseDTO = JsonHelper.getInstance().readValue(jsonData, dateFormat,
					new TypeReference<GoodsBaseDTO>() {
					});
			if (StringUtils.isEmpty(goodsBaseDTO.getId())) {//新增
				returnId = goodsBaseApi.insertGoodsBase(goodsBaseDTO);
			} else {//修改
				returnId = goodsBaseDTO.getId();
				goodsBaseApi.updateGoodsBaseSensitive(goodsBaseDTO);
			}
			mav.addObject("flag", OpResult.success);
			mav.addObject("id", returnId);
		} catch (Exception ex) {
			mav.addObject("flag", OpResult.failure);
			return mav;
		}
		return mav;
	}

	/**
	 * 按照id批量删除数据
	 * @param ids id数组
	 * @param request 请求
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/operation/delete", method = RequestMethod.POST)
	public ModelAndView toDeleteGoodsBaseDTO(@RequestBody String[] ids, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			goodsBaseApi.deleteGoodsBaseByIds(ids);
			mav.addObject("flag", OpResult.success);
		} catch (Exception ex) {
			mav.addObject("flag", OpResult.failure);
			return mav;
		}
		return mav;
	}
}
