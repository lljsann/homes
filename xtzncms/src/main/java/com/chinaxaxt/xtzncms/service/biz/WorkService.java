package com.chinaxaxt.xtzncms.service.biz;

import java.util.List;

import com.chinaxaxt.xtzncms.entity.biz.Work;

/**
 * 
 * @author 吴佳涛
 * 2018-7-4
 */
public interface WorkService {

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
    int deleteByUid(String uid);

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
    Work selectWorkByUid(String uid);
    
}