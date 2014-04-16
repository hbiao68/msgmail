package adtec.plugin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import adtec.init.ProjectProperty;

@Controller
@RequestMapping(value = "/plugin")
public class PluginController {

	Logger log = Logger.getLogger(PluginController.class);

	/**
	 * 跳转到上传附件的页面
	 * @return
	 */
	@RequestMapping(value = "/pluginAdd")
	public String pluginAdd() {
		log.debug("pluginAdd");
		return "plugin/pluginAdd";
	}

	/**
	 *  上传文件对应的action处理方法
	 * @param multipartRequest
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/pluginAddAction", method = RequestMethod.POST)
	public String pluginAddAction(MultipartHttpServletRequest multipartRequest, HttpServletRequest request) {
		log.debug("pluginAddAction");

		String sucMsg = "";
		try {
			MultipartFile file = multipartRequest.getFile("file"); // 获得文件
			InputStream fin = file.getInputStream(); // 获得输入流
			String realPath = request.getSession().getServletContext().getRealPath("/");
			
//			String pluginDir = "d:/myspring/plugin/";
			//获取配置文件中上传文件的位置
			String pluginDir = ProjectProperty.getInstance().get("plugin.dir");
			
			//创建文件夹
			File dir = new File(pluginDir);
			//判断文件夹是否存在，如果不存在则会创建对应的文件夹
			if(!dir.isDirectory()){
				//级联创建文件夹（创建多个文件夹，如果文件夹不存在）
				dir.mkdirs();
			}

			log.debug(file.getName());
			log.debug(file.getOriginalFilename());
			
			//写文件
//			File outFile = new File("d:/myspring/plugin/" + file.getOriginalFilename()); // 获得文件名);
			File outFile = new File(pluginDir + file.getOriginalFilename()); // 获得文件名);
			//如果文件存在，则说明是重复的，因此不能添加
			if(outFile.exists()){
				log.debug("文件已经存在，请重上传");
				return "plugin/pluginAdd"; 
			}
			if(outFile.isFile()){
				outFile.createNewFile();
			}
			
			FileOutputStream fout = new FileOutputStream(outFile);
			byte[] b = new byte[1024];
			int length = 0;
			while ((length = fin.read(b)) != -1) {
				fout.write(b, 0, length); // 写出文件
			}

			if (fin != null) {
				fin.close(); // 关闭从web读取的输入流
			}
			if (fout != null) {
				fout.close(); // 关闭写文件的输出流
			}

			return "plugin/pluginAdd";
		} catch (Exception e) {
			log.error("上传插件失败");
			log.error(e.getMessage());
		}
		
		return "plugin/pluginAdd";

	}
	
}
