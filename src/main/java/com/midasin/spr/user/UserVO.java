package com.midasin.spr.user;

public class UserVO {
    private int userNo;
    private String userID;
    private String userPW;
    private String userName;
    private String userPhone;
    private String userDivision;
    private String userRegisterDate;
    private String userImage;
    private boolean userSuper;

    public UserVO(){}

    public int getUserNo() {
        return userNo;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserPW() {
        return userPW;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserDivision(){
        return userDivision;
    }

    public String getUserRegisterDate() {
        return userRegisterDate;
    }

    public boolean getUserSuper(){
        return userSuper;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserPW(String userPW) {
        this.userPW = userPW;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserDivision(String userDivision){
        this.userDivision = userDivision;
    }

    public void setUserSuper(boolean userSuper) {
        this.userSuper = userSuper;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public void setUserRegisterDate(String userRegisterDate) {
        this.userRegisterDate = userRegisterDate;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
