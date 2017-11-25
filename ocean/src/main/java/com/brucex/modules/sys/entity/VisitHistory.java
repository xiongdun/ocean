/**
 * 
 */
package com.brucex.modules.sys.entity;

import com.brucex.common.entity.DataEntity;

/**
 * @description 访问历史 entity 类
 * @author xiongdun
 * @datetime 2017年4月8日下午4:19:10
 */
public class VisitHistory extends DataEntity<VisitHistory> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1935858760215381168L;

	private String ip;//访问Ip
	private String source;//访问来源
	private String longitude;// 经度
	private String latitude;// 纬度
	private String os;// 操作系统
	private String browser;// 浏览器版本
	
	private String userName;// 用户名
	
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
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "VisitHistory [ip=" + ip + ", source=" + source + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", os=" + os + ", browser=" + browser + "]";
	}
	@Override
	public int hashCode() {
		int result = 17;
		if (ip != null) {
			result = 37 * result + ip.hashCode();
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
		VisitHistory other = (VisitHistory) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		return true;
	}
	
}
