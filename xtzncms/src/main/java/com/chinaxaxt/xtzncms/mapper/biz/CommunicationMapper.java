package com.chinaxaxt.xtzncms.mapper.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.chinaxaxt.xtzncms.entity.biz.Communication;

/**
 * 
 * @author 吴佳涛
 * 2018-6-27
 */
@Repository
public interface CommunicationMapper {

	/**
	 * 增加
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
     * 修改LOGO
     * @param logo
     * @param uid
     * @return
     */
    @Update("update `t_communication` set `logo`=#{logo} where `uid`=#{uid} ")
    int updateLogo(@Param("logo")String logo,@Param("uid")String uid);
    
    /**
     * 修改二维码
     * @param qrcode
     * @param uid
     * @return
     */
    @Update("update `t_communication` set `qr_code`=#{qrcode} where `uid`=#{uid} ")
    int updateQrCode(@Param("qrcode")String qrcode,@Param("uid")String uid);
}