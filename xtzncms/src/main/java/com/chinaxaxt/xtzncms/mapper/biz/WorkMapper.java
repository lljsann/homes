package com.chinaxaxt.xtzncms.mapper.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chinaxaxt.xtzncms.entity.biz.Work;

/**
 * 
 * @author 吴佳涛
 * 2018-7-4
 */
@Repository
public interface WorkMapper {

	/**
	 * 增加
	 * @param work
	 * @return
	 */
    int insert(Work work);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(@Param("uid") String uid);

//    int update(Work work);

    /**
     * 查询所有
     * @param work
     * @return
     */
    List<Work> select(Work work);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    @Select("select * from t_work where uid = #{uid}")
    @ResultMap("work")
    Work selectWorkByUid(@Param("uid") String uid);
}