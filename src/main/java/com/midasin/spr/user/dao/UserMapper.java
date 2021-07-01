package com.midasin.spr.user.dao;

import com.midasin.spr.user.User;

@MyMapper
public interface UserMapper {
    public int insertUser(User user);
    public User selectUser(User user);
    public User selectUserByNo(int userNo);
    public int updateUser(User user);
    public int deleteUserByNo(int userNo);
}
