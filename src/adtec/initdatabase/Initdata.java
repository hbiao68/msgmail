package adtec.initdatabase;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;

import adtec.initdatabase.util.GetWriteData;

public class Initdata extends ContextLoaderListener {
		
	
		private ContextLoader contextLoader;
		private Logger log = Logger.getLogger(Initdata.class);
		
		@Override
		public void contextInitialized(ServletContextEvent event) {
			//spring启动之前获取配置文件的信息，一定要在实例化这个对象之前，即super()方法
			//获取properties中setup.database的值
			
			String webPath = event.getServletContext().getRealPath(File.separator);
			String seprator = File.separator;
			String path = webPath + "WEB-INF" + seprator + "config" + seprator + "project" + seprator + "project.properties";
			
			
			String setup = GetWriteData.getSetupData(path,"setup.database");
			//如果值为false，执行创建数据库以及表的脚本。
			if(setup.equals("false")){
				String iP = GetWriteData.getSetupData(path,"database.iP").trim();
				String port = GetWriteData.getSetupData(path,"database.port").trim();
				String userName = GetWriteData.getSetupData(path,"database.userName").trim();
				String passWord = GetWriteData.getSetupData(path,"database.passWord").trim();;
				
				boolean tag = GetWriteData.createData(iP,port,userName,passWord);
				if(tag){
					//成功后将setup.database的值变成true
					//GetWriteData.WriteData(path);
					try {
						GetWriteData.setProfileString(path, "setup.database", "true");
					} catch (IOException e) {
						log.error("修改配置文件的setup.database出错 ");
					}
				}
			}
		super.contextInitialized(event);
		this.contextLoader = createContextLoader();

		}
		
		 protected ContextLoader createContextLoader() {
             return new ContextLoader();
      }
      /**
      * Return the ContextLoader used by this listener.
      * @return the current ContextLoader
      */
      public ContextLoader getContextLoader() {
             return this.contextLoader;
      }

}
