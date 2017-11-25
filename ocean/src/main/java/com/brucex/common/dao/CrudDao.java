/**
 * 
 */
package com.brucex.common.dao;

import java.util.List;

/**
 * @description 公共 查询，删除，修改，增加记录接口类
 * @author xiongdun
 * @datetime 2017年4月8日下午3:57:06
 */
public interface CrudDao<T> extends BaseDao<T> {
	
	/**
	 * @description 按ID查询单条数据
	 * @datetime 2017年4月11日下午11:07:06
	 * @author xiongdun
	 * @param id
	 * @return
	 */
	public T selectById(String id);
	
	/**
	 * @description 按相关条件查询单条数据
	 * @datetime 2017年4月11日下午11:07:27
	 * @author xiongdun
	 * @param entity
	 * @return
	 */
	public T select(T entity);
	
	/**
	 * @description 查询数据列表，如果需要分页，请设置分页对象，
	 * 如：entity.setPage(new Page<T>());
	 * @datetime 2017年4月11日下午11:08:01
	 * @author xiongdun
	 * @param entity
	 * @return
	 */
	public List<T> selectList(T entity);
	
	/**
	 * @description 查询所有数据列表
	 * @datetime 2017年4月11日下午11:08:22
	 * @author xiongdun
	 * @param entity
	 * @return
	 */
    public List<T> selectAllList(T entity);
    
    /**
     * @description 插入数据
     * @datetime 2017年4月11日下午11:18:24
     * @author xiongdun
     * @param entity
     * @return
     */
    public int insert(T entity);
    
    /**
     * @description 修改数据
     * @datetime 2017年4月11日下午11:18:14
     * @author xiongdun
     * @param entity
     * @return
     */
    public int update(T entity);
    
    /**
     * @description 按Id逻辑删除，即为将del_flag 改为1
     * @datetime 2017年4月11日下午11:13:31
     * @author xiongdun
     * @param id
     * @return
     */
    public int deleteById(String id);
    
    /**
     * @description 逻辑删除，即为将del_flag 改为1
     * @datetime 2017年4月11日下午11:13:35
     * @author xiongdun
     * @return
     */
    public int delete(T entity);
    
    /**
     * @description 按Id物理删除，即为删除该纪录
     * @datetime 2017年4月11日下午11:12:01
     * @author xiongdun
     * @param id
     * @return
     */
    public int realDeleteById(String id);
    
    /**
     * @description 物理删除，即为删除该纪录
     * @datetime 2017年4月11日下午11:13:16
     * @author xiongdun
     * @param entity
     * @return
     */
    public int realDelete(T entity);
}
