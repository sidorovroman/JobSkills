<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="context" value="${pageContext.request.contextPath}" scope="request"/>
<html>
<head>
  <title>Login Page</title>
  <jsp:include page="../includes.jsp"/>
</head>
<body onload='document.f.j_username.focus();'>

<c:if test="${not empty error}">
  <div class="errorblock">
    Your login attempt was not successful, try again.<br/> Caused :
      ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
  </div>
</c:if>
<div id="m" class="centerblockabsolute">
  <table>
    <tr>
      <td align="center" valign="center" id="td">

        <form name='f' action="<c:url value='/j_spring_security_check' />"
              method='POST'>
          <div id="proxy">
            <div style="margin-left: 25px;">
              <div style="font-weight: bold; margin-top: 9px;">Введите имя пользователя и пароль</div>
              <table>
                <tr>
                  <td style="padding-top: 6px;">
                    <label style="color: #000000;" for="login">Пользователь:</label>
                  </td>
                  <td style="padding-top: 4px; padding-left: 12px;">
                    <input name="j_username" type="text" tabindex="1" maxlength="30" title="до 30 символов"
                           style="width: 150px; margin-left: 5px;"/>
                  </td>
                </tr>
                <tr>
                  <td style="padding-top: 9px;">
                    <label style="color: #000000" for="password">Пароль:</label>
                  </td>
                  <td style="padding-top: 7px; padding-left: 12px;">
                    <input name="j_password" id="password" type="password" tabindex="2" maxlength="30"
                           style="width: 150px; margin-left: 5px;"
                           title="до 30 символов"/>
                  </td>
                </tr>
                <tr>
                  <td colspan="2">
                    <table style="width:100%">
                      <tr>
                        <td valign="middle">

                        </td>
                        <td align="right">
                          <input name="submit" type="submit" value="Войти" id="submit" tabindex="3"
                                 style="margin-top: 2px;"/>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
              </table>
            </div>
          </div>
        </form>
      </td>
    </tr>
  </table>
</div>

</body>
</html>