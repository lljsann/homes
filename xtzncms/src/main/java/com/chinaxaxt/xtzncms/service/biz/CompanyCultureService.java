package com.chinaxaxt.xtzncms.service.biz;

import java.util.List;

import com.chinaxaxt.xtzncms.entity.biz.CompanyCulture;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
public interface CompanyCultureService {

	/**
	 * 增加
	 * @param companyCulture
	 * @return
	 */
    int insert(CompanyCulture companyCulture);

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    int deleteByUid(String uid);

    /**
     * 修改详情
     * @param context
     * @param uid
     * @return
     */
    int update(String context,String uid);
    
    /**
     * 查询所有
     * @param companyCulture
     * @return
     */
    List<CompanyCulture> select(CompanyCulture companyCulture);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    CompanyCulture selectCultureByUid(String uid);
    
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    List<CompanyCulture> selectCultureByName(String name);
    
    /**
     * 根据摘要查询
     * @param remark
     * @return
     */
    List<CompanyCulture> selectCultureByRemark(String remark);
    
    /**
     * 根据类型查询
     * @param type
     * @return
     */
    List<CompanyCulture> selectCultureByType(String type);
    
    /**
     * 查询所有团队建设并根据时间排序
     * @param type
     * @return
     */
    List<CompanyCulture> selectPro(String type);
    
}