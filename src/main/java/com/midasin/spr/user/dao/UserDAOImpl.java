package com.midasin.spr.user.dao;

import com.midasin.spr.user.UserVO;
import com.midasin.spr.pagination.Criteria;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDAOImpl implements IUserDAO{

    final private String driver = "com.mysql.cj.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost:3306/board";
    final private String id = "root";
    final private String pw = "qwer1234";

    private DriverManagerDataSource dataSource;
    private JdbcTemplate template;

    public UserDAOImpl(){
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(id);
        dataSource.setPassword(pw);

        template = new JdbcTemplate();
        template.setDataSource(dataSource);
    }

    @Override
    public int userInsert(UserVO user) {
        final String q = "INSERT INTO user (userID, userPW, userName, userPhone, userDivision, userRegisterDate, userSuper) values(?,?,?,?,?,curdate(),?)";
        return template.update(q, new PreparedStatementSetter(){

            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, user.getUserID());
                preparedStatement.setString(2, user.getUserPW());
                preparedStatement.setString(3, user.getUserName());
                preparedStatement.setString(4, user.getUserPhone());
                preparedStatement.setString(5, user.getUserDivision());
                preparedStatement.setBoolean(6, user.getUserSuper());
            }
        });
    }

    @Override
    public UserVO userSelect(UserVO user) {
        final String q = "SELECT * FROM user WHERE userID = ? AND userPW = ?";

        List<UserVO> users = null;
        users = template.query(q, new Object[]{user.getUserID(), user.getUserPW()},  new RowMapper<UserVO>() {
            @Override
            public UserVO mapRow(ResultSet resultSet, int i) throws SQLException {
                UserVO user = new UserVO();
                user.setUserNo(resultSet.getInt("userNo"));
                user.setUserID(resultSet.getString("userID"));
                user.setUserPW(resultSet.getString("userPW"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPhone(resultSet.getString("userPhone"));
                user.setUserDivision(resultSet.getString("userDivision"));
                user.setUserRegisterDate(resultSet.getString("userRegisterDate"));
                user.setUserSuper(resultSet.getBoolean("userSuper"));
                return user;
            }
        });
        if(users.isEmpty()) return null;

        return users.get(0);
    }

    @Override
    public UserVO userSelectByNo(int no) {
        final String q = "SELECT * FROM user WHERE userNo = ?";

        List<UserVO> users = null;
        users = template.query(q, new Object[]{no},  new RowMapper<UserVO>() {
            @Override
            public UserVO mapRow(ResultSet resultSet, int i) throws SQLException {
                UserVO user = new UserVO();
                user.setUserNo(resultSet.getInt("userNo"));
                user.setUserID(resultSet.getString("userID"));
                user.setUserPW(resultSet.getString("userPW"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPhone(resultSet.getString("userPhone"));
                user.setUserDivision(resultSet.getString("userDivision"));
                user.setUserRegisterDate(resultSet.getString("userRegisterDate"));
                user.setUserSuper(resultSet.getBoolean("userSuper"));
                return user;
            }
        });
        if(users.isEmpty()) return null;

        return users.get(0);
    }

    @Override
    public int userUpdate(UserVO user) {
        final String q = "Update user SET userID = ?, userPW = ?, userName = ?, userPhone = ?, userDivision = ?, userSuper = ? WHERE userNo = ?";
        return template.update(q, new PreparedStatementSetter(){

            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, user.getUserID());
                preparedStatement.setString(2, user.getUserPW());
                preparedStatement.setString(3, user.getUserName());
                preparedStatement.setString(4, user.getUserPhone());
                preparedStatement.setString(5, user.getUserDivision());
                preparedStatement.setBoolean(6, user.getUserSuper());
                preparedStatement.setInt(7, user.getUserNo());
            }
        });
    }

    @Override
    public int userDelete(UserVO user) {
        final String q = "DELETE user WHERE userID = ? AND userPW = ?";
        return 0;
    }

    @Override
    public int userDeleteByNo(int no) {
        final String q = "DELETE FROM user WHERE userNo = ?";
        return template.update(q, new PreparedStatementSetter(){

            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, no);
            }
        });
    }

    @Override
    public int userCount(Criteria criteria) {
        final String q = "SELECT COUNT(*) FROM user WHERE" + getSearchOptionString(criteria);
        return template.queryForObject(q, Integer.class);
    }

    @Override
    public List<UserVO> userListup(Criteria criteria) {
        final String q = "SELECT * FROM user WHERE" + getSearchOptionString(criteria) + " ORDER BY userNo desc LIMIT ?, ?";

        return template.query(q, new Object[]{criteria.getPageStart(), criteria.getPerPageNum()} , new RowMapper<UserVO>() {
            @Override
            public UserVO mapRow(ResultSet resultSet, int i) throws SQLException {
                UserVO user = new UserVO();
                user.setUserNo(resultSet.getInt("userNo"));
                user.setUserID(resultSet.getString("userID"));
                user.setUserPW(resultSet.getString("userPW"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPhone(resultSet.getString("userPhone"));
                user.setUserDivision(resultSet.getString("userDivision"));
                user.setUserRegisterDate(resultSet.getString("userRegisterDate"));
                user.setUserSuper(resultSet.getBoolean("userSuper"));
                return user;
            }
        });
    }

    private String getSearchOptionString(Criteria criteria){
        String optionStr = "";
        switch (criteria.getSearchType()) {
            case "all":
                optionStr = " userID like '%" + criteria.getKeyWorld() + "%'" + " OR userName like '%" + criteria.getKeyWorld() + "%'" + " OR userDivision like '%" + criteria.getKeyWorld() + "%'";
                break;
            case "id":
                optionStr = " userID like '%" + criteria.getKeyWorld() + "%'";
                break;
            case "name":
                optionStr = " userName like '%" + criteria.getKeyWorld() + "%'";
                break;
            case "division":
                optionStr = " userDivision like '%" + criteria.getKeyWorld() + "%'";
                break;
        }

        return optionStr;
    }
}
