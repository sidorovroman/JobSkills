<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="utf-8">
  <title>Skill &middot; Youself</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
</head>
<body>
<h2>Личный кабинет</h2>
  <div>
    <h3>Информация об аккаунте</h3>
  </div>
    <table>
      <tr>
        <td>
          ФИО:
        </td>
        <td>
          <c:out value="${user.userFullName}"/>
        </td>
      </tr>
      <tr>
        <td>
          Email:
        </td>
        <td>
          <c:out value="${user.email}"/>
        </td>
      </tr>
      <tr>
        <td>
          Логин:
        </td>
        <td>
          <c:out value="${user.userName}"/>
        </td>
      </tr>
      <tr>
        <td>
          Пол:
        </td>
        <td>
          <c:choose>
            <c:when test="${user.sex =='1'}">
              Женский
            </c:when>
            <c:otherwise>
              Мужской
            </c:otherwise>
          </c:choose>
        </td>
      </tr>
      <tr>
        <td>
          Вошли через:
        </td>
        <td>
          <c:out value="${user.network}"/>
        </td>
      </tr>
      <tr>
        <td>
          Дата рождения:
        </td>
        <td>
          <fmt:formatDate pattern='dd.MM.yyyy' value='${user.birthday}' />
        </td>
      </tr>
  </table>
</body>