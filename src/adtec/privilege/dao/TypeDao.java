package adtec.privilege.dao;

import java.util.List;

import adtec.privilege.model.Role;
import adtec.privilege.model.Type;

/**
 *  操作类型的数据管理接口。直接操作数据库
 * @author maojd 
 * @date 9:33 2014/3/3
 */
public interface TypeDao {

	/**
	 * 查询所有操作类型 数据
	 * @param type 最为查询条件。包括分页
	 * @return
	 */
	public List<Type> queryAllType(Type type);

	/**
	 * 添加操作类型数据
	 * @param type 要添加的数据
	 */
	public void insertType(Type type);

	/**
	 * 查询操作类型
	 * @param typeid 操作类型的主键id
	 * @return 操作类型实体
	 */
	public Type queryTypeById(String typeid);

	/**
	 * 修改操作类型 
	 * @param type 传入修改实体
	 */
	public void updateType(Type type);

	/**
	 * 删除操作类型
	 * @param type 要删除的实体（通过id删除）
	 */
	public void deleteType(Type type);

	/**
	 * 查询操作类型的总数量 （包括模糊查询）
	 * @return 数量
	 */
	public int queryTypeCount(Type type);

	/**
	 * 批量删除
	 * @param idList 要删除的id集合
	 */
	public void deleteUsersById(List<String> idList);

	/**
	 * 通过对象查询
	 * @param type
	 * @return
	 */
	public List<Role> queryTypeByObj(Type type);
}
