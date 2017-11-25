/**
 * 
 */
package com.brucex.common.utils;

import java.util.Collection;
import java.util.List;

/**
 * @description 对象工具
 * @author xiongdun
 * @datetime 2017年4月22日上午11:52:45
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

	/**
	 * @description 判断List集合对象是否为空！
	 * @author xiong
	 * @param list
	 * @return boolean
	 * @date 2017年6月26日下午9:57:12
	 */
	public static boolean isEmpty(List<?> list) {
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * @description 判断集合collection 对象是否为空
	 * @author xiong
	 * @param collection
	 * @return boolean
	 * @date 2017年6月26日下午10:00:09
	 */
	public static boolean isEmpty(Collection<?> collection) {
		if (!collection.isEmpty() && collection.size() > 0) {
			return true;
		}
		return false;
	}
}
