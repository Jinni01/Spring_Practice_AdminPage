<%--
  Created by IntelliJ IDEA.
  User: suj0324
  Date: 2021-06-21
  Time: 오후 5:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="/resources/css/template-menu.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css">
  </head>
  <body>
    <div class="box-outer">
      <div class="box-menu">
        <div class="box-inner">
          <a href="${context_path}/user/manage-admin" class="button-menu">관리자관리</a>
        </div>
        <%--<div class="box-inner">
          <a href="${context_path}/user/manage-recruit" class="button-menu">채용관리</a>
        </div>--%>
        <div class="box-inner">
          <a href="${context_path}/user/logout" class="button-menu">로그아웃</a>
        </div>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
          <div class="box-inner">
            <a class="grade-master">마스터 관리자</a>
          </div>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_USER')">
          <div class="box-inner">
            <a class="grade-normal">일반 관리자</a>
          </div>
        </sec:authorize>
      </div>
    </div>
  </body>
</html>
