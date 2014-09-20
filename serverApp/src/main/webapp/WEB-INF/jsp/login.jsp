<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="context" value="${pageContext.request.contextPath}" scope="request"/>
<div id="login-form">
  <form class="auth-col" method="POST" action="/j_spring_security_check">
    <h2 class="title">Вход</h2>
    <input type="text" name="username" class="input-login" placeholder="E-mail">
    <input type="password" name="password" class="password"  placeholder="Пароль">
    <button type="submit" class="btn rose btn-login">Войти</button>
    <a href="#/userInfo" class="btn violet btn-reg">Зарегистрироваться</a>
  </form>
  <div class="auth-col">
    <div class="login-icons">
      <div id="uLogin1" data-uloginid="cc8fbc96">
      </div>
    </div>
  </div>
</div>