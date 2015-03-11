<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru" ng-app="App">
<head>
  <meta charset="utf-8">
  <title>Skill &middot; Youself</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

  <%--<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/reset.css" />">--%>
  <link rel="stylesheet" type="text/css" href="<c:url value="../../../resources/styles/bootstrap.min.css" />">

  <%--<link rel="stylesheet" type="text/less" href="<c:url value="/resources/styles/bootswatch.less" />">--%>
  <%--<link rel="stylesheet" type="text/less" href="<c:url value="/resources/styles/variables.less" />">--%>
  <link rel="stylesheet" href="<c:url value="../../../resources/styles/bootstrap-multiselect.css" />">
  <%--<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/datepicker.css" />">--%>
  <link rel="stylesheet" type="text/css" href="<c:url value="../../../resources/styles/main.css" />">
  <link rel="stylesheet" type="text/css" href="<c:url value="../../../resources/styles/admin.css" />">
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="<c:url value="/resources/libs/pushy.min.js" />"></script>
  <script src="<c:url value="/resources/libs/bootstrap.min.js" />"></script>
</head>
<body>
<section class="navigation">
  <a id="navicon" class="animated fadeInLeft closed menu-btn"></a>

  <div id="" class="nav pushy pushy-left">
    <ul>
      <li>
        <a class="introduction" href="#">
          <i class="fa fa-angle-up"></i>Introduction
        </a>
      </li>

      <li>
        <a class="about" href="#">
          <i class="fa fa-user"></i>Who I am
        </a>
      </li>

      <li>
        <a class="interests" href="#">
          <i class="fa fa-code"></i>What I do
        </a>
      </li>

      <li>
        <a class="work" href="#">
          <i class="fa fa-suitcase"></i>What I've done
        </a>
      </li>

      <li>
        <a class="contact" href="#">
          <i class="fa fa-paper-plane"></i>Contact
        </a>
      </li>
    </ul>

    <div class="dash"></div>

    <ul>
      <li>
        <a href="/blog"><i class="fa fa-book"></i>Blog</a>
      </li>

      <li>
        <a href="/blog/archives"><i class="fa fa-archive"></i>Archives</a>
      </li>
    </ul>
  </div>
</section>
</body>
</html>