package avicit.demo.goods.goodsdetail.dto;

import java.io.Serializable;

/**
 * 商品详细VO
 * 
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：请填写 
 * @创建时间： 2019-06-01 10:27
 * @类说明：商品详细信息 
 * @修改记录：
 */
public class GoodsDetailVO implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;

	/** id */
	private String id;

	/** 商品基本信息id */
	private String goodsId;

	/** 颜色 */
	private String color;

	/** 型号 */
	private String goodsSize;

	/** 描述 */
	private String description;
	
	/** 商家 */
	private String merchants;
	
	/** 是否自营 */
	private String proprietary;
	

	/**库存数量*/
	private Integer num;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getMerchants() {
		return merchants;
	}

	public void setMerchants(String merchants) {
		this.merchants = merchants;
	}

	public String getProprietary() {
		return proprietary;
	}

	public void setProprietary(String proprietary) {
		this.proprietary = proprietary;
	}
}