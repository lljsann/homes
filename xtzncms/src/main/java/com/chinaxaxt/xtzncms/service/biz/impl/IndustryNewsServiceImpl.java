package com.chinaxaxt.xtzncms.service.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chinaxaxt.xtzncms.common.utils.tools.UUIDUtil;
import com.chinaxaxt.xtzncms.entity.biz.IndustryNews;
import com.chinaxaxt.xtzncms.mapper.biz.IndustryNewsMapper;
import com.chinaxaxt.xtzncms.service.biz.IndustryNewsService;

/**
 * 
 * @author 吴佳涛
 * 2018-6-25
 */
@Service
public class IndustryNewsServiceImpl implements IndustryNewsService {

    @Autowired
    IndustryNewsMapper industryNewsMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(IndustryNews industryNews){
    	industryNews.setUid(UUIDUtil.getRandom32BeginTimePK());
    	industryNews.setCreateTime(new Date());
    	industryNews.setHitCount(0);
    	industryNews.setApplaudCount(0);
        return industryNewsMapper.insert(industryNews);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByUid(String uid){
        return industryNewsMapper.deleteByUid(uid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int update(String context,String uid){
        return industryNewsMapper.updateContext(context,uid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<IndustryNews> select(IndustryNews industryNews){
        return industryNewsMapper.select(industryNews);
    }

	@Override
	public IndustryNews selectNewsByUid(String uid) {
		return industryNewsMapper.selectNewsByUid(uid);
	}

	@Override
	public List<IndustryNews> selectNewsByTitle(String title) {
		return industryNewsMapper.selectNewsByTitle(title);
	}

	@Override
	public List<IndustryNews> selectNewsByRemark(String remark) {
		return industryNewsMapper.selectNewsByRemark(remark);
	}

	@Override
	public List<IndustryNews> selectNewsByAuthor(String author) {
		return industryNewsMapper.selectNewsByAuthor(author);
	}
}