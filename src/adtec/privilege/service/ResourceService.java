package adtec.privilege.service;


import java.util.List;

import adtec.privilege.model.Resource;


/**
 * 资源管理service接口，进一步处理dao层的方法，便于control的调用
 * @author maojd
 * @date 15:45 2014/2/21
 */
public interface ResourceService {

	/**
	 * 查询所有资源
	 * @param resource 查询条件
	 * @return
	 * @throws Exception
	 */
	public List<Resource> queryAllResource(Resource resource) throws Exception;
	
	/**
	 * 通过id查询一条记录
	 * @param resid 资源主键
	 * @return	resource查询结果
	 */
	public Resource queryResByResid(String resid) throws Exception;

	/**
	 * 资源修改
	 * @param res 资源实体
	 * @return true修改成功 false修改失败
	 */
	public boolean updateRes(Resource res) throws Exception;

	/**
	 * 资源添加
	 * @param resource要添加的资源实体
	 * @return true添加成功 false天津爱失败
	 */
	public boolean insertRes(Resource resource) throws Exception;

	/**
	 * 删除资源
	 * @param res要删除的资源
	 * @return true删除成功，false删除失败
	 */
	public boolean deleteRes(Resource res) throws Exception;

	/**
	 * 查询资源数量
	 * @return
	 * @throws Exception
	 */
	public int queryResourceCount(Resource resource) throws Exception;

	/**
	 * 通过主键 批量删除资源
	 * @param resids 主键之间通过 ',' 分隔
	 * @return
	 * @throws Exception
	 */
	public boolean deleteRessById(String resids) throws Exception;

	/**
	 * 通过对象查询
	 * @param res
	 * @return
	 */
	public List<Resource> queryResourceByObj(Resource res) throws Exception;

	
}
