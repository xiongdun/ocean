/**
 * 
 */
package com.brucex.common.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * @description jdbc utils
 * @author xiongdun
 * @datetime 2017年4月16日上午8:53:47
 */
public class JdbcUtils extends JdbcTemplate {

	private static final Logger logger = Logger.getLogger(JdbcUtils.class);

	@Override
	public Map<String, Object> queryForMap(String sql) {
		try {
			return super.queryForMap(sql);
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.info(e.getMessage());
			return null;
		} catch (DataAccessException eae) {
			logger.warn(eae.getMessage());
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
	public <T> T queryForObject(String sql, RowMapper<T> rowMapper) {
		try {
			return super.queryForObject(sql, rowMapper);
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.info(e.getMessage());
			return null;
		} catch (DataAccessException eae) {
			logger.warn(eae.getMessage());
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
	public <T> T queryForObject(String sql, Class<T> requiredType) {
		try {
			return super.queryForObject(sql, requiredType);
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.info(e.getMessage());
			return null;
		} catch (DataAccessException eae) {
			logger.warn(eae.getMessage());
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
	public <T> T queryForObject(String sql, Object[] args, int[] argTypes, RowMapper<T> rowMapper) {
		try {
			return super.queryForObject(sql, args, argTypes, rowMapper);
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.info(e.getMessage());
			return null;
		} catch (DataAccessException eae) {
			logger.warn(eae.getMessage());
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
	public <T> T queryForObject(String sql, Object[] args, RowMapper<T> rowMapper) {
		try {
			return super.queryForObject(sql, args, rowMapper);
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.info(e.getMessage());
			return null;
		} catch (DataAccessException eae) {
			logger.warn(eae.getMessage());
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
	public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args) {
		try {
			return super.queryForObject(sql, rowMapper, args);
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
	public <T> T queryForObject(String sql, Object[] args, int[] argTypes, Class<T> requiredType) {
		try {
			return super.queryForObject(sql, args, argTypes, requiredType); // To
																			// change
																			// body
																			// of
																			// overridden
																			// methods
																			// use
																			// File
																			// |
																			// Settings
																			// |
																			// File
																			// Templates.
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.info(e.getMessage());
			return null;
		} catch (DataAccessException eae) {
			logger.warn(eae.getMessage());
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
	public <T> T queryForObject(String sql, Object[] args, Class<T> requiredType) {
		try {
			return super.queryForObject(sql, args, requiredType); // To change
																	// body of
																	// overridden
																	// methods
																	// use File
																	// |
																	// Settings
																	// | File
																	// Templates.
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.info(e.getMessage());
			return null;
		} catch (DataAccessException eae) {
			logger.warn(eae.getMessage());
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
	public <T> T queryForObject(String sql, Class<T> requiredType, Object... args) {
		try {
			return super.queryForObject(sql, requiredType, args); // To change
																	// body of
																	// overridden
																	// methods
																	// use File
																	// |
																	// Settings
																	// | File
																	// Templates.
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.info(e.getMessage());
			return null;
		} catch (DataAccessException eae) {
			logger.warn(eae.getMessage());
			return null;
		}
	}

	/**
	 * 查询map 键值对对象值
	 */
	@Override
	public Map<String, Object> queryForMap(String sql, Object[] args, int[] argTypes) {
		try {
			return super.queryForMap(sql, args, argTypes); // To change body of
															// overridden
															// methods use File
															// | Settings | File
															// Templates.
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.info(e.getMessage());
			return null;
		} catch (DataAccessException eae) {
			logger.warn(eae.getMessage());
			return null;
		}
	}

	/**
	 * 查询map 键值对对象值
	 */
	@Override
	public Map<String, Object> queryForMap(String sql, Object... args) {
		try {
			return super.queryForMap(sql, args); // To change body of overridden
													// methods use File |
													// Settings | File
													// Templates.
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.info(e.getMessage());
			return null;
		} catch (DataAccessException eae) {
			logger.warn(eae.getMessage());
			return null;
		}
	}

	/**
	 * @description 获取查询字符串值
	 * @datetime 2017年4月16日上午9:01:24
	 * @author xiongdun
	 * @param sql
	 * @return
	 */
	public String getString(String sql) {
		String str = queryForObject(sql, String.class);
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}

	/**
	 * @description 获取查询字符串值
	 * @datetime 2017年4月16日上午9:00:52
	 * @author xiongdun
	 * @param sql
	 * @param args
	 * @return
	 */
	public String getString(String sql, Object... args) {
		String str = queryForObject(sql, String.class, args);
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}

	/**
	 * @description 获取字符串数组矩阵
	 * @datetime 2017年4月16日上午9:00:24
	 * @author xiongdun
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String[][] getStringMatrix(String sql) {
		String[][] sMatrix;
		List list = queryForList(sql);
		if (list == null)
			return null;

		int totolColumnCount = ((Map) list.get(0)).size();
		sMatrix = new String[list.size()][totolColumnCount];
		int rowCount = 0;
		for (Object map : list) {
			Iterator it = ((Map) map).keySet().iterator();
			int columnCount = 0;
			while (it.hasNext()) {
				sMatrix[rowCount][columnCount] = (String) ((Map) map).get(it.next());
				columnCount++;
			}
			rowCount++;
		}
		return sMatrix;
	}
}
