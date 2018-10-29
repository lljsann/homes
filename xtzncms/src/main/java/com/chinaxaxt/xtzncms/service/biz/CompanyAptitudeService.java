package com.chinaxaxt.xtzncms.service.biz;

import java.util.List;

import com.chinaxaxt.xtzncms.entity.biz.CompanyAptitude;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
public interface CompanyAptitudeService {

	/**
	 * 添加
	 * @param companyAptitude
	 * @return
	 */
    int insert(CompanyAptitude companyAptitude);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(String uid);

//    int update(CompanyAptitude companyAptitude);

    /**
     * 查询所有
     * @param companyAptitude
     * @return
     */
    List<CompanyAptitude> select(CompanyAptitude companyAptitude);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    CompanyAptitude selectAptitudeByUid(String uid);
    
    /**
     * 根据标题查询
     * @param title
     * @return
     */
    List<CompanyAptitude> selectAptitudeByTitle(String title);
    
    /**
     * 根据详情查询
     * @param detail
     * @return
     */
    List<CompanyAptitude> selectAptitudeByDetail(String detail);
    
    /**
     * 根据类型查询
     * @param type
     * @return
     */
    List<CompanyAptitude> selectAptitudeByType(String type);
    
}