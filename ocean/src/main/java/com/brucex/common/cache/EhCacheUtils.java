/**
 * 
 */
package com.brucex.common.cache;

import com.brucex.common.spring.SpringContextHolder;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @description encache 缓存处理工具类
 * @author xiongdun
 * @datetime 2017年4月8日下午3:36:27
 */
public class EhCacheUtils {

	private static CacheManager cacheManager = ((CacheManager) SpringContextHolder.getBean("cacheManager"));

	private static final String SYS_CACHE = "sysCache";

	/**
	 * @description 获取SYS_CACHE缓存
	 * @author xiongdun
	 * @datetime 2017年4月29日下午10:57:13
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return get(SYS_CACHE, key);
	}

	/**
	 * @description 写入SYS_CACHE缓存
	 * @author xiongdun
	 * @datetime 2017年4月29日下午10:57:05
	 * @param key
	 * @param value
	 */
	public static void put(String key, Object value) {
		put(SYS_CACHE, key, value);
	}

	/**
	 * @description 从SYS_CACHE缓存中移除
	 * @author xiongdun
	 * @datetime 2017年4月29日下午10:56:56
	 * @param key
	 */
	public static void remove(String key) {
		remove(SYS_CACHE, key);
	}

	/**
	 * @description 获取缓存
	 * @author xiongdun
	 * @datetime 2017年4月29日下午10:56:48
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static Object get(String cacheName, String key) {
		Element element = getCache(cacheName).get(key);
		return element == null ? null : element.getObjectValue();
	}

	/**
	 * @description 写入缓存
	 * @author xiongdun
	 * @datetime 2017年4月29日下午10:56:40
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public static void put(String cacheName, String key, Object value) {
		Element element = new Element(key, value);
		getCache(cacheName).put(element);
	}

	/**
	 * @description 从缓存中移除
	 * @author xiongdun
	 * @datetime 2017年4月29日下午10:56:29
	 * @param cacheName
	 * @param key
	 */
	public static void remove(String cacheName, String key) {
		getCache(cacheName).remove(key);
	}

	/**
	 * @description 获得一个Cache，没有则创建一个。
	 * @author xiongdun
	 * @datetime 2017年4月29日下午10:56:20
	 * @param cacheName
	 * @return
	 */
	private static Cache getCache(String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			cacheManager.addCache(cacheName);
			cache = cacheManager.getCache(cacheName);
			cache.getCacheConfiguration().setEternal(true);
		}
		return cache;
	}

	/**
	 * @description 获取 CacheManager 对象
	 * @author xiongdun
	 * @datetime 2017年4月29日下午10:55:52
	 * @return
	 */
	public static CacheManager getCacheManager() {
		return cacheManager;
	}
}
