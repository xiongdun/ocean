/**
 * 
 */
package com.brucex.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brucex.common.service.CrudService;
import com.brucex.modules.sys.dao.DictDao;
import com.brucex.modules.sys.entity.Dict;

/**
 * @description DictService
 * @author xiongdun
 * @datetime 2017年4月16日下午8:03:46
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {

}
