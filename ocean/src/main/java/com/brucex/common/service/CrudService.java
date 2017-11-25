/**
 * 
 */
package com.brucex.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.brucex.common.dao.CrudDao;
import com.brucex.common.entity.DataEntity;
import com.brucex.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @description crudservice
 * @author xiongdun
 * @datetime 2017年4月10日下午11:55:12
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService {
	
	/**
	 * 持久层对象
	 * 说明：首先使用注解装载公共持久层对象
	 */
	@Autowired
	protected D dao;
	
	/**
	 * @description 按Id查询单条数据
	 * @author xiongdun
	 * @datetime 2017年4月23日下午4:10:31
	 * @param id
	 * @return
	 */
	public T getById(String id) {
		return this.dao.selectById(id);
	}
	
	/**
	 * @description 按相关条件查询单挑数据
	 * @author xiongdun
	 * @datetime 2017年4月23日下午4:11:31
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		return this.dao.select(entity);
	}
	
	/**
	 * @description 按条件查询数据
	 * @author xiongdun
	 * @datetime 2017年4月23日下午4:13:33
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return this.dao.selectList(entity);
	}
	
	/**
	 * @description 查询所有可用数据
	 * @author xiongdun
	 * @datetime 2017年4月23日下午4:13:56
	 * @param entity
	 * @return
	 */
	public List<T> findAllList(T entity) {
		return this.dao.selectAllList(entity);
	}
	
	/**
	 * @description 分页查询数据
	 * 				（带PageInfo 对象）
	 * @author xiongdun
	 * @datetime 2017年4月23日下午4:17:03
	 * @param page 分页信息
	 * @param entity 查询条件
	 * @return
	 */
	public PageInfo<T> findPage(PageInfo<T> page, T entity) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		return new PageInfo<T>(this.dao.selectList(entity));
	}
	
	/**
	 * @description 分页查询数据
	 * 				（不带PageInfo 对象）
	 * @author xiong
	 * @param entity
	 * @return PageInfo<T>
	 * @date 2017年6月27日上午12:25:04
	 */
	public PageInfo<T> findPage(T entity) {
		PageHelper.startPage(entity.getPage().getPageNum(), entity.getPage().getPageSize());
		return new PageInfo<T>(this.dao.selectList(entity));
	}
	
	/**
	 * @description 保存数据
	 * @author xiongdun
	 * @datetime 2017年4月23日下午4:20:44
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean save(T entity) {
		// 判断是否为新纪录
		if (entity.getIsNewRecord()) {
			// 为新纪录则为新增纪录
			entity.preInsert();
			return dao.insert(entity) > 0 ? true : false;
		} else {
			// 不为新纪录则进行修改记录
			entity.preUpdate();
			return dao.update(entity) > 0 ? true : false;
		}
	}
	
	/**
	 * @description 按Id删除--逻辑删除
	 * @author xiongdun
	 * @datetime 2017年4月23日下午4:25:14
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean remove(String id) {
		if (StringUtils.isEmpty(id)) {
			return false;
		}
		return dao.deleteById(id) > 0 ? true : false;
	}
	
	/**
	 * @description 按条件删除--逻辑删除
	 * @author xiongdun
	 * @datetime 2017年4月23日下午4:25:26
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean remove(T entity) {
		return dao.delete(entity) > 0 ? true : false;
	}
	
	/**
	 * @description 按ID删除，物理删除，一般少用
	 * @author xiongdun
	 * @datetime 2017年4月23日下午4:26:53
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean realRemove(String id) {
		return dao.realDeleteById(id) > 0 ? true : false;
	}
	
	/**
	 * @description 按条件删除，物理删除，一般少用
	 * @author xiongdun
	 * @datetime 2017年4月23日下午4:27:19
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean realRemove(T entity) {
		return dao.realDelete(entity) > 0 ? true : false;
	}
}
