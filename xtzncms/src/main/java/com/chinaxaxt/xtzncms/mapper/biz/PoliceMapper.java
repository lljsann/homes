package com.chinaxaxt.xtzncms.mapper.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chinaxaxt.xtzncms.entity.biz.Police;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Repository
public interface PoliceMapper {

	/**
	 * 增加
	 * @param police
	 * @return
	 */
    int insert(Police police);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(@Param("uid") String uid);

//    int update(Police police);

    /**
     * 查询所有按时间排序
     * @param police
     * @return
     */
    @Select("select * from t_police order by `create_time` asc ")
    @ResultMap("police")
    List<Police> selectBySort();
    
    /**
     * 查询所有
     * @param police
     * @return
     */
    List<Police> select(Police police);
    
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    @Select("select * from t_police where name like concat(concat('%',#{name}),'%')")
    @ResultMap("police")
    List<Police> selectPoliceByName(@Param("name")String name);
    
    /**
     * 根据摘要查询
     * @param synopsis
     * @return
     */
    @Select("select * from t_police where synopsis like concat(concat('%',#{synopsis}),'%')")
    @ResultMap("police")
    List<Police> selectPoliceBySynopsis(@Param("synopsis")String synopsis);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    @Select("select * from t_police where uid = #{uid}")
    @ResultMap("police")
    Police selectPoliceByUid(@Param("uid") String uid);
}