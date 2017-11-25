/**
 * 
 */
package com.brucex.common.utils;

import java.io.Serializable;
import java.net.InetAddress;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.util.IdGenerator;

import com.brucex.common.exception.DataException;
import com.brucex.common.spring.SpringContextHolder;
import com.brucex.common.utils.data.DataSqlTools;

/**
 * @description 生成uuid工具类
 * @author xiongdun
 * @datetime 2017年4月8日下午10:52:26
 */
public class UUIDUtils implements IdGenerator, SessionIdGenerator {

	/**
	 * 根据具体机器环境提供
	 */
	private final static long workerId;

	/**
	 * 滤波器,使时间变小,生成的总位数变小,一旦确定不能变动
	 */
	private final static long twepoch = 1361753741828L;

	private long sequence = 0L;

	private final static long workerIdBits = 4L;

	private final static long maxWorkerId = -1L ^ -1L << workerIdBits;

	private final static long sequenceBits = 12L;

	private final static long workerIdShift = sequenceBits;

	private final static long timestampLeftShift = sequenceBits + workerIdBits;

	private final static long sequenceMask = -1L ^ -1L << sequenceBits;

	private long lastTimestamp = -1L;

	/**
	 * 主机和进程的机器码
	 */
	private static UUIDUtils uuidUtils = new UUIDUtils();

	/**
	 * 主机和进程的机器码
	 */
	private static final int _genmachine;

	private static String sPre = "";

	private static SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");

	private static SecureRandom random = new SecureRandom();

	private static Logger logger = Logger.getLogger(UUIDUtils.class);

