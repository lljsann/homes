package com.chinaxaxt.xtzncms.service.biz;

import java.util.List;

import com.chinaxaxt.xtzncms.entity.biz.CompanyStruct;

/**
 * 
 * @author 吴佳涛
 * 2018-7-4
 */
public interface CompanyStructService {

	/**
	 * 增加
	 * @param companyStruct
	 * @return
	 */
    int insert(CompanyStruct companyStruct);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(String uid);

//    int update(CompanyStruct companyStruct);

    /**
     * 查询所有
     * @param companyStruct
     * @return
     */
    List<CompanyStruct> select(CompanyStruct companyStruct);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    CompanyStruct selectCompanyStructByUid(String uid);
    
}