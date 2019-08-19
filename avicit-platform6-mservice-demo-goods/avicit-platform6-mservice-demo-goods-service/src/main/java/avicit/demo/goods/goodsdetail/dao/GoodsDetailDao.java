package avicit.demo.goods.goodsdetail.dao;

import java.util.List;
import avicit.platform6.core.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.demo.goods.goodsdetail.dto.GoodsDetailDTO;

/**
 * @金航数码科技有限责任公司
 * @作者：请填写
 * @邮箱：avicitdev@avicit.com
 * @创建时间： 2019-06-01 10:27
 * @类说明：请填写
 * @修改记录： 
 */
@MyBatisRepository
public interface GoodsDetailDao {

	/**
	* 分页查询  商品详细信息
	* @param goodsDetailDTO 查询对象
	* @param orderBy 排序条件
	* @return Page<GoodsDetailDTO>
	*/
	public Page<GoodsDetailDTO> searchGoodsDetailByPage(@Param("bean") GoodsDetailDTO goodsDetailDTO,
			@Param("pOrderBy") String orderBy);

	/**
	* 按or条件的分页查询 商品详细信息
	* @param goodsDetailDTO 查询对象
	* @param orderBy 排序条件
	* @return Page<GoodsDetailDTO>
	*/
	public Page<GoodsDetailDTO> searchGoodsDetailByPageOr(@Param("bean") GoodsDetailDTO goodsDetailDTO,
			@Param("pOrderBy") String orderBy);

	/**
	* 查询商品详细信息
	* @param goodsDetailDTO 查询对象
	* @return List<GoodsDetailDTO>
	*/
	public List<GoodsDetailDTO> searchGoodsDetail(GoodsDetailDTO goodsDetailDTO);

	/**
	 * 查询 商品详细信息
	 * @param id 主键id
	 * @return GoodsDetailDTO
	 */
	public GoodsDetailDTO findGoodsDetailById(String id);

	/**
	* 新增商品详细信息
	* @param goodsDetailDTO 保存对象
	* @return int
	*/
	public int insertGoodsDetail(GoodsDetailDTO goodsDetailDTO);

	/**
	 * 批量新增 商品详细信息
	 * @param goodsDetailDTOList 保存对象集合
	 * @return int
	 */
	public int insertGoodsDetailList(List<GoodsDetailDTO> goodsDetailDTOList);

	/**
	 * 更新部分对象 商品详细信息
	 * @param goodsDetailDTO 更新对象
	 * @return int
	 */
	public int updateGoodsDetailSensitive(GoodsDetailDTO goodsDetailDTO);

	/**
	 * 更新全部对象 商品详细信息
	 * @param goodsDetailDTO 更新对象
	 * @return int
	 */
	public int updateGoodsDetailAll(GoodsDetailDTO goodsDetailDTO);

	/**
	 * 批量更新对象 商品详细信息
	 * @param dtoList 批量更新对象集合
	 * @return int
	 */
	public int updateGoodsDetailList(@Param("dtoList") List<GoodsDetailDTO> dtoList);

	/**
	 * 按主键删除 商品详细信息
	 * @param id 主键id
	 * @return int
	 */
	public int deleteGoodsDetailById(String id);

	/**
	 * 按主键批量删除 商品详细信息
	 * @param idList 主键集合
	 * @return int
	 */
	public int deleteGoodsDetailList(List<String> idList);

	/**
	 * 通过商品信息主键查询商品明细
	 * 
	 * @param id 商品信息主键
	 * @return
	 */
	public GoodsDetailDTO getByGoodIds(@Param("goodsId") String goodsId);
}
