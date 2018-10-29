package com.chinaxaxt.xtzncms.mapper.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chinaxaxt.xtzncms.entity.biz.Info;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Repository
public interface InfoMapper {

	/**
	 * 增加
	 * @param info
	 * @return
	 */
    int insert(Info info);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(@Param("uid") String uid);

//    int update(Info info);

    /**
     * 查询所有
     * @param info
     * @return
     */
    List<Info> select(Info info);
    
    /**
     * 根据标题查询
     * @param title
     * @return
     */
    @Select("select * from t_info where title like concat(concat('%',#{title}),'%')")
    @ResultMap("info")
    List<Info> selectInfoByTitle(@Param("title")String title);
    
    /**
     * 根据摘要查询
     * @param remark
     * @return
     */
    @Select("select * from t_info where remark like concat(concat('%',#{remark}),'%')")
    @ResultMap("info")
    List<Info> selectInfoByRemark(@Param("remark")String remark);
    
    /**
     * 根据作者查询
     * @param author
     * @return
     */
    @Select("select * from t_info where author like concat(concat('%',#{author}),'%')")
    @ResultMap("info")
    List<Info> selectInfoByAuthor(@Param("author")String author);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    @Select("select * from t_info where uid = #{uid}")
    @ResultMap("info")
    Info  selectInfoByUid(@Param("uid") String uid);
    
}