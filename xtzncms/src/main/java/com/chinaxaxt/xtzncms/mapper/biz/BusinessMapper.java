package com.chinaxaxt.xtzncms.mapper.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chinaxaxt.xtzncms.entity.biz.Business;

/**
 * 
 * @author 吴佳涛
 * 2018-7-4
 */
@Repository
public interface BusinessMapper {

	/**
	 * 增加
	 * @param business
	 * @return
	 */
    int insert(Business business);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(@Param("uid") String uid);

//    int update(Business business);

    /**
     * 查询所有
     * @param business
     * @return
     */
    List<Business> select(Business business);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    @Select("select * from t_business where uid = #{uid}")
    @ResultMap("business")
    Business selectBusinessByUid(@Param("uid") String uid);
}