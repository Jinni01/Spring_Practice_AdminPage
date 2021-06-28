package com.midasin.spr.user.dao;

import com.midasin.spr.user.User;
import com.midasin.spr.pagination.Criteria;

import java.util.List;

public interface IUserDAO {
    int userInsert(User user);
    User userSelect(User user);
    User userSelectByNo(int no);
    int userUpdate(User user);
    int userDelete(User user);
    int userDeleteByNo(int no);
    int userCount(Criteria criteria);
    List<User> userListup(Criteria criteria);
}
