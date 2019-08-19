package avicit.demo.goods.goodsbase.dto;

import java.util.Date;

import javax.persistence.Id;
import javax.ws.rs.FormParam;

import com.fasterxml.jackson.annotation.JsonIgnore;

import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.LogField;
import avicit.platform6.core.annotation.log.PojoRemark;
import avicit.platform6.core.domain.BeanDTO;
import avicit.platform6.core.properties.PlatformConstant.LogType;

/**
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：请填写 @创建时间： 2019-06-01 10:25
 * @类说明：商品基本信息 @修改记录：
 */
@PojoRemark(table = "goods_base", object = "GoodsBaseDTO", name = "商品基本信息")
public class GoodsBaseDTO extends BeanDTO {
	private static final long serialVersionUID = 1L;

	/** id */
	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "id")
	@FormParam("id")
	private String id;

	/** 商品编码 */
	@FieldRemark(column = "code", field = "code", name = "商品编码")
	@FormParam("code")
	private String code;

	/** 商品名称 */
	@FieldRemark(column = "name", field = "name", name = "商品名称")
	@FormParam("name")
	private String name;

	/** 价格 */
	@FieldRemark(column = "price", field = "price", name = "价格")
	@FormParam("price")
	private java.math.BigDecimal price;

	/** 商品简介 */
	@FieldRemark(column = "description_short", field = "descriptionShort", name = "商品简介")
	@FormParam("descriptionShort")
	private String descriptionShort;

	/** 商家 */
	@FieldRemark(column = "merchants", field = "merchants", name = "商家")
	@FormParam("merchants")
	private String merchants;

	/** 是否自营 */
	@FieldRemark(column = "proprietary", field = "proprietary", name = "是否自营")
	@FormParam("proprietary")
	private String proprietary;

	/** 缩略图 */
	@FieldRemark(column = "thumb", field = "thumb", name = "缩略图")
	@FormParam("thumb")
	private byte[] thumb;

	/** 商品状态 */
	@FieldRemark(column = "status", field = "status", name = "商品状态")
	@FormParam("status")
	private String status;

	/** 组织ID */
	@JsonIgnore
	@LogField
	@FieldRemark(column = "org_id", field = "orgId", name = "组织ID")
	private String orgId;

	/** 部门ID */
	@JsonIgnore
	@LogField
	@FieldRemark(column = "dept_id", field = "deptId", name = "部门ID")
	private String deptId;

	/** 系统标识ID */
	@JsonIgnore
	@FieldRemark(column = "sys_id", field = "sysId", name = "系统标识ID")
	private String sysId;

	/** 密级 */
	@JsonIgnore
	@FieldRemark(column = "secret_level", field = "secretLevel", name = "密级")
	private String secretLevel;

	/** 多应用ID */
	@JsonIgnore
	@FieldRemark(column = "sys_application_id", field = "sysApplicationId", name = "多应用ID")
	private String sysApplicationId;

	/** 创建时间开始时间 */
	@JsonIgnore
	private Date creationDateBegin;

	/** 创建时间截止时间 */
	@JsonIgnore
	private Date creationDateEnd;

	/** 最后修改时间开始时间 */
	@JsonIgnore
	private Date lastUpdateDateBegin;

	/** 最后修改时间截止时间 */
	@JsonIgnore
	private Date lastUpdateDateEnd;

	/** 预留字段1 */
	@FieldRemark(column = "attribute_01", field = "attribute01", name = "预留字段1")
	@JsonIgnore
	private String attribute01;

	/** 预留字段2 */
	@FieldRemark(column = "attribute_02", field = "attribute02", name = "预留字段2")
	@JsonIgnore
	private String attribute02;

	/** 预留字段3 */
	@FieldRemark(column = "attribute_03", field = "attribute03", name = "预留字段3")
	@JsonIgnore
	private String attribute03;

	/** 预留字段4 */
	@FieldRemark(column = "attribute_04", field = "attribute04", name = "预留字段4")
	@JsonIgnore
	private String attribute04;

	/** 预留字段5 */
	@FieldRemark(column = "attribute_05", field = "attribute05", name = "预留字段5")
	@JsonIgnore
	private String attribute05;

	/** 预留字段6 */
	@FieldRemark(column = "attribute_06", field = "attribute06", name = "预留字段6")
	@JsonIgnore
	private String attribute06;

	/** 预留字段7 */
	@FieldRemark(column = "attribute_07", field = "attribute07", name = "预留字段7")
	@JsonIgnore
	private String attribute07;

	/** 预留字段8 */
	@FieldRemark(column = "attribute_08", field = "attribute08", name = "预留字段8")
	@JsonIgnore
	private String attribute08;

	/** 预留字段9 */
	@FieldRemark(column = "attribute_09", field = "attribute09", name = "预留字段9")
	@JsonIgnore
	private Date attribute09;

	/** 预留字段9开始时间 */
	@JsonIgnore
	private Date attribute09Begin;

	/** 预留字段9截止时间 */
	@JsonIgnore
	private Date attribute09End;

	/** 预留字段10、 */
	@FieldRemark(column = "attribute_10", field = "attribute10", name = "预留字段10")
	@JsonIgnore
	private Date attribute10;

	/** 预留字段10开始时间 */
	@JsonIgnore
	private Date attribute10Begin;

	/** 预留字段10截止时间 */
	@JsonIgnore
	private Date attribute10End;

	/** 预留字段11 */
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
	@JsonIgnore
	@FieldRemark(column = "attribute_14", field = "attribute14", name = "预留字段14")
	private Long attribute14;

	/** 预留字段15 */
	@FieldRemark(column = "attribute_15", field = "attribute15", name = "预留字段15")
	@JsonIgnore
	private Long attribute15;

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

	public java.math.BigDecimal getPrice() {
		return price;
	}

	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}

	public String getDescriptionShort() {
		return descriptionShort;
	}

	public void setDescriptionShort(String descriptionShort) {
		this.descriptionShort = descriptionShort;
	}

	public byte[] getThumb() {
		return thumb;
	}

	public void setThumb(byte[] thumb) {
		this.thumb = thumb;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getSecretLevel() {
		return secretLevel;
	}

	public void setSecretLevel(String secretLevel) {
		this.secretLevel = secretLevel;
	}

	public String getSysApplicationId() {
		return sysApplicationId;
	}

	public void setSysApplicationId(String sysApplicationId) {
		this.sysApplicationId = sysApplicationId;
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

	@JsonIgnore
	public Date getCreationDateBegin() {
		return creationDateBegin;
	}

	public void setCreationDateBegin(Date creationDateBegin) {
		this.creationDateBegin = creationDateBegin;
	}

	@JsonIgnore
	public Date getCreationDateEnd() {
		return creationDateEnd;
	}

	public void setCreationDateEnd(Date creationDateEnd) {
		this.creationDateEnd = creationDateEnd;
	}

	@JsonIgnore
	public Date getLastUpdateDateBegin() {
		return lastUpdateDateBegin;
	}

	public void setLastUpdateDateBegin(Date lastUpdateDateBegin) {
		this.lastUpdateDateBegin = lastUpdateDateBegin;
	}

	@JsonIgnore
	public Date getLastUpdateDateEnd() {
		return lastUpdateDateEnd;
	}

	public void setLastUpdateDateEnd(Date lastUpdateDateEnd) {
		this.lastUpdateDateEnd = lastUpdateDateEnd;
	}

	public String getAttribute01() {
		return attribute01;
	}

	public void setAttribute01(String attribute01) {
		this.attribute01 = attribute01;
	}

	public String getAttribute02() {
		return attribute02;
	}

	public void setAttribute02(String attribute02) {
		this.attribute02 = attribute02;
	}

	public String getAttribute03() {
		return attribute03;
	}

	public void setAttribute03(String attribute03) {
		this.attribute03 = attribute03;
	}

	public String getAttribute04() {
		return attribute04;
	}

	public void setAttribute04(String attribute04) {
		this.attribute04 = attribute04;
	}

	public String getAttribute05() {
		return attribute05;
	}

	public void setAttribute05(String attribute05) {
		this.attribute05 = attribute05;
	}

	public String getAttribute06() {
		return attribute06;
	}

	public void setAttribute06(String attribute06) {
		this.attribute06 = attribute06;
	}

	public String getAttribute07() {
		return attribute07;
	}

	public void setAttribute07(String attribute07) {
		this.attribute07 = attribute07;
	}

	public String getAttribute08() {
		return attribute08;
	}

	public void setAttribute08(String attribute08) {
		this.attribute08 = attribute08;
	}

	public Date getAttribute09() {
		return attribute09;
	}

	public void setAttribute09(Date attribute09) {
		this.attribute09 = attribute09;
	}

	public Date getAttribute09Begin() {
		return attribute09Begin;
	}

	public void setAttribute09Begin(Date attribute09Begin) {
		this.attribute09Begin = attribute09Begin;
	}

	public Date getAttribute09End() {
		return attribute09End;
	}

	public void setAttribute09End(Date attribute09End) {
		this.attribute09End = attribute09End;
	}

	public Date getAttribute10() {
		return attribute10;
	}

	public void setAttribute10(Date attribute10) {
		this.attribute10 = attribute10;
	}

	public Date getAttribute10Begin() {
		return attribute10Begin;
	}

	public void setAttribute10Begin(Date attribute10Begin) {
		this.attribute10Begin = attribute10Begin;
	}

	public Date getAttribute10End() {
		return attribute10End;
	}

	public void setAttribute10End(Date attribute10End) {
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
			return "商品基本信息";
		} else {
			return super.logFormName;
		}
	}

	@JsonIgnore
	public String getLogTitle() {
		if (super.logTitle == null || super.logTitle.equals("")) {
			return "商品基本信息";
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