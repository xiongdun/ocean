/**
 * 
 */
package com.brucex.common.config;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import org.springframework.core.io.DefaultResourceLoader;

import com.brucex.common.utils.StringUtils;

/**
 * @description 获取common配置文件中的值，用于项目中所需的静态配置项
 * @author xiongdun
 * @datetime 2017年4月8日下午8:05:47
 */
public class Global {

	/**
	 * 静态资源文件绑定
	 */
	private static final ResourceBundle COMMON = ResourceBundle.getBundle("common");

	/**
	 * 显示/隐藏
	 */
	public static final String SHOW = "1";
	public static final String HIDE = "0";

	/**
	 * 是/否
	 */
	public static final String YES = "1";
	public static final String NO = "0";
	
	/**
	 * 对/错
	 */
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	/**
	 * 上传文件基础虚拟路径
	 */
	public static final String USERFILES_BASE_URL = "/userfiles/";
	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();

	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}

	/**
	 * @description 获取string 值
	 * @datetime 2017年4月8日下午9:02:22
	 * @author xiongdun
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		return COMMON.getString(key);
	}

	/**
	 * @description 获取上传文件的根目录
	 * @author xiongdun
	 * @datetime 2017年4月21日下午9:36:48
	 * @return
	 */
	public static String getUserfilesBaseDir() {
		String dir = Global.getString("userfiles.basedir");
		if (StringUtils.isBlank(dir)) {
			try {
				dir = null;//ServletContextFactory.getServletContext().getRealPath("/");
			} catch (Exception e) {
				return "";
			}
		}
		if (!dir.endsWith("/")) {
			dir += "/";
		}
		return dir;
	}

	/**
	 * 获取工程路径
	 * 
	 * @return
	 */
	public static String getProjectPath() {
		// 如果配置了工程路径，则直接返回，否则自动获取。
		String projectPath = Global.getString("projectPath");
		if (StringUtils.isNotBlank(projectPath)) {
			return projectPath;
		}
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null) {
				while (true) {
					File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
					if (f == null || f.exists()) {
						break;
					}
					if (file.getParentFile() != null) {
						file = file.getParentFile();
					} else {
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {
		}
		return projectPath;
	}
	

	/**
	 * @description 获取值，为空则取默认值
	 * @datetime 2017年4月8日下午9:11:38
	 * @author xiongdun
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getString(String key, String defaultValue) {
		if (StringUtils.isEmpty(COMMON.getString(key))) {
			return defaultValue;
		}
		return getString(key);
	}

	/**
	 * @description 获取Int类型的值
	 * @datetime 2017年4月8日下午9:14:53
	 * @author xiongdun
	 * @param key
	 * @return
	 */
	public static int getInt(String key) {
		return Integer.parseInt(COMMON.getString(key));
	}

	/**
	 * @description 获取Int类型的值，为空则取默认值
	 * @datetime 2017年4月8日下午9:15:12
	 * @author xiongdun
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getInt(String key, int defaultValue) {
		if (StringUtils.isEmpty(COMMON.getString(key))) {
			return defaultValue;
		}
		return getInt(key);
	}

	/**
	 * @description 获取系统配置路径
	 * @datetime 2017年4月8日下午11:36:53
	 * @author xiongdun
	 * @return
	 */
	public static String getAdminPath() {
		return getString("sys.adminPath");
	}

	/**
	 * @description 获取数据库类型
	 * @datetime 2017年4月11日下午11:26:47
	 * @author xiongdun
	 * @return
	 */
	public static String getDbType() {
		return getString("jdbc.dbtype");
	}
	
	/**
	 * @description 获取动态映射URL后缀
	 * @author xiongdun
	 * @datetime 2017年4月30日下午12:57:07
	 * @return
	 */
	public static String getUrlSuffix() {
		return getString("urlSuffix");
	}
}
