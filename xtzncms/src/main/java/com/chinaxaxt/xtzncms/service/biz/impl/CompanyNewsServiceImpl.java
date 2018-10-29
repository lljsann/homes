package com.chinaxaxt.xtzncms.service.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chinaxaxt.xtzncms.common.utils.tools.UUIDUtil;
import com.chinaxaxt.xtzncms.entity.biz.CompanyNews;
import com.chinaxaxt.xtzncms.mapper.biz.CompanyNewsMapper;
import com.chinaxaxt.xtzncms.service.biz.CompanyNewsService;

/**
 * 
 * @author 吴佳涛
 * 2018-6-25
 */
@Service
public class CompanyNewsServiceImpl implements CompanyNewsService {

    @Autowired
    CompanyNewsMapper companyNewsMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(CompanyNews companyNews){
    	companyNews.setUid(UUIDUtil.getRandom32BeginTimePK());
    	companyNews.setHitCount(0);
    	companyNews.setApplaudCount(0);
        return companyNewsMapper.insert(companyNews);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByUid(String uid){
        return companyNewsMapper.deleteByUid(uid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int update(String context,String uid){
        return companyNewsMapper.updateContext(context,uid);
    }

    @Override
    public List<CompanyNews> select(CompanyNews companyNews){
        return companyNewsMapper.selectCom(companyNews);
    }
    
    @Override
    public List<CompanyNews> selectAll(CompanyNews companyNews){
        return companyNewsMapper.selectAll(companyNews);
    }
    
    @Override
	public List<CompanyNews> selectByTime() {
		return companyNewsMapper.selectNewsByTime();
	}

	@Override
	public List<CompanyNews> selectNewsByTitle(String title) {
		return companyNewsMapper.selectNewsByTitle(title);
	}

	@Override
	public List<CompanyNews> selectNewsByRemark(String remark) {
		return companyNewsMapper.selectNewsByRemark(remark);
	}

	@Override
	public List<CompanyNews> selectNewsByAuthor(String author) {
		return companyNewsMapper.selectNewsByAuthor(author);
	}

	@Override
	public CompanyNews selectNewsByUid(String uid) {
		return companyNewsMapper.selectNewsByUid(uid);
	}

	@Override
	public int updateHitCount(String uid) {
		return companyNewsMapper.updateHitCount(uid);
	}

	@Override
	public int updateApplaudCount(String uid) {
		return companyNewsMapper.updateApplaudCount(uid);
	}

}