package com.chinaxaxt.xtzncms.service.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chinaxaxt.xtzncms.common.utils.tools.UUIDUtil;
import com.chinaxaxt.xtzncms.entity.biz.Cooperation;
import com.chinaxaxt.xtzncms.mapper.biz.CooperationMapper;
import com.chinaxaxt.xtzncms.service.biz.CooperationService;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Service
public class CooperationServiceImpl implements CooperationService {

    @Autowired
    CooperationMapper cooperationMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(Cooperation cooperation){
    	cooperation.setUid(UUIDUtil.getRandom32BeginTimePK());
    	cooperation.setCreateTime(new Date());
        return cooperationMapper.insert(cooperation);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByUid(String uid){
        return cooperationMapper.deleteByUid(uid);
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public int update(Cooperation cooperation){
//        return cooperationMapper.update(cooperation);
//    }

    @Override
    public List<Cooperation> select(Cooperation cooperation){
        return cooperationMapper.select(cooperation);
    }
    
    @Override
	public Cooperation selectCooperationByUid(String uid) {
		return cooperationMapper.selectCooperationByUid(uid);
	}

	@Override
	public List<Cooperation> selectCooperationByName(String name) {
		return cooperationMapper.selectCooperationByName(name);
	}

	@Override
	public List<Cooperation> selectBySort() {
		return cooperationMapper.selectBySort();
	}

}