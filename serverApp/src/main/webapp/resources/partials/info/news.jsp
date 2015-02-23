<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="news-container" xmlns="http://www.w3.org/1999/html">

    <h1>
        <a>{{news.caption}}</a>
        <sec:authorize var="loggedIn" access="isAuthenticated()">
            <a href="#/news/{{news.id}}/edit"><i class="btn-edit"></i></a>
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
    </div>
</div>
