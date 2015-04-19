<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="ng-rating">
    <div class="vote-up" ng-click="voteUp();  $event.stopPropagation();" ></div>
    <span class="rating-value">{{rating}}</span>
    <div class="vote-down" ng-click="voteDown();  $event.stopPropagation();"></div>
</div>