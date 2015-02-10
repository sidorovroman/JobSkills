<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="news-list" xmlns="http://www.w3.org/1999/html">

    <sec:authorize var="loggedIn" access="isAuthenticated()">
        <div class="add-news">ээй, админ, расскажи что-нибудь новенькое <a href="#/news/add" class="btn btn-add btn-primary">лаадно</a></div>
    </sec:authorize>

    <sec:authorize var="loggedIn" access="isAnonymous()">
        хочешь добавить новость, залогинься. по идеи надо будет давать админские права еще.
    </sec:authorize>

    <div class="post" ng-repeat="info in news">
        <h1>
            <a href="#">{{info.caption}}</a>
            <sec:authorize var="loggedIn" access="isAuthenticated()">
                <a href="#/news/{{info.id}}/edit"><i class="btn-edit"></i></a>
            </sec:authorize>
            <span class="date">{{info.addDate|date:'dd MMMM HH:mm'}}</span>

        </h1>

        <div class="body">
            {{info.body}}
        </div>
        <div class="info">
            <div class="author">
                от {{info.author.userName}}
            </div>
            <div class="rating">
                <div class="vote-up" ng-click="voteUp(info)"></div>
                   <span class="rating-value"> {{info.rating}}</span>
                <div class="vote-down" ng-click="voteDown(info)"></div>
            </div>
        </div>
    </div>
</div>
