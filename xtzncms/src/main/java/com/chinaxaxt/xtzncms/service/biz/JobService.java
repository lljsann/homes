package com.chinaxaxt.xtzncms.service.biz;

import java.util.List;

import com.chinaxaxt.xtzncms.entity.biz.Job;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
public interface JobService {

	/**
	 * 添加
	 * @param job
	 * @return
	 */
    int insert(Job job);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(String uid);

//    int update(Job job);

    /**
     * 查询所有
     * @param job
     * @return
     */
    List<Job> select(Job job);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    Job selectNewsByUid(String uid);
    
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    List<Job> selectJobsByName(String name);
    
    /**
     * 根据地址查询
     * @param address
     * @return
     */
    List<Job> selectJobsByAddress(String address);
}