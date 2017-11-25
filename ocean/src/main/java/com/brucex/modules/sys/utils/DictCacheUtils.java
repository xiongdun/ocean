/**
 * 
 */
package com.brucex.modules.sys.utils;

import java.util.List;

import org.apache.log4j.Logger;

import com.brucex.common.cache.EhCacheUtils;
import com.brucex.common.spring.SpringContextHolder;
import com.brucex.common.utils.ObjectUtils;
import com.brucex.modules.sys.entity.Dict;
import com.brucex.modules.sys.service.DictService;

/**
 * @description DictCacheUtils
 * @author xiongdun
 * @datetime 2017年4月25日下午10:04:03
 */
public class DictCacheUtils {
	
	private static final Logger logger = Logger.getLogger(DictCacheUtils.class);
	
	private static DictService dictService = SpringContextHolder.getBean(DictService.class);

	private static final String CACHE_DICT_LIST = "dictList";
	
	@SuppressWarnings("unchecked")
	public static List<Dict> getDictList() {
		List<Dict> dicts = (List<Dict>) EhCacheUtils.get(CACHE_DICT_LIST);
		if (ObjectUtils.isEmpty(dicts)) {
			logger.debug("缓存中值为空");
			Dict dict = new Dict();
			dict.setType("1");
			dicts = dictService.findList(dict);
		}
		return dicts;
	}
	
}
