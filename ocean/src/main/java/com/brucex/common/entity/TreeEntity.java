/**
 * 
 */
package com.brucex.common.entity;

import com.brucex.common.utils.Reflections;
import com.brucex.common.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @description 树机构数据 entity
 * @author xiongdun
 * @datetime 2017年4月23日上午11:29:54
 */
public abstract class TreeEntity<T> extends DataEntity<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6673301612496194550L;
	
	protected T parent;// 父结构数据实体
	protected String parentIds;//父级ID列表
	protected String name;// 树结构数据名称
	protected Integer sort;// 排序号
	
	//#######################       constractor   #######################
	
	public TreeEntity() {
		super();
		this.sort = 30;
	}
	
	public TreeEntity(String id) {
		super(id);
	}
	
	//#########################    abstract            #######################
	
	/**
	 * @description 交由子类实现 获取父机构数据
	 * @author xiongdun
	 * @datetime 2017年4月23日上午11:42:43
	 * @return
	 */
	@JsonBackReference
	public abstract T getParent();
	
	/**
	 * @description 交由子类实现，插入父结构数据
	 * @author xiongdun
	 * @datetime 2017年4月23日上午11:43:08
	 * @param parent
	 */
	public abstract void setParent(T parent);
	
	//###################### getter and setter  ########################
	
	public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	/**
	 * @description 获取父级Id
	 * @author xiongdun
	 * @datetime 2017年4月23日上午11:49:30
	 * @return
	 */
	public String getParentId() {
		String id = null;
		if (parent != null) {
			id = (String) Reflections.getFieldValue("parent", "id");
		}
		return StringUtils.isNotBlank(id) ? id : "0";
	}
	
}
