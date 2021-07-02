package com.midasin.spr.user.dao;

import com.midasin.spr.user.UserVO;

@MyMapper
public interface UserMapper {
    public int insertUser(UserVO user);
    public UserVO selectUser(UserVO user);
    public UserVO selectUserByNo(int userNo);
    public int updateUser(UserVO user);
    public int deleteUserByNo(int userNo);
}
