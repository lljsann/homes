package com.chinaxaxt.xtzncms.service.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chinaxaxt.xtzncms.common.utils.tools.UUIDUtil;
import com.chinaxaxt.xtzncms.entity.biz.Government;
import com.chinaxaxt.xtzncms.mapper.biz.GovernmentMapper;
import com.chinaxaxt.xtzncms.service.biz.GovernmentService;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Service
public class GovernmentServiceImpl implements GovernmentService {

    @Autowired
    GovernmentMapper governmentMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(Government government){
    	government.setUid(UUIDUtil.getRandom32BeginTimePK());
    	government.setCreateTime(new Date());
        return governmentMapper.insert(government);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByUid(String uid){
        return governmentMapper.deleteByUid(uid);
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public int update(Government government){
//        return governmentMapper.update(government);
//    }

    @Override
    public List<Government> select(Government government){
        return governmentMapper.select(government);
    }
    
    @Override
	public List<Government> selectGovernmentByName(String name) {
		return governmentMapper.selectGovernmentByName(name);
	}

	@Override
	public List<Government> selectGovernmentBySynopsis(String synopsis) {
		return governmentMapper.selectGovernmentBySynopsis(synopsis);
	}
	
	@Override
	public Government selectGovernmentByUid(String uid) {
		return governmentMapper.selectGovernmentByUid(uid);
	}

	@Override
	public List<Government> selectBySort() {
		return governmentMapper.selectBySort();
	}

}