package com.chinaxaxt.xtzncms.service.biz;

import java.util.List;

import com.chinaxaxt.xtzncms.entity.biz.Cooperation;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
public interface CooperationService {

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
    int deleteByUid(String uid);

//    int update(Cooperation cooperation);

    /**
     * 查询所有
     * @param cooperation
     * @return
     */
    List<Cooperation> select(Cooperation cooperation);
    
    /**
     * 查询所有并按时间排序
     * @return
     */
    List<Cooperation> selectBySort();
    
    /**
     * 根据id查询
     * @param uid
     * @return
     */
    Cooperation selectCooperationByUid(String uid);
    
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    List<Cooperation> selectCooperationByName(String name);
    
}