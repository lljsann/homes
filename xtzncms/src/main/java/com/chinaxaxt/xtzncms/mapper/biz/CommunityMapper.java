package com.chinaxaxt.xtzncms.mapper.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chinaxaxt.xtzncms.entity.biz.Community;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Repository
public interface CommunityMapper {

	/**
	 * 增加
	 * @param community
	 * @return
	 */
    int insert(Community community);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(@Param("uid") String uid);

//    int update(Community community);

    /**
     * 查询所有
     * @param community
     * @return
     */
    List<Community> select(Community community);
    
    /**
     * 查询所有并按时间排序
     * @param community
     * @return
     */
    @Select("select * from t_community order by `create_time` asc")
    @ResultMap("community")
    List<Community> selectBySort();
    
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    @Select("select * from t_community where name like concat(concat('%',#{name}),'%')")
    @ResultMap("community")
    List<Community> selectCommunityByName(@Param("name")String name);
    
    /**
     * 根据摘要查询
     * @param synopsis
     * @return
     */
    @Select("select * from t_community where synopsis like concat(concat('%',#{synopsis}),'%')")
    @ResultMap("community")
    List<Community> selectCommunityBySynopsis(@Param("synopsis")String synopsis);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    @Select("select * from t_community where uid = #{uid}")
    @ResultMap("community")
    Community selectCommunityByUid(@Param("uid") String uid);
}