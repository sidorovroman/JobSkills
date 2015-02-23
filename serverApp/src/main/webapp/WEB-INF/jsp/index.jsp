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

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/reset.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/bootstrap.min.css" />">
    <link rel="stylesheet" type="text/less" href="<c:url value="/resources/styles/variables.less" />">
    <link rel="stylesheet" href="<c:url value="/resources/styles/bootstrap-multiselect.css" />">
    <link rel="stylesheet" type="text/less" href="<c:url value="/resources/styles/bootswatch.less" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/datepicker.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/main.css" />">

</head>
<body>
    <div class="start-page">
        <div class="start-words">
            <p class="project-name">Skill Yourself</p>
            <p>Хочешь изменить мир начни со своих боков</p>
        </div>
        <button class="btn btn-info btn-start">УБРАТЬ</button>
    </div>
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
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><sec:authentication property="principal.username"></sec:authentication> <span class="caret"></span></a>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#/user/info">Настройки</a></li>
                                            <li><a href="/users/logout">Выход</a></li>
                                        </ul>
                                    </li>
                                </sec:authorize>
                                <sec:authorize var="loggedIn" access="isAnonymous()">
                                    <a class="btn-enter" data-toggle="modal" data-target="#modalLogin">Войти</a>
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
        <div class="pseudo-footer"></div>
    </div>

    <!-- Modal -->
    <footer class="footer">
        <div class="container">
            <p>Designed and built in 2015</p>
        </div>
    </footer>
    <div class="modal fade" id="modalLogin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Авторизация</h4>
                </div>
                <div class="modal-body">
                    <form class="cd-form" id="login-form">
                        <p class="fieldset">
                            <label class="image-replace cd-email" for="signin-email">E-mail</label>
                            <input name="email" class="full-width has-padding has-border" id="signin-email" type="email" placeholder="E-mail">
                            <span class="cd-error-message">Введите корректный e-mail!</span>
                        </p>
                        <p class="fieldset">
                            <label class="image-replace cd-password" for="signin-password">Пароль</label>
                            <input name="password" class="full-width has-padding has-border" id="signin-password" type="text"  placeholder="Пароль">
                        </p>
                    </form>
                </div>
                <div class="modal-footer">
                    <div class="login-icons">
                        <div id="uLogincc8fbc96"
                             x-ulogin-params="display=buttons;
                                     fields=first_name,last_name,nickname,sex,bdate,email;
                                     optional=phone,city,country;
                                     verify=1;
                                     providers=google,vkontakte,facebook,mailru,odnoklassniki;
                                     hidden=twitter,yandex,livejournal,openid,lastfm,linkedin,liveid,soundcloud,steam,flickr,uid,youtube,webmoney,foursquare,tumblr,googleplus,dudu,vimeo,instagram;
                                     redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Flogin_callback">
                            <div class="soc vk"x-ulogin-button="vkontakte"></div>
                            <div class="soc fb"x-ulogin-button="facebook"></div>
                            <div class="soc gl"x-ulogin-button="google"></div>
                        </div>

                    </div>
                    <div class="btn-container">
                        <button type="submit" value="login" class="btn btn-info login">Войти</button>
                        <button type="submit" value="registration" class="btn btn-primary reg" data-dismiss="modal">Зарегистрироваться</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="<c:url value="/resources/libs/jquery-2.1.3.min.js" />"></script>
<script src="<c:url value="/resources/libs/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/libs/bootstrap-multiselect.js" />"></script>
<script src="<c:url value="/resources/libs/bootstrap-datepicker.js" />"></script>
<script src="<c:url value="/resources/libs/bootstrap-datepicker.ru.js" />"></script>
<%--<script src="//cdn.ckeditor.com/4.4.7/standard/ckeditor.js"></script>--%>
<script src="//cdn.ckeditor.com/4.4.7/full/ckeditor.js"></script>
<script src="//ulogin.ru/js/ulogin.js"></script>
<script src="<c:url value="/resources/libs/bower_components/angular/angular.js" />"></script>
<script src="<c:url value="/resources/libs/bower_components/angular-route/angular-route.js" />"></script>

<script src="<c:url value="/resources/js/modalLogin.js" />"></script>

<script src="<c:url value="/resources/js/controllers/login.js" />"></script>
<script src="<c:url value="/resources/js/controllers/jobs.js" />"></script>
<script src="<c:url value="/resources/js/controllers/skills.js" />"></script>
<script src="<c:url value="/resources/js/controllers/skillWays.js" />"></script>
<script src="<c:url value="/resources/js/controllers/news.js" />"></script>
<script src="<c:url value="/resources/js/controllers/userInfo.js" />"></script>
<script src="<c:url value="/resources/js/controllers/controllers.js" />"></script>
<script src="<c:url value="/resources/js/app.js" />"></script>
</body>
</html>
