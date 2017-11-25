/**
 * 
 */
package com.brucex.common.service;

import com.brucex.common.dao.TreeDao;
import com.brucex.common.entity.TreeEntity;

/**
 * @description crudservice
 * @author xiongdun
 * @datetime 2017年4月10日下午11:55:12
 */
public class TreeService<D extends TreeDao<T>, T extends TreeEntity<T>> extends CrudService<D, T> {

}
