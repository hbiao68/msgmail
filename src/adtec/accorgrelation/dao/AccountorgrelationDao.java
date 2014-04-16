package adtec.accorgrelation.dao;


import java.util.List;
import java.util.Map;

import adtec.accorgrelation.model.Accountorgrelation;

/**
 * 
 * @FileName:      AccountorgrelationDao
 *
 * @FileType:      Interface
 *
 * @Date:          2014年3月10日 2014年3月10日 15:07:21
 * 
 * @Author:        李季
 * 
 * @Description:   帐号与机构关系接口
 */
public interface AccountorgrelationDao {

	/**
	 * 将帐号添入到机构下
	 * @param accountorgrelation
	 * @throws Exception
	 */
	public void insertAccountorgrelation(Accountorgrelation accountorgrelation)throws Exception;
	
	/**
	 * 删除帐号和机构的关系
	 * @param relationid
	 * @throws Exception
	 */
	public void deleteAccountorgrelation(int relationid)throws Exception;
	
	/**
	 * 修改帐号所在机构
	 * @param accountorgrelation
	 * @throws Exception
	 */
	public void updateAccountorgrelation(Accountorgrelation accountorgrelation)throws Exception;
	
	/**
	 * 分页查询数据
	 * @param accountorgrelation
	 * @return
	 * @throws Exception
	 */
	public List<Accountorgrelation> queryAccOrgRelByAccOrgRel(Accountorgrelation accountorgrelation)throws Exception;
	
	/**
	 * 查询所有数据
	 * @param accountorgrelation
	 * @return
	 * @throws Exception
	 */
	public List<Accountorgrelation> queryAccOrgRelforCountByAccOrgRel(Accountorgrelation accountorgrelation)throws Exception;
	
	/**
	 * 单一实例查询
	 * @param relationid
	 * @return
	 * @throws Exception
	 */
	public Accountorgrelation findAccOrgRelByRelationid(int relationid)throws Exception;
	
	/**
	 * 批量删除数据
	 * @throws Exception
	 */
	public void batchdelete(Accountorgrelation accountorgrelation)throws Exception;
	
	public List<Map<String,Object>> queryAccountByorgIdList(List<String> list); 
	
}
