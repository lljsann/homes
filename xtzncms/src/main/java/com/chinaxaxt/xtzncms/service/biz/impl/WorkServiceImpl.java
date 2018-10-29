package com.chinaxaxt.xtzncms.service.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chinaxaxt.xtzncms.common.utils.tools.UUIDUtil;
import com.chinaxaxt.xtzncms.entity.biz.Work;
import com.chinaxaxt.xtzncms.mapper.biz.WorkMapper;
import com.chinaxaxt.xtzncms.service.biz.WorkService;

/**
 * 
 * @author 吴佳涛
 * 2018-7-4
 */
@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    WorkMapper workMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(Work work){
    	work.setUid(UUIDUtil.getRandom32BeginTimePK());
    	work.setCreateTime(new Date());
        return workMapper.insert(work);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByUid(String uid){
        return workMapper.deleteByUid(uid);
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public int update(Work work){
//        return workMapper.update(work);
//    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<Work> select(Work work){
        return workMapper.select(work);
    }
	
	@Override
	public Work selectWorkByUid(String uid) {
		return workMapper.selectWorkByUid(uid);
	}


}