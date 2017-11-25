/**
 * 
 */
package com.brucex.modules.sys.entity;

import java.util.List;

import com.brucex.common.entity.TreeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @description 系统主菜单entity
 * @author xiongdun
 * @datetime 2017年4月8日下午4:19:10
 */
public class Menu extends TreeEntity<Menu> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6005180027871659062L;

	private Menu parent;// 父级菜单
	private String name;// 菜单显示值
	private String englishName;// 英文名
	private String href;// 链接地址
	private String target;// 链接地址目标类型（ mainFrame、_blank、_self、_parent、_top）
	private String permission;// 权限标记
	private String permissionName;// 权限名称
	private String icon;// 图标
	private String styleClass;// 样式

	
	//############ constructor #####################
	public Menu() {
		super();
		this.status = "0";
	}

	public Menu(String id) {
		super(id);
		this.status = "0";
	}

	// ############## getter and setter ####################


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

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.brucex.common.entity.TreeEntity#getParent()
	 */
	@Override
	public Menu getParent() {
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.brucex.common.entity.TreeEntity#setParent(java.lang.Object)
	 */
	@Override
	public void setParent(Menu parent) {
		this.parent = parent;
	}

	/**
	 * @description 集合排序
	 * @author xiongdun
	 * @datetime 2017年4月23日下午1:08:19
	 */
	@JsonIgnore
	public static void sortList(List<Menu> list, List<Menu> sourcelist, String parentId, boolean cascade) {
		for (int i = 0; i < sourcelist.size(); i++) {
			Menu e = sourcelist.get(i);
			if (e.getParent() != null && e.getParent().getId() != null && e.getParent().getId().equals(parentId)) {
				list.add(e);
				if (cascade) {
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j = 0; j < sourcelist.size(); j++) {
						Menu child = sourcelist.get(j);
						if (child.getParent() != null && child.getParent().getId() != null
								&& child.getParent().getId().equals(e.getId())) {
							sortList(list, sourcelist, e.getId(), true);
							break;
						}
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		return "Menu [parent=" + parent + ", name=" + name + ", englishName=" + englishName + ", href=" + href
				+ ", target=" + target + ", permission=" + permission + ", permissionName=" + permissionName + ", icon="
				+ icon + ", styleClass=" + styleClass + ", sort=" + sort + ", id=" + id + "]";
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
		Menu other = (Menu) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
