package com.chinaxaxt.xtzncms.mapper.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chinaxaxt.xtzncms.entity.biz.Job;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Repository
public interface JobMapper {

	/**
	 * 增加
	 * @param job
	 * @return
	 */
    int insert(Job job);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(@Param("uid") String uid);

//    int update(Job job);

    /**
     * 查询所有
     * @param job
     * @return
     */
    List<Job> select(Job job);
    
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    @Select("select * from t_job where name like concat(concat('%',#{name}),'%')")
    @ResultMap("job")
    List<Job> selectJobsByName(@Param("name")String name);
    
    /**
     * 根据地址查询
     * @param address
     * @return
     */
    @Select("select * from t_job where address like concat(concat('%',#{address}),'%')")
    @ResultMap("job")
    List<Job> selectJobsByAddress(@Param("address")String address);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    @Select("select * from t_job where uid = #{uid}")
    @ResultMap("job")
    Job selectJobsByUid(@Param("uid") String uid);
}