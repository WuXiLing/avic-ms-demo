package avicit.demo.goods.goodsbase.service;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avicit.demo.goods.goodsbase.dao.GoodsBaseDao;
import avicit.demo.goods.goodsbase.dto.GoodsBaseDTO;
import avicit.demo.goods.goodsbase.dto.GoodsVO;
import avicit.demo.goods.goodsdetail.dto.GoodsDetailDTO;
import avicit.demo.goods.goodsdetail.dto.GoodsDetailVO;
import avicit.demo.goods.goodsdetail.service.GoodsDetailService;
import avicit.demo.stock.stock.api.StockApi;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.PojoUtil;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.properties.PlatformConstant.OpType;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;
import sun.nio.ch.DirectBuffer;

/**
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：avicitdev@avicit.com @创建时间： 2019-06-01 10:25
 * @类说明：请填写 @修改记录：
 */
@Service
public class GoodsBaseService implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(GoodsBaseService.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	private GoodsBaseDao goodsBaseDao;

	/** 商品详细Service */
	@Autowired
	private GoodsDetailService goodsDetailService;

	@Autowired
	private StockApi stockApi;

	/** 内存集合 */
	private static List<ByteBuffer> memoryList = new ArrayList<ByteBuffer>();

	/** 100M大小 */
	private static int size = 10485760;

	/**
	 * 按条件分页查询
	 * 
	 * @param queryReqBean 分页
	 * @param orderBy      排序
	 * @return QueryRespBean<GoodsBaseDTO>
	 * @throws Exception
	 */
	public QueryRespBean<GoodsBaseDTO> searchGoodsBaseByPage(QueryReqBean<GoodsBaseDTO> queryReqBean, String orderBy)
			throws Exception {
		try {
			PageHelper.startPage(queryReqBean.getPageParameter());
			GoodsBaseDTO searchParams = queryReqBean.getSearchParams();
			Page<GoodsBaseDTO> dataList = goodsBaseDao.searchGoodsBaseByPage(searchParams, orderBy);
			QueryRespBean<GoodsBaseDTO> queryRespBean = new QueryRespBean<GoodsBaseDTO>();
			queryRespBean.setResult(dataList);
			return queryRespBean;
		} catch (Exception e) {
			logger.error("searchGoodsBaseByPage出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 按条件or查询的分页查询
	 * 
	 * @param queryReqBean 分页
	 * @param orderBy      排序
	 * @return QueryRespBean<GoodsBaseDTO>
	 * @throws Exception
	 */
	public QueryRespBean<GoodsBaseDTO> searchGoodsBaseByPageOr(QueryReqBean<GoodsBaseDTO> queryReqBean, String orderBy)
			throws Exception {
		try {
			PageHelper.startPage(queryReqBean.getPageParameter());
			GoodsBaseDTO searchParams = queryReqBean.getSearchParams();
			Page<GoodsBaseDTO> dataList = goodsBaseDao.searchGoodsBaseByPageOr(searchParams, orderBy);
			QueryRespBean<GoodsBaseDTO> queryRespBean = new QueryRespBean<GoodsBaseDTO>();

			queryRespBean.setResult(dataList);
			return queryRespBean;
		} catch (Exception e) {
			logger.error("searchGoodsBaseByPage出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 按条件查询
	 * 
	 * @param queryReqBean 条件
	 * @return List<GoodsBaseDTO>
	 * @throws Exception
	 */
	public List<GoodsBaseDTO> searchGoodsBase(QueryReqBean<GoodsBaseDTO> queryReqBean) throws Exception {
		try {
			GoodsBaseDTO searchParams = queryReqBean.getSearchParams();
			List<GoodsBaseDTO> dataList = goodsBaseDao.searchGoodsBase(searchParams);
			return dataList;
		} catch (Exception e) {
			logger.error("searchGoodsBase出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 通过主键查询单条记录
	 * 
	 * @param id 主键id
	 * @return GoodsBaseDTO
	 * @throws Exception
	 */
	public GoodsBaseDTO queryGoodsBaseByPrimaryKey(String id) throws Exception {
		try {
			GoodsBaseDTO dto = goodsBaseDao.findGoodsBaseById(id);
			// 记录日志
			if (dto != null) {
				SysLogUtil.log4Query(dto);
			}
			return dto;
		} catch (Exception e) {
			logger.error("queryGoodsBaseByPrimaryKey出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 通过主键查询单条记录
	 * 
	 * @param id 主键id
	 * @return GoodsVO
	 * @throws Exception
	 */
	public GoodsVO queryGoodsVOByPrimaryKey(String id) throws Exception {
		try {
			GoodsBaseDTO dto = goodsBaseDao.findGoodsBaseById(id);
			GoodsDetailVO dto1 = goodsDetailService.getByGoodIds(id);

			GoodsVO goodsVO = new GoodsVO(dto, dto1);
			// 记录日志
			if (dto != null) {
				SysLogUtil.log4Query(dto);
			}
			return goodsVO;
		} catch (Exception e) {
			logger.error("queryGoodsBaseByPrimaryKey出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 新增对象
	 * 
	 * @param dto 保存对象
	 * @return String
	 * @throws Exception
	 */
	public String insertGoodsBase(GoodsBaseDTO dto) throws Exception {
		try {
			// start add by lgl at 20190613 商品名重复判定
			// check name isRepect
			if (isNameRepeat(dto)) {
				throw new Exception("insertGoodsBase失败：商品名重复");
			}
			// end add by lgl at 20190613 商品名重复判定

			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			initSysinfo(dto);
			goodsBaseDao.insertGoodsBase(dto);
			// 记录日志
			if (dto != null) {
				SysLogUtil.log4Insert(dto);
			}
			return id;
		} catch (Exception e) {
			logger.error("insertGoodsBase出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 批量新增对象
	 * 
	 * @param dtoList 保存对象集合
	 * @return int
	 * @throws Exception
	 */
	public int insertGoodsBaseList(List<GoodsBaseDTO> dtoList) throws Exception {
		List<GoodsBaseDTO> beanList = new ArrayList<GoodsBaseDTO>();
		for (GoodsBaseDTO dto : dtoList) {
			String id = ComUtil.getId();
			dto.setId(id);
			initSysinfo(dto);
			PojoUtil.setSysProperties(dto, OpType.insert);
			// 记录日志
			if (dto != null) {
				SysLogUtil.log4Insert(dto);
			}
			beanList.add(dto);
		}
		try {
			return goodsBaseDao.insertGoodsBaseList(beanList);
		} catch (Exception e) {
			logger.error("insertGoodsBaseList出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 修改对象全部字段
	 * 
	 * @param dto 修改对象
	 * @return int
	 * @throws Exception
	 */
	public int updateGoodsBase(GoodsBaseDTO dto) throws Exception {
		// 记录日志
		GoodsBaseDTO old = findById(dto.getId());
		if (old != null) {
			SysLogUtil.log4Update(dto, old);
		}
		PojoUtil.setSysProperties(dto, OpType.update);
		PojoUtil.copyProperties(old, dto, true);
		initSysinfo(old);
		int ret = goodsBaseDao.updateGoodsBaseAll(old);
		if (ret == 0) {
			throw new DaoException("数据失效，请重新更新");
		}
		return ret;

	}

	/**
	 * 修改对象部分字段
	 * 
	 * @param dto 修改对象
	 * @return int
	 * @throws Exception
	 */
	public int updateGoodsBaseSensitive(GoodsBaseDTO dto) throws Exception {
		try {
			// 记录日志
			GoodsBaseDTO old = findById(dto.getId());
			// start modify by lgl at 20190613 商品名重复校验
			if (old == null) {
				throw new Exception("更新商品失败：商品不存在！");
			} else {
				SysLogUtil.log4Update(dto, old);
			}

			// check name isRepect
			if (isNameRepeat(dto)) {
				throw new Exception("更新商品失败：商品名重复");
			}
			// start modify by lgl at 20190613 商品名重复校验
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto, true);
			initSysinfo(dto);
			int count = goodsBaseDao.updateGoodsBaseSensitive(dto);
			if (count == 0) {
				throw new DaoException("数据失效，请重新更新");
			}
			return count;
		} catch (Exception e) {
			logger.error("updateGoodsBaseSensitive出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}

	}

	/**
	 * 判断商品名称是否重复
	 * 
	 * @param dto
	 * @return
	 */
	private boolean isNameRepeat(GoodsBaseDTO dto) throws Exception {
		if (null == dto || StringUtils.isEmpty(dto.getName())) {
			throw new Exception("商品名称不能为空");
		}

		GoodsBaseDTO dbDto = goodsBaseDao.findGoodsBaseByName(dto.getName());
		if (null == dbDto) {
			return false;
		} else if (StringUtils.isEmpty(dto.getId())) {
			return true;
		} else if (StringUtils.equals(dbDto.getId(), dto.getId())) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 批量更新对象
	 * 
	 * @param dtoList 修改对象集合
	 * @return int
	 * @throws Exception
	 */
	public int updateGoodsBaseList(List<GoodsBaseDTO> dtoList) throws Exception {
		try {
			return goodsBaseDao.updateGoodsBaseList(dtoList);
		} catch (Exception e) {
			logger.error("updateGoodsBaseList出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}

	}

	/**
	 * 按主键单条删除
	 * 
	 * @param id 主键id
	 * @return int
	 * @throws Exception
	 */
	public int deleteGoodsBaseById(String id) throws Exception {
		if (StringUtils.isEmpty(id)) {
			throw new Exception("删除失败！传入的参数主键为null");
		}
		try {
			// 先删除详细
			this.goodsDetailService.deleteGoodsDetailByGoodsBaseId(id);

			// 删除库存
			this.stockApi.deleteStockByGoodsId(id);

			// 记录日志
			GoodsBaseDTO obje = findById(id);
			if (obje != null) {
				SysLogUtil.log4Delete(obje);
			}
			return goodsBaseDao.deleteGoodsBaseById(id);
		} catch (Exception e) {
			logger.error("deleteGoodsBaseById出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 批量删除数据
	 * 
	 * @param ids id的数组
	 * @return int
	 * @throws Exception
	 */
	public int deleteGoodsBaseByIds(String[] ids) throws Exception {
		int result = 0;
		for (String id : ids) {
			deleteGoodsBaseById(id);
			result++;
		}
		return result;
	}

	/**
	 * 批量删除数据2
	 * 
	 * @param idList 主键集合
	 * @return int
	 * @throws Exception
	 */
	public int deleteGoodsBaseList(List<String> idList) throws Exception {
		try {
			return goodsBaseDao.deleteGoodsBaseList(idList);
		} catch (Exception e) {
			logger.error("deleteGoodsBaseList出错：", e);
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 日志专用，内部方法，不再记录日志
	 * 
	 * @param id 主键id
	 * @return GoodsBaseDTO
	 * @throws Exception
	 */
	private GoodsBaseDTO findById(String id) throws Exception {
		try {
			GoodsBaseDTO dto = goodsBaseDao.findGoodsBaseById(id);
			// 记录日志
			if (dto != null) {
				SysLogUtil.log4Query(dto);
			}
			return dto;
		} catch (Exception e) {
			logger.error("findById出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 保存商品信息和详细
	 * 
	 * @param dto1 商品信息
	 * @param dto2 商品明细
	 * @return 商品信息主键
	 * @throws Exception
	 */
	public String insertGoodsBaseAndDetail(GoodsBaseDTO dto1, GoodsDetailDTO dto2) throws Exception {
		String id = this.insertGoodsBase(dto1);
		dto2.setGoodsId(id);
		goodsDetailService.insertGoodsDetail(dto2);
		return id;
	}

	/**
	 * 修改商品信息和详细
	 * 
	 * @param dto1 商品信息
	 * @param dto2 商品明细
	 * @return
	 * @throws Exception
	 */
	public int updateAllDetail(GoodsBaseDTO dto1, GoodsDetailDTO dto2) throws Exception {
		int count = 0;
		count += this.updateGoodsBase(dto1);
		count += goodsDetailService.updateGoodsDetail(dto2);
		return count;
	}

	/**
	 * 修改商品信息和详细部分信息
	 * 
	 * @param dto1 商品信息
	 * @param dto2 商品明细
	 * @return
	 * @throws Exception
	 */
	public int updateSensitiveDetail(GoodsBaseDTO dto1, GoodsDetailDTO dto2) throws Exception {
		int count = 0;
		count += this.updateGoodsBaseSensitive(dto1);
		count += goodsDetailService.updateGoodsDetailSensitive(dto2);
		return count;
	}

	/**
	 * 更新缩略图
	 * 
	 * @param id    商品ID
	 * @param thumb 缩略图
	 * @return
	 */
	public int updateGoodsBaseThumb(@Param("id") String id, @Param("thumb") byte[] thumb) {
		return this.goodsBaseDao.updateGoodsBaseThumb(id, thumb);
	}

	/**
	 * 查询缩略图
	 * 
	 * @param id 商品ID
	 * @return
	 */
	public byte[] getGoodsBaseThumb(@Param("id") String id) {
		return this.goodsBaseDao.getGoodsBaseThumb(id);
	}

	/**
	 * 发布商品
	 * 
	 * @param id 商品ID
	 * @return
	 */
	public int goodsReleaseById(@Param("id") String id) {
		return this.goodsBaseDao.goodsReleaseById(id);
	}

	/**
	 * 批量发布商品
	 * 
	 * @param ids 商品ID数组
	 * @return
	 */
	public int goodsReleaseByIds(String[] ids) {
		int count = 0;
		for (String id : ids) {
			count += this.goodsBaseDao.goodsReleaseById(id);
		}
		return count;
	}
	
	/**
	 * 根据商品编码查询商品ID
	 * 
	 * @param code 商品编码
	 * @return
	 */
	public String getGoodsIdBaseByCode(String code) {
		List<String> list = goodsBaseDao.getGoodsIdBaseByCode(code);
		if(list!= null && !list.isEmpty()) {
			return list.get(0);
		}
		return "";
	}

	/**
	 * 初始化系统信息<br>
	 * 包含部门ID、组织ID、系统ID、密级、系统应用ID
	 * 
	 * @param dto
	 */
	private void initSysinfo(GoodsBaseDTO dto) {
		// TODO
		dto.setDeptId("");
		dto.setSysId("");
		dto.setOrgId("");
		dto.setSecretLevel("");
		dto.setSysApplicationId("");
	}

	/**
	 * 查询大批量数据<br>
	 * 用于演示弹性伸缩,占用内存直到报异常(栈)
	 * 
	 * @return
	 */
	public String queryBulkGoodsData() {
		logger.info("申请消耗内存  start");
		String msg = "";
		if (memoryList.isEmpty()) {
			int i = 0;
			try {
				while (i <= 300) {
//					ByteBuffer.allocate(size);//申请堆内存
					memoryList.add(ByteBuffer.allocateDirect(size));
					i++;
				}
			} catch (OutOfMemoryError e) {
				e.printStackTrace();
			} finally {
				msg = "共消耗内存" + i * 10 + "M";
			}
		} else {
			msg = "内存不够";
		}
		logger.info(msg);
		logger.info("申请消耗内存  end");
		return msg;
	}

	/**
	 * 取消查询大批量数据<br>
	 * 用于演示弹性伸缩,释放被占用的内存(栈)
	 * 
	 * @return
	 */
	public String cancelQueryBulkGoodsData() {
		logger.info("申请释放内存  start");
		String msg = "";
		if (!memoryList.isEmpty()) {
			for (ByteBuffer bb : memoryList) {
				if (bb != null) {
//					bb.clear();//释放堆内存
					((DirectBuffer) bb).cleaner().clean();
				}
			}
			memoryList.clear();
			msg = "释放内存:" + memoryList.size() * 10 + "M";
		} else {
			msg = "已取消查询";
		}
		logger.info("申请释放内存  end");
		return msg;
	}
}
