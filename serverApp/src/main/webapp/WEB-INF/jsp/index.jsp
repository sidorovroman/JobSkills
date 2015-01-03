<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Skill &middot; Youself</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <%--<link rel="stylesheet" type="text/css" href="<c:url value="/js/resources/styles/bootstrap.css" />">--%>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/reset.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/bootstrap.min.css" />">
    <link rel="stylesheet" type="text/less" href="<c:url value="/resources/styles/variables.less" />">
    <link rel="stylesheet" type="text/less" href="<c:url value="/resources/styles/bootswatch.less" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/main.css" />">


</head>
<body>
<sec:authorize access="isAuthenticated()">
  <sec:authentication property="principal.username"></sec:authentication>, Хочешь в <a href="/user/index">личный кабинет?</a> =)
</sec:authorize>
    <div id="wrap"   ng-app="App">
        <div id="header">
            <div class="navbar navbar-default">
                <div class="container">

                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">Skill Youself</a>
                    </div>
                    <div class="navbar-collapse collapse navbar-responsive-collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="#/jobs">Работы</a></li>
                            <li><a href="#/news">Новости</a></li>
                            <li><a href="#/aboutUs">О нас</a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="#">Личный кабинет</a></li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
        <div id="main">
            <div class="container">
                <div ng-view></div>
            </div>
        </div>
    </div>


<script src="//ulogin.ru/js/ulogin.js"></script>
<script src="<c:url value="/resources/libs/bower_components/angular/angular.js" />"></script>
<script src="<c:url value="/resources/libs/bower_components/angular-route/angular-route.js" />"></script>
<script src="<c:url value="/resources/js/app.js" />"></script>
<script src="<c:url value="/resources/js/services.js" />"></script>
<script src="<c:url value="/resources/js/controllers.js" />"></script>
</body>
</html>
