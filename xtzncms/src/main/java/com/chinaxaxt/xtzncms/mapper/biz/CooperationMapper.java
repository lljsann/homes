package com.chinaxaxt.xtzncms.mapper.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chinaxaxt.xtzncms.entity.biz.Cooperation;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Repository
public interface CooperationMapper {

	/**
	 * 增加
	 * @param cooperation
	 * @return
	 */
    int insert(Cooperation cooperation);
    
    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(@Param("uid") String uid);

//    int update(Cooperation cooperation);

    /**
     * 查询所有
     * @param cooperation
     * @return
     */
    List<Cooperation> select(Cooperation cooperation);
    
    /**
     * 查询所有并按时间排序
     * @param cooperation
     * @return
     */
    @Select("select * from t_cooperation order by `create_time` asc")
    @ResultMap("cooperation")
    List<Cooperation> selectBySort();
    
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    @Select("select * from t_cooperation where name like concat(concat('%',#{name}),'%')")
    @ResultMap("cooperation")
    List<Cooperation> selectCooperationByName(@Param("name")String name);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    @Select("select * from t_cooperation where uid = #{uid}")
    @ResultMap("cooperation")
    Cooperation  selectCooperationByUid(@Param("uid") String uid);
}