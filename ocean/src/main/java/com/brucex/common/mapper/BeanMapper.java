/**
 * 
 */
package com.brucex.common.mapper;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;

import com.google.common.collect.Lists;

/**
 * @description 对象映射工具
 * @author xiongdun
 * @datetime 2017年4月8日下午1:33:57
 */
public class BeanMapper {
	
	private static DozerBeanMapper dozer = new DozerBeanMapper();
	
	/**
	 * @description  构造新的destinationClass实例对象，通过source对象中的字段内容
	 * 映射到destinationClass实例对象中，并返回新的destinationClass实例对象。
	 * @datetime 2017年4月8日下午1:37:17
	 * @author xiongdun
	 * @param source 源数据对象
	 * @param destinationClass 要构造新的实例对象Class 
	 * @return
	 */
	public static <T> T map(Object source, Class<T> destinationClass) {
		return dozer.map(source, destinationClass);
	}
	
	/**
	 * @description 将集合对象source的所有属性值拷贝到集合对象destination中
	 * @datetime 2017年4月8日下午2:03:06
	 * @author xiongdun
	 * @param sourceList 集合对象source
	 * @param destinationClass 集合对象destination
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
		List destinationList = Lists.newArrayList();
		for (Iterator iterator = sourceList.iterator(); iterator.hasNext();) {
			Object sourceObject = (Object) iterator.next();
			Object destinationObject = dozer.map(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		}
		return destinationList;
	}
	
	/**
	 * @description 将对象source的所有属性值拷贝到对象destination中
	 * @datetime 2017年4月8日下午2:17:50
	 * @author xiongdun
	 * @param source 对象source
	 * @param destinationObject 对象destination 
	 */
	public static void copy(Object source, Object destinationObject) {
		dozer.map(source, destinationObject);
	}
}
