package avicit.demo.goods.goodsthumb.dto;

import javax.persistence.Id;
import javax.ws.rs.FormParam;

import com.fasterxml.jackson.annotation.JsonIgnore;

import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.LogField;
import avicit.platform6.core.annotation.log.PojoRemark;
import avicit.platform6.core.domain.BeanDTO;
import avicit.platform6.core.properties.PlatformConstant.LogType;

/**
 * 商品缩略图DTO
 * 
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：请填写 
 * @创建时间： 2019-06-01 10:27
 * @类说明：商品详细信息 
 * @修改记录：
 */
@PojoRemark(table = "goods_thumb", object = "GoodsThumbDTO", name = "商品缩略图信息")
public class GoodsThumbDTO extends BeanDTO {
	
	private static final long serialVersionUID = 1L;

	/** id */
	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "id")
	@FormParam("id")
	private java.lang.String id;

	/** 商品基本信息id */
	@FieldRemark(column = "goods_id", field = "goodsId", name = "商品基本信息id")
	@FormParam("goodsId")
	private java.lang.String goodsId;

	/** 颜色 */
	@FieldRemark(column = "color", field = "color", name = "颜色")
	@FormParam("color")
	private java.lang.String color;

	/** 型号 */
	@FieldRemark(column = "goods_size", field = "goodsSize", name = "型号")
	@FormParam("goodsSize")
	private java.lang.String goodsSize;

	/** 描述 */
	@FieldRemark(column = "description", field = "description", name = "描述")
	@FormParam("description")
	private java.lang.String description;

	/** 组织ID */
	@LogField
	@FieldRemark(column = "org_id", field = "orgId", name = "组织ID")
	@JsonIgnore
	private java.lang.String orgId;

	/** 部门ID */
	@LogField
	@FieldRemark(column = "dept_id", field = "deptId", name = "部门ID")
	@JsonIgnore
	private java.lang.String deptId;

	/** 系统标识ID */
	@FieldRemark(column = "sys_id", field = "sysId", name = "系统标识ID")
	@JsonIgnore
	private java.lang.String sysId;

	@FieldRemark(column = "secret_level", field = "secretLevel", name = "密级")
	/** 密级 */
	@JsonIgnore
	private java.lang.String secretLevel;

	@FieldRemark(column = "sys_application_id", field = "sysApplicationId", name = "多应用ID")
	
	/** 多应用ID */
	@JsonIgnore
	private java.lang.String sysApplicationId;
	
	/** 创建时间开始时间 */
	@JsonIgnore
	private java.util.Date creationDateBegin;
	
	/** 创建时间截止时间 */
	@JsonIgnore
	private java.util.Date creationDateEnd;
	
	/** 最后修改时间开始时间 */
	@JsonIgnore
	private java.util.Date lastUpdateDateBegin;
	
	/** 最后修改时间截止时间 */
	@JsonIgnore
	private java.util.Date lastUpdateDateEnd;

	/** 预留字段1 */
	@FieldRemark(column = "attribute_01", field = "attribute01", name = "预留字段1")
	@JsonIgnore
	private java.lang.String attribute01;

	/** 预留字段2 */
	@FieldRemark(column = "attribute_02", field = "attribute02", name = "预留字段2")
	@JsonIgnore
	private java.lang.String attribute02;

	/** 预留字段3 */
	@FieldRemark(column = "attribute_03", field = "attribute03", name = "预留字段3")
	@JsonIgnore
	private java.lang.String attribute03;

	/** 预留字段4 */
	@FieldRemark(column = "attribute_04", field = "attribute04", name = "预留字段4")
	@JsonIgnore
	private java.lang.String attribute04;

	/** 预留字段5 */
	@FieldRemark(column = "attribute_05", field = "attribute05", name = "预留字段5")
	@JsonIgnore
	private java.lang.String attribute05;

	/** 预留字段6 */
	@FieldRemark(column = "attribute_06", field = "attribute06", name = "预留字段6")
	@JsonIgnore
	private java.lang.String attribute06;

	/** 预留字段7 */
	@FieldRemark(column = "attribute_07", field = "attribute07", name = "预留字段7")
	@JsonIgnore
	private java.lang.String attribute07;

	/** 预留字段8 */
	@FieldRemark(column = "attribute_08", field = "attribute08", name = "预留字段8")
	@JsonIgnore
	private java.lang.String attribute08;

	/** 预留字段9 */
	@FieldRemark(column = "attribute_09", field = "attribute09", name = "预留字段9")
	@JsonIgnore
	private java.util.Date attribute09;
	
	/** 预留字段9开始时间*/
	@JsonIgnore
	private java.util.Date attribute09Begin;
	
	/** 预留字段9截止时间*/
	@JsonIgnore
	private java.util.Date attribute09End;

	/** 预留字段10*/
	@FieldRemark(column = "attribute_10", field = "attribute10", name = "预留字段10")
	@JsonIgnore
	private java.util.Date attribute10;
	
	/** 预留字段10开始时间*/
	@JsonIgnore
	private java.util.Date attribute10Begin;
	
	/** 预留字段10截止时间 */
	@JsonIgnore
	private java.util.Date attribute10End;

	/** 预留字段11*/
	@FieldRemark(column = "attribute_11", field = "attribute11", name = "预留字段11")
	@JsonIgnore
	private Long attribute11;

	/** 预留字段12 */
	@FieldRemark(column = "attribute_12", field = "attribute12", name = "预留字段12")
	@JsonIgnore
	private Long attribute12;

	/** 预留字段13 */
	@FieldRemark(column = "attribute_13", field = "attribute13", name = "预留字段13")
	@JsonIgnore
	private Long attribute13;

	/** 预留字段14 */
	@FieldRemark(column = "attribute_14", field = "attribute14", name = "预留字段14")
	@JsonIgnore
	private Long attribute14;

	/** 预留字段15 */
	@FieldRemark(column = "attribute_15", field = "attribute15", name = "预留字段15")
	@JsonIgnore
	private Long attribute15;

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(java.lang.String goodsId) {
		this.goodsId = goodsId;
	}

	public java.lang.String getColor() {
		return color;
	}

	public void setColor(java.lang.String color) {
		this.color = color;
	}

	public java.lang.String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(java.lang.String goodsSize) {
		this.goodsSize = goodsSize;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.String getOrgId() {
		return orgId;
	}

	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	public java.lang.String getDeptId() {
		return deptId;
	}

	public void setDeptId(java.lang.String deptId) {
		this.deptId = deptId;
	}

	public java.lang.String getSysId() {
		return sysId;
	}

	public void setSysId(java.lang.String sysId) {
		this.sysId = sysId;
	}

	public java.lang.String getSecretLevel() {
		return secretLevel;
	}

	public void setSecretLevel(java.lang.String secretLevel) {
		this.secretLevel = secretLevel;
	}

	public java.lang.String getSysApplicationId() {
		return sysApplicationId;
	}

	public void setSysApplicationId(java.lang.String sysApplicationId) {
		this.sysApplicationId = sysApplicationId;
	}

	public java.util.Date getCreationDateBegin() {
		return creationDateBegin;
	}

	public void setCreationDateBegin(java.util.Date creationDateBegin) {
		this.creationDateBegin = creationDateBegin;
	}

	public java.util.Date getCreationDateEnd() {
		return creationDateEnd;
	}

	public void setCreationDateEnd(java.util.Date creationDateEnd) {
		this.creationDateEnd = creationDateEnd;
	}

	public java.util.Date getLastUpdateDateBegin() {
		return lastUpdateDateBegin;
	}

	public void setLastUpdateDateBegin(java.util.Date lastUpdateDateBegin) {
		this.lastUpdateDateBegin = lastUpdateDateBegin;
	}

	public java.util.Date getLastUpdateDateEnd() {
		return lastUpdateDateEnd;
	}

	public void setLastUpdateDateEnd(java.util.Date lastUpdateDateEnd) {
		this.lastUpdateDateEnd = lastUpdateDateEnd;
	}

	public java.lang.String getAttribute01() {
		return attribute01;
	}

	public void setAttribute01(java.lang.String attribute01) {
		this.attribute01 = attribute01;
	}

	public java.lang.String getAttribute02() {
		return attribute02;
	}

	public void setAttribute02(java.lang.String attribute02) {
		this.attribute02 = attribute02;
	}

	public java.lang.String getAttribute03() {
		return attribute03;
	}

	public void setAttribute03(java.lang.String attribute03) {
		this.attribute03 = attribute03;
	}

	public java.lang.String getAttribute04() {
		return attribute04;
	}

	public void setAttribute04(java.lang.String attribute04) {
		this.attribute04 = attribute04;
	}

	public java.lang.String getAttribute05() {
		return attribute05;
	}

	public void setAttribute05(java.lang.String attribute05) {
		this.attribute05 = attribute05;
	}

	public java.lang.String getAttribute06() {
		return attribute06;
	}

	public void setAttribute06(java.lang.String attribute06) {
		this.attribute06 = attribute06;
	}

	public java.lang.String getAttribute07() {
		return attribute07;
	}

	public void setAttribute07(java.lang.String attribute07) {
		this.attribute07 = attribute07;
	}

	public java.lang.String getAttribute08() {
		return attribute08;
	}

	public void setAttribute08(java.lang.String attribute08) {
		this.attribute08 = attribute08;
	}

	public java.util.Date getAttribute09() {
		return attribute09;
	}

	public void setAttribute09(java.util.Date attribute09) {
		this.attribute09 = attribute09;
	}

	public java.util.Date getAttribute09Begin() {
		return attribute09Begin;
	}

	public void setAttribute09Begin(java.util.Date attribute09Begin) {
		this.attribute09Begin = attribute09Begin;
	}

	public java.util.Date getAttribute09End() {
		return attribute09End;
	}

	public void setAttribute09End(java.util.Date attribute09End) {
		this.attribute09End = attribute09End;
	}

	public java.util.Date getAttribute10() {
		return attribute10;
	}

	public void setAttribute10(java.util.Date attribute10) {
		this.attribute10 = attribute10;
	}

	public java.util.Date getAttribute10Begin() {
		return attribute10Begin;
	}

	public void setAttribute10Begin(java.util.Date attribute10Begin) {
		this.attribute10Begin = attribute10Begin;
	}

	public java.util.Date getAttribute10End() {
		return attribute10End;
	}

	public void setAttribute10End(java.util.Date attribute10End) {
		this.attribute10End = attribute10End;
	}

	public Long getAttribute11() {
		return attribute11;
	}

	public void setAttribute11(Long attribute11) {
		this.attribute11 = attribute11;
	}

	public Long getAttribute12() {
		return attribute12;
	}

	public void setAttribute12(Long attribute12) {
		this.attribute12 = attribute12;
	}

	public Long getAttribute13() {
		return attribute13;
	}

	public void setAttribute13(Long attribute13) {
		this.attribute13 = attribute13;
	}

	public Long getAttribute14() {
		return attribute14;
	}

	public void setAttribute14(Long attribute14) {
		this.attribute14 = attribute14;
	}

	public Long getAttribute15() {
		return attribute15;
	}

	public void setAttribute15(Long attribute15) {
		this.attribute15 = attribute15;
	}

	@JsonIgnore
	public String getLogFormName() {
		if (super.logFormName == null || super.logFormName.equals("")) {
			return "商品详细信息";
		} else {
			return super.logFormName;
		}
	}

	@JsonIgnore
	public String getLogTitle() {
		if (super.logTitle == null || super.logTitle.equals("")) {
			return "商品详细信息";
		} else {
			return super.logTitle;
		}
	}

	@JsonIgnore
	public LogType getLogType() {
		if (super.logType == null || super.logType.equals("")) {
			return LogType.module_operate;
		} else {
			return super.logType;
		}
	}

}