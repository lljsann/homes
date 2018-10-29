package com.chinaxaxt.xtzncms.mapper.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chinaxaxt.xtzncms.entity.biz.CompanyProfile;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Repository
public interface CompanyProfileMapper {

	/**
	 * 增加
	 * @param companyProfile
	 * @return
	 */
    int insert(CompanyProfile companyProfile);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(@Param("uid") String uid);

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
    @Select("select * from t_company_profile where uid = #{uid}")
    @ResultMap("companyProfile")
    CompanyProfile  selectProfileByUid(@Param("uid") String uid);
}