/**
 * 
 */
package com.brucex.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brucex.common.service.CrudService;
import com.brucex.modules.sys.dao.LogDao;
import com.brucex.modules.sys.entity.Log;

/**
 * @description logService
 * @author xiongdun
 * @datetime 2017年4月23日下午4:56:48
 */
@Service
@Transactional(readOnly = true)
public class LogService extends CrudService<LogDao, Log>{

}
