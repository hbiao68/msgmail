package adtec.accorgrelation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.accorgrelation.dao.AccountorgrelationDao;
import adtec.accorgrelation.model.Accountorgrelation;
import adtec.accorgrelation.service.SwitchAccOrgRelService;
import adtec.account.model.Account;
import adtec.appManager.model.PageModel;
import adtec.organizationManager.dao.OrganizationDao;
import adtec.organizationManager.entity.Organization;

/**
 * @FileName: AccountorgrelationServiceImpl
 * 
 * @FileType: Class
 * 
 * @Date: 2014年3月10日 15:20:48
 * 
 * @Author: ;lj
 * 
 * @Description: 帐号机构关系实现类
 * 
 */
@Transactional(propagation=Propagation.REQUIRED)
public class LocalAccOrgRelServiceImpl implements SwitchAccOrgRelService {
	
	Logger log = Logger.getLogger(LocalAccOrgRelServiceImpl.class);
	
	private AccountorgrelationDao accountorgrelationDao;
	
	private OrganizationDao orgDao = null;
	
	
	public OrganizationDao getOrgDao() {
		return orgDao;
	}

	public void setOrgDao(OrganizationDao orgDao) {
		this.orgDao = orgDao;
	}

	public AccountorgrelationDao getAccountorgrelationDao() {
		return accountorgrelationDao;
	}

	public void setAccountorgrelationDao(AccountorgrelationDao accountorgrelationDao) {
		this.accountorgrelationDao = accountorgrelationDao;
	}

	/**
	 * 将帐号添入到机构下
	 */
	@Override
	public boolean insertAccountorgrelation(
			Accountorgrelation accountorgrelation) throws Exception {
		try {
			String username = accountorgrelation.getUsername();
			String usernameTrim = username.trim();
			accountorgrelation.setUsername(usernameTrim);
			this.accountorgrelationDao.insertAccountorgrelation(accountorgrelation);
			log.debug("帐号机构关系添加成功！");
			return true;
		} catch (Exception e) {
			log.error("帐号机构关系添加失败！");
			throw e;
		}
	}
	
	/**
	 * 删除帐号和机构的关系
	 */
	@Override
	public boolean deleteAccountorgrelation(int relationid) throws Exception {
		try {
			this.accountorgrelationDao.deleteAccountorgrelation(relationid);
			log.debug("帐号机构关系删除成功！");
			return true;
		} catch (Exception e) {
			log.error("帐号机构关系删除失败！");
			throw e;
		}
	}
	
	/**
	 * 修改帐号所在机构
	 */
	@Override
	public boolean updateAccountorgrelation(
			Accountorgrelation accountorgrelation) throws Exception {
		try {
			this.accountorgrelationDao.updateAccountorgrelation(accountorgrelation);
			log.debug("帐号机构关系修改成功！");
			return true;
		} catch (Exception e) {
			log.error("帐号机构关系修改失败！");
			throw e;
		}
	}
	
	@Override
	public Accountorgrelation queryAccOrgRelByAccOrgRel(
			Accountorgrelation accountorgrelation) throws Exception {
		try {
			String username = accountorgrelation.getUsername();
			
			
			//System.out.println("ffffffff="+accountorgrelation.getOrgId()+"++++++++");
			int orgId =accountorgrelation.getOrgId();
			String orgName = accountorgrelation.getOrgName();
			if(username == null){
				username = "";
			}
			username = username.trim();
			// 该list里面放条件查询中机构条件所需的机构主键
			List<Integer> list = new ArrayList<Integer>();
			if(orgId == 0){
				orgId = 0;
			}else{
				
				//通过id查询该id的整条机构数据
				List<Organization> orgList = new ArrayList<Organization>();
				orgList =orgDao.queryOrgById(orgId);
				Organization org = (Organization) orgList.get(0);
				//由一个机构查询所有子机构（包括本身）
				List<Organization> orgIdList  = orgDao.querySubOrgListByOrg(org);
				Organization organization = new Organization();

				for (int j = 0; j < orgIdList.size(); j++) {
					// 循环获取批量删除的机构主键
					organization = (Organization) orgIdList.get(j);
					int orgIdback = Integer
							.parseInt(organization.getOrgId().toString());

					list.add(orgIdback);
				}
			}
			PageModel pageModel = new PageModel();//account.getPageModel();
			int pageNow = 1;
			// 获取当前页数
			if (pageModel.getPageNow() == 0
					|| "undefined".equals(pageModel.getPageNow())
					) {
				if (pageModel.getPageNow() == 0
						|| "undefined".equals(pageModel.getPageJump())
						) {
					pageNow = 1;
				} else {
					pageNow = pageModel.getPageJump();
				}

			} else {
				pageNow = pageModel.getPageNow();
			}
			
			// 每页显示的记录数
			pageModel.setPageNow(pageNow);
			int pageSize = pageModel.getPageSize();
			int start = pageModel.getStart();
			accountorgrelation.setList(list);
			accountorgrelation.setUsername(username);
			accountorgrelation.setPageSize(pageSize);
			accountorgrelation.setStart(start);
			//获取所有数据的条数
			List<Accountorgrelation> accorgCount = accountorgrelationDao.queryAccOrgRelforCountByAccOrgRel(accountorgrelation);
			
			// 获取记录总数
			int count = accorgCount.size();
			pageModel.setCount(count);
			// 获取总页数
			int pageCount = pageModel.getTotalPages();

			int pageUp = pageNow - 1;
			pageModel.setPageUp(pageUp);
			int pageDown = pageNow + 1;
			pageModel.setPageDown(pageDown);
			pageModel.setPageNow(pageNow);
			pageModel.setPageCount(pageCount);
			accountorgrelation.setPageModel(pageModel);
			List<Accountorgrelation> accorgList = accountorgrelationDao.queryAccOrgRelByAccOrgRel(accountorgrelation);
			accountorgrelation.setAccorgList(accorgList);
			accountorgrelation.setOrgName(orgName);
			accountorgrelation.setOrgId(orgId);

			return accountorgrelation;
		} catch (Exception e) {
			log.error("分页查询数据失败！ queryAccOrgRelByAccOrgRel error");
			throw e;
		}
	}
	
	/**
	 * 单一实例查询
	 */
	@Override
	public Accountorgrelation findAccOrgRelByRelationid(int relationid)
			throws Exception {
		try {
			return this.accountorgrelationDao.findAccOrgRelByRelationid(relationid);
		} catch (Exception e) {
			log.error(" 单一实例查询失败   findAccOrgRelByRelationid error");
			throw e;
		}
	}
	
	/**
	 * 批量删除数据
	 */
	
	@Override
	public boolean batchdelete(Accountorgrelation accountorgrelation)
			throws Exception {
		try {
			this.accountorgrelationDao.batchdelete(accountorgrelation);
			return true;
		} catch (Exception e) {

			log.error("批量删除失败！");
			throw e;
		}
		
		
	}
	
	


}
