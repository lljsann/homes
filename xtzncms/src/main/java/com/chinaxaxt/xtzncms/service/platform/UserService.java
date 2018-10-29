package com.chinaxaxt.xtzncms.service.platform;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.chinaxaxt.xtzncms.entity.platform.User;

/**
 * 
 * @author 吴佳涛
 * 2018-6-25
 */
public interface UserService {

	/**
	 * 查询用户信息
	 * @param login
	 * @return
	 */
	User findOneByAccount(String login);
	
	/**
	 * 返回用户信息
	 * @param user
	 * @param payload
	 * @return
	 */
	JSONObject login(User user,Map<String,Object> payload);
	
	/**
	 * 修改密码
	 * @param uid
	 * @param password
	 * @return
	 */
	int resetPassword(String uid,String password);

	/**
	 * 根据id查询用户
	 * @param userId
	 * @return
	 */
	User findOneByUserId(String userId);
	


}