/**
 * 
 */
package com.brucex.modules.sys.entity;

import com.brucex.common.entity.DataEntity;

/**
 * @description 访问日志 entity 类
 * @author xiongdun
 * @datetime 2017年4月8日下午4:19:10
 */
public class Log extends DataEntity<Log> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1935858760215381168L;

	private String ip; // ip地址
	private String source; // 来源
	private String href; // 日志访问链接地址
	private String type; // 日志类型
	private String exception; // 异常说明
	
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Log [ip=" + ip + ", source=" + source + ", type=" + type + ", exception=" + exception + "]";
	}
	@Override
	public int hashCode() {
		int result = 17;
		if (type != null) {
			result = 37 * result + type.hashCode();
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
		Log other = (Log) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
