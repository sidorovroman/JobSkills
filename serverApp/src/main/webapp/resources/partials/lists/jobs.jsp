<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="jobs-list" xmlns="http://www.w3.org/1999/html">
    <table class="table table-striped table-hover ">
        <thead>
        <tr>
            <th>Наименование
                <sec:authorize var="loggedIn" access="isAuthenticated()">
                    <a href="#/addJob" class="btn-add" >+</a>
                </sec:authorize>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="job in jobs" ng-click="select(job)">
            <td>{{job.caption}}
                <sec:authorize var="loggedIn" access="isAuthenticated()">
                    <a href="#/editJob/{{job.id}}">
                        <i class="btn-edit"></i>
                    </a>
                </sec:authorize>
            </td>
        </tr>
        </tbody>
    </table>
</div>
