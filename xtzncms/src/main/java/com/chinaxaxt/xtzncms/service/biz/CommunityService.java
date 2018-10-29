package com.chinaxaxt.xtzncms.service.biz;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.chinaxaxt.xtzncms.entity.biz.Community;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
public interface CommunityService {

	/**
	 * 增加
	 * @param community
	 * @return
	 */
    int insert(Community community);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(String uid);

//    int update(Community community);

    /**
     * 查询所有
     * @param community
     * @return
     */
    List<Community> select(Community community);
    
    /**
     * 查询所有并按时间排序
     * @param community
     * @return
     */
    List<Community> selectBySort();
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    Community selectCommunityByUid(String uid);
    
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    List<Community> selectCommunityByName(String name);
    
    /**
     * 根据摘要查询
     * @param synopsis
     * @return
     */
    List<Community> selectCommunityBySynopsis(String synopsis);
    
    /**
     * 多文件上传
     * @param files
     * @return
     */
    List<String> multiUpdate(MultipartFile[] files);
}