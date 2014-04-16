package adtec.privilege.service;

import java.util.List;

import adtec.privilege.model.Role;
import adtec.privilege.model.Type;

/**
 * 操作类型的 service 层接口
 * @author maojd
 * @date 9:39 2014/3/3
 */
public interface TypeService {

	/**
	 * 查询所有操作类型
	 * @return 操作类型list结果集
	 * @throws Exception 
	 */
	public List<Type> queryAllType(Type type) throws Exception;

	/**
	 * 添加操作类型数据
	 * @param type 要插入的实体
	 * @return true添加成功 false添加失败
	 * @throws Exception
	 */
	public boolean insertType(Type type) throws Exception;

	/**
	 * 查询操作类型 通过id
	 * @param typeid 操作类型的主键
	 * @return  操作类型实体
	 */
	public Type queryTypeById(String typeid) throws Exception;

	/**
	 * 修改操作类型
	 * @param type 传入修改的实体
	 * @return true修改成功， false修改失败
	 * @throws Exception
	 */
	public boolean updateType(Type type) throws Exception;

	/**
	 * 删除操作类型
	 * @param type 要删除的实体
	 * @return true删除成功 false删除失败
	 */
	public boolean deleteType(Type type) throws Exception;

	/**
	 * 查询操作类型的数据总数(包括模糊查询)
	 * @return 数量
	 * @throws Exception
	 */
	public int queryTypeCount(Type type) throws Exception;

	/**
	 * 通过id批量删除
	 * @param typeids id之间通过','分隔
	 * @return 
	 */
	public boolean deleteUsersById(String typeids) throws Exception;

	/**
	 * 通过对象查询
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<Role> queryTypeByObj(Type type) throws Exception;
}
