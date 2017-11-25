/**
 * 
 */
package com.brucex.common.entity;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.alibaba.fastjson.annotation.JSONField;
import com.brucex.common.config.Global;
import com.brucex.common.utils.StringUtils;
import com.brucex.modules.sys.entity.User;
import com.brucex.modules.sys.utils.UserCacheUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;

/**
 * @author xiongdun
 *
 */
public abstract class BaseEntity<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 830737485188048996L;

	/**
	 * 主键ID
	 */
	protected String id;
	/**
	 * 是否为新纪录
	 */
	protected Boolean isNewRecord = false;
	
	/**
	 * 当前用户
	 */
	protected transient User user;
	/**
	 * 当前数据库类型
	 */
	protected transient String dbType;
	
	/**
	 * 自定义Sql
	 */
	protected transient Map<String, String> sqlMap;
	
	/**
	 * 分页对象
	 */
	protected transient PageInfo<T> page;
	
	public BaseEntity() {
		
	}
	
	public BaseEntity(String id) {
		this();
		this.id = id;
	}
	
	@JsonIgnore
	@XmlTransient
	public Map<String, String> getSqlMap() {
		if (sqlMap == null) {
			sqlMap = Maps.newHashMap();
		}
		return sqlMap;
	}

	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}

	@JsonIgnore
	@XmlTransient
	public PageInfo<T> getPage() {
		if (page == null) {
			page = new PageInfo<T>();
		}
		return page;
	}

	public void setPage(PageInfo<T> page) {
		this.page = page;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @description
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，
	 * 使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 * @datetime 2017年4月11日下午11:32:22
	 * @author xiongdun
	 * @return
	 */
	@JsonIgnore
	public Boolean getIsNewRecord() {
		// 判断是否为新纪录
		return isNewRecord || StringUtils.isBlank(this.getId());
	}
	
	public void setIsNewRecord(Boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}

	public User getUser() {
		// 如果user对象为空
		if (user == null) {
			// 则从当前登录用户对象缓存中取
			user = UserCacheUtils.getUser();
		}
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getDbType() {
		// 在此处获取数据库类型 
		if (dbType == null) {
			return Global.getDbType();
		}
		return dbType;
	}
	
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	
	/**
	 * @description 获取全局配置对象
	 * @datetime 2017年4月11日下午11:29:50
	 * @author xiongdun
	 * @return
	 */
	@JSONField(serialize = false)
	public Global getGlobal() {
		return Global.getInstance();
	}
	
	/**
	 * @description 插入之前执行方法，子类实现
	 * @datetime 2017年4月11日下午11:24:57
	 * @author xiongdun
	 */
	public abstract void preInsert();
	
	/**
	 * @description 更新之前执行方法，子类实现
	 * @datetime 2017年4月11日下午11:25:08
	 * @author xiongdun
	 */
	public abstract void preUpdate();
	
	@Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseEntity<?> that = (BaseEntity<?>) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
	
	/**
	 * 状态标记（0：正常；）
	 */
	public static final String NORMAL_DEL_FLAG = "0";
	/**
	 * 1：删除；
	 */
	public static final String DELETE_DEL_FLAG = "1";
	/**
	 * 2：审核；
	 */
	public static final String AUDIT_DEL_FLAG = "2";
}
