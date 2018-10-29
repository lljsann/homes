package com.chinaxaxt.xtzncms.service.platform.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.chinaxaxt.xtzncms.common.utils.encrypt.Jwt;
import com.chinaxaxt.xtzncms.common.utils.tools.PasswordUtil;
import com.chinaxaxt.xtzncms.entity.platform.User;
import com.chinaxaxt.xtzncms.mapper.platform.UserMapper;
import com.chinaxaxt.xtzncms.service.platform.UserService;

/**
 * 
 * @author 吴佳涛
 * 2018-6-25
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public User findOneByAccount(String account) {
		return userMapper.findOneByAccount(account);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int resetPassword(String uid, String password) {
		return this.userMapper.updatePasswordByUid(uid, PasswordUtil.entrytPassword("SHA-1", password, null, 2));
	}

	@Override
	public User findOneByUserId(String userId) {
		return this.userMapper.findOneByUserId(userId);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public JSONObject login(User user,Map<String,Object> payload) {
		JSONObject data=new JSONObject();
		data.put("token", Jwt.createToken(payload));
		data.put("account", user.getAccount());
		return data;
	}

}