package com.chinaxaxt.xtzncms.common.utils.tools;

import com.chinaxaxt.xtzncms.common.utils.encrypt.Digests;

/**
 * 密码加密工具
 * @author 吴佳涛
 *
 */
public class PasswordUtil {

    public static final String ALGORITHM_SHA1 = "SHA-1";
    public static final String ALGORITHM_MD5 = "MD5";
    public static final int HASH_INTERATIONS = 2;
    public static final int SALT_SIZE=4;
    
    /**
     * 设定安全的密码，生成随机的salt并经过2次 sha-1 hash
     */
//    public static User entryptPassword(User user) {
//        byte[] salt = Digests.generateSalt(SALT_SIZE);
//        user.setSalt(Encodes.encodeHex(salt));
//        byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, HASH_INTERATIONS);
//        user.setPassword(Encodes.encodeHex(hashPassword));
//        return user;
//    }
//    
    
    public static String entrytPassword(String type,String password,String salt,int time) {
    	if(ALGORITHM_SHA1.equals(type)) {
    		if(salt==null) {
    			return new String(Digests.sha1(password.getBytes(), time));
    		} else {
    			return new String(Digests.sha1(password.getBytes(),salt.getBytes(), time));
    		}
    	}else {
    		if(salt==null) {
    			return new String(Digests.md5(password.getBytes(), time));
    		} else {
    			return new String(Digests.md5(password.getBytes(),salt.getBytes(), time));
    		}
    	}
    }

}
