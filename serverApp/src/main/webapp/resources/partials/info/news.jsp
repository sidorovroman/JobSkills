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
            <ng-rating id="{{news.id}}" value="{{news.rating}}" url-part="/news"></ng-rating>

        </div>
    </div>
    <ng-comments comments="{{news.comments}}" url="/news/comment"/>

</div>
