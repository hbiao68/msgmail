package adtec.plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import adtec.init.ProjectProperty;
import adtec.plugin.PluginTimerTask.MyClassLoader;

public class MessageInteceptorFilter implements Filter,ServletContextListener  {

	Logger log = Logger.getLogger(MessageInteceptorFilter.class);
	//系统的上下文
	static ServletContext servletContext;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		//对发送过来的请求全部进行拦截，处理自己添加的插件
//		String param = request.getParameter("content");
//		System.out.println("content : " + param);
//		if(null != param && !"".equals(param)){
//			String result = MessageInteceptorManager.getInstance().interceptorsAction(param);
//			System.out.println("MessageInteceptorManager.getInstance().interceptorsAction(param) : " + result);
//		}
		
		//将请求交给过滤器链处理
		filterChain.doFilter(request, response);
	}

	// 动态的添加拦截器
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		//获取工程默认的拦截器
		loadProjectInterceptor(ProjectProperty.getInstance().get("plugin.project.load.class"));
		
		// 1、监听某个目录(定时扫描目录，看是否上传了新的插件)
		Timer timer = new Timer();
		timer.schedule(new PluginTimerTask(),2000,5000);
//		//2秒之后每隔五秒执行run方法
//		timer.schedule(new TimerTask() {
//			// 获取指定目录中上传的插件，然后将插件中的类加载进来
//			URL[] urls = new URL[] {};
//			MyClassLoader classLoader = new MyClassLoader(urls, MessageInteceptorManager.class.getClassLoader());
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
////				String pluginDir = "d:/myspring/plugin/";
//				//从系统配置文件中获取插件存放的位置,如果没有，则获取当前的值
//				String pluginDir = ProjectProperty.getInstance().get("plugin.dir");
//				if(null == pluginDir || "".equals(pluginDir)){
//					pluginDir = ".";
//				}
//				File fileDir = new File(pluginDir);
//				// 2、获取这个目录中的所有jar文件，因此就需要对指定目录的文件过滤
//				File[] jars = fileDir.listFiles(new FileFilter() {
//					public boolean accept(File pathname) {
//						String fileName = pathname.getName().toLowerCase();
//						return fileName.endsWith(".jar");
//					}
//				});
//				
//				//如果没有插件，则就不需要执行后面解析jar的方法
//				if(jars == null){
//					return ;
//				}
//				
//				for (int i = 0; i < jars.length; i++) {
//					File tempFile = jars[i];
//					try {
//						classLoader.addJar(tempFile.toURI().toURL());
//						// 继承Interceptor接口的类要在配置文件中说明
//						// 3、解析文件中的jar，通过jar中的配置文件中找到Interceptor的子类
//						String interceptorClass = MessageInteceptorFilter.getInterceptorClass(tempFile);
//						if(!"".equals(interceptorClass) && null != interceptorClass){
//							Class<?> clazz = classLoader.loadClass(interceptorClass);
//							MessageInterceptor interceptor = (MessageInterceptor) clazz.newInstance();
//							// interceptor.interceptor();
//							// 4、将获取的子类放入拦截器链表中
//							MessageInteceptorManager.getInstance().addInterceptor(interceptorClass, interceptor);
//						}
//					} catch (MalformedURLException e) {
//						log.error("加载 插件 失败");
//						log.error(e.getMessage());
//					} catch (ClassNotFoundException e) {
//						log.error("找不到指定路径的类");
//						log.error(e.getMessage());
//					} catch (InstantiationException e) {
//						log.error("根据制定类的路径创建对象失败");
//						log.error(e.getMessage());
//					} catch (IllegalAccessException e) {
//						log.error("根据制定路径创建对象失败，参数异常");
//						log.error(e.getMessage());
//					}
//				}
//				
//			}
//		}, 2000, 50000);
//		
//		
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

	
	/**
	 * 根据传递过来的参数交给MessageInteceptorManager管理
	 * 传递过来的参数格式是：abc.Class1,bcd.Class2
	 * @param classes
	 */
	public void loadProjectInterceptor(String classes){
		//如果参数不对，则不用继续加载了
		if(StringUtils.isEmpty(classes)){
			return;
		}
		
		// 获取指定目录中上传的插件，然后将插件中的类加载进来
		URL[] urls = new URL[] {};
		MyClassLoader classLoader = new MyClassLoader(urls, MessageInteceptorManager.class.getClassLoader());
		StringTokenizer token = new StringTokenizer(classes,",");
		while(token.hasMoreElements()){
			String clazzStr = (String)token.nextElement();
			if(StringUtils.isNotBlank(clazzStr)){
				Class<?> clazz;
				try {
					clazz = classLoader.loadClass(clazzStr);
//					MessageInterceptor interceptor = (MessageInterceptor) clazz.newInstance();
					Constructor constructor = clazz.getConstructor(ServletContext.class);
					MessageInterceptor interceptor = (MessageInterceptor) constructor.newInstance(MessageInteceptorFilter.servletContext);
					//4、将获取的子类放入拦截器链表中
					MessageInteceptorManager.getInstance().addInterceptor(clazzStr, interceptor);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
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
		
	}
	
	/**
	 * 获取插件jar包中配置文件的主class节点的内容
	 * @param tempFile
	 * @return
	 */
	public static String getInterceptorClass(File tempFile) {
		BufferedReader br = null;
		InputStream inputStream = null;
		try {
			JarFile jarFile = new JarFile(tempFile);
			ZipEntry zipEntry = jarFile.getEntry("plugin.xml");
			inputStream = jarFile.getInputStream(zipEntry);
			br = new BufferedReader(new InputStreamReader(inputStream));
			SAXReader saxReader = new SAXReader();
			saxReader.setEncoding("UTF-8");
			Document document = null;
			document = saxReader.read(br);
			Element minServerVersion = (Element) document.selectSingleNode("/plugin/class");
			if (minServerVersion != null) {
				String requiredVersion = minServerVersion.getTextTrim();
				return requiredVersion;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletcontextevent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent servletcontextevent) {
		MessageInteceptorFilter.servletContext = servletcontextevent.getServletContext();
	}

}
