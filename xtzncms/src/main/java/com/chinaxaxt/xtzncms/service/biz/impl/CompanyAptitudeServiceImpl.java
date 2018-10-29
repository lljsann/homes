package com.chinaxaxt.xtzncms.service.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chinaxaxt.xtzncms.common.utils.tools.UUIDUtil;
import com.chinaxaxt.xtzncms.entity.biz.CompanyAptitude;
import com.chinaxaxt.xtzncms.mapper.biz.CompanyAptitudeMapper;
import com.chinaxaxt.xtzncms.service.biz.CompanyAptitudeService;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Service
public class CompanyAptitudeServiceImpl implements CompanyAptitudeService {

    @Autowired
    CompanyAptitudeMapper companyAptitudeMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(CompanyAptitude companyAptitude){
    	companyAptitude.setUid(UUIDUtil.getRandom32BeginTimePK());
    	companyAptitude.setCreateTime(new Date());
        return companyAptitudeMapper.insert(companyAptitude);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByUid(String uid){
        return companyAptitudeMapper.deleteByUid(uid);
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public int update(CompanyAptitude companyAptitude){
//        return companyAptitudeMapper.update(companyAptitude);
//    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<CompanyAptitude> select(CompanyAptitude companyAptitude){
        return companyAptitudeMapper.select(companyAptitude);
    }
    
    @Override
	public List<CompanyAptitude> selectAptitudeByTitle(String title) {
		return companyAptitudeMapper.selectAptitudeByTitle(title);
	}

	@Override
	public List<CompanyAptitude> selectAptitudeByDetail(String detail) {
		return companyAptitudeMapper.selectAptitudeByDetail(detail);
	}

	@Override
	public List<CompanyAptitude> selectAptitudeByType(String type) {
		return companyAptitudeMapper.selectAptitudeByType(type);
	}
	
	@Override
	public CompanyAptitude selectAptitudeByUid(String uid) {
		return companyAptitudeMapper.selectAptitudeByUid(uid);
	}

}