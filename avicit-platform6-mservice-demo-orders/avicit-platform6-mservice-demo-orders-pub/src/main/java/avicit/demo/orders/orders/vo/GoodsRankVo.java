package avicit.demo.orders.orders.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品排行榜
 * @金航数码科技有限责任公司
 * @作者：ligl
 * @邮箱：ligl@avicit.com
 * @创建时间： 2019/6/11 8:52
 * @类说明：请填写
 * @修改记录：
 */
public class GoodsRankVo implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private String goodsId;

    /** 销售额 */
    private java.math.BigDecimal sales;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }
}
