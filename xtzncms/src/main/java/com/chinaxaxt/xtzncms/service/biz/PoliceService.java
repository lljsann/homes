package com.chinaxaxt.xtzncms.service.biz;

import java.util.List;

import com.chinaxaxt.xtzncms.entity.biz.Police;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
public interface PoliceService {

	/**
	 * 增加
	 * @param police
	 * @return
	 */
    int insert(Police police);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(String uid);

//    int update(Police police);

    /**
     * 查询所有
     * @param police
     * @return
     */
    List<Police> select(Police police);
    
    /**
     * 查询所有
     * @param police
     * @return
     */
    List<Police> selectBySort();
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    Police selectPoliceByUid(String uid);
    
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    List<Police> selectPoliceByName(String name);
    
    /**
     * 根据摘要查询
     * @param synopsis
     * @return
     */
    List<Police> selectPoliceBySynopsis(String synopsis);
    
}