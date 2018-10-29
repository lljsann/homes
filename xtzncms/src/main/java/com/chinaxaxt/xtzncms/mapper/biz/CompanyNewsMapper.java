package com.chinaxaxt.xtzncms.mapper.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chinaxaxt.xtzncms.entity.biz.CompanyNews;

/**
 * 
 * @author 吴佳涛
 * 2018-6-25
 */
@Repository
public interface CompanyNewsMapper {

	/**
	 * 增加
	 * @param companyNews
	 * @return
	 */
    int insert(CompanyNews companyNews);

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
     * 更新阅读次数
     * @param uid
     * @return
     */
    int updateHitCount(@Param("uid") String uid);
    
    /**
     * 更新点赞次数
     * @param uid
     * @return
     */
    int updateApplaudCount(@Param("uid") String uid);

    /**
     * 查询最新四条
     * @return
     */
    List<CompanyNews> selectNewsByTime();
    
    /**
     * 管理查询所有
     * @param companyNews
     * @return
     */
    @Select("select `uid`,`author`,`title`,`remark`,`create_time`,`type`,`hit_count` from t_company_news")
    @ResultMap("companyNews")
    List<CompanyNews> selectCom(CompanyNews companyNews);
    
    
    /**
     * 网站查询所有
     * @param companyNews
     * @return
     */
    @Select("select `uid`,`image_url`,`title`,`remark`,`create_time`,`type` from t_company_news where 1=1 order by `create_time` desc")
    @ResultMap("companyNews")
    List<CompanyNews> selectAll(CompanyNews companyNews);
    
    /**
     * 查询所有
     * @param companyNews
     * @return
     */
    List<CompanyNews> select(CompanyNews companyNews);
    
    /**
     * 根据标题查询
     * @param title
     * @return
     */
    @Select("select `uid`,`author`,`title`,`remark`,`create_time`,`type`,`hit_count` from t_company_news where title like concat(concat('%',#{title}),'%')")
    @ResultMap("companyNews")
    List<CompanyNews> selectNewsByTitle(@Param("title")String title);
    
    /**
     * 根据摘要查询
     * @param remark
     * @return
     */
    @Select("select `uid`,`author`,`title`,`remark`,`create_time`,`type`,`hit_count` from t_company_news where remark like concat(concat('%',#{remark}),'%')")
    @ResultMap("companyNews")
    List<CompanyNews> selectNewsByRemark(@Param("remark")String remark);
    
    /**
     * 根据作者查询
     * @param author
     * @return
     */
    @Select("select `uid`,`author`,`title`,`remark`,`create_time`,`type`,`hit_count` from t_company_news where author like concat(concat('%',#{author}),'%')")
    @ResultMap("companyNews")
    List<CompanyNews> selectNewsByAuthor(@Param("author")String author);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    @Select("select `title`,`context`,`create_time` from t_company_news where uid = #{uid}")
    @ResultMap("companyNews")
    CompanyNews selectNewsByUid(@Param("uid") String uid);
}