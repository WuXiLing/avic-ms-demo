package avicit.demo.stock.stock.service;

import avicit.demo.goods.goodsbase.api.GoodsBaseApi;
import avicit.demo.stock.stock.dao.StockDao;
import avicit.demo.stock.stock.dto.StockDTO;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.PojoUtil;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.properties.PlatformConstant.OpType;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：avicitdev@avicit.com
 * @创建时间： 2019-06-01 11:21
 * @类说明：请填写
 * @修改记录：
 */
@Service
public class StockService implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(StockService.class);

    private static final long serialVersionUID = 1L;

    @Autowired
    private StockDao stockDao;
    
    @Autowired
    private GoodsBaseApi goodsBaseApi;

    /**
     * 按条件分页查询
     *
     * @param queryReqBean 分页
     * @param orderBy      排序
     * @return QueryRespBean<StockDTO>
     * @throws Exception
     */
    public QueryRespBean<StockDTO> searchStockByPage(QueryReqBean<StockDTO> queryReqBean, String orderBy)
            throws Exception {
        try {
            PageHelper.startPage(queryReqBean.getPageParameter());
            StockDTO searchParams = queryReqBean.getSearchParams();
            if(StringUtils.isNotBlank(searchParams.getCode())){
            	searchParams.setGoodsId(goodsBaseApi.getGoodsIdBaseByCode(searchParams.getCode()));
            	searchParams.setCode("");
            }
            Page<StockDTO> dataList = stockDao.searchStockByPage(searchParams, orderBy);
            QueryRespBean<StockDTO> queryRespBean = new QueryRespBean<StockDTO>();

            queryRespBean.setResult(dataList);
            return queryRespBean;
        } catch (Exception e) {
            logger.error("searchStockByPage出错：", e);
            e.printStackTrace();
            throw new DaoException(e.getMessage(), e);
        }
    }

    /**
     * 按条件or查询的分页查询
     *
     * @param queryReqBean 分页
     * @param orderBy      排序
     * @return QueryRespBean<StockDTO>
     * @throws Exception
     */
    public QueryRespBean<StockDTO> searchStockByPageOr(QueryReqBean<StockDTO> queryReqBean, String orderBy)
            throws Exception {
        try {
            PageHelper.startPage(queryReqBean.getPageParameter());
            StockDTO searchParams = queryReqBean.getSearchParams();
            Page<StockDTO> dataList = stockDao.searchStockByPageOr(searchParams, orderBy);
            QueryRespBean<StockDTO> queryRespBean = new QueryRespBean<StockDTO>();

            queryRespBean.setResult(dataList);
            return queryRespBean;
        } catch (Exception e) {
            logger.error("searchStockByPage出错：", e);
            e.printStackTrace();
            throw new DaoException(e.getMessage(), e);
        }
    }

    /**
     * 按条件查询
     *
     * @param queryReqBean 条件
     * @return List<StockDTO>
     * @throws Exception
     */
    public List<StockDTO> searchStock(QueryReqBean<StockDTO> queryReqBean) throws Exception {
        try {
            StockDTO searchParams = queryReqBean.getSearchParams();
            List<StockDTO> dataList = stockDao.searchStock(searchParams);
            return dataList;
        } catch (Exception e) {
            logger.error("searchStock出错：", e);
            e.printStackTrace();
            throw new DaoException(e.getMessage(), e);
        }
    }

    /**
     * 通过主键查询单条记录
     *
     * @param id 主键id
     * @return StockDTO
     * @throws Exception
     */
    public StockDTO queryStockByPrimaryKey(String id) throws Exception {
        try {
            StockDTO dto = stockDao.findStockById(id);
            //记录日志
            if (dto != null) {
                SysLogUtil.log4Query(dto);
            }
            return dto;
        } catch (Exception e) {
            logger.error("queryStockByPrimaryKey出错：", e);
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
    public String insertStock(StockDTO dto) throws Exception {
        try {
            // start add by lgl at 20190613 商品重复判定
            // check goodsId isRepect
            StockDTO dbDto = stockDao.findStockByGoodsId(dto.getGoodsId());
            ;
            if (isGoodsIdRepeatWithAdd(dbDto)) {
                // 根据需求，重复了就更新原来的，不阻断操作
                PojoUtil.setSysProperties(dbDto, OpType.update);
                PojoUtil.copyProperties(dbDto, dto, true);
                stockDao.updateStockSensitive(dbDto);
                return dbDto.getId();
            }
            // end add by lgl at 20190613 商品重复判定

            String id = ComUtil.getId();
            dto.setId(id);
            PojoUtil.setSysProperties(dto, OpType.insert);
            stockDao.insertStock(dto);
            //记录日志
            if (dto != null) {
                SysLogUtil.log4Insert(dto);
            }
            return id;
        } catch (Exception e) {
            logger.error("insertStock出错：", e);
            e.printStackTrace();
            throw new DaoException(e.getMessage(), e);
        }
    }

    /**
     * 新增库存信息时，判定是否有该商品的库存信息存在
     *
     * @param dbDto
     * @return
     */
    private boolean isGoodsIdRepeatWithAdd(StockDTO dbDto) {
        return !(null == dbDto);
    }

    /**
     * 批量新增对象
     *
     * @param dtoList 保存对象集合
     * @return int
     * @throws Exception
     */
    public int insertStockList(List<StockDTO> dtoList) throws Exception {
        List<StockDTO> beanList = new ArrayList<StockDTO>();
        for (StockDTO dto : dtoList) {
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
            return stockDao.insertStockList(beanList);
        } catch (Exception e) {
            logger.error("insertStockList出错：", e);
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
    public int updateStock(StockDTO dto) throws Exception {
        //记录日志
        StockDTO old = findById(dto.getId());
        // start modify by lgl at 20190613 商品库存重复校验
        if (old == null) {
            throw new DaoException("更新库存信息失败：库存信息不存在！");
        } else {
            SysLogUtil.log4Update(dto, old);
        }

        if (StringUtils.isEmpty(dto.getGoodsId())) {
            throw new DaoException("更新库存信息失败：商品ID不存在！");
        }

        // check isRepect
        StockDTO dbDto = stockDao.findStockByGoodsId(dto.getGoodsId());
        if (isGoodsIdRepeat(dto, dbDto)) {
            old = dbDto;
            dto.setId(null);
        }
        // start modify by lgl at 20190613 商品库存重复校验
        PojoUtil.setSysProperties(dto, OpType.update);
        PojoUtil.copyProperties(old, dto, true);
        int ret = stockDao.updateStockAll(old);
        if (ret == 0) {
            throw new DaoException("数据失效，请重新更新");
        }
        return ret;
    }

    /**
     * 商品库存信息是否重复
     *
     * @param dto
     * @param dbDto
     * @return
     */
    private boolean isGoodsIdRepeat(StockDTO dto, StockDTO dbDto) throws Exception {
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
     * 修改对象部分字段
     *
     * @param dto 修改对象
     * @return int
     * @throws Exception
     */
    public int updateStockSensitive(StockDTO dto) throws Exception {
        try {
            //记录日志
            StockDTO old = findById(dto.getId());
            if (old != null) {
                SysLogUtil.log4Update(dto, old);
            }
            PojoUtil.setSysProperties(dto, OpType.update);
            PojoUtil.copyProperties(old, dto, true);
            int count = stockDao.updateStockSensitive(old);
            if (count == 0) {
                throw new DaoException("数据失效，请重新更新");
            }
            return count;
        } catch (Exception e) {
            logger.error("updateStockSensitive出错：", e);
            e.printStackTrace();
            throw new DaoException(e.getMessage(), e);
        }
    }

    /**
     * 批量更新对象
     *
     * @param dtoList 修改对象集合
     * @return int
     * @throws Exception
     */
    public int updateStockList(List<StockDTO> dtoList) throws Exception {
        try {
            return stockDao.updateStockList(dtoList);
        } catch (Exception e) {
            logger.error("updateStockList出错：", e);
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
    public int deleteStockById(String id) throws Exception {
        if (StringUtils.isEmpty(id)) {
            throw new Exception("删除失败！传入的参数主键为null");
        }
        try {
            //记录日志
            StockDTO obje = findById(id);
            if (obje != null) {
                SysLogUtil.log4Delete(obje);
            }
            return stockDao.deleteStockById(id);
        } catch (Exception e) {
            logger.error("deleteStockById出错：", e);
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
    public int deleteStockByIds(String[] ids) throws Exception {
        int result = 0;
        for (String id : ids) {
            deleteStockById(id);
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
    public int deleteStockList(List<String> idList) throws Exception {
        try {
            return stockDao.deleteStockList(idList);
        } catch (Exception e) {
            logger.error("deleteStockList出错：", e);
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 日志专用，内部方法，不再记录日志
     *
     * @param id 主键id
     * @return StockDTO
     * @throws Exception
     */
    private StockDTO findById(String id) throws Exception {
        try {
            StockDTO dto = stockDao.findStockById(id);
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
     * 更新库存数量
     *
     * @param goodsId：库存ID
     * @param num：减去的库存数量,为正数，减库存，为负数，加库存
     * @return int 更新的库存数量
     */
    public int updateStockNum(String goodsId, Integer num) {
        return stockDao.updateStockNum(goodsId, num);
    }

    /**
     * 根据商品ID查询库存数量
     *
     * @param params goodsId：商品ID
     * @return int 库存数量
     */
    public int getStockNumByGoodsId(String goodsId) {
        return stockDao.getStockNumByGoodsId(goodsId);
    }

    /**
     * 更新库存数量
     *
     * @param id  库存ID
     * @param num 库存数量
     * @return
     */
    public int updateStock(String id, Long num) {
        return stockDao.updateStock(id, num);
    }

    /**
     * 查询库存
     *
     * @param goodsId 商品ID
     * @return
     */
    public List<StockDTO> searchStockByGoodsId(String goodsId) {
        StockDTO dto = new StockDTO();
        dto.setGoodsId(goodsId);
        return stockDao.searchStock(dto);
    }

    /**
     * 删除商品的库存
     *
     * @param goodsId
     * @return
     */
    public int deleteByGoodsId(String goodsId) {
        return stockDao.deleteByGoodsId(goodsId);
    }
}
