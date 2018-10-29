package com.chinaxaxt.xtzncms.controller.platform;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chinaxaxt.xtzncms.common.command.UserCommand;
import com.chinaxaxt.xtzncms.common.utils.tools.PasswordUtil;
import com.chinaxaxt.xtzncms.common.utils.web.RequestUtil;
import com.chinaxaxt.xtzncms.common.vo.ResponseVO;
import com.chinaxaxt.xtzncms.entity.platform.User;
import com.chinaxaxt.xtzncms.service.platform.UserService;

/**
 * 
 * @author 吴佳涛
 *
 */
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;
	
	/**
	 * 管理员修改密码
	 * @param uc
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updatePwd", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseVO updatePwd(@RequestBody UserCommand uc, HttpServletRequest request) {
		String et = "SHA-1";
		Integer num = 2;
		User user = this.userService.findOneByUserId((String) RequestUtil.getTokenDataByKey("uid"));
		if (user.getPassword().equals(PasswordUtil.entrytPassword(et, uc.getPassword(), null, num))) {
			return ResponseVO.success( userService.resetPassword(user.getUid(), uc.getNewPassword()));
		}
		return null;
	}
	
}
