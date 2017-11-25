/**
 * 
 */
package com.brucex.common.utils.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.brucex.common.utils.JdbcUtils;

/**
 * @description 预编译JDBC操作
 * @author xiongdun
 * @datetime 2017年4月16日上午11:00:09
 */
public class DataSqlTools {

	private static Logger logger = Logger.getLogger(DataSqlTools.class);

	/**
	 * @description 查询单条纪录
	 * @datetime 2017年4月16日上午11:30:29
	 * @author xiongdun
	 * @param conn
	 * @param sSql
	 * @param alValue
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getMap(Connection conn, String sSql, ArrayList<Object> alValue) throws Exception {
		Map<String, String> map = null;
		sSql = toString(sSql);
		PreparedStatement pStatement = null;
		ResultSet rs = null;

		if (conn == null)
			return null;
		if ("".equals(sSql))
			return null;

		try {
			String outStr = "";
			String[] arr = sSql.split("\\?");

			java.util.Date dBeginTime = new java.util.Date();

			pStatement = conn.prepareStatement(sSql);

			for (int i = 0; i < alValue.size(); i++) {
				if (alValue.get(i) instanceof String) {
					pStatement.setString(i + 1, toString(alValue.get(i).toString()));
					outStr += arr[i] + "'" + toString(alValue.get(i).toString()) + "'";
				}
				if (alValue.get(i) instanceof Double) {
					pStatement.setDouble(i + 1, toDouble(alValue.get(i).toString()));
					outStr += arr[i] + toDouble(alValue.get(i).toString());
				}
				if (alValue.get(i) instanceof Integer) {
					pStatement.setInt(i + 1, toInt(alValue.get(i).toString()));
					outStr += arr[i] + toDouble(alValue.get(i).toString());
				}
				if (alValue.get(i) == null) {
					pStatement.setObject(i + 1, alValue.get(i));
					outStr += arr[i] + alValue.get(i);
				}
			}

			if (arr != null && arr.length > alValue.size())
				outStr += arr[alValue.size()];

			map = new HashMap<String, String>();
			rs = pStatement.executeQuery();
			if (rs.next()) {
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					String sColumnName = toString(rs.getMetaData().getColumnName(i).toUpperCase());
					String sColumnValue = toString(rs.getString(sColumnName));
					map.put(sColumnName, sColumnValue);
				}
			}

			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			java.util.Date dEndTime = new java.util.Date();
			double iTimeConsuming = (dEndTime.getTime() - dBeginTime.getTime()) / 1000.0;
			String sWarning = "";
			if (iTimeConsuming > 0.5)
				sWarning = "WARN";
			logger.warn("[SQL" + sWarning + "]" + sdf.format(dBeginTime) + " : " + iTimeConsuming + " [" + outStr + "]");

			return map;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null)
				rs.getStatement().close();
			if (pStatement != null)
				pStatement.close();
		}
	}

	/**
	 * @description 查询单条记录
	 * @datetime 2017年4月16日上午11:30:56
	 * @author xiongdun
	 * @param conn
	 * @param sSql
	 * @param sValue
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getMap(Connection conn, String sSql, Object sValue) throws Exception {

		Map<String, String> map = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		sSql = toString(sSql);

		if (conn == null)
			return null;
		if ("".equals(sSql))
			return null;
		if (sValue == null)
			return null;

		try {
			String outStr = "";
			String[] arr = sSql.split("\\?");

			java.util.Date dBeginTime = new java.util.Date();

			pStatement = conn.prepareStatement(sSql);
			if (sValue instanceof String) {
				pStatement.setString(1, toString(sValue.toString()));
				outStr += arr[0] + "'" + toString(sValue.toString()) + "'";
			}
			if (sValue instanceof Double) {
				pStatement.setDouble(1, toDouble(sValue.toString()));
				outStr += arr[0] + toDouble(sValue.toString());
			}
			if (sValue instanceof Integer) {
				pStatement.setInt(1, toInt(sValue.toString()));
				outStr += arr[0] + toDouble(sValue.toString());
			}

			if (arr != null && arr.length > 1)
				outStr += arr[1];

			map = new HashMap<String, String>();
			rs = pStatement.executeQuery();
			if (rs.next()) {
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					String sColumnName = toString(rs.getMetaData().getColumnName(i).toUpperCase());
					String sColumnValue = toString(rs.getString(sColumnName));
					map.put(sColumnName, sColumnValue);
				}
			}

			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			java.util.Date dEndTime = new java.util.Date();
			double iTimeConsuming = (dEndTime.getTime() - dBeginTime.getTime()) / 1000.0;
			String sWarning = "";
			if (iTimeConsuming > 0.5)
				sWarning = "WARN";
			logger.warn("[SQL" + sWarning + "]" + sdf.format(dBeginTime) + " : " + iTimeConsuming + " [" + outStr + "]");

			return map;

		} catch (Exception e) {
			logger.debug("sql执行异常，异常原因：" + e.getMessage());
			throw e;
		} finally {
			if (rs != null)
				rs.getStatement().close();
			if (pStatement != null)
				pStatement.close();
		}
	}

	/**
	 * @description 查询单个字段
	 * @datetime 2017年4月16日上午11:31:09
	 * @author xiongdun
	 * @param conn
	 * @param sSql
	 * @param alValue
	 * @return
	 * @throws Exception
	 */
	public static String getString(Connection conn, String sSql, ArrayList<Object> alValue) throws Exception {

		String sColumnValue = "";
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		sSql = toString(sSql);

		if (conn == null)
			return null;
		if ("".equals(sSql))
			return null;

		try {
			String outStr = "";
			String[] arr = sSql.split("\\?");

			java.util.Date dBeginTime = new java.util.Date();

			pStatement = conn.prepareStatement(sSql);

			for (int i = 0; i < alValue.size(); i++) {
				if (alValue.get(i) instanceof String) {
					pStatement.setString(i + 1, toString(alValue.get(i).toString()));
					outStr += arr[i] + "'" + toString(alValue.get(i).toString()) + "'";
				}
				if (alValue.get(i) instanceof Double) {
					pStatement.setDouble(i + 1, toDouble(alValue.get(i).toString()));
					outStr += arr[i] + toDouble(alValue.get(i).toString());
				}
				if (alValue.get(i) instanceof Integer) {
					pStatement.setInt(i + 1, toInt(alValue.get(i).toString()));
					outStr += arr[i] + toDouble(alValue.get(i).toString());
				}
				if (alValue.get(i) == null) {
					pStatement.setObject(i + 1, alValue.get(i));
					outStr += arr[i] + alValue.get(i);
				}
			}

			if (arr != null && arr.length > alValue.size())
				outStr += arr[alValue.size()];

			rs = pStatement.executeQuery();
			if (rs.next()) {
				sColumnValue = toString(rs.getString(1));
			}

			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			java.util.Date dEndTime = new java.util.Date();
			double iTimeConsuming = (dEndTime.getTime() - dBeginTime.getTime()) / 1000.0;
			String sWarning = "";
			if (iTimeConsuming > 0.5)
				sWarning = "WARN";
			logger.warn("[SQL" + sWarning + "]" + sdf.format(dBeginTime) + " : " + iTimeConsuming + " [" + outStr + "]");

			return sColumnValue;

		} catch (Exception e) {
			logger.debug("sql执行异常，异常原因：" + e.getMessage());
			throw e;
		} finally {
			if (rs != null)
				rs.getStatement().close();
			if (pStatement != null)
				pStatement.close();
		}
	}

