/**
 * 
 */
package com.brucex.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brucex.common.service.CrudService;
import com.brucex.modules.sys.dao.VisitHistoryDao;
import com.brucex.modules.sys.entity.VisitHistory;

/**
 * @description VisitHistoryService
 * @author xiongdun
 * @datetime 2017年4月23日下午4:56:48
 */
@Service
@Transactional(readOnly = true)
public class VisitHistoryService extends CrudService<VisitHistoryDao, VisitHistory> {

	
}
