package com.chinaxaxt.xtzncms.service.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chinaxaxt.xtzncms.common.utils.tools.UUIDUtil;
import com.chinaxaxt.xtzncms.entity.biz.Info;
import com.chinaxaxt.xtzncms.mapper.biz.InfoMapper;
import com.chinaxaxt.xtzncms.service.biz.InfoService;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    InfoMapper infoMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(Info info){
    	info.setUid(UUIDUtil.getRandom32BeginTimePK());
    	info.setCreateTime(new Date());
    	info.setHitCount(0);
    	info.setApplaudCount(0);
        return infoMapper.insert(info);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByUid(String uid){
        return infoMapper.deleteByUid(uid);
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public int update(Info info){
//        return infoMapper.update(info);
//    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<Info> select(Info info){
        return infoMapper.select(info);
    }
    
    @Override
	public Info selectInfoByUid(String uid) {
		return infoMapper.selectInfoByUid(uid);
	}

	@Override
	public List<Info> selectInfoByTitle(String title) {
		return infoMapper.selectInfoByTitle(title);
	}

	@Override
	public List<Info> selectInfoByRemark(String remark) {
		return infoMapper.selectInfoByRemark(remark);
	}

	@Override
	public List<Info> selectInfoByAuthor(String author) {
		return infoMapper.selectInfoByAuthor(author);
	}

}