	/**
	 * @description 查询单个字段
	 * @datetime 2017年4月16日上午11:31:28
	 * @author xiongdun
	 * @param conn
	 * @param sSql
	 * @param sValue
	 * @return
	 * @throws Exception
	 */
	public static String getString(Connection conn, String sSql, Object sValue) throws Exception {

		String sColumnValue = "";
		PreparedStatement pStatement = null;
		ResultSet rs = null;

		sSql = toString(sSql);

		if (conn == null)
			return null;
		if ("".equals(sSql))
			return null;
		if (sValue == null)
			return null;

		try {
			String outStr = "";
			String[] arr = sSql.split("\\?");

			java.util.Date dBeginTime = new java.util.Date();

			pStatement = conn.prepareStatement(sSql);
			if (sValue instanceof String) {
				pStatement.setString(1, toString(sValue.toString()));
				outStr += arr[0] + "'" + toString(sValue.toString()) + "'";
			}
			if (sValue instanceof Double) {
				pStatement.setDouble(1, toDouble(sValue.toString()));
				outStr += arr[0] + toDouble(sValue.toString());
			}
			if (sValue instanceof Integer) {
				pStatement.setInt(1, toInt(sValue.toString()));
				outStr += arr[0] + toDouble(sValue.toString());
			}

			if (arr != null && arr.length > 1)
				outStr += arr[1];

			rs = pStatement.executeQuery();
			if (rs.next()) {
				sColumnValue = toString(rs.getString(1));
			}

			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			java.util.Date dEndTime = new java.util.Date();
			double iTimeConsuming = (dEndTime.getTime() - dBeginTime.getTime()) / 1000.0;
			String sWarning = "";
			if (iTimeConsuming > 0.5)
				sWarning = "WARN";
			logger.warn("[SQL" + sWarning + "]" + sdf.format(dBeginTime) + " : " + iTimeConsuming + " [" + outStr + "]");

			return sColumnValue;

		} catch (Exception e) {
			logger.debug("sql执行异常，异常原因：" + e.getMessage());
			throw e;
		} finally {
			if (rs != null)
				rs.getStatement().close();
			if (pStatement != null)
				pStatement.close();
		}
	}

