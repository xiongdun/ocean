/**
 * 
 */
package com.brucex.modules.sys.entity;

import com.brucex.common.entity.DataEntity;

/**
 * @description 城市地区实体
 * @author xiongdun
 * @datetime 2017年4月8日下午4:19:10
 */
public class Area extends DataEntity<Area> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1906053547510651687L;
	
	
	private String parentId;// 父级ID
	private String parentIds;// 父级ID列表
	private String shortName;//城市简称
	private String name;// 城市通称
	private String fullName;//城市全名
	private String englishName;// 英文名
	private String cityCode;// 城市代码
	private String carLicencode;// 车牌号编号
	private String postNo;// 邮政编码
	private String isOpen;// 是否为可开放业务城市
	private Integer sort;// 排序号
	
	// ###########   constructor  #########
	
	public Area() {
		
	}
	
	public Area(String id) {
		super(id);
	}
	
	
	public Area(String parentId, String parentIds, String shortName, String name, String fullName, String englishName,
			String cityCode, String carLicencode, String postNo, String isOpen, Integer sort) {
		super();
		this.parentId = parentId;
		this.parentIds = parentIds;
		this.shortName = shortName;
		this.name = name;
		this.fullName = fullName;
		this.englishName = englishName;
		this.cityCode = cityCode;
		this.carLicencode = carLicencode;
		this.postNo = postNo;
		this.isOpen = isOpen;
		this.sort = sort;
	}

	// ############  getter and setter    ##########
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
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCarLicencode() {
		return carLicencode;
	}
	public void setCarLicencode(String carLicencode) {
		this.carLicencode = carLicencode;
	}
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	public String getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Override
	public String toString() {
		return "City [parentId=" + parentId + ", parentIds=" + parentIds + ", shortName=" + shortName + ", name=" + name
				+ ", fullName=" + fullName + ", cityCode=" + cityCode + ", carLicencode=" + carLicencode + ", post_no="
				+ postNo + ", is_open=" + isOpen + ", sort=" + sort + "]";
	}

	@Override
	public int hashCode() {
		int result = 17;
		if (name != null) {
			result = 37 * result + name.hashCode();
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
		Area other = (Area) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
