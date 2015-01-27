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
    <link rel="stylesheet" href="<c:url value="/resources/styles/bootstrap-multiselect.css" />">

</head>
<body>
    <div id="wrap"   ng-app="App">
        <div id="header">
            <div class="navbar navbar-default">
                <div class="box">

                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">Skill Youself</a>
                    </div>
                    <div class="navbar-collapse collapse navbar-responsive-collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="#/jobs">Работы</a></li>
                            <li><a href="#/news">Новости</a></li>
                            <li><a href="#/about">О нас</a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li>

                                <sec:authorize var="loggedIn" access="isAuthenticated()">
                                    <a href="#/user/info"><sec:authentication property="principal.username"></sec:authentication></a>
                                </sec:authorize>
                                <sec:authorize var="loggedIn" access="isAnonymous()">
                                    <a href="#/login">Вход</a>
                                </sec:authorize>
                            </li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
        <div id="main">
            <div class="box">
                <div class="left-panel"></div>
                <div ng-view class="content"></div>
            </div>
        </div>
    </div>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/libs/bootstrap-multiselect.js" />"></script>

<script src="//ulogin.ru/js/ulogin.js"></script>
<script src="<c:url value="/resources/libs/bower_components/angular/angular.js" />"></script>
<script src="<c:url value="/resources/libs/bower_components/angular-route/angular-route.js" />"></script>
<script src="<c:url value="/resources/js/controllers/jobs.js" />"></script>
<script src="<c:url value="/resources/js/controllers/skills.js" />"></script>
<script src="<c:url value="/resources/js/controllers/skillWays.js" />"></script>
<script src="<c:url value="/resources/js/controllers/news.js" />"></script>
<script src="<c:url value="/resources/js/controllers/controllers.js" />"></script>
<script src="<c:url value="/resources/js/app.js" />"></script>
</body>
</html>
