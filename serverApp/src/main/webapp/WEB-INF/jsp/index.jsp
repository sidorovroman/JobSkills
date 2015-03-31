<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru" ng-app="App">
<head>
    <meta charset="utf-8">
    <title>Skill &middot; Youself</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <%--<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/reset.css" />">--%>
    <%--<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/bootstrap.min.css" />">--%>
    <%--<link href="//maxcdn.bootstrapcdn.com/bootswatch/3.3.4/sandstone/bootstrap.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="<c:url value="/resources/styles/bootstrap-multiselect.css" />">

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Include roboto.css to use the Roboto web font, material.css to include the theme and ripples.css to style the ripple effect -->
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/material/css/roboto.min.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/material/css/material.min.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/material/css/ripples.min.css" />">


    <%--<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/datepicker.css" />">--%>
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
    <div id="wrap">
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
                            <sec:authorize var="loggedIn" access="hasRole('ROLE_ADMIN')">
                              <li><a href="/admin/index">Админка</a></li>
                            </sec:authorize>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li>

                                <sec:authorize var="loggedIn" access="isAuthenticated()">
                                    <li class="dropdown">
                                        <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><sec:authentication property="principal.username"></sec:authentication> <span class="caret"></span></a>
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
                <div class="inner">
                    <div ng-view class="content"></div>
                    <div class="right-panel">
                        <div class="top-news info-block">
                            <div class="title">Топовые новости</div>
                            <div class="info-list">
                                <div class="info-item"><a href="#" >IT-компании о себе: ролики о команде, работе и атмосфере</a></div>
                                <div class="info-item"><a href="#" >Опрос об отношении к фразе «А у меня на компе работает»</a></div>
                                <div class="info-item"><a href="#" >Бесплатный курс «7 шагов к работе вашей мечты»</a></div>
                                <div class="info-item"><a href="#" >Создатель Gmail, Пол Бакхайт, о том, где лучше работать — в стартапе или большой компании</a></div>
                              <div class="info-item"><a href="#" >Консультируйте бесплатно, и вам заплатят за работу</a></div>
                              <div class="info-item"><a href="#" >Тренинг нестандартных поступков: как сделать работу продуктивнее</a></div>
                              <div class="info-item"><a href="#" >Как выглядит работа ИТ-специалиста изнутри</a></div>
                              <div class="info-item"><a href="#" >Шоу Звук — Первый год работы над подкастом</a></div>
                            </div>
                        </div>
                        <div class="new-skills info-block">
                            <div class="title">Новые навыки</div>
                            <div class="info-list">
                                <div class="info-item"><a href="#" >Командная работа</a></div>
                                <div class="info-item"><a href="#" >Английский язык</a></div>
                                <div class="info-item"><a href="#" >Скорость печати</a></div>
                                <div class="info-item"><a href="#" >Обработка металла</a></div>
                              <div class="info-item"><a href="#" >Хорошая физическая форма</a></div>
                              <div class="info-item"><a href="#" >Знание JDK</a></div>
                              <div class="info-item"><a href="#" >Использование магии</a></div>
                              <div class="info-item"><a href="#" >Точность стрельбы</a></div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>
        </div>
        <div class="pseudo-footer"></div>
    </div>

    <footer class="footer">
        <div class="container">
            <p>Designed and built in 2015</p>
        </div>
    </footer>

    <!-- Modal -->

    <div ng-controller="ModalLoginCtrl" class="modal fade" id="modalLogin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Авторизация</h4>
                </div>
                <div class="modal-body">
                    <form class="cd-form" id="login-form" method="POST" action="/j_spring_security_check">
                        <p class="fieldset">
                            <label class="image-replace cd-email" for="signin-email">E-mail</label>
                            <input name="mail" class="full-width has-padding has-border" id="signin-email" ng-model="mail" type="text" placeholder="E-mail">
                            <span class="cd-error-message">Введите корректный e-mail!</span>
                        </p>
                        <p class="fieldset">
                            <label class="image-replace cd-password" for="signin-password">Пароль</label>
                            <input name="password" class="full-width has-padding has-border" id="signin-password" ng-model="password" type="text"  placeholder="Пароль">
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
                        <button ng-click="login()" class="btn btn-info login">Войти</button>
                        <button ng-click="register()" class="btn btn-primary reg" data-dismiss="modal">Зарегистрироваться</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%--libs--%>
    <script src="<c:url value="/resources/libs/jquery-2.1.3.min.js" />"></script>
    <%--<script src="<c:url value="/resources/libs/bootstrap.min.js" />"></script>--%>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <script src="<c:url value="/resources/libs/bootstrap-multiselect.js" />"></script>
    <script src="//cdn.ckeditor.com/4.4.7/standard/ckeditor.js"></script>
    <script src="//ulogin.ru/js/ulogin.js"></script>
    <%--<script src="<c:url value="/resources/libs/angular.js" />"></script>--%>
    <%--<script src="<c:url value="/resources/libs/ui-bootstrap-custom-build/ui-bootstrap-custom-0.12.1.js" />"></script>--%>

    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.16/angular.js"></script>
    <script src="<c:url value="/resources/libs/angular-route.js" />"></script>
    <script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.12.1.js"></script>



    <script src="<c:url value="/resources/styles/material/js/ripples.min.js" />"></script>
    <script src="<c:url value="/resources/styles/material/js/material.min.js" />"></script>


    <%--resources--%>
    <%--<script src="<c:url value="/resources/js/loginModal.js" />"></script>--%>

    <%--app--%>
    <script src="<c:url value="/resources/js/app.js" />"></script>

    <script src="<c:url value="/resources/js/directives.js" />"></script>
    <script src="<c:url value="/resources/js/directives/datepicker.js" />"></script>
    <script src="<c:url value="/resources/js/controllers/loginModal.js" />"></script>

    <script src="<c:url value="/resources/js/controllers.js" />"></script>
    <script src="<c:url value="/resources/js/controllers/jobs.js" />"></script>
    <script src="<c:url value="/resources/js/controllers/skills.js" />"></script>
    <script src="<c:url value="/resources/js/controllers/skillWays.js" />"></script>
    <script src="<c:url value="/resources/js/controllers/news.js" />"></script>
    <script src="<c:url value="/resources/js/controllers/userInfo.js" />"></script>

</body>
</html>
