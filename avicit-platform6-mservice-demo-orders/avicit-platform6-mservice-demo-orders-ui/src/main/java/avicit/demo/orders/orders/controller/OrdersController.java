package avicit.demo.orders.orders.controller;

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

import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.api.application.SysApplicationAPI;
import avicit.platform6.api.session.SessionHelper;
import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.platform6.commons.utils.JsonHelper;
import avicit.platform6.core.properties.PlatformConstant.OpResult;
import avicit.platform6.core.rest.msg.PageParameter;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;

import avicit.demo.orders.orders.dto.OrdersDTO;
import avicit.demo.orders.orders.api.OrdersApi;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：avicitdev@avicit.com
 * @创建时间： 2019-06-01 11:23
 * @类说明：请填写
 * @修改记录： 
 */
@Controller
@Scope("prototype")
@RequestMapping("demo/orders/orders/ordersController")
public class OrdersController implements LoaderConstant {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrdersController.class);

	@Autowired
	private SysApplicationAPI sysApplicationAPI;

	@Autowired
	private OrdersApi ordersApi;

	/**
	 * 跳转到管理页面
	 * @param request 请求
	 * @param reponse 响应
	 * @return ModelAndView
	 */
	@RequestMapping(value = "toOrdersManage")
	public ModelAndView toOrdersManage(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("avicit/demo/orders/orders/OrdersManage");
		mav.addObject("url", "platform/demo/orders/orders/ordersController/operation/");
		return mav;
	}

	/**
	 * 分页查询方法
	 * @param pageParameter 查询条件
	 * @param request 请求
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/operation/getOrderssByPage")
	@ResponseBody
	public Map<String, Object> togetOrdersByPage(PageParameter pageParameter, HttpServletRequest request) {
		QueryReqBean<OrdersDTO> queryReqBean = new QueryReqBean<OrdersDTO>();
		queryReqBean.setPageParameter(pageParameter);
		HashMap<String, Object> map = new HashMap<String, Object>();
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		String keyWord = ServletRequestUtils.getStringParameter(request, "keyWord", "");//字段查询条件
		String sord = ServletRequestUtils.getStringParameter(request, "sord", "");//排序方式
		String sidx = ServletRequestUtils.getStringParameter(request, "sidx", "");//排序字段
		if (!StringUtils.isEmpty(keyWord)) {
			json = keyWord;
		}
		String orderBy = "";
		String cloumnName = "";
		if (sidx != null && sord != null && !"".equals(sord) && !"".equals(sidx)) {
			cloumnName = ComUtil.getColumn(OrdersDTO.class, sidx);
			if (cloumnName != null) {//这里先进行判断是否有对应的数据库字段
				orderBy = " " + cloumnName + " " + sord;
			} else {
				//判断是否为特殊控件导致字段无法匹配
				if (sidx.indexOf("Alias") != -1) {
					cloumnName = ComUtil.getColumn(OrdersDTO.class, sidx.substring(0, sidx.lastIndexOf("Alias")));
				} else if (sidx.indexOf("Name") != -1) {
					cloumnName = ComUtil.getColumn(OrdersDTO.class, sidx.substring(0, sidx.lastIndexOf("Name")));
				}
				if (cloumnName != null) {
					orderBy = " " + cloumnName + " " + sord;
				}
			}
		}
		OrdersDTO param = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		QueryRespBean<OrdersDTO> result = null;
		if (json != null && !"".equals(json)) {
			param = JsonHelper.getInstance().readValue(json, dateFormat, new TypeReference<OrdersDTO>() {
			});
			queryReqBean.setSearchParams(param);
		}
		try {
			if (!"".equals(keyWord)) {
				result = ordersApi.searchOrdersByPageOr(queryReqBean, orderBy);
			} else {
				result = ordersApi.searchOrdersByPage(queryReqBean, orderBy);
			}
		} catch (Exception ex) {
			return map;
		}

		for (OrdersDTO dto : result.getResult()) {

		}

		map.put("records", result.getPageParameter().getTotalCount());
		map.put("page", result.getPageParameter().getPage());
		map.put("total", result.getPageParameter().getTotalPage());
		map.put("rows", result.getResult());
		LOGGER.info("成功获取OrdersDTO分页数据");
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
			OrdersDTO dto = ordersApi.queryOrdersByPrimaryKey(id);

			mav.addObject("ordersDTO", dto);
		}
		mav.setViewName("avicit/demo/orders/orders/" + "Orders" + type);
		return mav;
	}

	/**
	 * 保存数据
	 * @param id 主键id
	 * @param request 请求
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/operation/save", method = RequestMethod.POST)
	public ModelAndView toSaveOrdersDTO(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String jsonData = ServletRequestUtils.getStringParameter(request, "data", "");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			OrdersDTO ordersDTO = JsonHelper.getInstance().readValue(jsonData, dateFormat,
					new TypeReference<OrdersDTO>() {
					});
			if (StringUtils.isEmpty(ordersDTO.getId())) {//新增
				ordersApi.insertOrders(ordersDTO);
			} else {//修改
				ordersApi.updateOrdersSensitive(ordersDTO);
			}
			mav.addObject("flag", OpResult.success);
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
	public ModelAndView toDeleteOrdersDTO(@RequestBody String[] ids, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			ordersApi.deleteOrdersByIds(ids);
			mav.addObject("flag", OpResult.success);
		} catch (Exception ex) {
			mav.addObject("flag", OpResult.failure);
			return mav;
		}
		return mav;
	}
}
