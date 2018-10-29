package com.chinaxaxt.xtzncms.service.biz;

import java.util.List;

import com.chinaxaxt.xtzncms.entity.biz.Government;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
public interface GovernmentService {

	/**
	 * 增加
	 * @param government
	 * @return
	 */
    int insert(Government government);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(String uid);

//    int update(Government government);

    /**
     * 查询所有
     * @param government
     * @return
     */
    List<Government> select(Government government);
    
    /**
     * 查询所有并按时间排序
     * @param government
     * @return
     */
    List<Government> selectBySort();
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    Government selectGovernmentByUid(String uid);
    
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    List<Government> selectGovernmentByName(String name);
    
    /**
     * 根据摘要查询
     * @param synopsis
     * @return
     */
    List<Government> selectGovernmentBySynopsis(String synopsis);
    
}