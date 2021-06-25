<%--
  Created by IntelliJ IDEA.
  User: suj0324
  Date: 2021-06-21
  Time: 오후 5:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="/resources/css/register-admin.css">
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
          <div class="box-submenu-button"><a href="${context_path}/user/register-admin">신규등록</a></div>
        </div>
        <div class="box-form">
          <form action="${context_path}/user/register" method="post" onsubmit="return submitCheck()">
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
                  <td><label><input type="text" name="userID" id="id"></label></td>
                </tr>
                <tr>
                  <td>성명</td>
                  <td><label><input type="text" name="userName" id="name"></label></td>
                </tr>
                <tr>
                  <td>소속</td>
                  <td><label><input type="text" name="userDivision" id="division"></label></td>
                </tr>
                <tr>
                  <td>연락처</td>
                  <td><label><input type="text" name="userPhone" id="phone"></label></td>
                </tr>
                <tr>
                  <td>비밀번호</td>
                  <td><label><input type="password" name="userPW" id="pw1"></label></td>
                </tr>
                <tr>
                  <td>비밀번호(확인)</td>
                  <td><label><input type="password" id="pw2"></label></td>
                </tr>
                <tr>
                  <td>관리자</td>
                  <td>
                    <input type="checkbox" name="userSuper"/>마스터관리자
                  </td>
                </tr>
              </tbody>
            </table>
            <input type="hidden" name="prevUrl" value="${prevUrl}">
            <div class="box-row">
              <div class="box-tool">
                <div><input type="submit"></div>
                <div><a href="${prevUrl}">취소</a></div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>

    <script  type="text/javascript">
      submitCheck = ()=>{

        if($("#id").val() == ""){
          alert("아이디를 입력하세요.")
          return false
        }
        else if($("#name").val() == ""){
          alert("이름을 입력하세요.")
          return false
        }
        else if($("#division").val() == ""){
          alert("소속을 입력하세요.")
          return false
        }
        else if($("#division").val() == ""){
          alert("소속을 입력하세요.")
          return false
        }
        else if($("#pw1").val() == ""){
          alert("비밀번호를 입력하세요.")
          return false
        }
        else if($("#pw2").val() == ""){
          alert("비밀번호(확인)을 입력하세요.")
          return false
        }

        if($("#pw1").val() != $("#pw2").val())
        {
          alert("입력된 비밀번호가 다릅니다.")
          return false
        }

        return true
      }
    </script>
  </body>
</html>
