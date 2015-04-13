<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="comments-list">
    <%-- эта кнопка скрывает/показывает форму добавления комментария--%>
    <sec:authorize var="loggedIn" access="isAuthenticated()">
        <a class="add-comment" ng-click="toggleCommentForm()">Комментировать</a>
    </sec:authorize>

    <form class="form-horizontal">
        <fieldset>
            <sec:authorize var="loggedIn" access="isAuthenticated()">
                <div class="add-news" ng-hide="showCommentForm">
                    <div class="form-group">
                        <div class="col-lg-10">
                            <textarea class="form-control" rows="3" placeholder="Ваш Комментарий" ng-model="message"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-10">
                            <button class="btn btn-primary" ng-click="comment(news)">Добавить</button>
                        </div>
                    </div>
                </div>
            </sec:authorize>

            <sec:authorize var="loggedIn" access="isAnonymous()">
                <a class="btn-enter" data-toggle="modal" data-target="#modalLogin">Авторизируйтесь</a>, чтобы оставить комментарий
            </sec:authorize>
        </fieldset>
    </form>

    <div class="comments-header">
        <strong>Комментарии</strong>
    </div>
    <div class="news-comments"  ng-repeat="comment in comments">
        {{comment}}
    </div>
</div>