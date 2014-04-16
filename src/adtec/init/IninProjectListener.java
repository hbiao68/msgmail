package adtec.init;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class IninProjectListener implements ServletContextListener {

	Logger log = Logger.getLogger(IninProjectListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent context) {

	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		//加载/WEB-INF/config/ajmessage/ajmessage.properties 配置文件到AjmessageProperty对象中
		loadAjmessageProperty(context.getServletContext().getRealPath(File.separator));
		
		//加载/WEB-INF/config/project/project.properties 配置文件到MyspringProperty对象中
		loadProjectProperty(context.getServletContext().getRealPath(File.separator));
		
		
		System.out.println("-----------------拉起消息邮局----------------------");
		
		System.out.println(ProjectProperty.getInstance().get("ajmessage.script.start"));

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				//获取启动OpenFile脚本的位置
				String scriptFileStr = ProjectProperty.getInstance().get("ajmessage.script.start");
				log.info(scriptFileStr);
				//如果没有找到脚本的位置，则不启动脚本
//				String scriptFileStr = "f:/calc.exe";	
				if(scriptFileStr != null){
					try {
						Process p = Runtime.getRuntime().exec(scriptFileStr);
						System.out.println("p : " + p);
						p.waitFor();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}).start();
		
//		ServerStart
		
		
	}

	/**
	 * 获取/WEB-INF/config/ajmessage/ajmessage.properties 配置文件
	 * @param webPath
	 */
	public void loadAjmessageProperty(String webPath) {

		String seprator = File.separator;
		String path = webPath + "WEB-INF" + seprator + "config" + seprator + "ajmessage" + seprator + "ajmessage.properties";
		System.out.println(path);
		Properties prop = new Properties();
		FileInputStream ferr = null;
		try {
			ferr = new FileInputStream(path);
			prop.load(ferr);
		} catch (FileNotFoundException e) {
			log.error("找不到文件");
		}// 用subString(6)去掉：file:/try{
		catch (IOException e) {
			log.error("读取数据流出现异常");
		}finally{
			try {
				ferr.close();
			} catch (IOException e) {
				log.error("关于数据流出错");
			}
		}
		
		AjmessageProperty.getInstance().loadProperties(prop);

	}
	
	
	/**
	 * 获取/WEB-INF/config/project/project.properties 配置文件
	 * @param webPath
	 */
	public void loadProjectProperty(String webPath) {

		String seprator = File.separator;
		String path = webPath + "WEB-INF" + seprator + "config" + seprator + "project" + seprator + "project.properties";
		log.debug("project.properties的路径是：" + path);
		Properties prop = new Properties();
		FileInputStream ferr = null;
		try {
			ferr = new FileInputStream(path);
			prop.load(ferr);
		} catch (FileNotFoundException e) {
			log.error("找不到project.properties 配置文件");
		}// 用subString(6)去掉：file:/try{
		catch (IOException e) {
			log.error("读取project.properties数据流出现异常");
		}finally{
			try {
				ferr.close();
			} catch (IOException e) {
				log.error("关于数据流出错");
			}
		}
		
		ProjectProperty.getInstance().loadProperties(prop);

	}

}
