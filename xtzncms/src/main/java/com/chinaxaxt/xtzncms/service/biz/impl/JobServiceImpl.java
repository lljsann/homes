package com.chinaxaxt.xtzncms.service.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chinaxaxt.xtzncms.common.utils.tools.UUIDUtil;
import com.chinaxaxt.xtzncms.entity.biz.Job;
import com.chinaxaxt.xtzncms.mapper.biz.JobMapper;
import com.chinaxaxt.xtzncms.service.biz.JobService;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobMapper jobMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(Job job){
    	job.setUid(UUIDUtil.getRandom32BeginTimePK());
    	job.setCreateTime(new Date());
        return jobMapper.insert(job);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByUid(String uid){
        return jobMapper.deleteByUid(uid);
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public int update(Job job){
//        return jobMapper.update(job);
//    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<Job> select(Job job){
        return jobMapper.select(job);
    }
    
    @Override
	public List<Job> selectJobsByName(String name) {
		return jobMapper.selectJobsByName(name);
	}

	@Override
	public List<Job> selectJobsByAddress(String address) {
		return jobMapper.selectJobsByAddress(address);
	}
	
	@Override
	public Job selectNewsByUid(String uid) {
		return jobMapper.selectJobsByUid(uid);
	}

}