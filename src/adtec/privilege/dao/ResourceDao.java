package adtec.privilege.dao;

import java.util.List;

import adtec.privilege.model.Resource;
/**
 * 资源管理 直接对资源表进行操作的dao层
 * @author maojd
 * @date 15:40 2014/2/21
 */
public interface ResourceDao {

/*	public void addResource(Resource resource);
	
	public void updateResource(Resource resource);
	
	public void delResource(Resource resource);
	
	public List<Resource> selectByResource(Resource resource);
*/
	/**
	 * 查询所有资源
	 * @param 查询条件（带分页，page对象作为resource的一个属性）
	 * @return 查询资源的list结果集
	 */
	public List<Resource> queryAllResource(Resource resource);
	
	/**
	 * 通过id查询一条资源记录
	 * @param resid 资源主键id
	 * @return 查询资源的结果
	 */
	public Resource queryResByResid(String resid);

	/**
	 * 条件查询
	 * @param res一个资源对象作为查询条件
	 * @return 符合条件的list结果集
	 */
	public List<Resource> queryResByObj(Resource res);

	/**
	 * 资源修改
	 * @param res
	 */
	public void updateRes(Resource res);

	/**
	 * 添加资源
	 * @param resource
	 */
	public void insertRes(Resource resource);

	/**
	 * 删除资源
	 * @param res要删除的资源
	 */
	public void deleteRes(Resource res);

	/**
	 * 查询资源总数
	 * @return 总数量
	 */
	public int queryResourceCount(Resource resource);

	/**
	 * 批量删除资源
	 * @param idList 要删除的id list集合
	 */
	public void deleteRessById(List<String> idList);
	
}
