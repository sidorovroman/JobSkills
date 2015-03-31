<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="jobs-list" xmlns="http://www.w3.org/1999/html">
                <sec:authorize var="loggedIn" access="isAuthenticated()">
                    <a href="#/addJob" class="job-block btn-add"><i class="mdi-content-add-circle-outline"></i></a>
                </sec:authorize>
    <div class="job-block" ng-repeat="job in jobs" ng-click="select(job)">
        <sec:authorize var="loggedIn" access="isAuthenticated()">
            <div class="edit" ng-click="edit(job); $event.stopPropagation();">
                <i class="mdi-action-settings"></i>
            </div>
        </sec:authorize>
        <div>
            <img src="/resources/img/job-icon-{{$index}}.png">
            <h1>{{job.caption}}</h1>
        </div>

        <p class="job-info"   ng-bind-html="renderHtml(job.description)"></p>
    </div>
</div>
