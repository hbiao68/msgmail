package adtec.categoryManager.clent;

import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import adtec.categoryManager.model.Ta_category;
import adtec.categoryManager.model.Ta_extendpropdef;
import adtec.categoryManager.service.CategoryManagerService;

import java.util.*;

public class DdflglClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		ApplicationContext act= new ClassPathXmlApplicationContext("applicationContext.xml");
		ApplicationContext act=
				new FileSystemXmlApplicationContext("D:/JavaProject/myspring/web/WEB-INF/applicationContext.xml");
		CategoryManagerService ddflglservices=(CategoryManagerService)act.getBean("ddflglServices");
		Ta_category ta_category=new Ta_category();
		Ta_extendpropdef ta_extendpropdef=new Ta_extendpropdef();
//		boolean tag=
//				ddflglservices.createtable();
//				ddflglservices.deltable();
//		System.out.println(tag);
//		List rows=ddflglservices.query();
	//	System.out.println(rows);
		
		
		String id=UUID.randomUUID().toString().replace("-", "");
		ta_category.setTa_id(id);
		ta_category.setAuthClass("bhdsaf");
		ta_category.setCateDesc("jhceoloikwes");
		ta_category.setCateName("klplhytaha");
		ta_category.setImportClass("kebdpjnmiceenvr");
		ta_extendpropdef.setProp_index(1);
		ta_extendpropdef.setPropDesc("dhimpfeicsa");
		ta_extendpropdef.setPropName("bmjjprxda");
		ta_extendpropdef.setTa_id(id);
//		boolean tag=ddflglservices.add(ta_category, ta_extendpropdef);
//		System.out.println(tag);
		
	}

}
