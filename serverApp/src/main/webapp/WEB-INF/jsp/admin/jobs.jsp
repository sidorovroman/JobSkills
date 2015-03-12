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
    width: 25%;
    margin: 20px;

  }

  .statistic{
    margin-top: 20px;
    width: 100%;
    border: 1px solid #000000;
    -webkit-box-shadow:
      10px 10px 10px #000;
    -moz-box-shadow:
      10px 10px 10px #000;
    -o-box-shadow:
      10px 10px 10px #000;
    box-shadow:
      10px 10px 10px #000;
  }
</style>
<div id="nav_panel" style="position: absolute; float: left; width: 80px; height: 100%; border: 1px solid black;">
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
     style="display: none; position: absolute; float: left; width: 300px; height: 100%; border: 1px solid black; margin-left: 80px;">
  <div style="text-align: center; margin-top: 60px; border: 1px solid red;"><h2>Навигация</h2></div>
  <a class="nav_row" href="/admin/index">Пользователи</a>
  <a class="nav_row" href="/admin/news">Новости</a>
  <a class="nav_row" href="/admin/jobs">Работы</a>
</div>
<div id="main_content" style="margin-left: 100px;">
  Работы

  <c:forEach var="job" items="${jobs}" varStatus="loop">
    <li><a href="#">${job.caption}</a></li>
  </c:forEach>

  <div  id="right_block">
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

</div>
<script type="text/javascript">
  $('.nav_button').click(function () {
    $('#nav_bar').animate({width: 'toggle'});
  });
</script>
</body>
</html>