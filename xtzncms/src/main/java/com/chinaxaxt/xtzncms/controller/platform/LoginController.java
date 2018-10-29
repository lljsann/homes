package com.chinaxaxt.xtzncms.controller.platform;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.chinaxaxt.xtzncms.common.command.UserCommand;
import com.chinaxaxt.xtzncms.common.command.WebStatus;
import com.chinaxaxt.xtzncms.common.utils.tools.PasswordUtil;
import com.chinaxaxt.xtzncms.common.vo.ResponseVO;
import com.chinaxaxt.xtzncms.entity.platform.User;
import com.chinaxaxt.xtzncms.service.platform.UserService;

/**
 * 
 * @author 吴佳涛
 *
 */
@RequestMapping("login")
@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户登陆
	 * @param uc
	 * @param request
	 * @return
	 */
	@RequestMapping(value="pclogin",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVO pclogin(@RequestBody UserCommand uc,HttpServletRequest request) {
		String login=uc.getUserName();
		String password=uc.getPassword();
		if("".equals(login)||login==null||password==null||"".equals(password)) {
			return WebStatus.ILLEGAL_OPERATION;
		}
		User user=this.userService.findOneByAccount(login);
		if(user==null) {
			return WebStatus.UNKNOWUSER;
		}
		String pwd= PasswordUtil.entrytPassword("SHA-1", password, null, 2);
		if(pwd.equals(user.getPassword())) {
			Map<String, Object> payload = new HashMap<String, Object>(0);
			//用户id
			payload.put("uid", user.getUid());
			//用户账号
			payload.put("account",user.getAccount());
			JSONObject data=this.userService.login(user,payload);
			request.setAttribute("tokendata", payload);
			return ResponseVO.success("登录成功", data);
		}else {
		}
		return null;
	}
	
	
}
