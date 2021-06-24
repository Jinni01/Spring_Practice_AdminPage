package com.midasin.spr.user.dao;

import com.midasin.spr.user.User;
import com.midasin.spr.user.pagination.Criteria;

import java.util.List;

public interface IUserDAO {
    int userInsert(User user);
    User userSelect(User user);
    int userUpdate(User user);
    int userDelete(User user);
    int userCount(Criteria criteria);
    List<User> userListup(Criteria criteria);
}
