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
  <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <script src="<c:url value="../../../resources/js/admin.js" />"></script>
</head>
<body>
<style>
  body {
    background: #EFEFEF;
    overflow: scroll;
  }

  #nav_panel {
    background: #008E9E;
    position: absolute;
    float: left;
    width: 71px;
    height: 100%;
    border: 1px solid black;
  }

  .nav_row {
    display: block;
    color: #8c8c8b;
    font-size: 14px;
    font-weight: bold;
    padding: 10px 25px;
    text-decoration: none;
    position: relative;
    cursor: pointer;
  }

  .nav_row:hover {
    background: #07c507;
  }

  #nav_bar {
    background: white;
  }

  #right_block {
    float: right;
    width: 330px;
    margin: -5px 20px 0px 0px;

  }

  .statistic {
    margin-top: 15px;
    width: 100%;
    border: 1px solid #A7A7A7;
    border-radius: 3px;
    -webkit-box-shadow: 10px 10px 25px #A7A7A7;
    -moz-box-shadow: 10px 10px 25px #A7A7A7;
    -o-box-shadow: 10px 10px 25px #A7A7A7;
    box-shadow: 10px 10px 25px #A7A7A7;
    background: #00BAD3;
    padding-bottom: 10px;
  }

  .job-block {
    float: left;
    width: 280px;
    height: 300px;
    border: 1px solid #e9e9e9;
    border-radius: 3px;
    overflow: hidden;
    padding: 8px;
    margin: 10px;
    background: #fafafa;
    -webkit-box-shadow: 10px 10px 25px #A7A7A7;
    -moz-box-shadow: 10px 10px 25px #A7A7A7;
    -o-box-shadow: 10px 10px 25px #A7A7A7;
    box-shadow: 10px 10px 25px #A7A7A7;
  }

  .job-block:hover {
    background: #f5f5f5;
  }

  .job-block img {
    width: 70px;
    margin: 5px;
  }

  .job-block h1 {
    display: inline-block;
    margin-top: 13px;
    font-size: 24px;
    margin-bottom: 10px;
    color: #575050;
  }

  .job-block .job-info {
    float: left;
    margin: 10px;
  }
</style>
<div id="nav_panel">
  <div id="logo" class="text-center" style="border: 1px solid green;">
    <h2 style="margin-top: 10px;">S &middot; Y</h2>
  </div>
  <div class="nav_button text-center" style="border: 1px solid red; cursor: pointer;">
    <h3>
      ===<br>===
    </h3>
  </div>
</div>
<div id="nav_bar"
     style="display: none; position: absolute; float: left; width: 300px; height: 100%; border: 1px solid black; margin-left: 70px;">
  <div style="text-align: center; margin-top: 60px; border: 1px solid red;"><h2>Навигация</h2></div>
  <a class="nav_row" href="/admin/index">Пользователи</a>
  <a class="nav_row" href="/admin/news">Новости</a>
  <a class="nav_row" href="/admin/jobs">Работы</a>
</div>
<div id="main_content" style="margin-left: 85px; width: 925px; padding: 10px 0px 0px 0px;">
  <h2 style="margin-top: 0px; text-align: center;">Работы</h2>

  <c:forEach var="job" items="${jobs}" varStatus="loop">
    <div class="job-block">
      <div>
        <img src="/resources/img/job-icon-1.png">

        <h1>${job.caption}</h1>
      </div>
      <p class="job-info">${job.description}</p>
    </div>
  </c:forEach>
</div>
<div id="right_block">
  <div class="statistic">
    <h3 class="text-center">Общая статистика</h3>

    <div class="nav_row"> Новостей: ${newsCount} </div>
    <div class="nav_row"> Пользователей: ${usersCount} </div>
    <div class="nav_row"> Работ: ${jobsCount} </div>
  </div>

  <div class="statistic">
    <h3 class="text-center">Cтатистика за 30 дней</h3>

    <div class="nav_row"> Новостей: ${newNewss} </div>
    <div class="nav_row"> Пользователей: ${newUsers} </div>
    <div class="nav_row"> Работ: ${newJobs} </div>
  </div>
</div>
<script type="text/javascript">
  $('.nav_button').click(function () {
    $('#nav_bar').animate({width: 'toggle'});
  });
</script>
</body>
</html>