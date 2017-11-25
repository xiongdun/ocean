/**
 * 
 */
package com.brucex.modules.sys.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.brucex.common.entity.DataEntity;

/**
 * @description User entity class
 * @author xiongdun
 * @datetime 2017年4月8日下午3:54:35
 */
public class User extends DataEntity<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 593912886063680551L;
	
	private String name; // 用户名
	private String loginName; // 登录名
	private String englishName;// 英文名
	private transient  String password; // 密码
	private String repeatPassword;// 重复密码
	private transient String passwordSalt;// 密码盐
	private String sex; // 心别
	private Integer age; // 年龄
	private String phone; // 电话号码
	private String idCard; // 身份证号
	private String email; // 电子邮箱
	private Double salary; // 薪资水平
	private String nation; // 民族
	private String workAddress; // 工作地址
	private String houseAddress; // 住宅地址
	private String photo; // 头像
	private String userType; // 用户类型
	private Integer loginTimes; // 登录次数
	private Date loginDate;// 登录时间
	private Area city; // 所在城市
	private Office office;// 所属机构
	private String pinyin; // 名称拼音
	
	private List<Role> roleList = new ArrayList<Role>(); // 用户拥有的权限
	private List<Menu> menuList = new ArrayList<Menu>(); // 用户可以访问的菜单
	
	//=================== constranctor =============================
	public User(String name, String loginName, String phone, String idCard, String email) {
		super();
		this.name = name;
		this.loginName = loginName;
		this.phone = phone;
		this.idCard = idCard;
		this.email = email;
	}
	
	public User() {
		super();
	}
	
	public User(User user) {
		super(user.id);
	}
	
	public User(String id, String loginName) {
		this.id = id;
		this.loginName = loginName;
	}
	
	public User(String loginName) {
		this.loginName = loginName;
	}
	//##########      getter and setter   #################
	
	
	public Office getOffice() {
		return office;
	}
	public String getPasswordSalt() {
		return passwordSalt;
	}

	
	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public List<Menu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	public void setOffice(Office office) {
		this.office = office;
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
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @description shiro认证盐 name + passwordSalt
	 * @author xiongdun
	 * @datetime 2017年5月18日下午8:52:30
	 * @return
	 */
	@JSONField(serialize = false)
	public String getCredentialsSalt() {
		return loginName + passwordSalt;
	}
	
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getWorkAddress() {
		return workAddress;
	}
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	public String getHouseAddress() {
		return houseAddress;
	}
	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public Integer getLoginTimes() {
		return loginTimes;
	}
	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}
	public Area getCity() {
		return city;
	}
	public void setCity(Area city) {
		this.city = city;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", loginName=" + loginName + ", englishName=" + englishName + ", password="
				+ password + ", passwordSalt=" + passwordSalt + ", sex=" + sex + ", age=" + age + ", phone=" + phone
				+ ", idCard=" + idCard + ", email=" + email + ", salary=" + salary + ", nation=" + nation
				+ ", userType=" + userType + ", loginTimes=" + loginTimes + ", loginDate=" + loginDate + ", city="
				+ city + ", office=" + office + ", pinyin=" + pinyin + "]";
	}

	@Override
	public int hashCode() {
		int result = 17;
		if (loginName != null) {
			result = 37 * result + loginName.hashCode();
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
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
