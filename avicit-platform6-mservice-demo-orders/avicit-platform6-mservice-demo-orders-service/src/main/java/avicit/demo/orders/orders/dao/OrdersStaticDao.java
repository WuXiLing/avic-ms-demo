package avicit.demo.orders.orders.dao;

import avicit.demo.orders.orders.dto.OrdersDTO;
import avicit.demo.orders.orders.vo.GoodsRankVo;
import avicit.demo.orders.orders.vo.TotalOrdersAspectVo;
import avicit.demo.orders.orders.vo.TotalSalesAspectVo;
import avicit.platform6.core.mybatis.MyBatisRepository;
import avicit.platform6.core.mybatis.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 订单统计dao
 *
 * @金航数码科技有限责任公司
 * @作者：ligl
 * @邮箱：ligl@avicit.com
 * @创建时间： 2019/6/11 8:58
 * @类说明：请填写
 * @修改记录：
 */
@MyBatisRepository
public interface OrdersStaticDao {

	/**
	 * 统计商品销售额
	 * @return
	 */
	List<GoodsRankVo> staticGoodsRank();

    /**
     *  总销售额
     * @return
     */
    BigDecimal getGoodsTotalSales();

    /**
     * 有效订单数：状态为 待支付、已支付的订单总数量
     * @return
     */
    Integer getEffectiveOrdersNum();

    /**
     * 支付笔数：状态为 已支付的订单总数量
     * @return
     */
    Integer getPayedOrdersNum();

    /**
     * 按 本年、本月、本周、今日统计销售额
     * @param aspect
     * @return
     */
    List<TotalSalesAspectVo> getTotalSalesByAspect(@Param("aspect")String aspect);

    /**
     * 按 本年、本月、本周、今日统计下单量
     * @param aspect
     * @return
     */
    List<TotalOrdersAspectVo> getOrdersByAspect(@Param("aspect")String aspect);
}
