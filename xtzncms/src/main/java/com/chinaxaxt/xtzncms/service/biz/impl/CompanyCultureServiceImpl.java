package com.chinaxaxt.xtzncms.service.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chinaxaxt.xtzncms.common.utils.tools.UUIDUtil;
import com.chinaxaxt.xtzncms.entity.biz.CompanyCulture;
import com.chinaxaxt.xtzncms.mapper.biz.CompanyCultureMapper;
import com.chinaxaxt.xtzncms.service.biz.CompanyCultureService;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Service
public class CompanyCultureServiceImpl implements CompanyCultureService {

    @Autowired
    CompanyCultureMapper companyCultureMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(CompanyCulture companyCulture){
    	companyCulture.setUid(UUIDUtil.getRandom32BeginTimePK());
        return companyCultureMapper.insert(companyCulture);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByUid(String uid){
        return companyCultureMapper.deleteByUid(uid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int update(String detail,String uid){
        return companyCultureMapper.updateDetail(detail,uid);
    }

    @Override
    public List<CompanyCulture> select(CompanyCulture companyCulture){
        return companyCultureMapper.selectAll(companyCulture);
    }
    
    @Override
	public List<CompanyCulture> selectCultureByName(String name) {
		return companyCultureMapper.selectCultureByName(name);
	}

	@Override
	public List<CompanyCulture> selectCultureByRemark(String remark) {
		return companyCultureMapper.selectCultureByRemark(remark);
	}

	@Override
	public List<CompanyCulture> selectCultureByType(String type) {
		return companyCultureMapper.selectCultureByType(type);
	}

	@Override
	public CompanyCulture selectCultureByUid(String uid) {
		return companyCultureMapper.selectCultureByUid(uid);
	}

	@Override
	public List<CompanyCulture> selectPro(String type) {
		return companyCultureMapper.selectPro(type);
	}

}