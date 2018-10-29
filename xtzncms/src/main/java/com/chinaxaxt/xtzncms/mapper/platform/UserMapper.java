package com.chinaxaxt.xtzncms.mapper.platform;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.chinaxaxt.xtzncms.entity.platform.User;

/**
 * 
 * @author 吴佳涛
 * 2018-6-25
 */
@Repository
public interface UserMapper {

	/**
	 * 登陆时查询用户信息
	 * @param account
	 * @return
	 */
	@Select("select `uid`, `name`, `account`, `password`, `post` from t_user")
    @ResultMap("user")
	User findOneByAccount(@Param("account")String account);
	
	/**
	 * 根据id修改密码
	 * @param uid
	 * @param password
	 * @return
	 */
	@Update("update `t_user` set `password`=#{password} where `uid`=#{uid} ")
    int updatePasswordByUid(@Param("uid")String uid,@Param("password")String password);
	
	/**
	 * 根据id查询用户信息
	 * @param userId
	 * @return
	 */
	@Select("select `uid` ,`name` ,`account`,`password` from t_user where uid=#{userId}")
    @ResultMap("user")
	User findOneByUserId(@Param("userId")String userId);

}