package com.chinaxaxt.xtzncms.entity.platform;

import java.io.Serializable;

/**
 * 用户实体类
 * @author 吴佳涛
 * 2018-6-25
 */
public class User implements Serializable{

	private static final long serialVersionUID = -7062601116972035697L;

	/**
	 *  用户UID
	 */
    private String uid;
    /**
     *  姓名
     */
    private String name;
    
    /**
     *  登录账号
     */
    private String account;
    /**
     *  登录密码
     */
    private String password;
    /**
     *  职务职位
     */
    private String post;
    
	public String getUid() {
        return uid;
    }

    public User setUid(String uid) {
        this.uid = uid;
        return this;
    }
    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }
    public String getAccount() {
        return account;
    }

    public User setAccount(String account) {
        this.account = account;
        return this;
    }
    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
    public String getPost() {
        return post;
    }

    public User setPost(String post) {
        this.post = post;
        return this;
    }

}
