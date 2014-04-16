package adtec.accountManager.client;

import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import adtec.categoryManager.model.Ta_category;
import adtec.categoryManager.model.Ta_extendpropdef;
import adtec.categoryManager.service.CategoryManagerService;

import java.util.*;

import adtec.accountManager.model.Ta_Account_cateName;
import adtec.accountManager.service.Ta_Account_cateNameService;

public class AccountManagerClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ApplicationContext act= new
		// ClassPathXmlApplicationContext("applicationContext.xml");
		ApplicationContext act = new FileSystemXmlApplicationContext(
				"D:/JavaProject/myspring/web/WEB-INF/applicationContext.xml");
		Ta_Account_cateNameService ta_Account_cateNameService = (Ta_Account_cateNameService) act
				.getBean("ta_Account_cateNameService");
		Ta_Account_cateName ta_Account_cateName = new Ta_Account_cateName();
		// Map dto=new HashMap();
		// dto.put("AccountName", "fdsa");
		// dto.put("AccountPwd", "cesaceda");
		// ta_Account_cateName.setAccountName("csadfe12");
		// ta_Account_cateName.setEmail("csafegw");
		// boolean tag=ta_Account_cateNameService.add(ta_Account_cateName);
		// System.out.println(tag);
		// List rows=ta_Account_cateNameService.query(ta_Account_cateName);
		// Map ins=ta_Account_cateNameService.findById(2);
		// System.out.println(rows);
		// System.out.println(ins);

		// boolean tag=ta_Account_cateNameService.del(5);
		// System.out.println(tag);

		// boolean tag=
		// ddflglservices.createtable();
		// ddflglservices.deltable();
		// System.out.println(tag);
		// List rows=ddflglservices.query();
		// System.out.println(rows);

		// String id=UUID.randomUUID().toString().replace("-", "");
		// ta_category.setTa_id(id);
		// ta_category.setAuthClass("bhdsaf");
		// ta_category.setCateDesc("jhceoloikwes");
		// ta_category.setCateName("klplhytaha");
		// ta_category.setImportClass("kebdpjnmiceenvr");
		// ta_extendpropdef.setProp_index(1);
		// ta_extendpropdef.setPropDesc("dhimpfeicsa");
		// ta_extendpropdef.setPropName("bmjjprxda");
		// ta_extendpropdef.setTa_id(id);
		// boolean tag=ddflglservices.add(ta_category, ta_extendpropdef);
		// System.out.println(tag);

	}

}
