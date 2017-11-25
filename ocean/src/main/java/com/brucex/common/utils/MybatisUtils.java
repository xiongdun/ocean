/**
 * 
 */
package com.brucex.common.utils;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

/**
 * @description parse the mybatis config file
 * @author xiongdun
 * @datetime 2017年4月8日下午3:39:03
 */
public class MybatisUtils {

	private static Logger logger = Logger.getLogger(MybatisUtils.class);

	private static SqlSessionFactory sessionFactory = null;

	private static Reader reader = null;

	static {
		try {
			// init mybatis-config.xml
			String resource = "mybatis-config.xml";
			reader = Resources.getResourceAsReader(resource);
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		} catch (Exception e) {
			logger.info("mybatis file io exception! because :" + e.getMessage());
		}
	}

	/**
	 * @description get sessionFactory instance
	 * @datetime 2017年4月8日下午3:36:48
	 * @author xiongdun
	 * @return
	 */
	public static SqlSessionFactory getInstance() {
		return sessionFactory;
	}
}
