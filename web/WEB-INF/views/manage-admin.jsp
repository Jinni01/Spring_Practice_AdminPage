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
    <link rel="stylesheet" type="text/css" href="/resources/css/manage-admin.css">
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
        <div class="box-listup">
          <div class="box-search">
            <div class="box-search-tool">
              <select name="search-option">
                <option value="all" selected>전체</option>
                <option value="id">아이디</option>
                <option value="name">이름</option>
                <option value="division">소속</option>
              </select>
              <input class="input-search">
              <button class="button-search">검색</button>
            </div>
          </div>
          <div class="box-list">
            <div class="box-table">
              <table class="table-list">

                <colgroup>
                  <col width="10%"/>
                  <col width="20%"/>
                  <col width="30%"/>
                  <col width="25%/">
                  <col width="15%"/>
                </colgroup>
                <thead>
                  <tr>
                    <th>번호</th>
                    <th>아이디</th>
                    <th>이름</th>
                    <th>소속</th>
                    <th>등록일</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach items="${userList}" var="user">
                  <tr class="tablerow-user">
                    <td class="tablecol-no"><a href='<c:url value="${context_path}/user/info?userNo=${user.userNo}"/>'>${user.userNo}</a></td>
                    <td>${user.userID}</td>
                    <td>${user.userName}</td>
                    <td>${user.userDivision}</td>
                    <td>${user.userRegisterDate}</td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
            <div class="box-control">
              <ul>
                <c:if test="${pageMaker.prev}">
                  <li>
                    <a href='<c:url value="${context_path}/user/manage-admin?page=${pageMaker.startPage-1}&searchType=${pageMaker.cri.searchType}&keyWorld=${pageMaker.cri.keyWorld}"/>'><<</a>
                  </li>
                </c:if>
                <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
                  <li>
                    <a href='<c:url value="${context_path}/user/manage-admin?page=${pageNum}&searchType=${pageMaker.cri.searchType}&keyWorld=${pageMaker.cri.keyWorld}"/>'>${pageNum}</a>
                  </li>
                </c:forEach>
                <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                  <li>
                    <a href='<c:url value="${context_path}/user/manage-admin?page=${pageMaker.endPage+1}&searchType=${pageMaker.cri.searchType}&keyWorld=${pageMaker.cri.keyWorld}"/>'>>></a>
                  </li>
                </c:if>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>

    <script  type="text/javascript">
      $(".button-search").click(()=>{
        var url = "${context_path}/user/manage-admin"
        url = url + "?searchType=" + $("select[name=search-option]").val()
        url = url + "&keyWorld=" + $(".input-search").val()
        location.href = url;
      })

      $(".input-search").on("keydown", (key)=>{
        if(key.keyCode==13) {
          $(".button-search").click()
        }
      });
    </script>
  </body>
</html>
