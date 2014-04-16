package adtec.initdatabase.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

public class GetWriteData {

	// 获取project.properties中setup.database的信息
	/**
	 * 获取project.properties中属性值
	 * 
	 * @param path
	 * @param propKey
	 * @return
	 */
	public static String getSetupData(String path, String propKey) {
		Logger log = Logger.getLogger(GetWriteData.class);
		try {
			Properties prop = new Properties();// 属性集合对象

			// FileInputStream fis = new
			// FileInputStream("D:/JavaProject/myspring/web/WEB-INF/config/project/project.properties");//

			// 属性文件输入流
			FileInputStream fis = new FileInputStream(path);// 属性文件输入流

			prop.load(fis);// 将属性文件流装载到Properties对象中
			fis.close();// 关闭流
			// 获取属性值，sitename已在文件中定义
			// System.out.println("获取属性值：setup.database="+
			// prop.getProperty("setup.database"));
			log.debug("获取属性值：" + propKey + "=" + prop.getProperty(propKey));

			return prop.getProperty(propKey);
		} catch (FileNotFoundException e) {
			log.debug("读取project.properties文件出错");
		} catch (IOException e) {
			log.debug("输入输出流异常");
		}
		return null;
	}

	// 修改project.properties中setup.database的信息
	public static void WriteData(String path) {
		Logger log = Logger.getLogger(GetWriteData.class);
		try {
			Properties prop = new Properties();// 属性集合对象
			// FileInputStream fis = new
			// FileInputStream("D:/JavaProject/myspring/web/WEB-INF/config/project/project.properties");//
			// 属性文件输入流

			FileInputStream fis = new FileInputStream(path);// 属性文件输入流
			prop.load(fis);// 将属性文件流装载到Properties对象中
			fis.close();// 关闭流
			log.debug("获取属性值：setup.database="
					+ prop.getProperty("setup.database"));
			// 修改sitename的属性值
			prop.setProperty("setup.database", "true");
			// 文件输出流
			// FileOutputStream fos = new
			// FileOutputStream("D:/JavaProject/myspring/web/WEB-INF/config/project/project.properties");
			FileOutputStream fos = new FileOutputStream(path);

			prop.store(fos, "Copyright (c) Boxcode Studio");// 将Properties集合保存到流中
			fos.close();// 关闭流
			log.debug("获取修改后的属性值：setup.database="
					+ prop.getProperty("setup.database"));

		} catch (FileNotFoundException e) {
			log.error("读取文件异常");
		} catch (IOException e) {
			log.error("输入输出文件异常");
		}
	}

	// 执行myspring.sql脚本，创建数据库以及数据库所用的表
	public static boolean createData(String iP, String port, String userName,
			String passWord) {
		Logger log = Logger.getLogger(GetWriteData.class);
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Connection conn = (Connection) DriverManager.getConnection(
			// "jdbc:mysql://localhost:3306/", "root", "admin");
			//String url = "jdbc:mysql://localhost:3306/";
			String url = "jdbc:mysql://"+iP+":"+port+"/";
			Connection conn = (Connection) DriverManager
					.getConnection(url,
							userName, passWord);
			ScriptRunner runner = new ScriptRunner(conn);
			runner.setErrorLogWriter(null);
			runner.setLogWriter(null);
			runner.runScript(Resources
					.getResourceAsReader("adtec/initdatabase/myspring.sql"));
			return true;
		} catch (Exception e) {
			// 出错了删除数据库
			log.error("创建数据库出错");
			return false;
		}
	}

	/**
	 * 修改配置文件
	 * 
	 * @param filePath
	 *            要修改的配置文件的路径
	 * @param key
	 *            配置文件的key
	 * @param value
	 *            要修改的value
	 * @return true表示修改成功 false表示修改失败
	 * @throws IOException
	 */
	public static boolean setProfileString(String filePath, String key,
			String value) throws IOException {
		Logger log = Logger.getLogger(GetWriteData.class);
		File f = new File(filePath);
		boolean res = false;
		if (f.exists()) {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String outstr = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				if (line == "") // 如果为空
				{
					outstr += "\n";
					continue;
				}
				if (line.startsWith("#")) // 如果为注释
				{
					outstr += line + "\n";
					continue;
				}
				if (line.trim().startsWith(key)) {
					String[] keyandvalue = line.split("=");

					outstr += keyandvalue[0].toString() + "="
							+ value.toString() + "\n";

					res = true;
					continue;
				}

				outstr += line + "\n";

			}
			//System.out.println(outstr);
			log.debug(outstr);
			if (res) {

				FileWriter fw = new FileWriter(filePath, false);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(outstr);

				bw.close();
				fw.close();
				return true;

			}

		}
		return false;

	}

}
