package com.chinaxaxt.xtzncms.mapper.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chinaxaxt.xtzncms.entity.biz.IndustryNews;

/**
 * 
 * @author 吴佳涛
 * 2018-6-25
 */
@Repository
public interface IndustryNewsMapper {

	/**
	 * 增加
	 * @param industryNews
	 * @return
	 */
    int insert(IndustryNews industryNews);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(@Param("uid") String uid);

    /**
     * 修改详情
     * @param context
     * @param uid
     * @return
     */
    int updateContext(@Param("context") String context,@Param("uid") String uid);

    /**
     * 查询所有
     * @param industryNews
     * @return
     */
    List<IndustryNews> select(IndustryNews industryNews);
    
    /**
     * 根据标题查询
     * @param title
     * @return
     */
    @Select("select * from t_industry_news where title like concat(concat('%',#{title}),'%')")
    @ResultMap("industryNews")
    List<IndustryNews> selectNewsByTitle(@Param("title")String title);
    
    /**
     * 根据摘要查询
     * @param remark
     * @return
     */
    @Select("select * from t_industry_news where remark like concat(concat('%',#{remark}),'%')")
    @ResultMap("industryNews")
    List<IndustryNews> selectNewsByRemark(@Param("remark")String remark);
    
    /**
     * 根据作者查询
     * @param author
     * @return
     */
    @Select("select * from t_industry_news where author like concat(concat('%',#{author}),'%')")
    @ResultMap("industryNews")
    List<IndustryNews> selectNewsByAuthor(@Param("author")String author);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    @Select("select * from t_industry_news where uid = #{uid}")
    @ResultMap("industryNews")
    IndustryNews selectNewsByUid(@Param("uid") String uid);
    
}