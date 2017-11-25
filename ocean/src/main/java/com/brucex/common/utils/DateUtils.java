/**
 * 
 */
package com.brucex.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @description 时间处理工具类
 * @author xiongdun
 * @datetime 2017年4月8日下午3:43:12
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static Logger logger = Logger.getLogger(DateUtils.class);
	
	/**
	 * 日期格式数组
	 */
	private static String[] parsePatterns = {
			"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
			"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM",
			"yyyy"
	};
	
	/**
	 * @description 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 * @author xiong
	 * @return Date
	 * @date 2017年6月25日下午4:39:56
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * @description 获取当前日期字符串， 默认格式yyyy-MM-dd
	 * @datetime 2017年4月8日下午3:42:45
	 * @author xiongdun
	 * @return 日期字符串
	 */
	public static String getDate() {
		return getDateTime("yyyy-MM-dd");
	}

	/**
	 * @description 获取当前日期加时间字符串，默认格式yyyy-MM-dd HH:mm:ss
	 * @datetime 2017年4月8日下午3:42:37
	 * @author xiongdun
	 * @return 日期字符串
	 */
	public static String getDatetime() {
		return getDateTime("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @description 获取当前日期加时间字符串
	 * @datetime 2017年4月8日下午3:48:57
	 * @author xiongdun
	 * @param pattern 显示日期格式
	 * @return 日期字符串
	 */
	public static String getDateTime(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}
	
	/**
	 * @description 获取日期对象
	 * @datetime 2017年4月8日下午3:52:12
	 * @author xiongdun
	 * @return
	 */
	public static Date getDateObj() {
		return new Date();
	}
	
	/**
	 * @description 比较某个时间点是否在某个时间段内
	 * @datetime 2017年4月8日下午3:41:53
	 * @author xiongdun
	 * @param strDateNow
	 * @param strDateBegin
	 * @param strDateEnd
	 * @param bool
	 * @return
	 */
	@Deprecated
	public static boolean isInDate(String strDateNow, String strDateBegin,
			String strDateEnd, boolean bool) {
		int strDateH = Integer.parseInt(strDateNow.substring(11, 13));
		int strDateM = Integer.parseInt(strDateNow.substring(14, 16));
		int strDateS = Integer.parseInt(strDateNow.substring(17, 19));
		int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
		int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
		int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
		int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
		int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
		int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));

		if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
			if (strDateH > strDateBeginH && strDateH < strDateEndH) {
				return true;
			} else if (strDateH == strDateBeginH && strDateM >= strDateBeginM
					&& strDateM <= strDateEndM) {
				return true;
			} else if (strDateH == strDateBeginH && strDateM == strDateBeginM
					&& strDateS >= strDateBeginS && strDateS <= strDateEndS) {
				return true;
			} else if (strDateH >= strDateBeginH && strDateH == strDateEndH 
					&& strDateM <= strDateEndM) {
				return true;
			} else if (strDateH >= strDateBeginH && strDateH == strDateEndH
					&& strDateM == strDateEndM && strDateS <= strDateEndS) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * @description 比较某个时间点是否在某个时间段内
	 * @datetime 2017年4月8日下午3:40:15
	 * @author xiongdun
	 * @param strDateNow 目前的时间
	 * @param strDateBegin 开始时间
	 * @param strDateEnd 结束时间
	 * @return
	 */
	public static boolean isInDate(String strDateNow, String strDateBegin,
			String strDateEnd) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer sbDateBegin = new StringBuffer();
		sbDateBegin.append(getDate());
		sbDateBegin.append(" ");
		sbDateBegin.append(strDateBegin);
		StringBuffer sbDateEnd = new StringBuffer();
		sbDateEnd.append(getDate());
		sbDateEnd.append(" ");
		sbDateEnd.append(strDateEnd);
		try {
			Date dateNow = sdf.parse(strDateNow);
			long timeNow = dateNow.getTime();
			Date dateBegin = sdf.parse(sbDateBegin.toString());
			long timeBegin = dateBegin.getTime();
			Date dateEnd = sdf.parse(sbDateEnd.toString());
			long timeEnd = dateEnd.getTime();
			
			if (timeNow >= timeBegin && timeNow <= timeEnd) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			logger.error("转换异常！异常原因：" + e.getMessage());
		}
		return false;
	}
}
