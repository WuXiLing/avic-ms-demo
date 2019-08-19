package avicit.demo.orders.orders.vo;

import java.io.Serializable;

/**
 * 按维度统计的销售额VO
 * @金航数码科技有限责任公司
 * @作者：ligl
 * @邮箱：ligl@avicit.com
 * @创建时间： 2019/6/11 11:53
 * @类说明：请填写
 * @修改记录：
 */
public class TotalSalesAspectVo implements Serializable, Cloneable{
    private static final long serialVersionUID = 1L;

    /** 维度统计下的key值，如 2019[年]， 12[月]， 31[日]，注意实际封装时没带单位*/
    private String rowKey;

    private Integer sales;

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }
}