	static {
		try {
			// build a 2-byte machine piece based on NICs info
			int machinePiece;
			{
				try {
					StringBuilder sb = new StringBuilder();
					InetAddress addr = InetAddress.getLocalHost();
					sb.append(Integer.toHexString(addr.getHostName().hashCode()));
					sb.append(Integer.toHexString(addr.getHostAddress().hashCode()));
					machinePiece = sb.toString().hashCode() & 0xFFFF;
				} catch (Throwable e) {
					logger.error(" uuid generater error. ", e);
					machinePiece = new Random().nextInt() << 16;
				}
			}

			// add a 2 byte process piece. It must represent not only the JVM
			// but the class loader.
			// Since static var belong to class loader there could be collisions
			// otherwise
			final int processPiece;
			{
				int processId = new java.util.Random().nextInt();
				try {
					processId = java.lang.management.ManagementFactory.getRuntimeMXBean().getName().hashCode();
				} catch (Throwable t) {
				}

				ClassLoader loader = UUIDUtils.class.getClassLoader();
				int loaderId = loader != null ? System.identityHashCode(loader) : 0;

				StringBuilder sb = new StringBuilder();
				sb.append(Integer.toHexString(processId));
				sb.append(Integer.toHexString(loaderId));
				processPiece = sb.toString().hashCode() & 0xFFFF;
			}
			_genmachine = machinePiece | processPiece;

			workerId = _genmachine % (UUIDUtils.maxWorkerId + 1);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @description 随机生成唯一键
	 * @datetime 2017年4月8日下午10:55:13
	 * @author xiongdun
	 * @return
	 */
	public static String getRandomUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * @description 生成24位业务流水号
	 * @datetime 2017年4月8日下午11:15:36
	 * @author xiongdun
	 * @return
	 */
	public static String getSerialNum() {
		return DateUtils.getDateTime("yyyyMMddHHmmss") + random.nextLong();
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return Encodes.encodeBase62(randomBytes);
	}

	public static String getId() {
		return getId(sPre);
	}

	public static String getId(String sPre) {
		return String.valueOf(sPre + dateGen() + uuidUtils.nextId());
	}

	public String getNextId() {
		return UUIDUtils.getId();
	}

	public synchronized long nextId() {
		long timestamp = timeGen();
		if (lastTimestamp == timestamp) {
			sequence = sequence + 1 & UUIDUtils.sequenceMask;
			if (sequence == 0) {
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0;
		}
		if (timestamp < lastTimestamp) {
			try {
				throw new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
						lastTimestamp - timestamp));
			} catch (Exception e) {
				logger.error(" IdGen error. ", e);
			}
		}
		lastTimestamp = timestamp;
		return timestamp - twepoch << timestampLeftShift | workerId << UUIDUtils.workerIdShift | sequence;
	}

	private long tilNextMillis(final long lastTimestamp1) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp1) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}

	private static String dateGen() {
		return simpledateformat.format(new Date());
	}

	@Override
	public Serializable generateId(Session session) {
		return UUIDUtils.getId();
	}

	@Override
	public UUID generateId() {
		return null;
	}

	/**
	 * 不能乱用，容易线程阻塞
	 * 
	 * 根据指定格式获得流水号 String sTableName 指定数据表 String sColumnName 指定数据表的字段 String
	 * sWhereClause 指定数据表过滤条件 String sDateFormat 指定日期前缀格式，符合Java
	 * SimpleDateFormat标准 String sNoFormat 指定序号的格式 符合Java DecimalFormat标准 Date
	 * dateToday 指定的日期
	 */
	public synchronized final static String getId(String sTableName, String sColumnName) throws Exception {
		return getId(sTableName, sColumnName, "XA");
	}

	/**
	 * 不能乱用，容易线程阻塞
	 * 
	 * @param sTableName
	 * @param sColumnName
	 * @param sPrefix
	 * @return
	 * @throws Exception
	 */
	public synchronized final static String getId(String sTableName, String sColumnName, String sPrefix)
			throws Exception {
		if (sPrefix == null || sPrefix.equals("")) {
			sPrefix = "'" + sPre + "'";
		} else {
			sPrefix = "'" + sPrefix + "'";
		}
		return getId(sTableName, sColumnName, sPrefix + "yyyyMMdd", "000000", new java.util.Date());
	}

	/**
	 * 不能乱用，容易线程阻塞
	 * 
	 * 从最大流水号表中取对应流水号
	 * 
	 * @param sTableName
	 * @param sColumnName
	 * @param sDateFormat
	 * @param sNoFormat
	 * @param dateToday
	 * @return
	 * @throws Exception
	 */
	public synchronized final static String getId(String tableName, String columnName, String dateFormat,
			String noFormat, Date dateToday) throws Exception {
		String sSql = "";
		int iMaxNo = 0;
		Object[] oValue = null;
		ArrayList<Object> alValue = null;
		SimpleDateFormat simpledateformat = new SimpleDateFormat(dateFormat);
		DecimalFormat decimalformat = new DecimalFormat(noFormat);
		String sDate = simpledateformat.format(dateToday);
		int iDateLen = sDate.length();
		String newMaxId = "";

		tableName = tableName.toUpperCase();
		columnName = columnName.toUpperCase();

		JdbcUtils jdbcUtils = SpringContextHolder.getBean("jdbcUtils");

		int iRow = 0;
		try {
			sSql = "Select Max_ID From ID_MAX Where Table_Name= ? And Column_Name = ? ";
			oValue = new Object[] { tableName, columnName };
			List<Map<String, Object>> lMaps = DataSqlTools.getList(jdbcUtils, sSql, oValue);
			if (lMaps == null || lMaps.size() == 0) {
				// 流水号不存在，则初始化流水信息
				newMaxId = sDate + decimalformat.format(1);
				sSql = "Insert Into Id_MAX(Table_Name,Column_Name,Max_ID,No_FMT,Date_FMT) Values(?,?,?,?,?)";
				alValue = new ArrayList<Object>();
				alValue.add(tableName);
				alValue.add(columnName);
				alValue.add(newMaxId);
				alValue.add(noFormat);
				alValue.add(dateFormat);
				iRow = DataSqlTools.executeSQL(jdbcUtils, sSql, alValue);
			} else {
				// 取最大流水号
				Map<String, Object> map = lMaps.get(0);
				String maxId = DataConvert.toString(map.get("Max_ID"));

				// 如果流水号存在且为同一天，则流水号从当前号向后排列，否则从1开始。
				iMaxNo = 0;
				if (maxId != null && maxId.indexOf(sDate, 0) != -1) {
					iMaxNo = Integer.valueOf(maxId.substring(iDateLen)).intValue();
					newMaxId = sDate + decimalformat.format(iMaxNo + 1);
				} else {
					newMaxId = sDate + decimalformat.format(iMaxNo + 1);
				}
				sSql = "Update ID_MAX Set Max_ID = ? Where Table_Name = ? And Column_Name = ? And Max_ID = ? ";
				alValue = new ArrayList<Object>();
				alValue.add(newMaxId);
				alValue.add(tableName);
				alValue.add(columnName);
				alValue.add(maxId);
				iRow = DataSqlTools.executeSQL(jdbcUtils, sSql, alValue);
			}

		} catch (Exception e) {
			throw new DataException("获取主键信息异常!" + e.getMessage());
		} finally {

		}
		// 如果更新失败，重新获取序列号
		if (iRow == 0) {
			newMaxId = getId(tableName, columnName, dateFormat, noFormat, dateToday);
		}
		return newMaxId;
	}

}
