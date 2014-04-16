package adtec.privilege.service;

import java.util.List;

import adtec.privilege.model.Column;


/**
 * 菜单栏位信息的service接口
 * @author maojd
 * @date 13:59 2014/4/1
 */
public interface ColumnService {

	/**
	 * 查询所有栏位（带分页 、模糊查询）
	 * @param column
	 * @return
	 */
	public List<Column> queryAllColumn(Column column) throws Exception;

	/**
	 * 查询栏位总数（带模糊查询）
	 * @return
	 * @throws Exception
	 */
	public int queryColumnCount(Column column) throws Exception;

	/**
	 * 添加栏位
	 * @param column 要添加的栏位实体
	 * @return true表示添加成功 false表示添加失败
	 * @throws Exception
	 */
	public boolean insertColumn(Column column) throws Exception;

	/**
	 * 通过对象查询
	 * @param column
	 * @return 符合条件的list结果集
	 */
	public List<Column> queryColumnByObj(Column column) throws Exception;

	/**
	 * 通过对象查询数量
	 * @param column 栏位对象
	 * @return 符合条件的数量
	 */
	public int queryCountByObj(Column column) throws Exception;

	/**
	 * 修改栏位
	 * @param column
	 * @return
	 * @throws Exception 
	 */
	public boolean updateColumn(Column column) throws Exception;

	/**
	 * 通过id批量删除
	 * @param ids 各个id之间通过','分隔
	 * @return true批量删除成功 false批量删除失败
	 * @throws Exception
	 */
	public boolean deleteColumnsById(String ids) throws Exception;
	
}
