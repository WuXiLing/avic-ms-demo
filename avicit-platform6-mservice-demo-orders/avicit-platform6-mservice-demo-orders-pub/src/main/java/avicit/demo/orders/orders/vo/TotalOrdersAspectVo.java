package avicit.demo.orders.orders.vo;

import java.io.Serializable;

/**
 * 按维度统计的下单量VO
 * @金航数码科技有限责任公司
 * @作者：ligl
 * @邮箱：ligl@avicit.com
 * @创建时间： 2019/6/11 13:31
 * @类说明：请填写
 * @修改记录：
 */
public class TotalOrdersAspectVo implements Serializable, Cloneable{
    private static final long serialVersionUID = 1L;

    /** 维度统计下的key值，如 2019[年]， 12[月]， 31[日]，注意实际封装时没带单位*/
    private String rowKey;

    /** 有效订单量 */
    private Integer orders;

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }
}
