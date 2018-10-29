package com.chinaxaxt.xtzncms.service.biz.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.chinaxaxt.xtzncms.common.utils.tools.UUIDUtil;
import com.chinaxaxt.xtzncms.entity.biz.Community;
import com.chinaxaxt.xtzncms.mapper.biz.CommunityMapper;
import com.chinaxaxt.xtzncms.service.biz.CommunityService;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    CommunityMapper communityMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(Community community){
    	community.setUid(UUIDUtil.getRandom32BeginTimePK());
    	community.setCreateTime(new Date());
        return communityMapper.insert(community);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByUid(String uid){
        return communityMapper.deleteByUid(uid);
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public int update(Community community){
//        return communityMapper.update(community);
//    }

    @Override
    public List<Community> select(Community community){
        return communityMapper.select(community);
    }
    
    @Override
	public List<Community> selectCommunityByName(String name) {
		return communityMapper.selectCommunityByName(name);
	}

	@Override
	public List<Community> selectCommunityBySynopsis(String synopsis) {
		return communityMapper.selectCommunityBySynopsis(synopsis);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<String> multiUpdate(MultipartFile[] files) {
		List<String> list = new ArrayList<String>();
		File dir = new File("/usr/java/xtzncms/docs/images/");
		if (files != null && files.length != 0) {
			for (MultipartFile fi : files) {
				String fileName = fi.getOriginalFilename();
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				String fileTransName = UUIDUtil.getRandom32BeginTimePK();
				File serverfile = new File(dir + "/" + fileTransName + suffix);
				// 判断文件父目录是否存在
				if (!serverfile.getParentFile().exists()) { 
					serverfile.getParentFile().mkdir();
				}
				try {
					fi.transferTo(serverfile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				String imageUrl = fileTransName + suffix;
				list.add(imageUrl);
			}
		}
		return list;
	}
	
	@Override
	public Community selectCommunityByUid(String uid) {
		return communityMapper.selectCommunityByUid(uid);
	}

	@Override
	public List<Community> selectBySort() {
		return communityMapper.selectBySort();
	}

}