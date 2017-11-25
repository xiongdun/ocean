/**
 * 
 */
package com.brucex.common.sercurity;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

/**
 * @description Md5工具类
 * @author xiongdun
 * @datetime 2017年4月8日下午3:34:10
 */
public class MD5Utils {
	private static Logger logger = Logger.getLogger(MD5Utils.class);

	private static MessageDigest md5 = null;

	static {
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			logger.error("MD5实例获取异常！", e);
		}
	}

	/**
	 * @description 字符串转换为MD5值
	 * @datetime 2017年4月8日下午3:34:56
	 * @author xiongdun
	 * @param str
	 * @return
	 */
	public static String convertStrToMD5(String str) {
		byte[] bs = md5.digest(str.getBytes());
		StringBuilder sb = new StringBuilder(40);
		for (byte x : bs) {
			if ((x & 0xff) >> 4 == 0) {
				sb.append("0").append(Integer.toHexString(x & 0xff));
			} else {
				sb.append(Integer.toHexString(x & 0xff));
			}
		}
		return sb.toString();
	}

	/**
	 * @description MD5字符串比较
	 * @datetime 2017年4月8日下午3:34:27
	 * @author xiongdun
	 * @param norStr
	 * @param md5Str
	 * @return
	 */
	public static boolean compareStringWithMD5(String norStr, String md5Str) {

		if (norStr == null || "".equals(norStr)) {
			return false;
		}
		if (md5Str == null || "".equals(md5Str)) {
			return false;
		}

		String convertMd5 = convertStrToMD5(norStr);
		if (convertMd5 == md5Str || convertMd5.equals(md5Str)) {
			return true;
		}

		return false;
	}
}
