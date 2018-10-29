package com.chinaxaxt.xtzncms.service.biz;

import java.util.List;

import com.chinaxaxt.xtzncms.entity.biz.IndustryNews;

/**
 * 
 * @author 吴佳涛
 * 2018-6-25
 */
public interface IndustryNewsService {

	/**
	 * 增加
	 * @param industryNews
	 * @return
	 */
    int insert(IndustryNews industryNews);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(String uid);

    /**
     * 修改详情
     * @param context
     * @param uid
     * @return
     */
    int update(String context,String uid);

    /**
     * 查询所有
     * @param industryNews
     * @return
     */
    List<IndustryNews> select(IndustryNews industryNews);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    IndustryNews selectNewsByUid(String uid);
    
    /**
     * 根据标题查询
     * @param title
     * @return
     */
    List<IndustryNews> selectNewsByTitle(String title);
    
    /**
     * 根据摘要查询
     * @param remark
     * @return
     */
    List<IndustryNews> selectNewsByRemark(String remark);
    
    /**
     * 根据作者查询
     * @param author
     * @return
     */
    List<IndustryNews> selectNewsByAuthor(String author);
    
}