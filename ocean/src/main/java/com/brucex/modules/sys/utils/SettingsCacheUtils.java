/**
 * 
 */
package com.brucex.modules.sys.utils;

import com.brucex.common.spring.SpringContextHolder;
import com.brucex.modules.sys.entity.Settings;
import com.brucex.modules.sys.service.SettingsService;

/**
 * @description SettingsCacheUtils
 * @author xiongdun
 * @datetime 2017年4月25日下午10:04:03
 */
public class SettingsCacheUtils {
	
	private static SettingsService settingsService = SpringContextHolder.getBean(SettingsService.class);
	

	/**
	 * @description TODO
	 * @author xiong
	 * @param name
	 * @return Settings
	 * @date 2017年6月24日下午2:00:44
	 */
	public static Settings get(String name) {
		settingsService.getById("1");
		return new Settings();
	}

}
