/**
 * 
 */
package com.brucex.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import com.brucex.common.spring.SpringContextHolder;
import com.google.common.collect.Lists;

/**
 * @description string 工具类
 * @author xiongdun
 * @datetime 2017年4月8日下午6:55:43
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	private static final String HANDIGISTR[] = new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	private static final String HANDIVISTR[] = new String[] { "", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟",
			"万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟" };

	private static final char SEPARATOR = '_';
	private static final String CHARSET_NAME = "UTF-8";

	/**
	 * @description 判断字符串是否为邮箱
	 * @datetime 2017年4月10日下午10:53:07
	 * @author xiongdun
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String patternStr = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		return matcher(patternStr, email);
	}

	/**
	 * @description 判断字符串是否为手机号码
	 * @datetime 2017年4月10日下午10:59:49
	 * @author xiongdun
	 * @param mobileNumber
	 * @return
	 */
	public static boolean isMobileNumber(String mobileNumber) {
		String patternStr = "^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";
		return matcher(patternStr, mobileNumber);
	}

	/**
	 * @description 判断是否为IDCard
	 * @author xiongdun
	 * @datetime 2017年5月16日下午10:51:54
	 * @param idcard
	 * @return
	 */
	public static boolean isIdcard(String idcard) {
		String patternStr = null;
		if (idcard.length() == 15) {
			patternStr = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
		} else if (idcard.length() == 18) {
			patternStr = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$";
		}
		return matcher(patternStr, idcard);
	}
	
	/**
	 * @description 验证用户名
	 * @author xiongdun
	 * @datetime 2017年4月20日下午11:26:25
	 * @param username
	 * @return
	 */
	public static boolean validateUserName(String username) {
		String patternStr = "^[a-zA-Z]\\w{5,17}$";
		return matcher(patternStr, username);
	}

	/**
	 * @description 验证密码
	 * @author xiongdun
	 * @datetime 2017年4月20日下午11:30:17
	 * @param password
	 * @return
	 */
	public static boolean validatePassword(String password) {
		String patternStr = "^[a-zA-Z]\\w{5,17}$";
		return matcher(patternStr, password);
	}

	/**
	 * @description 验证是否为汉子
	 * @author xiongdun
	 * @datetime 2017年4月20日下午11:30:01
	 * @param chinese
	 * @return
	 */
	public static boolean isChinese(String chinese) {
		String patternStr = "^[a-zA-Z]\\w{5,17}$";
		return matcher(patternStr, chinese);
	}

	/**
	 * @description 验证url
	 * @author xiongdun
	 * @datetime 2017年4月20日下午11:29:49
	 * @param url
	 * @return
	 */
	public static boolean validateURL(String url) {
		String patternStr = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
		return matcher(patternStr, url);
	}

	/**
	 * @description 验证ip地址
	 * @author xiongdun
	 * @datetime 2017年4月20日下午11:29:37
	 * @param ipAddress
	 * @return
	 */
	public static boolean validateIP(String ipAddress) {
		String patternStr = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
		return matcher(patternStr, ipAddress);
	}

	/**
	 * @description 正则表达式匹配器
	 * @datetime 2017年4月10日下午11:00:12
	 * @author xiongdun
	 * @param patternStr
	 * @param matcherStr
	 * @return
	 */
	public static boolean matcher(String patternStr, String matcherStr) {
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile(patternStr);
			Matcher matcher = regex.matcher(matcherStr);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 重写isEmpty，增加null串的判断
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean isEmpty(final CharSequence cs) {
		if ("null".equals(cs)) {
			return true;
		}
		return org.apache.commons.lang3.StringUtils.isEmpty(cs);
	}

	/**
	 * 转换为字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] getBytes(String str) {
		if (str != null) {
			try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 转换为字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static String toString(byte[] bytes) {
		try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
	}

	/**
	 * 是否包含字符串
	 * 
	 * @param str
	 *            验证字符串
	 * @param strs
	 *            字符串组
	 * @return 包含返回true
	 */
	public static boolean inString(String str, String... strs) {
		if (str != null) {
			for (String s : strs) {
				if (str.equals(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * 
	 * @param html
	 * @return
	 */
	public static String replaceMobileHtml(String html) {
		if (html == null) {
			return "";
		}
		return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
	}

	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * 
	 * @param txt
	 * @return
	 */
	public static String toHtml(String txt) {
		if (txt == null) {
			return "";
		}
		return replace(replace(Encodes.escapeHtml(txt), "\n", "<br/>"), "\t", "&nbsp; &nbsp; ");
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * 
	 * @param str
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String abbr2(String param, int length) {
		if (param == null) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		int n = 0;
		char temp;
		boolean isCode = false; // 是不是HTML代码
		boolean isHTML = false; // 是不是HTML特殊字符,如&nbsp;
		for (int i = 0; i < param.length(); i++) {
			temp = param.charAt(i);
			if (temp == '<') {
				isCode = true;
			} else if (temp == '&') {
				isHTML = true;
			} else if (temp == '>' && isCode) {
				n = n - 1;
				isCode = false;
			} else if (temp == ';' && isHTML) {
				isHTML = false;
			}
			try {
				if (!isCode && !isHTML) {
					n += String.valueOf(temp).getBytes("GBK").length;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (n <= length - 3) {
				result.append(temp);
			} else {
				result.append("...");
				break;
			}
		}
		// 取出截取字符串中的HTML标记
		String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)", "$1$2");
		// 去掉不需要结素标记的HTML标记
		temp_result = temp_result.replaceAll(
				"</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
				"");
		// 去掉成对的HTML标记
		temp_result = temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>", "$2");
		// 用正则表达式取出标记
		Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
		Matcher m = p.matcher(temp_result);
		List<String> endHTML = Lists.newArrayList();
		while (m.find()) {
			endHTML.add(m.group(1));
		}
		// 补全不成对的HTML标记
		for (int i = endHTML.size() - 1; i >= 0; i--) {
			result.append("</");
			result.append(endHTML.get(i));
			result.append(">");
		}
		return result.toString();
	}

	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val) {
		if (val == null) {
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val) {
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val) {
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val) {
		return toLong(val).intValue();
	}

	/**
	 * 获得i18n字符串
	 */
	public static String getMessage(String code, Object[] args) {
		LocaleResolver localLocaleResolver = (LocaleResolver) SpringContextHolder.getBean(LocaleResolver.class);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Locale localLocale = localLocaleResolver.resolveLocale(request);
		return SpringContextHolder.getApplicationContext().getMessage(code, args, localLocale);
	}

	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("X-Real-IP");
		if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	/**
	 * 驼峰命名法工具
	 * 
	 * @return toCamelCase("hello_world") == "helloWorld"
	 *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 *         toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}

		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * 驼峰命名法工具
	 * 
	 * @return toCamelCase("hello_world") == "helloWorld"
	 *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 *         toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * 驼峰命名法工具
	 * 
	 * @return toCamelCase("hello_world") == "helloWorld"
	 *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 *         toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toUnderScoreCase(String s) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i > 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * 转换为JS获取对象值，生成三目运算返回结果
	 * 
	 * @param objectString
	 *            对象串 例如：row.user.id
	 *            返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
	 */
	public static String jsGetVal(String objectString) {
		StringBuilder result = new StringBuilder();
		StringBuilder val = new StringBuilder();
		String[] vals = split(objectString, ".");
		for (int i = 0; i < vals.length; i++) {
			val.append("." + vals[i]);
			result.append("!" + (val.substring(1)) + "?'':");
		}
		result.append(val.substring(1));
		return result.toString();
	}

	/**
	 * @description 获取访问者ip地址
	 * @author xiongdun
	 * @datetime 2017年4月21日下午10:19:13
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次方向代理后可能后出现多个Ip值，其中第一个为真实Ip地址
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}
	
	/**
	 * @description 获取访问的浏览器版本
	 * @author xiongdun
	 * @datetime 2017年4月22日上午11:19:03
	 * @param request
	 * @return
	 */
	public static String getRequestBrowserInfo(HttpServletRequest request) {
		
		String header = request.getHeader("user-agent");
		String browserVersion = null;
		if (StringUtils.isBlank(header)) {
			return null;
		}
		if (header.indexOf("MSIE") > 0) {
			browserVersion = "IE";
		} else if (header.indexOf("Firefox") > 0) {
			browserVersion = "Firefox";
		} else if (header.indexOf("Chrome") > 0) {
			browserVersion = "Chrome";
		} else if (header.indexOf("Safari") > 0) {
			browserVersion = "Safari";
		} else if (header.indexOf("Camino") > 0) {
			browserVersion = "Camino";
		} else if (header.indexOf("Konqueror") > 0) {
			browserVersion = "Konqueror";
		}
		return browserVersion;
	}
	
	/**
	 * @description 获取请求的操作系统信息
	 * @author xiongdun
	 * @datetime 2017年4月22日上午11:21:23
	 * @param request
	 * @return
	 */
	public static String getRequestSystemInfo(HttpServletRequest request) {
		String header = request.getHeader("user-agent");
		String osVersion = null;
		if (StringUtils.isBlank(header)) {
			return null;
		}
		//得到用户的操作系统
		if (header.indexOf("NT 6.0") > 0) {
			osVersion = "Windows Vista/Server 2008";
		} else if (header.indexOf("NT 5.2") > 0) {
			osVersion = "Windows Server 2003";
		} else if (header.indexOf("NT 5.1") > 0) {
			osVersion = "Windows XP";
		} else if (header.indexOf("NT 6.0") > 0) {
			osVersion = "Windows Vista";
		} else if (header.indexOf("NT 6.1") > 0) {
			osVersion = "Windows 7";
		} else if (header.indexOf("NT 6.2") > 0) {
			osVersion = "Windows Slate";
		} else if (header.indexOf("NT 6.3") > 0) {
			osVersion = "Windows 9";
		} else if (header.indexOf("NT 10.0") > 0) {
			osVersion = "Windows 10";
		} else if (header.indexOf("NT 5") > 0) {
			osVersion = "Windows 2000";
		} else if (header.indexOf("NT 4") > 0) {
			osVersion = "Windows NT4";
		} else if (header.indexOf("Me") > 0) {
			osVersion = "Windows Me";
		} else if (header.indexOf("98") > 0) {
			osVersion = "Windows 98";
		} else if (header.indexOf("95") > 0) {
			osVersion = "Windows 95";
		} else if (header.indexOf("Mac") > 0) {
			osVersion = "Mac";
		} else if (header.indexOf("Unix") > 0) {
			osVersion = "UNIX";
		} else if (header.indexOf("Linux") > 0) {
			osVersion = "Linux";
		} else if (header.indexOf("SunOS") > 0) {
			osVersion = "SunOS";
		}
        return osVersion;
	}
	
	/**
	 * 将货币转换为大写形式
	 * 
	 * @param val
	 *            传入的数据
	 * @return String 返回的人民币大写形式字符串
	 */
	public static final String numberToChinese(double val) {

		String SignStr = "";
		String TailStr = "";
		long fraction, integer;
		int jiao, fen;
		if (val < 0) {
			val = -val;
			SignStr = "负";
		}
		if (val > 99999999999999.999 || val < -99999999999999.999) {
			return "数值位数过大!";
		}
		// 四舍五入到分
		long temp = Math.round(val * 100);
		integer = temp / 100;
		fraction = temp % 100;
		jiao = (int) fraction / 10;
		fen = (int) fraction % 10;
		if (jiao == 0 && fen == 0) {
			TailStr = "整";
		} else {
			TailStr = HANDIGISTR[jiao];
			if (jiao != 0) {
				TailStr += "角";
			}
			// 零元后不写零几分
			if (integer == 0 && jiao == 0) {
				TailStr = "";
			}
			if (fen != 0) {
				TailStr += HANDIGISTR[fen] + "分";
			}
		}
		// 下一行可用于非正规金融场合，0.03只显示“叁分”而不是“零元叁分”
		return SignStr + positiveIntegerToHanStr(String.valueOf(integer)) + "元" + TailStr;
	}

	/**
	 * 将货币转换为大写形式(类内部调用)<br>
	 * 输入字符串必须正整数，只允许前导空格(必须右对齐)，不宜有前导零
	 * 
	 * @param val
	 * @return String
	 */
	private static String positiveIntegerToHanStr(String NumStr) {

		// 输入字符串必须正整数，只允许前导空格(必须右对齐)，不宜有前导零
		String RMBStr = "";
		boolean lastzero = false;
		boolean hasvalue = false; // 亿、万进位前有数值标记

		int len, n;
		len = NumStr.length();
		if (len > 15) {
			return "数值过大!";
		}
		for (int i = len - 1; i >= 0; i--) {
			if (NumStr.charAt(len - i - 1) == ' ') {
				continue;
			}
			n = NumStr.charAt(len - i - 1) - '0';
			if (n < 0 || n > 9) {
				return "输入含非数字字符!";
			}
			if (n != 0) {
				if (lastzero) {
					RMBStr += HANDIGISTR[0]; // 若干零后若跟非零值，只显示一个零
				}
				// 除了亿万前的零不带到后面
				// 如十进位前有零也不发壹音用此行
				if (!(n == 1 && (i % 4) == 1 && i == len - 1)) { // 十进位处于第一位不发壹音

					RMBStr += HANDIGISTR[n];
				}
				RMBStr += HANDIVISTR[i]; // 非零值后加进位，个位为空

				hasvalue = true; // 置万进位前有值标记

			} else {
				if ((i % 8) == 0 || ((i % 8) == 4 && hasvalue)) // 亿万之间必须有非零值方显示万
					RMBStr += HANDIVISTR[i]; // “亿”或“万”
			}
			if (i % 8 == 0) {
				hasvalue = false; // 万进位前有值标记逢亿复位
			}
			lastzero = (n == 0) && (i % 4 != 0);
		}
		if (RMBStr.length() == 0) {
			return HANDIGISTR[0]; // 输入空字符或"0"，返回"零"
		}
		return RMBStr;
	}
}