	/**
	 * @description 查询多条信息
	 * @datetime 2017年4月16日上午11:31:38
	 * @author xiongdun
	 * @param conn
	 * @param sSql
	 * @param alValue
	 * @return
	 * @throws Exception
	 */
	public static Vector<Map<String, String>> getVector(Connection conn, String sSql, ArrayList<Object> alValue)
			throws Exception {

		Vector<Map<String, String>> vTor = new Vector<Map<String, String>>();
		PreparedStatement pStatement = null;
		ResultSet rs = null;

		sSql = toString(sSql);

		if (conn == null) {
			return null;
		}
		if ("".equals(sSql)) {
			return null;
		}

		try {
			String outStr = "";
			String[] arr = sSql.split("\\?");

			java.util.Date dBeginTime = new java.util.Date();

			pStatement = conn.prepareStatement(sSql);

			for (int i = 0; i < alValue.size(); i++) {
				System.out.println("DataSqlPara" + i + ":" + toString(alValue.get(i).toString()));
				if (alValue.get(i) instanceof String) {
					pStatement.setString(i + 1, toString(alValue.get(i).toString()));
					outStr += arr[i] + "'" + toString(alValue.get(i).toString()) + "'";
				}
				if (alValue.get(i) instanceof Double) {
					pStatement.setDouble(i + 1, toDouble(alValue.get(i).toString()));
					outStr += arr[i] + toDouble(alValue.get(i).toString());
				}
				if (alValue.get(i) instanceof Integer) {
					pStatement.setInt(i + 1, toInt(alValue.get(i).toString()));
					outStr += arr[i] + toDouble(alValue.get(i).toString());
				}
				if (alValue.get(i) == null) {
					pStatement.setObject(i + 1, alValue.get(i));
					outStr += arr[i] + alValue.get(i);
				}
			}

			if (arr != null && arr.length > alValue.size()) {
				outStr += arr[alValue.size()];
			}

			rs = pStatement.executeQuery();
			while (rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					String sColumnName = toString(rs.getMetaData().getColumnName(i).toUpperCase());
					String sColumnValue = toString(rs.getString(sColumnName));
					map.put(sColumnName, sColumnValue);
				}
				vTor.add(map);
			}

			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			java.util.Date dEndTime = new java.util.Date();
			double iTimeConsuming = (dEndTime.getTime() - dBeginTime.getTime()) / 1000.0;
			String sWarning = "";
			if (iTimeConsuming > 0.5) {
				sWarning = "WARN";
			}
			logger.warn("[SQL" + sWarning + "]" + sdf.format(dBeginTime) + " : " + iTimeConsuming + " [" + outStr + "]");

			return vTor;
		} catch (Exception e) {
			logger.debug("sql执行异常，异常原因：" + e.getMessage());
			throw e;
		} finally {
			if (rs != null)
				rs.getStatement().close();
			if (pStatement != null)
				pStatement.close();
		}
	}

	/**
	 * @description 查询多条信息
	 * @datetime 2017年4月16日上午11:31:53
	 * @author xiongdun
	 * @param jdbcUtils
	 * @param sSql
	 * @param sValue
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> getList(JdbcUtils jdbcUtils, String sSql, Object[] sValue)
			throws Exception {
		List<Map<String, Object>> vTor = new ArrayList<Map<String, Object>>();

		sSql = toString(sSql);
		if (jdbcUtils == null) {
			return null;
		}
			
		if ("".equals(sSql)) {
			return null;
		}
			
		if (sValue == null) {
			return null;
		}
			
		try {
			vTor = jdbcUtils.queryForList(sSql, sValue);
			return vTor;
		} catch (Exception e) {
			logger.debug("sql执行异常，异常原因：" + e.getMessage());
			throw e;
		}
	}

	/**
	 * @description 执行SQL语句 insert,update,delete
	 * @datetime 2017年4月16日上午11:12:32
	 * @author xiongdun
	 * @param jdbcUtils
	 * @param sql
	 * @param alValue
	 * @return
	 */
	public static int executeSQL(JdbcUtils jdbcUtils, String sql, final ArrayList<Object> alValue) {
		int iexecute = 0;
		sql = toString(sql);
		if (jdbcUtils == null || "".equals(sql)) {
			return iexecute;
		}
		try {
			iexecute = jdbcUtils.update(sql, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement statement) throws SQLException {
					for (int i = 0; i < alValue.size(); i++) {
						if (alValue.get(i) instanceof String) {
							statement.setString(i + 1, DataSqlTools.toString(alValue.get(i).toString()));
						}
						if (alValue.get(i) instanceof Double) {
							statement.setDouble(i + 1, DataSqlTools.toDouble(alValue.get(i).toString()));
						}
						if (alValue.get(i) instanceof Integer) {
							statement.setInt(i + 1, DataSqlTools.toInt(alValue.get(i).toString()));
						}
						if (alValue.get(i) == null) {
							statement.setObject(i + 1, alValue.get(i));
						}
					}
				}
			});
			return iexecute;
		} catch (Exception e) {
			logger.debug("执行sql 异常，异常原因：" + e.getMessage());
		}
		return iexecute;
	}

	/**
	 * @description 对value string 进行处理，转换为string
	 * @datetime 2017年4月16日上午11:07:05
	 * @author xiongdun
	 * @param value
	 * @return
	 */
	private static String toString(String value) {
		if (value == null) {
			return "";
		} else {
			if ("NULL".equals(value.toUpperCase())) {
				return "";
			} else {
				return value;
			}
		}
	}

	/**
	 * @description 对value string 进行处理，转换为int
	 * @datetime 2017年4月16日上午11:09:29
	 * @author xiongdun
	 * @param value
	 * @return
	 */
	private static int toInt(String value) {
		if (value == null || "".equals(value)) {
			return 0;
		}
		return Integer.parseInt(value);
	}

	/**
	 * @description 对value string 进行处理，转换为double
	 * @datetime 2017年4月16日上午11:10:38
	 * @author xiongdun
	 * @param value
	 * @return
	 */
	private static double toDouble(String value) {
		if (value == null || "".equals(value)) {
			return 0;
		}
		return Double.parseDouble(value);
	}
}
