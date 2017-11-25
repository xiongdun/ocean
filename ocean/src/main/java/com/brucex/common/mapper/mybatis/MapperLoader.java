/**
 * 
 */
package com.brucex.common.mapper.mybatis;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

/**
 * @description Mybatis的mapper文件中的sql语句被修改后, 只能重启服务器才能被加载, 非常耗时,所以就写了一个自动加载的类,
 *              配置后检查xml文件更改,如果发生变化,重新加载xml里面的内容.
 * @author xiongdun
 * @datetime 2017年4月16日上午9:44:54
 */
public class MapperLoader implements DisposableBean, InitializingBean, ApplicationContextAware {

	/**
	 * 配置spring 上下文
	 */
	private ConfigurableApplicationContext context = null;

	/**
	 * transient 标记字段不能被序列化 basePackage基础包
	 */
	private transient String basePackage = null;

	private HashMap<String, String> fileMapping = new HashMap<String, String>();

	private Scanner scanner = null;

	private ScheduledExecutorService service = null;

	private static Logger logger = Logger.getLogger(MapperLoader.class);

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = (ConfigurableApplicationContext) applicationContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		service = Executors.newScheduledThreadPool(1);
		// 获取xml 所在包
		MapperScannerConfigurer config = context.getBean(MapperScannerConfigurer.class);
		Field field = config.getClass().getDeclaredField("basePackage");
		field.setAccessible(true);
		basePackage = (String) field.get(config);
		
		// 触发文件监听事件
		scanner = new Scanner();
		scanner.scan();
		service.scheduleAtFixedRate(new Task(), 5, 5, TimeUnit.SECONDS);
	}

	@Override
	public void destroy() throws Exception {
		if (service != null) {
			service.shutdownNow();
		}
	}

	class Task implements Runnable {

		@Override
		public void run() {
			try {
				if (scanner.isChanged()) {
					logger.info("*Mapper.xml文件改变,重新加载.");
					scanner.reloadXml();
					logger.info("加载完毕.");
				}
			} catch (Exception e) {
				logger.debug("运行任务异常，异常原因：" + e.getMessage());
			}
		}

	}

	/**
	 * 用于扫描配置xml 文件
	 */
	class Scanner {
		private String[] basePackages;

		private static final String XML_RESOURCE_PATTERN = "**/*.xml";

		private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

		/**
		 * 从包中扫描相关配置文件
		 */
		public Scanner() {
			basePackages = StringUtils.tokenizeToStringArray(MapperLoader.this.basePackage,
					ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
		}

		/**
		 * @description 获取配置源文件
		 * @datetime 2017年4月16日上午10:16:15
		 * @author xiongdun
		 * @param basePackage
		 * @param pattern
		 * @return
		 * @throws IOException
		 */
		public Resource[] getResource(String basePackage, String pattern) throws IOException {
			String packageSerchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils
					.convertClassNameToResourcePath(context.getEnvironment().resolveRequiredPlaceholders(basePackage))
					+ "/" + pattern;
			return resourcePatternResolver.getResources(packageSerchPath);
		}
		
		/**
		 * @description 重新加载xml 配置文件
		 * @datetime 2017年4月16日上午10:25:05
		 * @author xiongdun
		 * @throws IOException 
		 */
		public void reloadXml() throws IOException {
			SqlSessionFactory factory = context.getBean(SqlSessionFactory.class);
			Configuration configuration = factory.getConfiguration();
			// 移除加载项
			removeConfig(configuration);
			// 重新扫描加载
			for (String basePackage : basePackages) {
				Resource[] resources = getResource(basePackage, XML_RESOURCE_PATTERN);
				if (resources != null) {
					for (int i = 0; i < resources.length; i++) {
						if (resources[i] == null) {
							continue;
						}
						try {
							XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(resources[i].getInputStream(),
									configuration, resources[i].toString(), configuration.getSqlFragments());
							xmlMapperBuilder.parse();
						} catch (Exception e) {
							throw new NestedIOException("Failed to parse mapping resource: '" + resources[i] + "'", e);
						} finally {
							ErrorContext.instance().reset();
						}
					}
				}
			}
		}
		
		/**
		 * @description 移除配置文件加载项
		 * @datetime 2017年4月16日上午10:25:36
		 * @author xiongdun
		 * @param configuration
		 */
		public void removeConfig(Configuration configuration) {
			Class<?> classConfig = configuration.getClass();
			clearMap(classConfig, configuration, "mappedStatements");
			clearMap(classConfig, configuration, "caches");
			clearMap(classConfig, configuration, "resultMaps");
			clearMap(classConfig, configuration, "parameterMaps");
			clearMap(classConfig, configuration, "keyGenerators");
			clearMap(classConfig, configuration, "sqlFragments");
			clearSet(classConfig, configuration, "loadedResources");
		}
		
		/**
		 * @description 移除配置项
		 * @datetime 2017年4月16日上午10:31:11
		 * @author xiongdun
		 * @param classConfig
		 * @param configuration
		 * @param fieldName
		 */
		@SuppressWarnings("rawtypes")
		private void clearMap(Class<?> classConfig, Configuration configuration, String fieldName) {
			Field field = null;
			try {
				field = classConfig.getDeclaredField(fieldName);
				field.setAccessible(true);
				Map mapConfig = (Map) field.get(configuration);
				mapConfig.clear();
			} catch (Exception e) {
				logger.debug("异常原因：" + e.getMessage());
			}
		}
		
		/**
		 * @description 移除配置项
		 * @datetime 2017年4月16日上午10:32:06
		 * @author xiongdun
		 * @param classConfig
		 * @param configuration
		 * @param fieldName
		 */
		@SuppressWarnings("rawtypes")
		private void clearSet(Class<?> classConfig, Configuration configuration, String fieldName) {
			Field field = null;
			try {
				field = classConfig.getDeclaredField(fieldName);
				field.setAccessible(true);
				Set setConfig = (Set) field.get(configuration);
				setConfig.clear();
			} catch (Exception e) {
				logger.debug("异常原因：" + e.getMessage());
			}
		}
		
		/**
		 * @description 扫描
		 * @datetime 2017年4月16日上午10:34:12
		 * @author xiongdun
		 * @throws IOException
		 */
		public void scan() throws IOException {
			if (!fileMapping.isEmpty()) {
				return;
			}
			for (String basePackage : basePackages) {
				Resource[] resources = getResource(basePackage, XML_RESOURCE_PATTERN);
				if (resources != null) {
					for (int i = 0; i < resources.length; i++) {
						fileMapping.put(resources[i].getFilename(), getValue(resources[i]));
					}
				}
			}
		}
		
		/**
		 * @description 获取配置文件的值
		 * @datetime 2017年4月16日上午10:37:35
		 * @author xiongdun
		 * @param resource
		 * @return
		 * @throws IOException
		 */
		private String getValue(Resource resource) throws IOException {
			String contentLength = String.valueOf((resource.contentLength()));
			String lastModified = String.valueOf((resource.lastModified()));
			return new StringBuilder(contentLength).append(lastModified).toString();
		}
		
		public boolean isChanged() throws IOException {
			boolean isChanged = false;
			for (String basePackage : basePackages) {
				Resource[] resources = getResource(basePackage, XML_RESOURCE_PATTERN);
				if (resources != null) {
					for (int i = 0; i < resources.length; i++) {
						String name = resources[i].getFilename();
						String value = fileMapping.get(name);
						String multi_key = getValue(resources[i]);
						if (!multi_key.equals(value)) {
							isChanged = true;
							fileMapping.put(name, multi_key);
						}
					}
				}
			}
			return isChanged;
		}
	}
}
