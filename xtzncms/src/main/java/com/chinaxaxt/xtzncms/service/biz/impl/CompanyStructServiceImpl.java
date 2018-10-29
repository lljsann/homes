package com.chinaxaxt.xtzncms.service.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chinaxaxt.xtzncms.common.utils.tools.UUIDUtil;
import com.chinaxaxt.xtzncms.entity.biz.CompanyStruct;
import com.chinaxaxt.xtzncms.mapper.biz.CompanyStructMapper;
import com.chinaxaxt.xtzncms.service.biz.CompanyStructService;

/**
 * 
 * @author 吴佳涛
 * 2018-7-4
 */
@Service
public class CompanyStructServiceImpl implements CompanyStructService {

    @Autowired
    CompanyStructMapper companyStructMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(CompanyStruct companyStruct){
    	companyStruct.setUid(UUIDUtil.getRandom32BeginTimePK());
    	companyStruct.setCreateTime(new Date());
        return companyStructMapper.insert(companyStruct);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByUid(String uid){
        return companyStructMapper.deleteByUid(uid);
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public int update(CompanyStruct companyStruct){
//        return companyStructMapper.update(companyStruct);
//    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<CompanyStruct> select(CompanyStruct companyStruct){
        return companyStructMapper.select(companyStruct);
    }
	
	@Override
	public CompanyStruct selectCompanyStructByUid(String uid) {
		return companyStructMapper.selectCompanyStructByUid(uid);
	}


}