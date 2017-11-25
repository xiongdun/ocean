/**
 * 
 */
package com.brucex.common.dao;

import java.util.List;

import com.brucex.common.entity.TreeEntity;

/**
 * @description 树结构数据查询
 * @author xiongdun
 * @datetime 2017年4月23日上午11:30:18
 */
public interface TreeDao<T extends TreeEntity<T>> extends CrudDao<T> {

	/**
	 * @description 找到所有子节点
	 * @author xiongdun
	 * @datetime 2017年4月23日下午3:06:30
	 * @param entity
	 * @return
	 */
	public List<T> findByParentIdsLike(T entity);
	
	/**
	 * @description 更新所有父节点字段
	 * @author xiongdun
	 * @datetime 2017年4月23日下午3:06:34
	 * @param entity
	 * @return
	 */
	public int updateParentIds(T entity);
}
