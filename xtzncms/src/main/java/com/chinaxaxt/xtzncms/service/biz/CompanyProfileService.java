package com.chinaxaxt.xtzncms.service.biz;

import java.util.List;

import com.chinaxaxt.xtzncms.entity.biz.CompanyProfile;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
public interface CompanyProfileService {

	/**
	 * 增加
	 * @param companyProfile
	 * @return
	 */
    int insert(CompanyProfile companyProfile);

    /**
     * 根据id查询
     * @param uid
     * @return
     */
    int deleteByUid(String uid);

//    int update(CompanyProfile companyProfile);

    /**
     * 查询所有
     * @param companyProfile
     * @return
     */
    List<CompanyProfile> select(CompanyProfile companyProfile);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    CompanyProfile selectProfileByUid(String uid);
    
}