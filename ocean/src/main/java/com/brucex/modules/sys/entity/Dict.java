/**
 * 
 */
package com.brucex.modules.sys.entity;

import com.brucex.common.entity.DataEntity;

/**
 * @description 数据字典 entity 类
 * @author xiongdun
 * @datetime 2017年4月8日下午4:19:10
 */
public class Dict extends DataEntity<Dict> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1935858760215381168L;

	private String parentId; // 父级ID
	private String parentIds; // 父级ID列表
	private String label; // 显示名称
	private String value; // 实际值
	private String type; // 数据类型
	private String englishName;// 英文名
	private Integer sort; // 排序值

	// ################ constructor ###########

	public Dict() {

	}

	public Dict(String id) {
		super(id);
	}

	public Dict(String parentId, String parentIds, String label, String value, String type, String englishName,
			Integer sort) {
		super();
		this.parentId = parentId;
		this.parentIds = parentIds;
		this.label = label;
		this.value = value;
		this.type = type;
		this.englishName = englishName;
		this.sort = sort;
	}

	// ########### getter and setter ################
	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "Dict [parentId=" + parentId + ", parentIds=" + parentIds + ", label=" + label + ", value=" + value
				+ ", type=" + type + ", sort=" + sort + "]";
	}

	@Override
	public int hashCode() {
		int result = 17;
		if (label != null) {
			result = 37 * result + label.hashCode();
		}
		result = 37 * result + 1;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dict other = (Dict) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}
	
	
}
