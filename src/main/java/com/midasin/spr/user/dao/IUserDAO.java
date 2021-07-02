package com.midasin.spr.user.dao;

import com.midasin.spr.user.UserVO;
import com.midasin.spr.pagination.Criteria;

import java.util.List;

public interface IUserDAO {
    int userInsert(UserVO user);
    UserVO userSelect(UserVO user);
    UserVO userSelectByNo(int no);
    int userUpdate(UserVO user);
    int userDelete(UserVO user);
    int userDeleteByNo(int no);
    int userCount(Criteria criteria);
    List<UserVO> userListup(Criteria criteria);
}
