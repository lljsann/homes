package com.chinaxaxt.xtzncms.service.biz;

import java.util.List;

import com.chinaxaxt.xtzncms.entity.biz.CompanyNews;

/**
 * 
 * @author 吴佳涛
 * 2018-6-25
 */
public interface CompanyNewsService {

	/**
	 * 增加
	 * @param companyNews
	 * @return
	 */
    int insert(CompanyNews companyNews);

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
     * 更新阅读次数
     * @param uid
     * @return
     */
    int updateHitCount(String uid);
    
    /**
     * 更新点赞次数
     * @param uid
     * @return
     */
    int updateApplaudCount(String uid);
    
    /**
     * 查询最新四条
     * @param companyNews
     * @return
     */
    List<CompanyNews> selectByTime();
    
    /**
     * 查询所有
     * @param companyNews
     * @return
     */
    List<CompanyNews> select(CompanyNews companyNews);
    
    /**
     * 查询所有
     * @param companyNews
     * @return
     */
    List<CompanyNews> selectAll(CompanyNews companyNews);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    CompanyNews selectNewsByUid(String uid);
    
    /**
     * 根据标题查询
     * @param title
     * @return
     */
    List<CompanyNews> selectNewsByTitle(String title);
    
    /**
     * 根据摘要查询
     * @param remark
     * @return
     */
    List<CompanyNews> selectNewsByRemark(String remark);
    
    /**
     * 根据作者查询
     * @param author
     * @return
     */
    List<CompanyNews> selectNewsByAuthor(String author);
    
}