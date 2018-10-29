package com.chinaxaxt.xtzncms.service.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chinaxaxt.xtzncms.common.utils.tools.UUIDUtil;
import com.chinaxaxt.xtzncms.entity.biz.CompanyProfile;
import com.chinaxaxt.xtzncms.mapper.biz.CompanyProfileMapper;
import com.chinaxaxt.xtzncms.service.biz.CompanyProfileService;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Service
public class CompanyProfileServiceImpl implements CompanyProfileService {

    @Autowired
    CompanyProfileMapper companyProfileMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(CompanyProfile companyProfile){
    	companyProfile.setUid(UUIDUtil.getRandom32BeginTimePK());
    	companyProfile.setCreateTime(new Date());
        return companyProfileMapper.insert(companyProfile);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByUid(String uid){
        return companyProfileMapper.deleteByUid(uid);
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public int update(CompanyProfile companyProfile){
//        return companyProfileMapper.update(companyProfile);
//    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<CompanyProfile> select(CompanyProfile companyProfile){
        return companyProfileMapper.select(companyProfile);
    }

	@Override
	public CompanyProfile selectProfileByUid(String uid) {
		return companyProfileMapper.selectProfileByUid(uid);
	}

}