package com.chinaxaxt.xtzncms.common.command;

import java.io.Serializable;

/**
 * @author 陕西协通智能科技有限公司
 *
 */
public class UserCommand implements Serializable {

	private static final long serialVersionUID = 5057570256133640356L;

	private String userName;

	private String email;

	private String password;

	private String newPassword;

	private String passwordAgain;

	private String smsCode;
	
	private String imageCode;
	
	private boolean remenberMe;
	

	public boolean isRemenberMe() {
		return remenberMe;
	}

	public void setRemenberMe(boolean remenberMe) {
		this.remenberMe = remenberMe;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

}
