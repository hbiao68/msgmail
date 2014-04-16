package adtec.ta_org_relationManager.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.apache.ibatis.annotations.Param;

import adtec.ta_org_relationManager.model.Ta_org_relation;

import adtec.ta_org_relationManager.model.Ta_org_relation;

/**
 * 
 * @FileName:      Ta_org_relationDao
 *
 * @FileType:      Interface
 *
 * @Date:          2013年12月06日
 * 
 * @Author:        李季
 * 
 * @Description:   机构业务开通dao接口
 */
public interface Ta_org_relationDao {
	
	/**
	 * 添加方法,为机构开通业务
	 * @param list
	 */
	public void batchInsert(List<Ta_org_relation> list)throws Exception;
	
	/**
	 * 通过业务查询所有的机构
	 * @return
	 */
	public List<Ta_org_relation> queryOrgByApp(Map<String,Object> map);
	
	/**
	 * 通过业务查询所有的机构
	 * @param ta_org_relation
	 * @return
	 */
	public List<Ta_org_relation> queryAllByApp(Map<String,Object> map);
	

	/**
	 * 删除机构已开通的业务
	 * @param list
	 */
	public void batchDelete(Map<String,Object> map) throws Exception;
	
	/**
	 * 
	 * 通过appid查询该业务下的机构数量（业务被多少机构使用）
	 * @author maojd
	 * date:15:41 2013/12/25
	 * @param appid 业务id
	 * @return	机构数量
	 */
	public int queryOrgNumByAppid(int appid)throws Exception;
	
	/**
	 * 查询该银行下是否开通了某些业务
	 * @param ta_org_relation
	 * @return
	 */
	public int queryAppCountByOrgRelation(Ta_org_relation ta_org_relation);

}
