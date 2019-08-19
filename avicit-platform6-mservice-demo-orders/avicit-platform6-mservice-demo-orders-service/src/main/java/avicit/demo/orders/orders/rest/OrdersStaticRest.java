package avicit.demo.orders.orders.rest;

import avicit.demo.orders.orders.service.OrdersStaticService;
import avicit.demo.orders.orders.vo.GoodsRankVo;
import avicit.demo.orders.orders.vo.TotalOrdersAspectVo;
import avicit.demo.orders.orders.vo.TotalSalesAspectVo;
import avicit.platform6.core.rest.msg.ResponseMsg;
import avicit.platform6.core.rest.resteasy.RestEasyController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单相关统计
 *
 * @金航数码科技有限责任公司
 * @作者：ligl
 * @邮箱：ligl@avicit.com
 * @创建时间： 2019/6/11 8:43
 * @类说明：请填写
 * @修改记录：
 */
@RestEasyController
@Path("/api/demo/orders/orders/OrdersStaticRest")
public class OrdersStaticRest {

    @Autowired
    private OrdersStaticService service;

    /**
     * 商品排行榜
     *
     * @return
     * @throws Exception
     */
    @GET
    @Path("/getGoodsRank/v1")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseMsg<List<GoodsRankVo>> getGoodsRank() throws Exception {
        List<GoodsRankVo> list = service.getGoodsRank();

        ResponseMsg<List<GoodsRankVo>> responseMsg = new ResponseMsg<List<GoodsRankVo>>();
        responseMsg.setResponseBody(list);
        return responseMsg;
    }

    /**
     * 总销售额：已支付的订单总金额
     *
     * @return
     * @throws Exception
     */
    @GET
    @Path("/getGoodsTotalSales/v1")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseMsg<BigDecimal> getGoodsTotalSales() throws Exception {
        BigDecimal totalSales = service.getGoodsTotalSales();

        ResponseMsg<BigDecimal> responseMsg = new ResponseMsg<BigDecimal>();
        responseMsg.setResponseBody(totalSales);
        return responseMsg;
    }

    /**
     * 有效订单数：状态为 待支付、已支付的订单总数量
     *
     * @return
     * @throws Exception
     */
    @GET
    @Path("/getEffectiveOrdersNum/v1")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseMsg<Integer> getEffectiveOrdersNum() throws Exception {
        Integer effectiveOrders = service.getEffectiveOrdersNum();

        ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
        responseMsg.setResponseBody(effectiveOrders);
        return responseMsg;
    }

    /**
     * 支付笔数：状态为 已支付的订单总数量
     *
     * @return
     * @throws Exception
     */
    @GET
    @Path("/getPayedOrdersNum/v1")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseMsg<Integer> getPayedOrdersNum() throws Exception {
        Integer orders = service.getPayedOrdersNum();

        ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
        responseMsg.setResponseBody(orders);
        return responseMsg;
    }

    /**
     * 按 本年、本月、本周、今日统计销售额
     * 参数：curYear|curMonth|curWeek|curDay
     * @return
     * @throws Exception
     */
    @GET
    @Path("/getSalesByAspect/v1/{aspect}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseMsg<List<TotalSalesAspectVo>> getTotalSalesByAspect(@PathParam("aspect") String aspect) throws Exception {
        ResponseMsg<List<TotalSalesAspectVo>> responseMsg = new ResponseMsg<List<TotalSalesAspectVo>>();

        // 检查维度是否被支持
        if (!checkIsSupportAspect(aspect)) {
            responseMsg.setRetCode("405");
            responseMsg.setErrorDesc("not support parameter. please use curYear|curMonth|curWeek|curDay.");
            return responseMsg;
        }

        List<TotalSalesAspectVo> list = service.getTotalSalesByAspect(aspect);

        responseMsg.setResponseBody(list);
        return responseMsg;
    }

    /**
     * 按 本年、本月、本周、今日统计下单量（包含未支付/已支付）
     * 参数：curYear|curMonth|curWeek|curDay
     * @return
     * @throws Exception
     */
    @GET
    @Path("/getOrdersByAspect/v1/{aspect}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseMsg<List<TotalOrdersAspectVo>> getOrdersByAspect(@PathParam("aspect") String aspect) throws Exception {
        ResponseMsg<List<TotalOrdersAspectVo>> responseMsg = new ResponseMsg<List<TotalOrdersAspectVo>>();

        // 检查维度是否被支持
        if (!checkIsSupportAspect(aspect)) {
            responseMsg.setRetCode("405");
            responseMsg.setErrorDesc("not support parameter. please use curYear|curMonth|curWeek|curDay.");
            return responseMsg;
        }

        List<TotalOrdersAspectVo> list = service.getOrdersByAspect(aspect);

        responseMsg.setResponseBody(list);
        return responseMsg;
    }

    /**
     * 判断是否是支持的类型 curYear|curMonth|curWeek|curDay
     * @param aspect
     * @return
     */
    private boolean checkIsSupportAspect(String aspect) {
        return StringUtils.equals(aspect, "curYear")
                || StringUtils.equals(aspect, "curMonth")
                || StringUtils.equals(aspect, "curWeek")
                || StringUtils.equals(aspect, "curDay");
    }
}
