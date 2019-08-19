package avicit.demo.goods.goodsbase.dto;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import avicit.demo.goods.goodsdetail.dto.GoodsDetailDTO;
import avicit.demo.goods.goodsdetail.dto.GoodsDetailVO;

/**
 * 用于保存数据VO
 * 
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：请填写 
 * @创建时间： 2019-06-01 10:25
 * @类说明：商品基本信息
 * @修改记录：
 */
public class GoodsVO implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	/** id */
	private String id;

	/** 商品编码 */
	private String code;

	/** 商品名称 */
	private String name;

	/** 价格 */
	private BigDecimal price;

	/** 商品简介 */
	private String descriptionShort;

	/** 缩略图 */
	private String thumb;

	/** 商品状态 */
	private String [] status = new String[1];

	/** 颜色(详细属性) */
	private String [] color = new String[1];

	/** 型号(详细属性) */
	private String [] goodsSize = new String[1];

	/** 描述(详细属性) */
	private String description;

	/** 商家 */
	private String merchants;

	/** 是否自营 */
	private String proprietary;

	/** 库存数量 */
	private Integer num;

	public GoodsVO() {
		super();
	}

	public GoodsVO(GoodsBaseDTO dto, GoodsDetailVO dto1) {
		super();
		if(dto != null){
			BeanUtils.copyProperties(dto, this);
			if(dto.getThumb() != null){
				try {
					this.setThumb(new String(dto.getThumb(),"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			this.status[0] = dto.getStatus();
		}
		if(dto1 != null){
			BeanUtils.copyProperties(dto1, this);
			this.goodsSize[0] = dto1.getGoodsSize();
			this.color[0] = dto1.getColor();
		}
		this.setId(dto.getId());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescriptionShort() {
		return descriptionShort;
	}

	public void setDescriptionShort(String descriptionShort) {
		this.descriptionShort = descriptionShort;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String[] getStatus() {
		return status;
	}

	public void setStatus(String[] status) {
		this.status = status;
	}

	public String[] getColor() {
		return color;
	}

	public void setColor(String[] color) {
		this.color = color;
	}

	public String[] getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String[] goodsSize) {
		this.goodsSize = goodsSize;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 将{@link GoodsVO}中的数据转到{@link GoodsBaseDTO}
	 * 
	 */
	@JsonIgnore
	public GoodsBaseDTO getGoodsBaseDto() {
		GoodsBaseDTO dto = new GoodsBaseDTO();
		BeanUtils.copyProperties(this, dto);
		if(StringUtils.isNotBlank(this.getThumb())){
			dto.setThumb(this.getThumb().getBytes());
		}
//		dto.setId(id);
//		dto.setCode(code);
//		dto.setThumb(thumb);
//		dto.setPrice(price);
//		dto.setName(name);
//		dto.setDescriptionShort(descriptionShort);
		dto.setStatus(this.getStatus()[0]);
		return dto;
	}

	/**
	 * 将{@link GoodsVO}中的数据转到{@link GoodsDetailDTO}
	 * 
	 */
	@JsonIgnore
	public GoodsDetailDTO getGoodsDetailDTO() {
		GoodsDetailDTO dto = new GoodsDetailDTO();
		BeanUtils.copyProperties(this, dto);
		dto.setGoodsSize(this.getGoodsSize()[0]);
		dto.setColor(this.getColor()[0]);
		dto.setGoodsId(id);
		dto.setId("");
		return dto;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + Arrays.hashCode(color);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((descriptionShort == null) ? 0 : descriptionShort.hashCode());
		result = prime * result + Arrays.hashCode(goodsSize);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((merchants == null) ? 0 : merchants.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((proprietary == null) ? 0 : proprietary.hashCode());
		result = prime * result + Arrays.hashCode(status);
		result = prime * result + ((thumb == null) ? 0 : thumb.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GoodsVO other = (GoodsVO) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (!Arrays.equals(color, other.color))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (descriptionShort == null) {
			if (other.descriptionShort != null)
				return false;
		} else if (!descriptionShort.equals(other.descriptionShort))
			return false;
		if (!Arrays.equals(goodsSize, other.goodsSize))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (merchants == null) {
			if (other.merchants != null)
				return false;
		} else if (!merchants.equals(other.merchants))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (num == null) {
			if (other.num != null)
				return false;
		} else if (!num.equals(other.num))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (proprietary == null) {
			if (other.proprietary != null)
				return false;
		} else if (!proprietary.equals(other.proprietary))
			return false;
		if (!Arrays.equals(status, other.status))
			return false;
		if (thumb == null) {
			if (other.thumb != null)
				return false;
		} else if (!thumb.equals(other.thumb))
			return false;
		return true;
	}
}