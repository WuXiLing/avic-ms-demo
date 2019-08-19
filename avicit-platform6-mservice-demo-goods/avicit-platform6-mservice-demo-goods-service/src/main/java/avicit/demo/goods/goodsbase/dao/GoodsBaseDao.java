package avicit.demo.goods.goodsbase.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import avicit.platform6.core.mybatis.MyBatisRepository;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.demo.goods.goodsbase.dto.GoodsBaseDTO;

/**
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：avicitdev@avicit.com
 * @创建时间： 2019-06-01 10:25
 * @类说明：请填写
 * @修改记录：
 */
@MyBatisRepository
public interface GoodsBaseDao {

	/**
	* 分页查询  商品基本信息
	* @param goodsBaseDTO 查询对象
	* @param orderBy 排序条件
	* @return Page<GoodsBaseDTO>
	*/
	public Page<GoodsBaseDTO> searchGoodsBaseByPage(@Param("bean") GoodsBaseDTO goodsBaseDTO,
			@Param("pOrderBy") String orderBy);

	/**
	* 按or条件的分页查询 商品基本信息
	* @param goodsBaseDTO 查询对象
	* @param orderBy 排序条件
	* @return Page<GoodsBaseDTO>
	*/
	public Page<GoodsBaseDTO> searchGoodsBaseByPageOr(@Param("bean") GoodsBaseDTO goodsBaseDTO,
			@Param("pOrderBy") String orderBy);

	/**
	 * 查询商品基本信息
	 * @param goodsBaseDTO 查询对象
	 * @return List<GoodsBaseDTO>
	 */
	public List<GoodsBaseDTO> searchGoodsBase(GoodsBaseDTO goodsBaseDTO);

	/**
	 * 查询 商品基本信息
	 * @param id 主键id
	 * @return GoodsBaseDTO
	 */
	public GoodsBaseDTO findGoodsBaseById(String id);

	/**
	* 新增商品基本信息
	* @param goodsBaseDTO 保存对象
	* @return int
	*/
	public int insertGoodsBase(GoodsBaseDTO goodsBaseDTO);

	/**
	 * 批量新增 商品基本信息
	 * @param goodsBaseDTOList 保存对象集合
	 * @return int
	 */
	public int insertGoodsBaseList(List<GoodsBaseDTO> goodsBaseDTOList);

	/**
	 * 更新部分对象 商品基本信息
	 * @param goodsBaseDTO 更新对象
	 * @return int
	 */
	public int updateGoodsBaseSensitive(GoodsBaseDTO goodsBaseDTO);

	/**
	 * 更新全部对象 商品基本信息
	 * @param goodsBaseDTO 更新对象
	 * @return int
	 */
	public int updateGoodsBaseAll(GoodsBaseDTO goodsBaseDTO);

	/**
	 * 批量更新对象 商品基本信息
	 * @param dtoList 批量更新对象集合
	 * @return int
	 */
	public int updateGoodsBaseList(@Param("dtoList") List<GoodsBaseDTO> dtoList);

	/**
	 * 更新缩略图
	 * 
	 * @param id 商品ID
	 * @param thumb 缩略图
	 * @return
	 */
	public int updateGoodsBaseThumb(@Param("id") String id,@Param("thumb")byte[] thumb);
	
	/**
	 * 查询缩略图
	 * 
	 * @param id 商品ID
	 * @return
	 */
	public byte[] getGoodsBaseThumb(@Param("id") String id);
	
	/**
	 * 按主键删除 商品基本信息
	 * @param id 主键id
	 * @return int
	 */
	public int deleteGoodsBaseById(String id);

	/**
	 * 按主键批量删除 商品基本信息
	 * @param idList 主键集合
	 * @return int
	 */
	public int deleteGoodsBaseList(List<String> idList);
	
	/**
	 * 发布商品
	 * 
	 * @param id 商品ID
	 * @return
	 */
	public int goodsReleaseById(@Param("id") String id);

	/**
	 * 根据商品名称查询商品
	 * @param name 名称
	 * @return
	 */
	GoodsBaseDTO findGoodsBaseByName(@Param("name")String name);
	
	/**
	 * 根据商品编码查询商品ID
	 * 
	 * @param code 商品编码
	 * @return
	 */
	List<String> getGoodsIdBaseByCode(@Param("code")String code);
}
