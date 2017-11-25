package com.brucex.common.entity;

import java.util.Date;

import com.brucex.common.utils.StringUtils;
import com.brucex.common.utils.UUIDUtils;
import com.brucex.modules.sys.entity.User;
import com.brucex.modules.sys.utils.UserCacheUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @description 数据实体公共父类
 * @author xiongdun
 * @datetime 2017年4月10日下午11:56:30
 */
public class DataEntity<T> extends BaseEntity<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1543979745112868368L;

	/**
	 * 备注
	 */
	protected String remark;
	/**
	 * 状态
	 */
	protected String status;

	/**
	 * 删除标记
	 */
	protected String delFlag;

	/**
	 * 创建者
	 */
	protected User createBy;

	/**
	 * 创建时间
	 */
	protected Date createTime;

	/**
	 * 修改者
	 */
	protected User updateBy;

	/**
	 * 修改时间
	 */
	protected Date updateTime;

	/**
	 * 相关描述
	 */
	protected String description;
	
	private String prefix;

	// ################ constructor #######################

	public DataEntity() {
		super();
		this.delFlag = NORMAL_DEL_FLAG;
		this.status = USERABLE_STATUS_CODE;
	}

	public DataEntity(String id) {
		super(id);
	}

	// ############### getter and setter ######################
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemark() {
		return remark;
	}

	@JsonIgnore
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonIgnore
	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JsonIgnore
	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 插入方法执行前，需要手动调用生成ID
	 */
	@Override
	public void preInsert() {
		if (!this.isNewRecord) {
			if (StringUtils.isBlank(prefix)) {
				setId(UUIDUtils.getId());
			} else {
				setId(UUIDUtils.getId(prefix));
			}
		}
		User user = UserCacheUtils.getUser();
	
		if (StringUtils.isNotBlank(user.getId())) {
			this.createBy = user;
			this.updateBy = user;
		}
		this.updateTime = new Date();
		this.createTime = this.updateTime;
	}

	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate() {
		User user = UserCacheUtils.getUser();
		if (StringUtils.isNoneBlank(user.getId())) {
			this.updateBy = user;
		}
		this.updateTime = new Date();
	}

	public static final String UNUSERABLE_STATUS_CODE = "0"; // 不可用状态
	public static final String USERABLE_STATUS_CODE = "1"; // 可用状态
	public static final String OTHER_STATUS_CODE = "2"; // 其他可用状态
}
