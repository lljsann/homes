package com.chinaxaxt.xtzncms.service.biz;

import java.util.List;

import com.chinaxaxt.xtzncms.entity.biz.Info;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
public interface InfoService {

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
    int deleteByUid(String uid);

    /**
     * 查询所有
     * @param info
     * @return
     */
    List<Info> select(Info info);
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    Info selectInfoByUid(String uid);
    
    /**
     * 根据标题查询
     * @param title
     * @return
     */
    List<Info> selectInfoByTitle(String title);
    
    /**
     * 根据摘要查询
     * @param remark
     * @return
     */
    List<Info> selectInfoByRemark(String remark);
    
    /**
     * 根据作者查询
     * @param author
     * @return
     */
    List<Info> selectInfoByAuthor(String author);
    
}