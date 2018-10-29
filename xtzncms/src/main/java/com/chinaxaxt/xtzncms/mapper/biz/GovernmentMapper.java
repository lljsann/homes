package com.chinaxaxt.xtzncms.mapper.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chinaxaxt.xtzncms.entity.biz.Government;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Repository
public interface GovernmentMapper {

	/**
	 * 增加
	 * @param government
	 * @return
	 */
    int insert(Government government);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(@Param("uid") String uid);

//    int update(Government government);

    /**
     * 查询所有
     * @param government
     * @return
     */
    List<Government> select(Government government);
    
    /**
     * 查询所有并按时间排序
     * @param government
     * @return
     */
    @Select("select * from t_government order by `create_time` asc")
    @ResultMap("government")
    List<Government> selectBySort();
    
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    @Select("select * from t_government where name like concat(concat('%',#{name}),'%')")
    @ResultMap("government")
    List<Government> selectGovernmentByName(@Param("name")String name);
    
    /**
     * 根据摘要查询
     * @param synopsis
     * @return
     */
    @Select("select * from t_government where synopsis like concat(concat('%',#{synopsis}),'%')")
    @ResultMap("government")
    List<Government> selectGovernmentBySynopsis(@Param("synopsis")String synopsis);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    @Select("select * from t_government where uid = #{uid}")
    @ResultMap("government")
    Government selectGovernmentByUid(@Param("uid") String uid);
}