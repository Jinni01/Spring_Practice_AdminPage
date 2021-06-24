<%--
  Created by IntelliJ IDEA.
  User: suj0324
  Date: 2021-06-21
  Time: 오후 5:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css">
    <title>관리자 로그인</title>
  </head>
  <body>
  <div class="box-outer">
    <div class="box-login">
      <img src="${context_path}/resources/admin.svg" class="img-logo">
      <form action="${context_path}/user/login" method="post">
        <table>
          <tr>
            <td><label><input type="text" name="userID" class="input-input" placeholder="ID"></label></td>
          </tr>
          <tr>
            <td><label><input type="password" name="userPW" class="input-input" placeholder="PASSWORD"></label></td>
          </tr>
          <tr>
            <td><input type="submit" value="로그인" class="input-button"></td>
          </tr>
        </table>
      </form>
    </div>
  </div>
  </body>
</html>
