<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="reg-form">
  <form class="auth-col" method="POST" action="/newuser">
    <table class="reg-table">
      <tr><td class="main-data">Никнейм</td><td><input name="login" type="text" class="login" value="<c:out value="${user.userName}"/>"></td></tr>
      <tr><td class="main-data">Пароль</td><td><input name="password" type="text" class="password"></td></tr>
      <tr><td>Повторите пароль</td><td><input type="text" class="re-password"></td></tr>
      <tr><td>Ф.И.О.</td><td><input name="fio" type="text" class="fio" value="<c:out value="${user.userFullName}"/>"></td></tr>
      <tr><td>Пол</td><td>          <c:choose>
        <c:when test="${user.sex =='1'}">
          Женский
        </c:when>
        <c:otherwise>
          Мужской
        </c:otherwise>
      </c:choose><input name="sex" type="text" class="pol"></td></tr>
      <tr><td>Дата рождения</td><td name="birthday" class="birthday"><fmt:formatDate pattern='dd.MM.yyyy' value='${user.birthday}' /><input type="text" class="date" placeholder="ДД"> / <input type="text" class="month" placeholder="ММ"> / <input type="text" class="year" placeholder="ГГГГ"></td></tr>
      <tr><td>Эл.почта</td><td><input type="text" name="email" class="email" value="<c:out value="${user.email}"/>"></td></tr>
      <tr><td></td><td><a class="btn rose btn-save">Сохранить</a></td></tr>
    </table>
  </form>
</div>