package com.chinaxaxt.xtzncms.common.command;

import com.chinaxaxt.xtzncms.common.vo.ResponseVO;

/**
 * 
 * @author 陕西协通智能科技有限公司
 *
 */
public class WebStatus {
	
	
	public static final ResponseVO UNAUTHORIZED=new ResponseVO(801,"未认证");
	
	public static final ResponseVO UNKNOWUSER=new ResponseVO(802, "用户不存在");
	
	public static final ResponseVO ILLEGAL_OPERATION=new ResponseVO(803, "非法操作");
	
	public static final ResponseVO INCORRECT_CREDENTIALS=new ResponseVO(804, "用户名密码不匹配");
	
	public static final ResponseVO OLD_PASSWORD_ERROR=new ResponseVO(805, "旧密码错误");
	
	public static final ResponseVO LOCKEDACCOUNT=new ResponseVO(806, "账号已被锁定，请联系管理员解锁");
	
	public static final ResponseVO EXPIRED=new ResponseVO(807, "登陆失效,凭证已过期");
	
	public static final ResponseVO SERVEREXCEPTION=new ResponseVO(808, "服务端异常");
	
	
	//808服务端未知异常 809解析异常
	
	public static final ResponseVO PARAMSERROR=new ResponseVO(810, "参数错误");
	
	public static final ResponseVO NOPERMISSION=new ResponseVO(813, "无权限");
	
	//850为服务端异常,内容自定义，可以调用ResponseVO.error
	//public static final ResponseVO DIYEXCEPTION=new ResponseVO(850, "自定义异常");
	
	
	
}
