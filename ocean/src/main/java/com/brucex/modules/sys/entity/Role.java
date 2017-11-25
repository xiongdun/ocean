/**
 * 
 */
package com.brucex.modules.sys.entity;

import com.brucex.common.entity.DataEntity;

/**
 * @description role entity
 * @author xiongdun
 * @datetime 2017年4月23日下午7:30:08
 */
public class Role extends DataEntity<Role> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;// 权限名
	private String englishName;// 英文名
	private String type;// 权限类型
	
	private String userId;// 用户Id
	
	
	//###################  constractor   ####################
	
	public Role() {
		super();
	}
	
	public Role(String id) {
		super(id);
	}
	
	public Role(User user) {
		this.user = user;
		this.userId = user.getId();
	}
	
	public Role(String name, String englishName, String type) {
		super();
		this.name = name;
		this.englishName = englishName;
		this.type = type;
	}

	///#############    getter and setter  ######################
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEnglishName() {
		return englishName;
	}
	
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", englishName=" + englishName + ", type=" + type + ", id=" + id + "]";
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
		Role other = (Role) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
