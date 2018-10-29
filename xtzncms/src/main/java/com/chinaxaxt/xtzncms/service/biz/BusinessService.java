package com.chinaxaxt.xtzncms.service.biz;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.chinaxaxt.xtzncms.entity.biz.Business;

/**
 * 
 * @author 吴佳涛
 * 2018-7-4
 */
public interface BusinessService {
	
	/**
	 * 添加
	 * @param business
	 * @return
	 */
    int insert(Business business);
    
    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(String uid);

//    int update(Business business);
    
    /**
     * 查询所有
     * @param business
     * @return
     */
    List<Business> select(Business business);
    
    /**
     * 根据id删除
     * @param uid
     * @return
     */
    Business selectBusinessByUid(String uid);
    
    /**
     * 单文件上传
     * @param file
     * @return
     */
    String singleUpdate(MultipartFile file);
}