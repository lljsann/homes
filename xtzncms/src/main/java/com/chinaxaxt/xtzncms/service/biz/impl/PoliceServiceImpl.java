package com.chinaxaxt.xtzncms.service.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chinaxaxt.xtzncms.common.utils.tools.UUIDUtil;
import com.chinaxaxt.xtzncms.entity.biz.Police;
import com.chinaxaxt.xtzncms.mapper.biz.PoliceMapper;
import com.chinaxaxt.xtzncms.service.biz.PoliceService;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Service
public class PoliceServiceImpl implements PoliceService {

    @Autowired
    PoliceMapper policeMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(Police police){
    	police.setUid(UUIDUtil.getRandom32BeginTimePK());
    	police.setCreateTime(new Date());
        return policeMapper.insert(police);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByUid(String uid){
        return policeMapper.deleteByUid(uid);
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public int update(Police police){
//        return policeMapper.update(police);
//    }

    @Override
    public List<Police> select(Police police){
        return policeMapper.select(police);
    }
    
    @Override
	public List<Police> selectPoliceByName(String name) {
		return policeMapper.selectPoliceByName(name);
	}

	@Override
	public List<Police> selectPoliceBySynopsis(String synopsis) {
		return policeMapper.selectPoliceBySynopsis(synopsis);
	}
	
	@Override
	public Police selectPoliceByUid(String uid) {
		return policeMapper.selectPoliceByUid(uid);
	}

	@Override
	public List<Police> selectBySort() {
		return policeMapper.selectBySort();
	}

}