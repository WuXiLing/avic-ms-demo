package avicit.demo.stock.stock.dto;

import javax.persistence.Id;

import avicit.demo.goods.goodsbase.dto.GoodsBaseDTO;
import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.LogField;
import avicit.platform6.core.annotation.log.PojoRemark;
import avicit.platform6.core.domain.BeanDTO;
import avicit.platform6.core.properties.PlatformConstant.LogType;

/**
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：请填写 @创建时间： 2019-06-01 11:21
 * @类说明：库存信息 @修改记录：
 */
@PojoRemark(table = "stock", object = "StockDTO", name = "库存信息")
public class StockDTO extends BeanDTO {
	private static final long serialVersionUID = 1L;

	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "id")
	/*
	 * id
	 */
	private java.lang.String id;

	@FieldRemark(column = "goods_id", field = "goodsId", name = "商品id")
	/*
	 * 商品id
	 */
	private java.lang.String goodsId;
	
	/**商品名称*/
	private String code;
	
	private GoodsBaseDTO goodsBaseDTO;

	/*
	 * 库存数量
	 */
	@FieldRemark(column = "num", field = "num", name = "库存数量")
	private Long num;
	/*
	 * 组织ID
	 */
	@LogField
	@FieldRemark(column = "org_id", field = "orgId", name = "组织ID")
	private java.lang.String orgId;
	@LogField

	@FieldRemark(column = "dept_id", field = "deptId", name = "部门ID")
	/*
	 * 部门ID
	 */
	private java.lang.String deptId;

	@FieldRemark(column = "sys_id", field = "sysId", name = "系统标识ID")
	/*
	 * 系统标识ID
	 */
	private java.lang.String sysId;

	@FieldRemark(column = "secret_level", field = "secretLevel", name = "密级")
	/*
	 * 密级
	 */
	private java.lang.String secretLevel;

	@FieldRemark(column = "sys_application_id", field = "sysApplicationId", name = "多应用ID")
	/*
	 * 多应用ID
	 */
	private java.lang.String sysApplicationId;
	/*
	 * 创建时间开始时间
	 */
	private java.util.Date creationDateBegin;
	/*
	 * 创建时间截止时间
	 */
	private java.util.Date creationDateEnd;
	/*
	 * 最后修改时间开始时间
	 */
	private java.util.Date lastUpdateDateBegin;
	/*
	 * 最后修改时间截止时间
	 */
	private java.util.Date lastUpdateDateEnd;

	@FieldRemark(column = "attribute_01", field = "attribute01", name = "预留字段1")
	/*
	 * 预留字段1
	 */
	private java.lang.String attribute01;

	@FieldRemark(column = "attribute_02", field = "attribute02", name = "预留字段2")
	/*
	 * 预留字段2
	 */
	private java.lang.String attribute02;

	@FieldRemark(column = "attribute_03", field = "attribute03", name = "预留字段3")
	/*
	 * 预留字段3
	 */
	private java.lang.String attribute03;

	@FieldRemark(column = "attribute_04", field = "attribute04", name = "预留字段4")
	/*
	 * 预留字段4
	 */
	private java.lang.String attribute04;

	@FieldRemark(column = "attribute_05", field = "attribute05", name = "预留字段5")
	/*
	 * 预留字段5
	 */
	private java.lang.String attribute05;

	@FieldRemark(column = "attribute_06", field = "attribute06", name = "预留字段6")
	/*
	 * 预留字段6
	 */
	private java.lang.String attribute06;

	@FieldRemark(column = "attribute_07", field = "attribute07", name = "预留字段7")
	/*
	 * 预留字段7
	 */
	private java.lang.String attribute07;

	@FieldRemark(column = "attribute_08", field = "attribute08", name = "预留字段8")
	/*
	 * 预留字段8
	 */
	private java.lang.String attribute08;

	@FieldRemark(column = "attribute_09", field = "attribute09", name = "预留字段9")
	/*
	 * 预留字段9
	 */
	private java.util.Date attribute09;
	/*
	 * 预留字段9开始时间
	 */
	private java.util.Date attribute09Begin;
	/*
	 * 预留字段9截止时间
	 */
	private java.util.Date attribute09End;

	@FieldRemark(column = "attribute_10", field = "attribute10", name = "预留字段10")
	/*
	 * 预留字段10
	 */
	private java.util.Date attribute10;
	/*
	 * 预留字段10开始时间
	 */
	private java.util.Date attribute10Begin;
	/*
	 * 预留字段10截止时间
	 */
	private java.util.Date attribute10End;

	@FieldRemark(column = "attribute_11", field = "attribute11", name = "预留字段11")
	/*
	 * 预留字段11
	 */
	private Long attribute11;

	@FieldRemark(column = "attribute_12", field = "attribute12", name = "预留字段12")
	/*
	 * 预留字段12
	 */
	private Long attribute12;

	@FieldRemark(column = "attribute_13", field = "attribute13", name = "预留字段13")
	/*
	 * 预留字段13
	 */
	private Long attribute13;

	@FieldRemark(column = "attribute_14", field = "attribute14", name = "预留字段14")
	/*
	 * 预留字段14
	 */
	private Long attribute14;

	@FieldRemark(column = "attribute_15", field = "attribute15", name = "预留字段15")
	/*
	 * 预留字段15
	 */
	private Long attribute15;

	public StockDTO() {
		super();
	}

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

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
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

	public GoodsBaseDTO getGoodsBaseDTO() {
		return goodsBaseDTO;
	}

	public void setGoodsBaseDTO(GoodsBaseDTO goodsBaseDTO) {
		this.goodsBaseDTO = goodsBaseDTO;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLogFormName() {
		if (super.logFormName == null || super.logFormName.equals("")) {
			return "库存信息";
		} else {
			return super.logFormName;
		}
	}

	public String getLogTitle() {
		if (super.logTitle == null || super.logTitle.equals("")) {
			return "库存信息";
		} else {
			return super.logTitle;
		}
	}

	public LogType getLogType() {
		if (super.logType == null || super.logType.equals("")) {
			return LogType.module_operate;
		} else {
			return super.logType;
		}
	}

}