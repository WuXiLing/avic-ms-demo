package avicit.demo.goods.goodsdetail.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avicit.demo.goods.goodsdetail.dao.GoodsDetailDao;
import avicit.demo.goods.goodsdetail.dto.GoodsDetailDTO;
import avicit.demo.goods.goodsdetail.dto.GoodsDetailVO;
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

/**
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：avicitdev@avicit.com
 * @创建时间： 2019-06-01 10:27
 * @类说明：请填写
 * @修改记录： 
 */
@Service
public class GoodsDetailService implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(GoodsDetailService.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	private GoodsDetailDao goodsDetailDao;
	
	@Autowired
	private StockApi stockApi;

	/**
	* 按条件分页查询
	* @param queryReqBean 分页
	* @param orderBy 排序
	* @return QueryRespBean<GoodsDetailDTO>
	* @throws Exception
	*/
	public QueryRespBean<GoodsDetailDTO> searchGoodsDetailByPage(QueryReqBean<GoodsDetailDTO> queryReqBean,
			String orderBy) throws Exception {
		try {
			PageHelper.startPage(queryReqBean.getPageParameter());
			GoodsDetailDTO searchParams = queryReqBean.getSearchParams();
			Page<GoodsDetailDTO> dataList = goodsDetailDao.searchGoodsDetailByPage(searchParams, orderBy);
			QueryRespBean<GoodsDetailDTO> queryRespBean = new QueryRespBean<GoodsDetailDTO>();

			queryRespBean.setResult(dataList);
			return queryRespBean;
		} catch (Exception e) {
			logger.error("searchGoodsDetailByPage出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	* 按条件or查询的分页查询
	* @param queryReqBean 分页 
	* @param orderBy 排序
	* @return QueryRespBean<GoodsDetailDTO>
	* @throws Exception
	*/
	public QueryRespBean<GoodsDetailDTO> searchGoodsDetailByPageOr(QueryReqBean<GoodsDetailDTO> queryReqBean,
			String orderBy) throws Exception {
		try {
			PageHelper.startPage(queryReqBean.getPageParameter());
			GoodsDetailDTO searchParams = queryReqBean.getSearchParams();
			Page<GoodsDetailDTO> dataList = goodsDetailDao.searchGoodsDetailByPageOr(searchParams, orderBy);
			QueryRespBean<GoodsDetailDTO> queryRespBean = new QueryRespBean<GoodsDetailDTO>();

			queryRespBean.setResult(dataList);
			return queryRespBean;
		} catch (Exception e) {
			logger.error("searchGoodsDetailByPage出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	* 按条件查询
	* @param queryReqBean 条件
	* @return List<GoodsDetailDTO>
	* @throws Exception
	*/
	public List<GoodsDetailDTO> searchGoodsDetail(QueryReqBean<GoodsDetailDTO> queryReqBean) throws Exception {
		try {
			GoodsDetailDTO searchParams = queryReqBean.getSearchParams();
			List<GoodsDetailDTO> dataList = goodsDetailDao.searchGoodsDetail(searchParams);
			return dataList;
		} catch (Exception e) {
			logger.error("searchGoodsDetail出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 通过主键查询单条记录
	 * @param id 主键id
	 * @return GoodsDetailDTO
	 * @throws Exception
	 */
	public GoodsDetailDTO queryGoodsDetailByPrimaryKey(String id) throws Exception {
		try {
			GoodsDetailDTO dto = goodsDetailDao.findGoodsDetailById(id);
			//记录日志
			if (dto != null) {
				SysLogUtil.log4Query(dto);
			}
			return dto;
		} catch (Exception e) {
			logger.error("queryGoodsDetailByPrimaryKey出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	* 新增对象
	* @param dto 保存对象
	* @return String
	* @throws Exception
	*/
	public String insertGoodsDetail(GoodsDetailDTO dto) throws Exception {
		try {
			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			goodsDetailDao.insertGoodsDetail(dto);
			//记录日志
			if (dto != null) {
				SysLogUtil.log4Insert(dto);
			}
			return id;
		} catch (Exception e) {
			logger.error("insertGoodsDetail出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 批量新增对象
	 * @param dtoList 保存对象集合
	 * @return int
	 * @throws Exception
	 */
	public int insertGoodsDetailList(List<GoodsDetailDTO> dtoList) throws Exception {
		List<GoodsDetailDTO> beanList = new ArrayList<GoodsDetailDTO>();
		for (GoodsDetailDTO dto : dtoList) {
			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			//记录日志
			if (dto != null) {
				SysLogUtil.log4Insert(dto);
			}
			beanList.add(dto);
		}
		try {
			return goodsDetailDao.insertGoodsDetailList(beanList);
		} catch (Exception e) {
			logger.error("insertGoodsDetailList出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 修改对象全部字段
	 * @param dto 修改对象
	 * @return int
	 * @throws Exception
	 */
	public int updateGoodsDetail(GoodsDetailDTO dto) throws Exception {
		//记录日志
		GoodsDetailDTO old = findById(dto.getId());
		if (old != null) {
			SysLogUtil.log4Update(dto, old);
		}
		PojoUtil.setSysProperties(dto, OpType.update);
		PojoUtil.copyProperties(old, dto, true);
		int ret = goodsDetailDao.updateGoodsDetailAll(old);
		if (ret == 0) {
			throw new DaoException("数据失效，请重新更新");
		}
		return ret;
	}

	/**
	 * 修改对象部分字段
	 * @param dto 修改对象
	 * @return int
	 * @throws Exception
	 */
	public int updateGoodsDetailSensitive(GoodsDetailDTO dto) throws Exception {
		try {
			//记录日志
			GoodsDetailDTO old = this.goodsDetailDao.getByGoodIds(dto.getGoodsId());
			if (old != null) {
				SysLogUtil.log4Update(dto, old);
				dto.setId(old.getId());
			}else {
				return 0;
			}
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto, true);
			int count = goodsDetailDao.updateGoodsDetailSensitive(old);
			if (count == 0) {
				throw new DaoException("数据失效，请重新更新");
			}
			return count;
		} catch (Exception e) {
			logger.error("updateGoodsDetailSensitive出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 批量更新对象
	 * @param dtoList 修改对象集合
	 * @return int
	 * @throws Exception
	 */
	public int updateGoodsDetailList(List<GoodsDetailDTO> dtoList) throws Exception {
		try {
			return goodsDetailDao.updateGoodsDetailList(dtoList);
		} catch (Exception e) {
			logger.error("updateGoodsDetailList出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}

	}

	/**
	 * 按主键单条删除
	 * @param id 主键id
	 * @return int
	 * @throws Exception
	 */
	public int deleteGoodsDetailById(String id) throws Exception {
		if (StringUtils.isEmpty(id)) {
			throw new Exception("删除失败！传入的参数主键为null");
		}
		try {
			//记录日志
			GoodsDetailDTO obje = findById(id);
			if (obje != null) {
				SysLogUtil.log4Delete(obje);
			}
			return goodsDetailDao.deleteGoodsDetailById(id);
		} catch (Exception e) {
			logger.error("deleteGoodsDetailById出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}
	
	/**
	 * 按商品信息主键单条删除
	 * 
	 * @param id 商品信息主键id
	 * @return int 删除个数
	 * @throws Exception
	 */
	public int deleteGoodsDetailByGoodsBaseId(String id) throws Exception {
		if (StringUtils.isEmpty(id)) {
			throw new Exception("删除失败！传入的参数主键为null");
		}
		try {
			//记录日志
			GoodsDetailVO obje = getByGoodIds(id);
			if (obje != null) {
				GoodsDetailDTO dto = new GoodsDetailDTO();
				BeanUtils.copyProperties(obje, dto);
				SysLogUtil.log4Delete(dto);
			}
			return goodsDetailDao.deleteGoodsDetailById(obje.getId());
		} catch (Exception e) {
			logger.error("deleteGoodsDetailByGoodsBaseId出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 批量删除数据
	 * @param ids id的数组
	 * @return int
	 * @throws Exception
	 */
	public int deleteGoodsDetailByIds(String[] ids) throws Exception {
		int result = 0;
		for (String id : ids) {
			deleteGoodsDetailById(id);
			result++;
		}
		return result;
	}

	/**
	 * 批量删除数据2
	 * @param idList 主键集合
	 * @return int
	 * @throws Exception
	 */
	public int deleteGoodsDetailList(List<String> idList) throws Exception {
		try {
			return goodsDetailDao.deleteGoodsDetailList(idList);
		} catch (Exception e) {
			logger.error("deleteGoodsDetailList出错：", e);
			e.printStackTrace();
			throw e;
		}
	}

	/**
	* 日志专用，内部方法，不再记录日志
	* @param id 主键id
	* @return GoodsDetailDTO
	* @throws Exception
	*/
	private GoodsDetailDTO findById(String id) throws Exception {
		try {
			GoodsDetailDTO dto = goodsDetailDao.findGoodsDetailById(id);
			//记录日志
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
	 * 通过商品信息主键查询商品明细
	 * @param goodsId 商品信息主键
	 * @return ResponseMsg<GoodsDetailDTO>
	 * @throws Exception
	 */
	public GoodsDetailVO getByGoodIds(String goodsId) {
		try {
			GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
			GoodsDetailDTO dto = goodsDetailDao.getByGoodIds(goodsId);
			if (dto != null) {
				BeanUtils.copyProperties(dto, goodsDetailVO);
			}
			return goodsDetailVO;
		} catch (Exception e) {
			logger.error("findById出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}
	
	/**
	 * 通过商品信息主键查询商品明细，包含库存
	 * 
	 * @param goodsId 商品信息主键
	 * @return ResponseMsg<GoodsDetailDTO>
	 * @throws Exception
	 */
	public GoodsDetailVO getAndStockNumByGoodIds(String goodsId) {
		try {
			GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
			GoodsDetailDTO dto = goodsDetailDao.getByGoodIds(goodsId);
			if (dto != null) {
				BeanUtils.copyProperties(dto, goodsDetailVO);
				goodsDetailVO.setNum(stockApi.getStockNumByGoodsId(goodsId));
			}
			return goodsDetailVO;
		} catch (Exception e) {
			logger.error("getAndStockNumByGoodIds出错：", e);
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		}
	}
}
