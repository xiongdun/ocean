/**
 * 
 */
package com.brucex.interfaces.service.news;

import com.brucex.interfaces.entity.modules.news.NewsDTO;

import java.util.List;

/**
 * @description 对外服务的News服务
 * @author xiongdun
 * @datetime 2017年4月17日下午11:11:44
 */
public interface NewsOutService {

	List<NewsDTO> findAllNewsList();

}
