package adtec.plugin;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import adtec.init.ProjectProperty;
import adtec.plugin.MessageInteceptorFilter;
import adtec.plugin.MessageInteceptorManager;
import adtec.plugin.MessageInterceptor;

public class PluginTimerTask extends TimerTask {

	Logger log = Logger.getLogger(PluginTimerTask.class);
	// 获取指定目录中上传的插件，然后将插件中的类加载进来
	URL[] urls = new URL[] {};
	MyClassLoader classLoader = new MyClassLoader(urls, MessageInteceptorManager.class.getClassLoader());

	@Override
	public void run() {
		
//		log.debug("开始扫描文件夹");
		
		// String pluginDir = "d:/myspring/plugin/";
		// 从系统配置文件中获取插件存放的位置,如果没有，则获取当前的值
		String pluginDir = ProjectProperty.getInstance().get("plugin.dir");
		if (null == pluginDir || "".equals(pluginDir)) {
			pluginDir = ".";
		}
		File fileDir = new File(pluginDir);
		// 2、获取这个目录中的所有jar文件，因此就需要对指定目录的文件过滤
		File[] jars = fileDir.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				String fileName = pathname.getName().toLowerCase();
				return fileName.endsWith(".jar");
			}
		});

		// 如果没有插件，则就不需要执行后面解析jar的方法
		if (jars == null) {
			return;
		}

		for (int i = 0; i < jars.length; i++) {
			File tempFile = jars[i];
			try {
				classLoader.addJar(tempFile.toURI().toURL());
				// 继承Interceptor接口的类要在配置文件中说明
				// 3、解析文件中的jar，通过jar中的配置文件中找到Interceptor的子类
				String interceptorClass = MessageInteceptorFilter.getInterceptorClass(tempFile);
				if (!"".equals(interceptorClass) && null != interceptorClass) {
					Class<?> clazz = classLoader.loadClass(interceptorClass);
//					MessageInterceptor interceptor = (MessageInterceptor) clazz.newInstance();
					Constructor constructor = clazz.getConstructor(ServletContext.class);
					MessageInterceptor interceptor = (MessageInterceptor) constructor.newInstance(MessageInteceptorFilter.servletContext);
					//4、将获取的子类放入拦截器链表中
					// interceptor.interceptor();
					// 4、将获取的子类放入拦截器链表中
					MessageInteceptorManager.getInstance().addInterceptor(interceptorClass, interceptor);
				}
			} catch (MalformedURLException e) {
				log.error("加载 插件 失败");
				log.error(e.getMessage());
			} catch (ClassNotFoundException e) {
				log.error("找不到指定路径的类");
				log.error(e.getMessage());
			} catch (InstantiationException e) {
				log.error("根据制定类的路径创建对象失败");
				log.error(e.getMessage());
			} catch (IllegalAccessException e) {
				log.error("根据制定路径创建对象失败，参数异常");
				log.error(e.getMessage());
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	class MyClassLoader extends URLClassLoader {
		public MyClassLoader(URL[] urls) {
			super(urls);
		}

		public MyClassLoader(URL[] urls, ClassLoader parent) {
			super(urls, parent);
		}

		public void addJar(URL url) {
			this.addURL(url);
		}
	}
}
