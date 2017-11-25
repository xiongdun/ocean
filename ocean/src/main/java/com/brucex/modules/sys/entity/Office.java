/**
 * 
 */
package com.brucex.modules.sys.entity;

import com.brucex.common.entity.TreeEntity;

/**
 * @description 机构实体类
 * @author xiongdun
 * @datetime 2017年4月8日下午4:19:10
 */
public class Office extends TreeEntity<Office> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Area city; // 所属地区，城市
	private String englishName;// 英文名
	private String type;// 机构类型
	private String address;// 机构地址
	private String level;// 机构等级
	private String phone;// 机构号码
	private String email;// 机构电子邮箱
	private User manager;// 机构主管
	private String fax;// fax 账号
	
	// #################    constructor        ##################
	
	public Office() {
		super();
		this.type = "2";
	}
	
	public Office(String id) {
		super(id);
		this.sort = 30;
		this.type = "2";
	}
	
	//##################  getter and setter   ################
	
	public Area getCity() {
		return city;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public void setCity(Area city) {
		this.city = city;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	/* (non-Javadoc)
	 * @see com.brucex.common.entity.TreeEntity#getParent()
	 */
	@Override
	public Office getParent() {
		return parent;
	}

	/* (non-Javadoc)
	 * @see com.brucex.common.entity.TreeEntity#setParent(java.lang.Object)
	 */
	@Override
	public void setParent(Office parent) {
		// 当前机构对象设置为父级机构对象
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Office [city=" + city + ", englishName=" + englishName + ", type=" + type + ", address=" + address
				+ ", level=" + level + ", phone=" + phone + ", email=" + email + ", manager=" + manager + ", fax=" + fax
				+ ", name=" + name + ", sort=" + sort + ", id=" + id + "]";
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
		Office other = (Office) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
