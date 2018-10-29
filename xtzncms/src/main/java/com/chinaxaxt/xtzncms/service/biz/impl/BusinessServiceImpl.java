package com.chinaxaxt.xtzncms.service.biz.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.chinaxaxt.xtzncms.common.utils.tools.UUIDUtil;
import com.chinaxaxt.xtzncms.entity.biz.Business;
import com.chinaxaxt.xtzncms.mapper.biz.BusinessMapper;
import com.chinaxaxt.xtzncms.service.biz.BusinessService;

/**
 * 
 * @author 吴佳涛
 * 2018-7-4
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    BusinessMapper businessMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(Business business){
    	business.setUid(UUIDUtil.getRandom32BeginTimePK());
    	business.setCreateTime(new Date());
        return businessMapper.insert(business);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByUid(String uid){
        return businessMapper.deleteByUid(uid);
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public int update(Business business){
//        return businessMapper.update(business);
//    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<Business> select(Business business){
        return businessMapper.select(business);
    }
    
    @Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String singleUpdate(MultipartFile file) {
		File dir = new File("/usr/java/xtzncms/docs/images/");
		if (!dir.exists()) {
			dir.mkdir();
		}
		String fileName = file.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		String fileTransName = UUIDUtil.getRandom32BeginTimePK();
		File serverfile = new File(dir + "/" + fileTransName + suffix);
		// 判断文件父目录是否存在
		if (!serverfile.getParentFile().exists()) { 
			serverfile.getParentFile().mkdir();
		}
		try {
			file.transferTo(serverfile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String imageUrl = fileTransName + suffix;
		return imageUrl;
	}
	
	@Override
	public Business selectBusinessByUid(String uid) {
		return businessMapper.selectBusinessByUid(uid);
	}

}