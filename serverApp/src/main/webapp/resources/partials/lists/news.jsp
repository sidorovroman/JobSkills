<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="news-list" xmlns="http://www.w3.org/1999/html">

    <sec:authorize var="loggedIn" access="isAuthenticated()">
        <div class="add-news"><a href="#/news/add" class="btn btn-add btn-primary">Добавить новость</a></div>
    </sec:authorize>

    <sec:authorize var="loggedIn" access="isAnonymous()">
        хочешь добавить новость, залогинься. по идеи надо будет давать админские права еще.
    </sec:authorize>

    <div class="post" ng-repeat="info in news">
        <h1>
            <a ng-click="select(info)">{{info.caption}}</a>
            <span class="date">{{info.addDate|date:'dd MMMM HH:mm'}}</span>

        </h1>

        <div class="body" ng-bind-html="renderHtml(info.body)"></div>
        <div class="info">
            <div class="author">
                от {{info.author.userName}}
            </div>
            <ng-rating id="{{info.id}}"  value="{{info.rating}}" url-part = "/news"/>
        </div>
    </div>
</div>
