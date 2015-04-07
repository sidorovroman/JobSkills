<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="news-container" xmlns="http://www.w3.org/1999/html">

    <div class="news-block">
        <h1>
            <a>{{news.caption}}</a>
            <sec:authorize var="loggedIn" access="isAuthenticated()">
                <a href="#/news/{{news.id}}/edit"><i class="mdi-action-settings"></i></a>
            </sec:authorize>
            <span class="date">{{news.addDate|date:'dd MMMM HH:mm'}}</span>

        </h1>

        <div class="body" ng-bind-html="renderHtml(news.body)"></div>
        <div class="info">
            <div class="author">
                от {{news.author.userName}}
            </div>
            <div class="rating">
                <div class="vote-up" ng-click="voteUp(news)"></div>
                <span class="rating-value"> {{news.rating}}</span>
                <div class="vote-down" ng-click="voteDown(news)"></div>
            </div>
            <a class="add-comment"  ng-click="toggleCommentForm()">Комментировать</a>
        </div>
    </div>

    <form class="form-horizontal">
        <fieldset>
            <sec:authorize var="loggedIn" access="isAuthenticated()">
                <div class="add-news" ng-hide="showCommentForm">
                    <div class="form-group">
                        <div class="col-lg-10">
                            <textarea class="form-control" rows="3" placeholder="Ваш Комментарий" ng-model="news.message"></textarea>
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
    <div class="news-comments"  ng-repeat="comment in news.comments">
        {{comment}}
    </div>


</div>
