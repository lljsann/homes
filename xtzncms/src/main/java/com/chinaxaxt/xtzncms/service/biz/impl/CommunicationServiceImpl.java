package com.chinaxaxt.xtzncms.service.biz.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.chinaxaxt.xtzncms.common.utils.tools.UUIDUtil;
import com.chinaxaxt.xtzncms.entity.biz.Communication;
import com.chinaxaxt.xtzncms.mapper.biz.CommunicationMapper;
import com.chinaxaxt.xtzncms.service.biz.CommunicationService;

/**
 * 
 * @author 吴佳涛
 * 2018-6-27
 */
@Service
public class CommunicationServiceImpl implements CommunicationService {

    @Autowired
    CommunicationMapper communicationMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(Communication communication){
        return communicationMapper.insert(communication);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int update(Communication communication){
        return communicationMapper.update(communication);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<Communication> select(Communication communication){
        return communicationMapper.select(communication);
    }
    
    @Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String uploadLogo(MultipartFile file, String uid) {
		String originalFilename = file.getOriginalFilename();  
        String newfileName=UUIDUtil.getRandom32BeginTimePK()+originalFilename.substring(originalFilename.lastIndexOf("."));  
        File newfile=new File("/usr/java/xtzncms/docs/images/"+newfileName);  
        try {
			file.transferTo(newfile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
        this.communicationMapper.updateLogo(newfileName, uid);
        return newfileName;
	}
    
    @Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String uploadQrCode(MultipartFile file, String uid) {
		String originalFilename = file.getOriginalFilename();  
        String newfileName=UUIDUtil.getRandom32BeginTimePK()+originalFilename.substring(originalFilename.lastIndexOf("."));  
        File newfile=new File("/usr/java/xtzncms/docs/images/"+newfileName);  
        try {
			file.transferTo(newfile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
        this.communicationMapper.updateQrCode(newfileName, uid);
        return newfileName;
	}

}