package adtec.privilege.dao;

import java.util.List;

import adtec.privilege.model.Column;

/**
 * 菜单栏位dao层。直接操作column表的数据
 * @author maojd
 * @date 14:02 2014/4/1
 * 
 */
public interface ColumnDao {
	
	/**
	 * 查询所有栏位（带分页）
	 * @param column
	 * @return
	 */
	public List<Column> queryAllColumn(Column column);

	/**
	 * 查询栏位总数（带模糊查询）
	 * @return
	 */
	public int queryColumnCount(Column column);

	/**
	 * 添加栏位
	 * @param column
	 */
	public void insertColumn(Column column);

	/**
	 * 通过对象查询
	 * @param column
	 * @return
	 */
	public List<Column> queryColumnByObj(Column column);

	/**
	 * 通过对象查询数量
	 * @param column
	 * @return
	 */
	public int queryCountByObj(Column column);

	/**
	 * 修改栏位
	 * @param column 传入修改的实体 参数
	 */
	public void updateColumn(Column column);

	/**
	 * 通过id集合批量删除栏位
	 * @param idsList要删除的栏位 id集合
	 */
	public void deleteColumnsById(List<String> idsList);

}
