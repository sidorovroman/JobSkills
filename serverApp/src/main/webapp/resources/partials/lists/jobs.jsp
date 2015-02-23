<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="jobs-list" xmlns="http://www.w3.org/1999/html">
                <sec:authorize var="loggedIn" access="isAuthenticated()">
                    <a href="#/addJob" class="btn-add" >+</a>
                </sec:authorize>
    <div class="job-block" ng-repeat="job in jobs" ng-click="select(job)">
        <img src="/resources/img/job-icon.png">

        <div class="job-info">
            <h1>{{job.caption}}</h1>
            <p>{{job.description}}</p>
            <sec:authorize var="loggedIn" access="isAuthenticated()">
                <a href="#/editJob/{{job.id}}">
                    <i class="btn-edit"></i>
                </a>
            </sec:authorize>

        </div>
    </div>
</div>
