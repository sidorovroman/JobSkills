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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/login-form.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/bootstrap.min.css" />">
    <link rel="stylesheet" type="text/less" href="<c:url value="/resources/styles/variables.less" />">
    <link rel="stylesheet" href="<c:url value="/resources/styles/bootstrap-multiselect.css" />">
    <link rel="stylesheet" type="text/less" href="<c:url value="/resources/styles/bootswatch.less" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/datepicker.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/main.css" />">

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
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><sec:authentication property="principal.username"></sec:authentication> <span class="caret"></span></a>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#/user/info">Настройки</a></li>
                                            <li><a href="/users/logout">Выход</a></li>
                                        </ul>
                                    </li>
                                </sec:authorize>
                                <sec:authorize var="loggedIn" access="isAnonymous()">
                                    <nav class="main-nav">
                                        <ul>
                                            <!-- ссылки на вызов форм -->
                                            <li><a class="cd-signin" href="#0">Вход</a></li>
                                        </ul>
                                    </nav>
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
        <div class="cd-user-modal"> <!-- все формы на фоне затемнения-->
            <div class="cd-user-modal-container"> <!-- основной контейнер -->
                <ul class="cd-switcher">
                    <li><a href="#0">Вход</a></li>
                    <li><a href="#0">Регистрация</a></li>
                </ul>
                <div id="cd-login"> <!-- форма входа -->
                    <form class="cd-form" method="POST" action="/j_spring_security_check">
                        <p class="fieldset">
                            <label class="image-replace cd-email" for="signin-email">E-mail</label>
                            <input name="username" class="full-width has-padding has-border" id="signin-email" type="email" placeholder="E-mail">
                            <span class="cd-error-message">Введите корректный e-mail!</span>
                        </p>
                        <p class="fieldset">
                            <label class="image-replace cd-password" for="signin-password">Пароль</label>
                            <input name="password" class="full-width has-padding has-border" id="signin-password" type="text"  placeholder="Пароль">
                            <a href="#0" class="hide-password">Скрыть</a>
                            <span class="cd-error-message">Введите пароль!</span>
                        </p>
                        <p class="fieldset">
                            <input type="checkbox" id="remember-me" checked>
                            <label for="remember-me">Запомнить меня</label>
                        </p>
                        <p class="fieldset">
                            <input class="full-width" type="submit" value="Войти">
                        </p>
                    </form>
                    <p class="cd-form-bottom-message"><a href="#0">Забыли свой пароль?</a></p>
                    <!-- <a href="#0" class="cd-close-form">Close</a> -->
                </div> <!-- конец блока с формой входа -->
                <div id="cd-signup"> <!-- блок с формой регистрации -->
                    <form class="cd-form">
                        <p class="fieldset">
                            <label class="image-replace cd-username" for="signup-username">Имя пользователя</label>
                            <input class="full-width has-padding has-border" id="signup-username" type="text" placeholder="Имя пользователя">
                            <span class="cd-error-message">Введите имя пользователя!</span>
                        </p>
                        <p class="fieldset">
                            <label class="image-replace cd-email" for="signup-email">E-mail</label>
                            <input class="full-width has-padding has-border" id="signup-email" type="email" placeholder="E-mail">
                            <span class="cd-error-message">Введите корректный e-mail!</span>
                        </p>
                        <p class="fieldset">
                            <label class="image-replace cd-password" for="signup-password">Пароль</label>
                            <input class="full-width has-padding has-border" id="signup-password" type="text"  placeholder="Пароль">
                            <a href="#0" class="hide-password">Скрыть</a>
                            <span class="cd-error-message">Введите пароль!</span>
                        </p>
                        <p class="fieldset">
                            <input type="checkbox" id="accept-terms">
                            <label for="accept-terms">Я согласен с <a href="#0">Условиями</a></label>
                        </p>
                        <p class="fieldset">
                            <input class="full-width has-padding" type="submit" value="Создать аккаунт">
                        </p>
                    </form>
                    <!-- <a href="#0" class="cd-close-form">Закрыть</a> -->
                </div> <!-- cd-signup -->
                <div id="cd-reset-password"> <!-- форма восстановления пароля -->
                    <p class="cd-form-message">Забыли пароль? Пожалуйста, введите адрес своей электронной почты. Вы получите ссылку, чтобы создать новый пароль.</p>
                    <form class="cd-form">
                        <p class="fieldset">
                            <label class="image-replace cd-email" for="reset-email">E-mail</label>
                            <input class="full-width has-padding has-border" id="reset-email" type="email" placeholder="E-mail">
                            <span class="cd-error-message">Введите корректный e-mail!</span>
                        </p>
                        <p class="fieldset">
                            <input class="full-width has-padding" type="submit" value="Восстановить пароль">
                        </p>
                    </form>
                    <p class="cd-form-bottom-message"><a href="#0">Вернуться к входу</a></p>
                </div> <!-- cd-reset-password -->
                <a href="#0" class="cd-close-form">Закрыть</a>
            </div> <!-- cd-user-modal-container -->
        </div> <!-- cd-user-modal -->

    </div>

    <script src="<c:url value="/resources/libs/jquery-2.1.3.min.js" />"></script>
<script src="<c:url value="/resources/libs/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/libs/bootstrap-multiselect.js" />"></script>
<script src="<c:url value="/resources/libs/bootstrap-datepicker.js" />"></script>
<script src="<c:url value="/resources/libs/bootstrap-datepicker.ru.js" />"></script>
<script src="<c:url value="/resources/libs/custom-login-form.js" />"></script>
<script src="<c:url value="/resources/libs/modernizr.js" />"></script>
<%--<script src="//cdn.ckeditor.com/4.4.7/standard/ckeditor.js"></script>--%>
<script src="//cdn.ckeditor.com/4.4.7/full/ckeditor.js"></script>
<script src="//ulogin.ru/js/ulogin.js"></script>
<script src="<c:url value="/resources/libs/bower_components/angular/angular.js" />"></script>
<script src="<c:url value="/resources/libs/bower_components/angular-route/angular-route.js" />"></script>

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
