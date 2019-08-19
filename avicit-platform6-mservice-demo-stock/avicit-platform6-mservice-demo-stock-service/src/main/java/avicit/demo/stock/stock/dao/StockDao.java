package avicit.demo.stock.stock.dao;

import java.util.List;
import avicit.platform6.core.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.demo.stock.stock.dto.StockDTO;

/**
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：avicitdev@avicit.com
 * @创建时间： 2019-06-01 11:21
 * @类说明：请填写
 * @修改记录： 
 */
@MyBatisRepository
public interface StockDao {

	/**
	* 分页查询  库存信息
	* @param stockDTO 查询对象
	* @param orderBy 排序条件
	* @return Page<StockDTO>
	*/
	public Page<StockDTO> searchStockByPage(@Param("bean") StockDTO stockDTO, @Param("pOrderBy") String orderBy);

	/**
	* 按or条件的分页查询 库存信息
	* @param stockDTO 查询对象
	* @param orderBy 排序条件
	* @return Page<StockDTO>
	*/
	public Page<StockDTO> searchStockByPageOr(@Param("bean") StockDTO stockDTO, @Param("pOrderBy") String orderBy);

	/**
	* 查询库存信息
	* @param stockDTO 查询对象
	* @return List<StockDTO>
	*/
	public List<StockDTO> searchStock(StockDTO stockDTO);

	/**
	 * 查询 库存信息
	 * @param id 主键id
	 * @return StockDTO
	 */
	public StockDTO findStockById(String id);

	/**
	* 新增库存信息
	* @param stockDTO 保存对象
	* @return int
	*/
	public int insertStock(StockDTO stockDTO);

	/**
	 * 批量新增 库存信息
	 * @param stockDTOList 保存对象集合
	 * @return int
	 */
	public int insertStockList(List<StockDTO> stockDTOList);

	/**
	 * 更新部分对象 库存信息
	 * @param stockDTO 更新对象
	 * @return int
	 */
	public int updateStockSensitive(StockDTO stockDTO);

	/**
	 * 更新全部对象 库存信息
	 * @param stockDTO 更新对象
	 * @return int
	 */
	public int updateStockAll(StockDTO stockDTO);

	/**
	 * 批量更新对象 库存信息
	 * @param dtoList 批量更新对象集合
	 * @return int
	 */
	public int updateStockList(@Param("dtoList") List<StockDTO> dtoList);

	/**
	 * 按主键删除 库存信息
	 * @param id 主键id
	 * @return int
	 */
	public int deleteStockById(String id);

	/**
	 * 按主键批量删除 库存信息
	 * @param idList 主键集合
	 * @return int
	 */
	public int deleteStockList(List<String> idList);
	
	/**
	 * 更新库存信息
	 * @param id 库存主键
	 * @param num 库存数量
	 * @return int
	 */
	public int updateStockNum(@Param("goodsId") String goodsId,@Param("num") Integer num);

	/**
	 * 根据商品ID查询库存数量
	 * 
	 * @param params
	 *            goodsId：商品ID
	 * 
	 * @return int 库存数量
	 */
	public int getStockNumByGoodsId(@Param("goodsId") String goodsId);

	/**
	 * 更新库存数量
	 * 
	 * @param id 库存ID
	 * @param num 库存数量
	 * @return
	 */
	public int updateStock(@Param("id")String id, @Param("num")Long num);

	/**
	 * 根据商品ID查询库存信息
	 * @param goodsId
	 * @return
	 */
	StockDTO findStockByGoodsId(@Param("goodsId")String goodsId);

	/**
	 * 删除商品库存信息
	 * @param goodsId
	 * @return
	 */
    int deleteByGoodsId(@Param("goodsId")String goodsId);
}
