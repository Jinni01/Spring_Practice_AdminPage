<%--
  Created by IntelliJ IDEA.
  User: suj0324
  Date: 2021-06-21
  Time: 오후 5:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="/resources/css/info-admin.css">
    <title>관리자관리</title>

    <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
  </head>
  <body>
    <jsp:include page="template-menu.jsp" flush="false" />
    <div class="box-main">
      <div class="box-title">관리자 등록/관리</div>
      <div class="box-view">
        <div class="box-submenu">
          <div class="box-submenu-title"><div class="submenu-title-text">관리자 관리</div></div>
          <div class="box-submenu-button"><a href="${context_path}/user/manage-admin">관리자 조회</a></div>
          <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="box-submenu-button"><a href="${context_path}/user/register-admin">신규등록</a></div>
          </sec:authorize>
        </div>
        <div class="box-form">
          <div class="box-table">
            <table>
              <colgroup>
                <col width="20%"/>
                <col width="80%"/>
              </colgroup>
              <thead>
              </thead>
              <tbody>
              <tr>
                <td>아이디</td>
                <td>${user.userID}</td>
              </tr>
              <tr>
                <td>성명</td>
                <td>${user.userName}</td>
              </tr>
              <tr>
                <td>소속</td>
                <td>${user.userDivision}</td>
              </tr>
              <tr>
                <td>연락처</td>
                <td>${user.userPhone}</td>
              </tr>
              <tr>
                <td>관리자</td>
                <td>
                  <c:choose>
                    <c:when test="${user.userSuper==true}">
                      마스터관리자
                    </c:when>
                    <c:otherwise>
                      일반관리자
                    </c:otherwise>
                  </c:choose>
                </td>
              </tr>
              <tr>
                <td>이미지</td>
                <td><a href="/user/info/download-image?userNo=${user.userNo}"><img src="/resources/upload/${user.userImage}"></a></td>
              </tr>
              </tbody>
            </table>
            <div class="box-row">
              <div class="box-tool">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                  <div><a href="${context_path}/user/delete?userNo=${user.userNo}">삭제</a></div>
                  <div><a href="${context_path}/user/modify-admin?userNo=${user.userNo}&prevUrl=${prevUrl}">수정</a></div>
                </sec:authorize>
                <div><a href="${context_path}/user/manage-admin">확인</a></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <script  type="text/javascript">
    </script>
  </body>
</html>
