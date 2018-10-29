package com.chinaxaxt.xtzncms.mapper.biz;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.chinaxaxt.xtzncms.entity.biz.CompanyStruct;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author 吴佳涛
 * 2018-7-4
 */
@Repository
public interface CompanyStructMapper {

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
    int deleteByUid(@Param("uid") String uid);

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
    @Select("select * from t_company_struct where uid = #{uid}")
    @ResultMap("companyStruct")
    CompanyStruct selectCompanyStructByUid(@Param("uid") String uid);
}