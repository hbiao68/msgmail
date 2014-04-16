package adtec.accorgrelation.service;

import java.util.List;

import adtec.accorgrelation.model.Accountorgrelation;

/**
 * @FileName: AccountorgrelationService
 * 
 * @FileType: Interface
 * 
 * @Date:  2014年3月10日 15:16:24
 * 
 * @Author: lj
 * 
 * @Description: 帐号机构关系接口
 * 
 */
public interface SwitchAccOrgRelService {
	
	/**
	 * 将帐号添入到机构下
	 * @param accountorgrelation
	 * @return
	 * @throws Exception
	 */
	public boolean insertAccountorgrelation(Accountorgrelation accountorgrelation)throws Exception;
	
	/**
	 * 删除帐号和机构的关系
	 * @param relationid
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAccountorgrelation(int relationid)throws Exception;
	
	/**
	 * 修改帐号所在机构
	 * @param accountorgrelation
	 * @return
	 * @throws Exception
	 */
	public boolean updateAccountorgrelation(Accountorgrelation accountorgrelation)throws Exception;
	
	/**
	 * 分页查询数据
	 * @param accountorgrelation
	 * @return
	 * @throws Exception
	 */
	public Accountorgrelation queryAccOrgRelByAccOrgRel(Accountorgrelation accountorgrelation)throws Exception;
	
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
	public boolean batchdelete(Accountorgrelation accountorgrelation)throws Exception;
	
}
