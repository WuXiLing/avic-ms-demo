package avicit.demo.orders.orders.service;

import avicit.demo.orders.orders.dao.OrdersDao;
import avicit.demo.orders.orders.dao.OrdersStaticDao;
import avicit.demo.orders.orders.vo.GoodsRankVo;
import avicit.demo.orders.orders.vo.TotalOrdersAspectVo;
import avicit.demo.orders.orders.vo.TotalSalesAspectVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 *
 * @金航数码科技有限责任公司
 * @作者：ligl
 * @邮箱：ligl@avicit.com
 * @创建时间： 2019/6/11 8:58
 * @类说明：请填写
 * @修改记录：
 */
@Service
public class OrdersStaticService implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(OrdersStaticService.class);

    private static final long serialVersionUID = 1L;

    @Autowired
    private OrdersStaticDao dao;

    /**
     * 商品排行
     * @return
     */
    public List<GoodsRankVo> getGoodsRank() {
        return dao.staticGoodsRank();
    }

    /**
     * 商品总销售额
     * @return
     */
    public BigDecimal getGoodsTotalSales() {
        return dao.getGoodsTotalSales();
    }

    /**
     * 有效订单数：状态为 待支付、已支付的订单总数量
     * @return
     */
    public Integer getEffectiveOrdersNum() {
        return dao.getEffectiveOrdersNum();
    }

    /**
     * 支付笔数：状态为 已支付的订单总数量
     * @return
     */
    public Integer getPayedOrdersNum() {
        return dao.getPayedOrdersNum();
    }

    /**
     * 按 本年、本月、本周、今日统计销售额
     * @param aspect
     * @return
     */
    public List<TotalSalesAspectVo> getTotalSalesByAspect(String aspect) {
        return dao.getTotalSalesByAspect(aspect);
    }

    /**
     * 按 本年、本月、本周、今日统计下单量
     * @param aspect
     * @return
     */
    public List<TotalOrdersAspectVo> getOrdersByAspect(String aspect) {
        return dao.getOrdersByAspect(aspect);
    }
}
