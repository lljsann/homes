package com.chinaxaxt.xtzncms.mapper.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chinaxaxt.xtzncms.entity.biz.CompanyAptitude;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Repository
public interface CompanyAptitudeMapper {

	/**
	 * 增加
	 * @param companyAptitude
	 * @return
	 */
    int insert(CompanyAptitude companyAptitude);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(@Param("uid") String uid);

//    int update(CompanyAptitude companyAptitude);

    /**
     * 查询所有
     * @param companyAptitude
     * @return
     */
    List<CompanyAptitude> select(CompanyAptitude companyAptitude);
    
    /**
     * 根据标题查询
     * @param title
     * @return
     */
    @Select("select * from t_company_aptitude where title like concat(concat('%',#{title}),'%')")
    @ResultMap("companyAptitude")
    List<CompanyAptitude> selectAptitudeByTitle(@Param("title")String title);
    
    /**
     * 根据详情查询
     * @param detail
     * @return
     */
    @Select("select * from t_company_aptitude where detail like concat(concat('%',#{detail}),'%')")
    @ResultMap("companyAptitude")
    List<CompanyAptitude> selectAptitudeByDetail(@Param("detail")String detail);
    
    /**
     * 根据类型查询
     * @param type
     * @return
     */
    @Select("select * from t_company_aptitude where type like concat(concat('%',#{type}),'%')")
    @ResultMap("companyAptitude")
    List<CompanyAptitude> selectAptitudeByType(@Param("type")String type);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    @Select("select * from t_company_aptitude where uid = #{uid}")
    @ResultMap("companyAptitude")
    CompanyAptitude selectAptitudeByUid(@Param("uid") String uid);
}