package com.chinaxaxt.xtzncms.service.biz;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.chinaxaxt.xtzncms.entity.biz.Communication;

/**
 * 
 * @author 吴佳涛
 * 2018-6-27
 */
public interface CommunicationService {

	/**
	 * 添加
	 * @param communication
	 * @return
	 */
    int insert(Communication communication);

    /**
     * 修改
     * @param communication
     * @return
     */
    int update(Communication communication);

    /**
     * 查询所有
     * @param communication
     * @return
     */
    List<Communication> select(Communication communication);
    
    /**
     * 上传LOGO
     * @param file
     * @param uid
     * @return
     */
    String uploadLogo(MultipartFile file, String uid);
    
    /**
     * 上传二维码
     * @param file
     * @param uid
     * @return
     */
    String uploadQrCode(MultipartFile file, String uid);
}