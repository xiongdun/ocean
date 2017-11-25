/**
 * 
 */
package com.brucex.common.datasource;

import java.util.Properties;

import com.alibaba.druid.util.DruidPasswordCallback;
import com.brucex.common.sercurity.Cryptos;
import com.brucex.common.utils.StringUtils;

/**
 * @description druid 数据库连接池 密码回调
 * @author xiongdun
 * @datetime 2017年4月20日下午10:29:39
 */
public class DBPasswordCallback extends DruidPasswordCallback {

	/**
	 * 
	 */
	private static final long serialVersionUID = -375257616480052878L;

	
	/* (non-Javadoc)
	 * @see com.alibaba.druid.util.DruidPasswordCallback#setProperties(java.util.Properties)
	 */
	@Override
	public void setProperties(Properties properties) {
		super.setProperties(properties);  
        String pwd = properties.getProperty("password");  
        if (StringUtils.isNoneBlank(pwd)) {  
            try {  
                //这里的password是将jdbc.properties配置得到的密码进行解密之后的值  
                //所以这里的代码是将密码进行解密  
                //将pwd进行解密;  
                String password = Cryptos.aesDecrypt(pwd);
                setPassword(password.toCharArray());
            } catch (Exception e) {
                setPassword(pwd.toCharArray());
            }
        }
	}
	
	/**
	 * @description 对数据库密码加密后，拷贝到数据库连接配置文件中
	 * @author xiongdun
	 * @datetime 2017年4月20日下午10:57:05
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Cryptos.aesEncrypt("123456"));
	}
	
}
