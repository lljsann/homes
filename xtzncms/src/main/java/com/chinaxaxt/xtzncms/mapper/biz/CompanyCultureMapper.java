package com.chinaxaxt.xtzncms.mapper.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chinaxaxt.xtzncms.entity.biz.CompanyCulture;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Repository
public interface CompanyCultureMapper {

	/**
	 * 增加
	 * @param companyCulture
	 * @return
	 */
    int insert(CompanyCulture companyCulture);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(@Param("uid") String uid);

    /**
     * 修改详情
     * @param detail
     * @param uid
     * @return
     */
    int updateDetail(@Param("detail") String detail,@Param("uid") String uid);
    
    /**
     * 查询所有
     * @param companyCulture
     * @return
     */
    List<CompanyCulture> select(CompanyCulture companyCulture);
    
    /**
     * 管理查询所有
     * @param companyCulture
     * @return
     */
    @Select("select `uid`,`name`,`remark`,`type`,`create_time` from t_company_culture")
    @ResultMap("companyCulture")
    List<CompanyCulture> selectAll(CompanyCulture companyCulture);
    
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    @Select("select `uid`,`name`,`remark`,`type`,`create_time` from t_company_culture where name like concat(concat('%',#{name}),'%')")
    @ResultMap("companyCulture")
    List<CompanyCulture> selectCultureByName(@Param("name")String name);
    
    /**
     * 根据摘要查询
     * @param remark
     * @return
     */
    @Select("select `uid`,`name`,`remark`,`type`,`create_time` from t_company_culture where remark like concat(concat('%',#{remark}),'%')")
    @ResultMap("companyCulture")
    List<CompanyCulture> selectCultureByRemark(@Param("remark")String remark);
    
    /**
     * 根据类型查询
     * @param type
     * @return
     */
    @Select("select `uid`,`image`,`name`,`remark` from t_company_culture where type like concat(concat('%',#{type}),'%') order by create_time asc")
    @ResultMap("companyCulture")
    List<CompanyCulture> selectCultureByType(@Param("type")String type);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    @Select("select `remark`,`detail`,`create_time` from t_company_culture where uid = #{uid}")
    @ResultMap("companyCulture")
    CompanyCulture selectCultureByUid(@Param("uid") String uid);
    
    /**
     * 查询所有团队建设并按时间降序排列
     * @param type
     * @return
     */
    @Select("select `uid`,`image`,`name` from t_company_culture where type like concat(concat('%',#{type}),'%') order by create_time desc")
    @ResultMap("companyCulture")
    List<CompanyCulture> selectPro(@Param("type")String type);
}